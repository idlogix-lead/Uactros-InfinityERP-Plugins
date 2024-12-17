package org.infinity.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.annotation.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.util.Env;
@Callout(tableName = {MPaymentAllocate.Table_Name,MPayment.Table_Name}, columnName = {MPaymentAllocate.COLUMNNAME_C_Invoice_ID,MPayment.COLUMNNAME_C_Invoice_ID})

public class InvoicePayScheduleCallout implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		String C_InvoicePaySchedule_ID=null;
		
		C_InvoicePaySchedule_ID=Env.getContext(ctx, WindowNo, Env.TAB_INFO, "C_InvoicePaySchedule_ID");
		if(C_InvoicePaySchedule_ID != null && !C_InvoicePaySchedule_ID.isEmpty()) {
		int integerValue = Integer.parseInt(C_InvoicePaySchedule_ID);

		
		if( integerValue > 0  ) {
			mTab.setValue("C_InvoicePaySchedule_ID", integerValue);
			
		}
		}
		return null;
	}

}
