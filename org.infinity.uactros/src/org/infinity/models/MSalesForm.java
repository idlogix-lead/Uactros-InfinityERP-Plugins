package org.infinity.models;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.base.Core;
import org.adempiere.base.IProductPricing;
import org.adempiere.base.event.annotations.doc.AfterReactivate;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MProduct;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;

@org.adempiere.base.Model(table="SalesForm")
public class MSalesForm extends X_SalesForm implements DocAction ,DocOptions  {	

	public MSalesForm(Properties ctx, int SalesForm_ID, String trxName) {
		super(ctx, SalesForm_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MSalesForm (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
	@Override
	public boolean processIt(String action) throws Exception {
		log.warning("Processing Action=" + action + " - DocStatus=" + getDocStatus() + " - DocAction=" + getDocAction());
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(action, getDocAction());
	}
	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String prepareIt() {
		setC_DocType_ID(getC_DocTypeTarget_ID());
		return DocAction.STATUS_InProgress;
	}
	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String completeIt() {

		int SalesformId= getSalesForm_ID();
		MSalesForm form = new MSalesForm(getCtx(), SalesformId, get_TrxName());
		
		if(form.get_ID()>0 && form.getC_BPartner_ID()>0) {
			MBPartner customer = (MBPartner) form.getC_BPartner();
			try {
				createInvoice(form,customer);
			} catch (Exception e) {
				throw e;
			}
			}
		
		setProcessed(true);
		return DocAction.STATUS_Completed;
	}
	@Override
	public boolean voidIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean reActivateIt() {
		ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REACTIVATE);
		int invoiceID = getC_Invoice_ID();
		MInvoice myinvoice = new MInvoice(p_ctx, invoiceID, null);
		
		if (myinvoice.processIt("RC"))
		{
			myinvoice.saveEx();
		} else {
			throw new IllegalStateException("invoice Process Failed: " + myinvoice + " - " + myinvoice.getProcessMsg());
			
		}
		setC_Invoice_ID(0);
		setProcessed(false);
		setDocStatus(STATUS_Drafted);
		 saveEx();
		System.out.println(getDocStatus());
				
		return true;
	}
	
	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return BigDecimal.ONE;
	}
	@Override
	public int customizeValidActions(String docStatus, Object processing, String orderType, String isSOTrx,
			int AD_Table_ID, String[] docAction, String[] options, int index) {
		if (options == null)
			throw new IllegalArgumentException("Option array parameter is null");
		if (docAction == null)
			throw new IllegalArgumentException("Doc action array parameter is null");

		// If a document is drafted or invalid, the users are able to complete, prepare or void
		if (docStatus.equals(DocumentEngine.STATUS_Drafted) || docStatus.equals(DocumentEngine.STATUS_Invalid)) {
			options[index++] = DocumentEngine.ACTION_Complete;
			options[index++] = DocumentEngine.ACTION_Prepare;
			options[index++] = DocumentEngine.ACTION_Void;

			// If the document is already completed, we also want to be able to reactivate or void it instead of only closing it
		} else if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
			options[index++] = DocumentEngine.ACTION_Void;
			options[index++] = DocumentEngine.ACTION_ReActivate;
		}

		return index;
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		if (is_ValueChanged(MSalesForm.COLUMNNAME_C_Buyer_ID)) {
			List<MSalesFormOwner> owners = new Query(Env.getCtx(),MSalesFormOwner.Table_Name, " Current_Owner  ='Y' ", null)
    				.list();
            for(MSalesFormOwner obj:owners) {
            	obj.setCurrent_Owner(false);
            	obj.save();
            }
            MSalesFormOwner owner = new MSalesFormOwner(getCtx(), 0, get_TrxName());
            owner.setSalesForm_ID(get_ID());
            owner.setSalesForm_Owner_ID(getSalesForm_ID());
            owner.setC_Buyer_ID(getC_Buyer_ID());
            owner.setAD_Org_ID(getAD_Org_ID());
            owner.setCurrent_Owner(true);
            owner.saveEx();      
        }
		
		return super.beforeSave(newRecord);
	}


void createInvoice(MSalesForm form,MBPartner customer) {
		
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
		invoice.setC_Project_ID(form.getC_Project_ID());
		invoice.setPaymentRule("P");
		invoice.setC_PaymentTerm_ID(form.getC_PaymentTerm_ID()>0?form.getC_PaymentTerm_ID(): 1000000);
		invoice.setC_Currency_ID(306);
		invoice.set_ValueOfColumn("SalesForm_ID", form.getSalesForm_ID());
//		invoice.setOrder(order);
		invoice.save();
		setC_Invoice_ID(invoice.get_ID());		
		
		
//		for(MOrderLine oline: order.getLines()){
//			line.setOrderLine(oline);
//			line.setM_Product_ID(oline.getM_Product_ID());
//			line.setQty(oline.getQtyOrdered());
	
		MInvoiceLine line = new MInvoiceLine(Env.getCtx(), 0, form.get_TrxName());
			line.setInvoice(invoice);
			line.setAD_Org_ID(invoice.getAD_Org_ID());
			line.setC_Invoice_ID(invoice.get_ID());
			line.setQty(Env.ONE);
			BigDecimal PriceEntered= (BigDecimal) form.get_Value("priceactual");
			
			 if (form.getM_Product_ID() > 0) {
		            line.setM_Product_ID(form.getM_Product_ID());
		            setLinePricing(line);
		        } else {
		            line.setC_Charge_ID(form.get_ValueAsInt("C_Charge_ID"));
		            line.setPriceActual(PriceEntered);
		            line.setPriceEntered(PriceEntered);
		            line.setPriceList(PriceEntered);

		        }
			line.save();
//		}
		if(invoice==null || invoice.getC_Invoice_ID()<=0 )
			return;
			invoice.setDocAction("CO");
			
		if (invoice.processIt("CO"))
		{
			invoice.saveEx();
			
		} else {
			throw new IllegalStateException("Invoice Process Failed: " + invoice + " - " + invoice.getProcessMsg());
			
		}
		
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
@AfterReactivate
public boolean AfterReActivateIt() {
	ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REACTIVATE);
	
	setDocStatus(STATUS_Drafted);
	System.out.println(getDocStatus());
			
	return true;
}
	
}
