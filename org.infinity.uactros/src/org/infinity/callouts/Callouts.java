package org.infinity.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.infinity.models.MSalesFormOwner;

public class Callouts implements IColumnCallout {

	
	
    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
        if (mField.getColumnName().equals("C_Buyer_ID")) {
            if (value != null) {
            
                int buyerId = (Integer) value;
                
                int salesFormId = (Integer) mTab.getValue("SalesForm_ID");

                int orgId = (Integer) mTab.getValue("AD_Org_ID");

                MSalesFormOwner owner = new MSalesFormOwner(ctx, 0, null);
                owner.setC_Buyer_ID(buyerId);
                owner.setSalesForm_ID(salesFormId);
                owner.setAD_Org_ID(orgId);
                owner.setCurrent_Owner(true);

                
                if (owner.save()) {
                    mTab.dataRefresh();
                } else {
                    return "Error: Could not save new SalesForm_Owner entry.";
                }
            }
        }
        return null;
    }
}
