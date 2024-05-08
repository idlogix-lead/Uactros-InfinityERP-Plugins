package org.infinity.models;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;

@org.adempiere.base.Model(table="SalesForm")
public class MSalesForm extends X_SalesForm implements DocAction ,DocOptions  {

	public MSalesForm(Properties ctx, int SalesForm_ID, String trxName) {
		super(ctx, SalesForm_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MSalesForm (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
	@Override
	public boolean processIt(String action) throws Exception {
		log.warning("Processing Action=" + action + " - DocStatus=" + getDocStatus() + " - DocAction=" + getDocAction());
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(action, getDocAction());
	}
	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String prepareIt() {
		setC_DocType_ID(getC_DocTypeTarget_ID());
		return DocAction.STATUS_InProgress;
	}
	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String completeIt() {
		ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		setProcessed(true);
		return DocAction.STATUS_Completed;
	}
	@Override
	public boolean voidIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean reActivateIt() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return BigDecimal.ONE;
	}
	@Override
	public int customizeValidActions(String docStatus, Object processing, String orderType, String isSOTrx,
			int AD_Table_ID, String[] docAction, String[] options, int index) {
		if (options == null)
			throw new IllegalArgumentException("Option array parameter is null");
		if (docAction == null)
			throw new IllegalArgumentException("Doc action array parameter is null");

		// If a document is drafted or invalid, the users are able to complete, prepare or void
		if (docStatus.equals(DocumentEngine.STATUS_Drafted) || docStatus.equals(DocumentEngine.STATUS_Invalid)) {
			options[index++] = DocumentEngine.ACTION_Complete;
			options[index++] = DocumentEngine.ACTION_Prepare;
			options[index++] = DocumentEngine.ACTION_Void;

			// If the document is already completed, we also want to be able to reactivate or void it instead of only closing it
		} else if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
			options[index++] = DocumentEngine.ACTION_Void;
			options[index++] = DocumentEngine.ACTION_ReActivate;
		}

		return index;
	}


}
