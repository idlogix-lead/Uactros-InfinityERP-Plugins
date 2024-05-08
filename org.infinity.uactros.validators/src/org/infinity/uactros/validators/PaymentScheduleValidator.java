package org.infinity.uactros.validators;

import java.sql.Timestamp;

import org.compiere.model.MClient;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.MPaySchedule;
import org.compiere.model.MProject;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

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
			int payScheduleID = ips.getC_PaySchedule_ID();
			MInvoice invoice = new MInvoice(Env.getCtx(), invoiceID, null);
			int projectID = invoice.getC_Project_ID();
			if(projectID>0 && payScheduleID>0) { 
				MProject project = new MProject(Env.getCtx(), projectID, null);
				Timestamp contractDate = project.getDateContract();
				if(contractDate !=null) { //Contract Date Should not be NULL
					MPaySchedule paySchedule = new MPaySchedule(Env.getCtx(), payScheduleID, null);
					Timestamp dueDate = TimeUtil.addDays(contractDate, paySchedule.getNetDays());
					ips.setDueDate (dueDate);
					Timestamp discountDate = TimeUtil.addDays(contractDate, paySchedule.getDiscountDays());
					ips.setDiscountDate (discountDate);
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
