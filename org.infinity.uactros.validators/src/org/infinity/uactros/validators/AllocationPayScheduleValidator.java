package org.infinity.uactros.validators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MClient;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Env;

public class AllocationPayScheduleValidator implements ModelValidator {

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addDocValidate(MAllocationHdr.Table_Name, this);
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
		if(po.get_TableName().equals(MAllocationHdr.Table_Name) && timing==TIMING_BEFORE_COMPLETE) {
			MAllocationHdr alo = (MAllocationHdr)po;
			MPayment pay=null;
			for(MAllocationLine line: alo.getLines(true)) {
				
			int PaymentID=line.getC_Payment_ID();
			if (PaymentID >0) {
				 pay = new MPayment(po.getCtx(), PaymentID, po.get_TrxName());
		String	WhereClause="C_Payment_ID=? ";
		List<MPaymentAllocate> payalo = new Query(po.getCtx(), MPaymentAllocate.Table_Name, WhereClause, po.get_TrxName())
				.setParameters(pay.get_ID()).list();
			if(payalo.size()>0) {
				for(MPaymentAllocate aloline: payalo) {
						if(line.getC_Payment_ID() == aloline.getC_Payment_ID() && line.get_ID()==aloline.getC_AllocationLine_ID() 
								&& aloline.get_ValueAsInt("C_InvoicePaySchedule_ID")>0){
							line.set_ValueOfColumn("C_InvoicePaySchedule_ID", aloline.get_ValueAsInt("C_InvoicePaySchedule_ID"));
							line.save();
						}
					
				}
			}else {
				if(pay != null && pay.get_ValueAsInt("C_InvoicePaySchedule_ID") >0) {
						if(PaymentID == pay.get_ID() || line.getC_Charge_ID()>0){
							line.set_ValueOfColumn("C_InvoicePaySchedule_ID", pay.get_ValueAsInt("C_InvoicePaySchedule_ID"));
							line.save();
						}
				}
			}
			}
			
			}
			
			
		}
		return null;
	}

	
}
