package org.infinity.uactros.validators;

import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.infinity.models.MSalesForm;

public class SalesFormBusinesPartnerValidator implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MSalesForm.Table_Name, this);
		
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
		if(po.get_Table_ID()==MSalesForm.Table_ID && type == TYPE_BEFORE_NEW || type== TYPE_BEFORE_CHANGE ) {
			
			 MSalesForm sf = (MSalesForm) po;
	            MBPartner bp = (MBPartner) sf.getC_BPartner(); 

	            if (bp != null && bp.getSalesRep_ID() == 0) {
	                return "Sales Representative is not assigned to this Business Partner.";
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
