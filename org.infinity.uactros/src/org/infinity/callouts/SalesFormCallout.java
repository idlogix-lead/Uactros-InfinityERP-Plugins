package org.infinity.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.annotation.Callout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.infinity.models.MSalesForm;

@Callout(tableName =MSalesForm.Table_Name, columnName =MSalesForm.COLUMNNAME_M_Product_ID)
public class SalesFormCallout  implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		
		if(mTab.get_ValueAsString("M_Product_ID") != null || !mTab.get_ValueAsString("M_Product_ID").isEmpty()) {
			int productID= Integer.parseInt(mTab.get_ValueAsString("M_Product_ID"));
			
			MProduct Product = new MProduct(ctx, productID, null);
			int ProdCatId= Product.getM_Product_Category_ID();
			
			MProductCategory prodcat = new MProductCategory(ctx, ProdCatId, null);
			
			int PaymenttermId=prodcat.get_ValueAsInt("C_PaymentTerm_ID");
			
			if(PaymenttermId >0 ) {
			mTab.setValue("C_PaymentTerm_ID", PaymenttermId);
			}
			}
		return null;
	}

}
