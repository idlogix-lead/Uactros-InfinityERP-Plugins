package org.infinity.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MProduct;
import org.compiere.model.MRfQ;
import org.compiere.model.MRfQLine;
import org.compiere.model.MRfQResponse;
import org.compiere.model.MRfQResponseLine;
import org.compiere.model.MRfQResponseLineQty;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

@org.adempiere.base.annotation.Process
public class CreateRFQResponseFromRFQ extends SvrProcess {

	int bpId;
	int rfqId;
	int paymentterm_id;
	int c_tax_id;
	boolean inclusiveTax=false;
	
	private ArrayList<Integer> 			selectionIDList = new ArrayList<Integer>();
	private HashMap<String, Object> 	selectionValueMap = new HashMap<String, Object>();
	private int							m_created = 0;
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_RfQ_ID"))
				rfqId = para[i].getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				bpId = para[i].getParameterAsInt();
			else if (name.equals("C_PaymentTerm_ID"))
				paymentterm_id = para[i].getParameterAsInt();
			else if (name.equals("C_Tax_ID"))
				c_tax_id = para[i].getParameterAsInt();
			else if (name.equals("ExclusiveTax"))
				inclusiveTax = para[i].getParameterAsBoolean();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

		@Override
		protected String doIt() throws Exception {
			
			if (getProcessInfo().getAD_InfoWindow_ID() > 0)
				return createLines();
			else
				throw new AdempiereException("@NotSupported@");
		}
		
		private String createLines() {

			// Lines
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT t.T_Selection_ID, CAST(t.ViewID AS Integer) AS AD_Table_ID ");
			sql.append("FROM T_Selection t ");
			sql.append("WHERE t.AD_PInstance_ID=? ");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setInt(1, getAD_PInstance_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					int T_Selection_ID = rs.getInt("T_Selection_ID");				
					if (!selectionIDList.contains(T_Selection_ID))
						selectionIDList.add(T_Selection_ID);
					
					String ColumnName = "AD_Table_ID";
					MTable table = MTable.get(rs.getInt(ColumnName));
					String key = table.getKeyColumns()[0] + "_" + T_Selection_ID;
					System.out.println(table+"  "+key);
					selectionValueMap.put(key, T_Selection_ID);
				}
			}
			catch (Exception e)
			{
				throw new AdempiereException(e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			
			sql = new StringBuilder();
			sql.append("SELECT T_Selection_ID, ColumnName, Value_String, Value_Number, Value_Date ");
			sql.append("FROM T_Selection_InfoWindow ");
			sql.append("WHERE AD_PInstance_ID=? ");
			sql.append("ORDER BY T_Selection_ID, ColumnName ");
			
			pstmt = null;
			rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setInt(1, getAD_PInstance_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					int T_Selection_ID = rs.getInt("T_Selection_ID");
					String ColumnName = rs.getString("ColumnName");
					String Value_String = rs.getString("Value_String");
					
					Object Value_Number = null;
					if (ColumnName.toUpperCase().endsWith("_ID"))
						Value_Number = rs.getInt("Value_Number");
					else
						Value_Number = rs.getBigDecimal("Value_Number");
					
					Timestamp Value_Date = rs.getTimestamp("Value_Date");
					
					String key = ColumnName + "_" + T_Selection_ID;
					Object value = null;
					if (Value_String != null)
						value = Value_String;
					else if (Value_Number != null)
						value = Value_Number;
					else if (Value_Date != null)
						value = Value_Date;
					selectionValueMap.put(key, value);
				}
			}
			catch (Exception e)
			{
				throw new AdempiereException(e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
					
			MRfQ rfq = null;
			MRfQResponse rfqres=null;
			
			for (int i = 0; i < selectionIDList.size(); i++)
			{
				int T_Selection_ID = selectionIDList.get(i);
				
				String ColumnName = "C_RfQ_ID";
				String key = ColumnName + "_" + T_Selection_ID;
				Object value = selectionValueMap.get(key);
				int M_RfQ_ID = value != null ? ((Number) value).intValue() : 0;
				if (M_RfQ_ID != 0 && (rfq == null || rfq.get_ID() != M_RfQ_ID))
					rfq = new MRfQ(getCtx(), M_RfQ_ID, get_TrxName());
				
				ColumnName = "Qty";
				key = ColumnName + "_" + T_Selection_ID;
				value = selectionValueMap.get(key);
				BigDecimal QtyEntered = value != null ? (BigDecimal) value : null;
				
				ColumnName = "C_UOM_ID";
				key = ColumnName + "_" + T_Selection_ID;
				value = selectionValueMap.get(key);
				int C_UOM_ID = value != null ? ((Number) value).intValue() : 0;
				
				ColumnName = "C_RfQLineQty_ID";
				key = ColumnName + "_" + T_Selection_ID;
				value = selectionValueMap.get(key);
				// If a locator is specified on the product, choose that otherwise default locator
				int C_RfQLineQty_ID = value != null ? ((Number) value).intValue() : 0;

				ColumnName = "M_Product_ID";
				key = ColumnName + "_" + T_Selection_ID;
				value = selectionValueMap.get(key);
				int M_Product_ID = value != null ? ((Number) value).intValue() : 0;
				
				ColumnName = "C_RfQLine_ID";
				key = ColumnName + "_" + T_Selection_ID;
				value = selectionValueMap.get(key);
				int C_RfQLine_ID = value != null ? ((Number) value).intValue() : 0;
				
				ColumnName = "BenchmarkPrice";
				key = ColumnName + "_" + T_Selection_ID;
				value = selectionValueMap.get(key);
				BigDecimal price = value != null ? (BigDecimal) value : BigDecimal.ZERO;
				
				int precision = 2;
				if (M_Product_ID != 0)
				{
					MProduct product = MProduct.get(getCtx(), M_Product_ID);
					precision = product.getUOMPrecision();
				}
				QtyEntered = QtyEntered.setScale(precision, RoundingMode.HALF_DOWN);
				//
				if (log.isLoggable(Level.FINE)) log.fine("Line QtyEntered=" + QtyEntered
						+ ", Product=" + M_Product_ID 
						+ ", OrderLine=" + C_RfQLine_ID );
	  
				
//				System.out.println(m_Inout);
				System.out.println("inoutlineid"+C_RfQLine_ID);
				System.out.println("productid"+M_Product_ID);
				System.out.println("qty"+QtyEntered);
				
				  if (rfqres == null) {
				MBPartner pb = new MBPartner(getCtx(), bpId, get_TrxName());
				
				 rfqres =new MRfQResponse(getCtx(), 0, get_TrxName());
				rfqres.setC_BPartner_ID(bpId);
				rfqres.setC_BPartner_Location_ID(pb.getPrimaryC_BPartner_Location_ID());
				rfqres.setName(rfq.getName());
				rfqres.setC_Currency_ID(rfq.getC_Currency_ID());
				rfqres.setC_RfQ_ID(rfq.get_ID());
				rfqres.set_ValueOfColumn("C_PaymentTerm_ID", paymentterm_id);			
				rfqres.saveEx();
				  }
				
				this.createLineFrom(rfqres.get_ID(),C_RfQLine_ID, M_Product_ID, C_UOM_ID, QtyEntered, C_RfQLineQty_ID,price);
				m_created++;
			}  
			
			StringBuilder msgreturn = new StringBuilder("@Created@ = ").append(m_created);
			addBufferLog(rfqres.get_ID(), null,
					null, rfqres.getName(),
					MRfQResponse.Table_ID, rfqres.get_ID());
			return msgreturn.toString();
		}
		
		public void createLineFrom(int C_RfQResponse_ID,int C_RfQLine_ID,  
				int M_Product_ID, int C_UOM_ID, BigDecimal Qty, int C_RfQLineQty_ID,BigDecimal Price)
		{

			MRfQResponseLine resl = null;

		    String whereClause = "C_RfQLine_ID = ? AND C_RfQResponse_ID = ?";
		    List<MRfQResponseLine> existingResponseLines = new Query(getCtx(), MRfQResponseLine.Table_Name, whereClause, get_TrxName())
		            .setParameters(C_RfQLine_ID, C_RfQResponse_ID)
		            .list();

		    if (!existingResponseLines.isEmpty()) {
		    	resl = existingResponseLines.get(0);
		    } else {
		    	resl = new MRfQResponseLine(getCtx(), 0, get_TrxName());
		        MRfQLine ol = new MRfQLine(Env.getCtx(), C_RfQLine_ID, get_TrxName());
		        resl.setC_RfQLine_ID(C_RfQLine_ID);
		        resl.setDescription(ol.getDescription());
		        resl.setC_RfQResponse_ID(C_RfQResponse_ID);
		        resl.saveEx();
		    }
			
			if(C_RfQLineQty_ID !=0) {
				
				MRfQResponseLineQty reslq= new MRfQResponseLineQty(getCtx(), 0, get_TrxName());
				reslq.setPrice(Price);
				reslq.setC_RfQLineQty_ID(C_RfQLineQty_ID);
				reslq.setC_RfQResponseLine_ID(resl.get_ID());
				reslq.set_ValueOfColumn("C_Tax_ID", c_tax_id);
				reslq.set_ValueOfColumn("ExclusiveTax", inclusiveTax);
				reslq.saveEx();
				
			}
			
		}}
