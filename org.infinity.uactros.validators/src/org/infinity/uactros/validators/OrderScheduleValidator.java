package org.infinity.uactros.validators;

import java.sql.Timestamp;

import org.compiere.model.MClient;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderPaySchedule;
import org.compiere.model.MPaySchedule;
import org.compiere.model.MProject;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

public class OrderScheduleValidator implements ModelValidator {

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MOrderPaySchedule.Table_Name, this);
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
		if(po.get_TableName().equals(MOrderPaySchedule.Table_Name) && type==TYPE_BEFORE_NEW) {
			MOrderPaySchedule ops = (MOrderPaySchedule)po;
			int orderID =ops.getC_Order_ID();
			int payScheduleID = ops.getC_PaySchedule_ID();
			MOrder order = new MOrder(Env.getCtx(), orderID, null);
			int projectID = order.getC_Project_ID();
			if(projectID>0 && payScheduleID>0) {
				MProject project = new MProject(Env.getCtx(), projectID, null);
				Timestamp contractDate = project.getDateContract();
				if(contractDate !=null) {//Contract date should not be null
					MPaySchedule paySchedule = new MPaySchedule(Env.getCtx(), payScheduleID, null);
					Timestamp dueDate = TimeUtil.addDays(contractDate, paySchedule.getNetDays());
					ops.setDueDate (dueDate);
					Timestamp discountDate = TimeUtil.addDays(contractDate, paySchedule.getDiscountDays());
					ops.setDiscountDate (discountDate);
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
