package org.infinity.models;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInfoWindow;
import org.compiere.model.Query;
import org.compiere.util.Env;

@org.adempiere.base.Model(table="SalesForm_Hdr")
public class MSalesFormHdr extends X_SalesForm_Hdr {

	public MSalesFormHdr(Properties ctx, int SalesForm_Hdr_ID, String trxName) {
		super(ctx, SalesForm_Hdr_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MSalesFormHdr (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
	
	public List<MSalesForm> getLines(){
		return new Query(Env.getCtx(),MSalesForm.Table_Name, " SalesForm_Hdr_ID = ? ", null)
				.setParameters(getSalesForm_Hdr_ID())
				.list();
	}
	@Override
	protected boolean beforeSave(boolean newRecord) {
		// TODO Auto-generated method stub
		 new Query(Env.getCtx(),MSalesFormHdr.Table_Name, " isactive  ='Y' and SalesForm_Hdr_ID <> ? ", null)
		 .setParameters(getSalesForm_Hdr_ID())
			.list().forEach(obj->{
			MSalesFormHdr hdr =(MSalesFormHdr)obj;
			if(hdr.getC_Project_ID()==getC_Project_ID())
				throw new AdempiereException("Form Group Against this Project Already Exists!");
			});
		return super.beforeSave(newRecord);
	}
}
