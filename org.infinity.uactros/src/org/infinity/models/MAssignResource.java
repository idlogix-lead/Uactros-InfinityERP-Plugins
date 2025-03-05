package org.infinity.models;

import java.sql.ResultSet;
import java.util.Properties;

@org.adempiere.base.Model(table="S_AssignResource")
public class MAssignResource extends X_S_AssignResource {

	public MAssignResource(Properties ctx, int S_AssignResource_ID, String trxName, String... virtualColumns) {
		super(ctx, S_AssignResource_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MAssignResource(Properties ctx, int S_AssignResource_ID, String trxName) {
		super(ctx, S_AssignResource_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MAssignResource(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MAssignResource(Properties ctx, String S_AssignResource_UU, String trxName, String... virtualColumns) {
		super(ctx, S_AssignResource_UU, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MAssignResource(Properties ctx, String S_AssignResource_UU, String trxName) {
		super(ctx, S_AssignResource_UU, trxName);
		// TODO Auto-generated constructor stub
	}

}
