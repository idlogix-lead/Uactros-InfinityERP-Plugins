package org.infinity.uactros.validators;

import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.MClient;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderPaySchedule;
import org.compiere.model.MPaySchedule;
import org.compiere.model.MProject;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.infinity.models.MSalesFormSchedule;

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
			List<MOrderPaySchedule> list = new Query(Env.getCtx(), MOrderPaySchedule.Table_Name, "  c_order_id = ? ", po.get_TrxName())
					.setParameters(orderID).setOrderBy(" created")
					.list();
			if(list.size()<=0) {
				return null;
			}
			int payScheduleID = ops.getC_PaySchedule_ID();
			MOrder order = new MOrder(po.getCtx(), orderID, po.get_TrxName());
			int salesform_id= order.get_ValueAsInt("SalesForm_ID");
			if(payScheduleID>0) {
				MSalesFormSchedule sfs= new Query(po.getCtx(), MSalesFormSchedule.Table_Name , "C_PaySchedule_ID=? AND SalesForm_ID=? ", po.get_TrxName())
						.setParameters(payScheduleID,salesform_id).first();
				
				if(sfs!=null) {
					ops.setDiscountDate(sfs.getDiscountDate());
					ops.setDiscountDate(sfs.getDiscountDate());
					ops.setDueAmt(sfs.getDueAmt());
					ops.setDueDate(sfs.getDueDate());
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
