package org.infinity.processes;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.model.MProductPrice;
import org.compiere.model.MStorageOnHand;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;

@org.adempiere.base.annotation.Process
public class ProductPriceUpdation extends SvrProcess {

	private int priceListVersionId;
	private BigDecimal PriceStd, PriceLimit, PriceList;
	private int propertyFloor, productAttr1, productAttr2, productAttr3, productAttr4, productCategoryId;
	private String propertyCategory, ownership;
	private Boolean OnlySelectedProduct = false;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (ProcessInfoParameter processInfoParameter : para) {
			String name = processInfoParameter.getParameterName();
			if (processInfoParameter.getParameter() == null) {
				continue;
			} else if (name.equals("S_Floor_ID"))
				propertyFloor = processInfoParameter.getParameterAsInt();
			else if (name.equals("PropertyCategory"))
				propertyCategory = processInfoParameter.getParameterAsString();
			else if (name.equals("OwnerShip"))
				ownership = processInfoParameter.getParameterAsString();
			else if (name.equals("S_Resource_Att1_ID"))
				productAttr1 = processInfoParameter.getParameterAsInt();
			else if (name.equals("S_Resource_Att2_ID"))
				productAttr2 = processInfoParameter.getParameterAsInt();
			else if (name.equals("S_Resource_Att3_ID"))
				productAttr3 = processInfoParameter.getParameterAsInt();
			else if (name.equals("S_Resource_Att4_ID"))
				productAttr4 = processInfoParameter.getParameterAsInt();
			else if (name.equals("OnlySelectedProduct"))
				OnlySelectedProduct = processInfoParameter.getParameterAsBoolean();
			else if (name.equals("M_Product_Category_ID"))
				productCategoryId = processInfoParameter.getParameterAsInt();
			else if (name.equals("M_PriceList_Version_ID"))
				priceListVersionId = processInfoParameter.getParameterAsInt();
			else if (name.equals("PriceList"))
				PriceList = processInfoParameter.getParameterAsBigDecimal();
			else if (name.equals("PriceStd"))
				PriceStd = processInfoParameter.getParameterAsBigDecimal();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		// Retrieve products based on filters
		PriceLimit=Env.ZERO;
		HashSet<Integer> filteredProducts = new HashSet<>();
		StringBuilder sql = new StringBuilder("SELECT M_Product_ID FROM M_Product p WHERE 1=1 ");
		List<Object> parameters = new ArrayList<>();

		if (propertyFloor > 0) {
			sql.append(" AND p.S_Floor_ID = ?");
			parameters.add(propertyFloor);
		}
		if (propertyCategory != null && !propertyCategory.isEmpty()) {
			sql.append(" AND p.PropertyCategory = ?");
			parameters.add(propertyCategory);
		}
		if (productCategoryId > 0) {
			sql.append(" AND p.M_Product_Category_ID = ?");
			parameters.add(productCategoryId);
		}
		if (ownership != null && !ownership.isEmpty()) {
			sql.append(" AND p.OwnerShip = ?");
			parameters.add(ownership);
		}
		if (productAttr1 > 0) {
			sql.append(" AND p.S_Resource_Att1_ID = ?");
			parameters.add(productAttr1);
		}
		if (productAttr2 > 0) {
			sql.append(" AND p.S_Resource_Att2_ID = ?");
			parameters.add(productAttr2);
		}
		if (productAttr3 > 0) {
			sql.append(" AND p.S_Resource_Att3_ID = ?");
			parameters.add(productAttr3);
		}
		if (productAttr4 > 0) {
			sql.append(" AND p.S_Resource_Att4_ID = ?");
			parameters.add(productAttr4);
		}

//        sql.append(" AND NOT EXISTS (SELECT 1 FROM SalesForm sf WHERE sf.M_Product_ID = p.M_Product_ID AND sf.DocStatus = 'CO')");

		try (PreparedStatement pstmt = DB.prepareStatement(sql.toString(), get_TrxName())) {
			for (int i = 0; i < parameters.size(); i++) {
				if (parameters.get(i) instanceof Integer) {
					pstmt.setInt(i + 1, (Integer) parameters.get(i));
				} else if (parameters.get(i) instanceof String) {
					pstmt.setString(i + 1, (String) parameters.get(i));
				}
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					filteredProducts.add(rs.getInt("M_Product_ID"));
				}
			}
		}

		for (Integer productId : filteredProducts) {
			insertOrUpdateProductPrice(productId, priceListVersionId, PriceStd, PriceLimit, PriceList);
		}

		if (OnlySelectedProduct)
			return "Product Price Updated Successfully";

		HashSet<Integer> allProducts = new HashSet<>();
		try (PreparedStatement pstmt = DB.prepareStatement(
				"SELECT M_Product_ID FROM M_Product Where M_Product_Category_ID=1000001", get_TrxName());
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				allProducts.add(rs.getInt("M_Product_ID"));
			}
		}
				allProducts.removeAll(filteredProducts);
		for (Integer productId : allProducts) {
			int lastPriceListVersionId = getLastPriceListVersionId(priceListVersionId);
			if (lastPriceListVersionId > 0) {
				BigDecimal[] lastPrices = getLastProductPrices(productId, lastPriceListVersionId);
				insertOrUpdateProductPrice(productId, priceListVersionId, lastPrices[0], lastPrices[1], lastPrices[2]);
			}

		}

		return "Product prices updated successfully";
	}

	private int getLastPriceListVersionId(int currentPriceListVersionId) {
		String sql = "SELECT plv.M_PriceList_Version_ID FROM M_PriceList_Version plv "
				+ "WHERE plv.M_PriceList_ID = (SELECT M_PriceList_ID FROM M_PriceList_Version WHERE M_PriceList_Version_ID = ?) "
				+ "AND plv.ValidFrom < (SELECT ValidFrom FROM M_PriceList_Version WHERE M_PriceList_Version_ID = ?) "
				+ "ORDER BY plv.ValidFrom DESC LIMIT 1";
		return DB.getSQLValue(get_TrxName(), sql, currentPriceListVersionId, currentPriceListVersionId);
	}

	private BigDecimal[] getLastProductPrices(int productId, int priceListVersionId) {
		String sql = "SELECT PriceStd, PriceLimit, PriceList FROM M_ProductPrice "
				+ "WHERE M_Product_ID = ? AND M_PriceList_Version_ID = ?";
		try (PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName())) {
			pstmt.setInt(1, productId);
			pstmt.setInt(2, priceListVersionId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new BigDecimal[] { rs.getBigDecimal("PriceStd"), rs.getBigDecimal("PriceLimit"),
							rs.getBigDecimal("PriceList") };
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error fetching last product prices", e);
		}

		return new BigDecimal[] { BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO };
	}

	private void insertOrUpdateProductPrice(int productId, int priceListVersionId, BigDecimal priceStd,
			BigDecimal priceLimit, BigDecimal priceList) {
		MProductPrice productPrice = MProductPrice.get(getCtx(), priceListVersionId, productId, get_TrxName());
		if (productPrice == null) {
			productPrice = new MProductPrice(getCtx(), 0, get_TrxName());
			productPrice.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
			productPrice.setIsActive(true);
			productPrice.setM_Product_ID(productId);
			productPrice.setM_PriceList_Version_ID(priceListVersionId);
		}
		productPrice.setPriceStd(priceStd);
		productPrice.setPriceLimit(priceLimit);
		productPrice.setPriceList(priceList);
		productPrice.saveEx();
	}
}
