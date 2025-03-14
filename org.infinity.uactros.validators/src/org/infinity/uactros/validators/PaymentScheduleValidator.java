package org.infinity.uactros.validators;

import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.I_C_Order;
import org.compiere.model.MClient;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.MOrder;
import org.compiere.model.MPaySchedule;
import org.compiere.model.MProject;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.infinity.models.MSalesFormSchedule;

public class PaymentScheduleValidator implements ModelValidator {

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MInvoicePaySchedule.Table_Name, this);
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
		if(po.get_TableName().equals(MInvoicePaySchedule.Table_Name) && type==TYPE_BEFORE_NEW) {
			
			MInvoicePaySchedule ips = (MInvoicePaySchedule)po;
			int invoiceID =ips.getC_Invoice_ID();
			List<MInvoicePaySchedule> list = new Query(Env.getCtx(), MInvoicePaySchedule.Table_Name, "  c_invoice_id = ? ", po.get_TrxName())
					.setParameters(invoiceID).setOrderBy(" created")
					.list();
			if(list.size()<=0) {
				return null;
			}
			int payScheduleID = ips.getC_PaySchedule_ID();
			MInvoice invoice = new MInvoice(po.getCtx(), invoiceID, po.get_TrxName());
			int salesform_id= invoice.get_ValueAsInt("SalesForm_ID");
			if(payScheduleID>0) {
				
				MSalesFormSchedule sfs= new Query(po.getCtx(), MSalesFormSchedule.Table_Name , "C_PaySchedule_ID=? AND SalesForm_ID=? ", po.get_TrxName())
						.setParameters(payScheduleID,salesform_id).first();
				
				if(sfs!=null) {
					ips.setDiscountDate(sfs.getDiscountDate());
					ips.setDiscountDate(sfs.getDiscountDate());
					ips.setDueAmt(sfs.getDueAmt());
					ips.setDueDate(sfs.getDueDate());
				}
				
			}
		
		}
		
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
