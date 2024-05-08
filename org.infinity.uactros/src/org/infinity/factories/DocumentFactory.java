package org.infinity.factories;

import java.sql.ResultSet;

import org.adempiere.base.IDocFactory;
import org.compiere.acct.Doc;
import org.compiere.model.MAcctSchema;

public class DocumentFactory implements IDocFactory{

	@Override
	public Doc getDocument(MAcctSchema as, int AD_Table_ID, ResultSet rs, String trxName) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
