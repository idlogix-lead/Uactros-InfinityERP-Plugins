package org.infinity.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.annotation.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MPayment;
import org.infinity.models.MSalesForm;
@Callout(tableName = {MPayment.Table_Name}, columnName = {"SalesForm_ID"})

public class PaymentSalesFormCallout implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Integer salesFormID = (Integer) value;

        MSalesForm salesForm = new MSalesForm(ctx, salesFormID, null);

        if (salesForm != null && salesForm.getC_BPartner_ID() > 0) {
            mTab.setValue("C_BPartner_ID", salesForm.getC_BPartner_ID());
        }

        return "";
    }
}