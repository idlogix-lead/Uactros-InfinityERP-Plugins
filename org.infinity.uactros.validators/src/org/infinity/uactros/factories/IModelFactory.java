package org.infinity.uactros.factories;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.infinity.uactros.validators.AllocationPayScheduleValidator;
import org.infinity.uactros.validators.OrderScheduleValidator;
import org.infinity.uactros.validators.PaymentScheduleValidator;
import org.infinity.uactros.validators.SalesFormBusinesPartnerValidator;
import org.infinity.uactros.validators.SalesFormValidator;



public class IModelFactory implements IModelValidatorFactory {

	@Override
	public ModelValidator newModelValidatorInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.infinity.validators.SalesFormValidator"))
			return new SalesFormValidator();
		if(className.equals("org.infinity.validators.PaymentScheduleValidator"))
			return new PaymentScheduleValidator();
		if(className.equals("org.infinity.validators.OrderScheduleValidator"))
			return new OrderScheduleValidator();
		if(className.equals("org.infinity.validators.AllocationPayScheduleValidator"))
			return new AllocationPayScheduleValidator();
		if(className.equals("org.infinity.validators.SalesFormBusinesPartnerValidator"))
			return new SalesFormBusinesPartnerValidator();	
		return null;
	}
}
