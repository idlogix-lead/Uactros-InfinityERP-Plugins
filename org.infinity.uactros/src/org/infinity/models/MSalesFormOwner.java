package org.infinity.models;

import java.sql.ResultSet;
import java.util.Properties;

public class MSalesFormOwner extends X_SalesForm_Owner {

	public MSalesFormOwner(Properties ctx, int SalesForm_Owner_ID, String trxName, String[] virtualColumns) {
		super(ctx, SalesForm_Owner_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}
	
	 public MSalesFormOwner(Properties ctx, int SalesForm_Owner_ID, String trxName) {
	        super(ctx, SalesForm_Owner_ID, trxName);
	    }

	    public MSalesFormOwner(Properties ctx, ResultSet rs, String trxName) {
	        super(ctx, rs, trxName);
	    }

	    public MSalesFormOwner(Properties ctx, String SalesForm_Owner_UU, String trxName, String... virtualColumns) {
	        super(ctx, SalesForm_Owner_UU, trxName, virtualColumns);
	    }

	    public MSalesFormOwner(Properties ctx, String SalesForm_Owner_UU, String trxName) {
	        super(ctx, SalesForm_Owner_UU, trxName);
	    }
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		// TODO Auto-generated method stub
		return super.beforeSave(newRecord);
	}

}
