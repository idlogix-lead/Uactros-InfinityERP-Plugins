package org.infinity.uactros.validators;

import java.sql.Timestamp;

import org.adempiere.base.Core;
import org.adempiere.base.IProductPricing;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.infinity.models.MSalesForm;

public class SalesFormValidator implements ModelValidator {

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addDocValidate(MSalesForm.Table_Name, this);
	}

	@Override
	public int getAD_Client_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		if(po.get_Table_ID()==MSalesForm.Table_ID && timing == TIMING_BEFORE_COMPLETE) {
			MSalesForm form = (MSalesForm) po;
			if(form.getM_Product_ID()>0 && form.getC_BPartner_ID()>0) {
				MBPartner customer = (MBPartner) form.getC_BPartner();
				MProduct product = (MProduct) form.getM_Product();
				try {
					createOrder(form,customer,product);
				} catch (Exception e) {
					throw e;
				}
				
				
				
				
			}
			
			
		}
		return null;
	}
	
	void createOrder(MSalesForm form,MBPartner customer,MProduct product) {
		MOrder order =  new MOrder(Env.getCtx(),0,form.get_TrxName());
		order.setAD_Org_ID(form.getAD_Org_ID());
		order.setC_DocTypeTarget_ID(MDocType.DOCSUBTYPESO_StandardOrder);
		order.setDateOrdered((new Timestamp(System.currentTimeMillis())));
		order.setC_BPartner_ID(form.getC_BPartner_ID());
		if(customer.getLocation(customer.get_ID())==null)
			throw new AdempiereException("Open Customer Location First then complete form!");
		order.setC_BPartner_Location_ID((customer.getLocation(customer.get_ID())).get_ID());
		order.setM_Warehouse_ID(1000000);
		order.setM_PriceList_ID(1000001);
		order.setSalesRep_ID(1000001);
		order.setC_Project_ID(form.getC_Project_ID());
		order.setIsSOTrx(true);
//		order.setPaymentRule("P");
//		order.setDeliveryRule("B");
		order.setC_PaymentTerm_ID(form.getC_PaymentTerm_ID()>0?form.getC_PaymentTerm_ID(): 1000000);
		order.setC_Currency_ID(306);
		order.set_ValueOfColumn("SalesForm_ID", form.getSalesForm_ID());
		order.save();
		form.setC_Order_ID(order.get_ID());
		
		
		MOrderLine line = new MOrderLine(Env.getCtx(), 0, form.get_TrxName());
		line.setHeaderInfo(order);
		line.setAD_Org_ID(order.getAD_Org_ID());
		line.setProduct(product);
		line.setQty(Env.ONE);
		setLinePricing(line);
		
		line.setC_Order_ID(order.get_ID());
		line.save();
		
		if(order==null || order.getC_Order_ID()<=0 )
			return;
		order.setDocAction("CO");
			
		if (order.processIt("CO"))
		{
			order.saveEx();
		} else {
			throw new IllegalStateException("Order Process Failed: " + order + " - " + order.getProcessMsg());
			
		}
		try {
			createInvoice(form,customer,product,order);
		} catch (Exception e) {
			throw e;
		}
		
	}
	void createInvoice(MSalesForm form,MBPartner customer,MProduct product,MOrder order) {
		
		
		
		MInvoice invoice =  new MInvoice(Env.getCtx(),0,form.get_TrxName());
		invoice.setAD_Org_ID(form.getAD_Org_ID());
		invoice.setIsSOTrx(true);
		invoice.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice);
		invoice.setDateInvoiced((new Timestamp(System.currentTimeMillis())));
		invoice.setDateAcct((new Timestamp(System.currentTimeMillis())));
		invoice.setC_BPartner_ID(form.getC_BPartner_ID());
		if(customer.getLocation(customer.get_ID())==null)
			throw new AdempiereException("Open Customer Location First then complete form!");
		invoice.setC_BPartner_Location_ID((customer.getLocation(customer.get_ID())).get_ID());
		invoice.setM_PriceList_ID(1000001);
		invoice.setSalesRep_ID(1000001);
		invoice.setC_Project_ID(order.getC_Project_ID());
		invoice.setPaymentRule("P");
		invoice.setC_PaymentTerm_ID(form.getC_PaymentTerm_ID()>0?form.getC_PaymentTerm_ID(): 1000000);
		invoice.setC_Currency_ID(306);
		invoice.set_ValueOfColumn("SalesForm_ID", form.getSalesForm_ID());
		invoice.setOrder(order);
		invoice.save();
		form.setC_Invoice_ID(invoice.get_ID());
		
		
		for(MOrderLine oline: order.getLines()){
			MInvoiceLine line = new MInvoiceLine(Env.getCtx(), 0, form.get_TrxName());
			line.setOrderLine(oline);
			line.setInvoice(invoice);
			line.setAD_Org_ID(invoice.getAD_Org_ID());
			line.setM_Product_ID(oline.getM_Product_ID());
			line.setQty(oline.getQtyOrdered());
			setLinePricing(line);
			line.setC_Invoice_ID(invoice.get_ID());
			line.save();
		}
		if(invoice==null || invoice.getC_Invoice_ID()<=0 )
			return;
			invoice.setDocAction("CO");
			
		if (invoice.processIt("CO"))
		{
			invoice.saveEx();
			
		} else {
			throw new IllegalStateException("invoice Process Failed: " + invoice + " - " + invoice.getProcessMsg());
			
		}
		
	}
	
	void setLinePricing(MOrderLine oline) {
		
		MOrder order = (MOrder)oline.getC_Order();
		MBPartner customer = (MBPartner) order.getC_BPartner();
		MProduct product = (MProduct) oline.getProduct();
		
		IProductPricing pp = Core.getProductPricing();
		pp.setInitialValues(product.getM_Product_ID(), customer.getC_BPartner_ID(), Env.ONE,true, null);
		Timestamp orderDate = (Timestamp)order.getDateOrdered();
		pp.setPriceDate(orderDate);
		pp.setOrderLine(oline, null);
		int M_PriceList_ID = order.getM_PriceList_ID();
		pp.setM_PriceList_ID(M_PriceList_ID);
		String sql = "SELECT plv.M_PriceList_Version_ID "
				+ "FROM M_PriceList_Version plv "
				+ "WHERE plv.M_PriceList_ID=? "						//	1
				+ " AND plv.ValidFrom <= ? "
				+ "ORDER BY plv.ValidFrom DESC";
			//	Use newest price list - may not be future

		int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, M_PriceList_ID, orderDate);
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		oline.setPriceList(pp.getPriceList());
		oline.setPriceLimit(pp.getPriceLimit());
		oline.setPriceActual(pp.getPriceStd());
		oline.setPriceEntered(pp.getPriceStd());
		oline.setC_Currency_ID(Integer.valueOf(pp.getC_Currency_ID()));
		oline.setDiscount(pp.getDiscount());
		oline.setC_UOM_ID(Integer.valueOf(pp.getC_UOM_ID()));
		
	}
void setLinePricing(MInvoiceLine iline) {
		
		MInvoice invoice = (MInvoice)iline.getC_Invoice();
		MBPartner customer = (MBPartner) invoice.getC_BPartner();
		MProduct product = (MProduct) iline.getProduct();
		
		IProductPricing pp = Core.getProductPricing();
		pp.setInitialValues(product.getM_Product_ID(), customer.getC_BPartner_ID(), Env.ONE,true, null);
		Timestamp invoiceDate = (Timestamp)invoice.getDateInvoiced();
		pp.setPriceDate(invoiceDate);
		pp.setInvoiceLine(iline, null);
		int M_PriceList_ID = invoice.getM_PriceList_ID();
		pp.setM_PriceList_ID(M_PriceList_ID);
		String sql = "SELECT plv.M_PriceList_Version_ID "
				+ "FROM M_PriceList_Version plv "
				+ "WHERE plv.M_PriceList_ID=? "						//	1
				+ " AND plv.ValidFrom <= ? "
				+ "ORDER BY plv.ValidFrom DESC";
			//	Use newest price list - may not be future

		int M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, M_PriceList_ID, invoiceDate);
		pp.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		iline.setPriceList(pp.getPriceList());
		iline.setPriceLimit(pp.getPriceLimit());
		iline.setPriceActual(pp.getPriceStd());
		iline.setPriceEntered(pp.getPriceStd());
		iline.setC_UOM_ID(Integer.valueOf(pp.getC_UOM_ID()));
		iline.set_ValueOfColumn("C_Currency_ID", Integer.valueOf(pp.getC_Currency_ID()));
		
	}


}
