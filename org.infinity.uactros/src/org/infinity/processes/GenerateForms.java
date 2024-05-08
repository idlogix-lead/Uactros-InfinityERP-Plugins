package org.infinity.processes;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.infinity.models.MSalesForm;
import org.infinity.models.MSalesFormHdr;


@org.adempiere.base.annotation.Process
public class GenerateForms extends SvrProcess {

	private int m_recordID;
	private final String formTemplate = "%s%s";
	private MSalesFormHdr header;
	private boolean isGenerate = true;
	private int pRangeStart;
	private int pRangeEnd;
	private int pUserID;
	private String pPrefix;
	private int pNumericLength;
	@Override
	protected void prepare() {

		m_recordID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if(name.equalsIgnoreCase("isGenerate"))
				isGenerate = para[i].getParameterAsBoolean();
			else if(name.equalsIgnoreCase("Range_Start"))
				pRangeStart = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("Range_End"))
				pRangeEnd = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("NumericLength"))
				pNumericLength = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("AD_User_ID"))
				pUserID = para[i].getParameterAsInt();
			else if(name.equalsIgnoreCase("Prefix"))
				pPrefix = para[i].getParameterAsString();
			else 
				log.severe("Unknown Parameter: " + name);
		}
	}
	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		header = new MSalesFormHdr(getCtx(), m_recordID, get_TrxName());
		if(!isGenerate) {
			
			assignForms();
			return null;
			
		}
		if(pRangeStart>pRangeEnd || pRangeStart<0 || pRangeEnd<0)
			throw new AdempiereException("Set RangeStart & RangeEnd & Length Properly!");
		
		
		IntStream.range(pRangeStart, pRangeEnd+1).forEach(i->{
			
			MSalesForm form = new MSalesForm(getCtx(), 0, get_TrxName());
			String formNo = generateFormNo(i);
			if(isFormExist(formNo))
				return;
			form.setSalesForm_Hdr_ID(header.get_ID());
			form.setForm_No(formNo);
			form.setName(formNo);
			form.setAD_Org_ID(header.getAD_Org_ID());
			form.setSerialNo(new BigDecimal(i));
			form.setPrefix(pPrefix);
			form.setC_Project_ID(header.getC_Project_ID());
			form.setDocAction("CO");
			form.setC_DocType_ID(1000049);
			form.setC_DocTypeTarget_ID(1000049);
			form.setIsApproved(false);
			form.save();
		});
		return null;
	}
	private String generateFormNo(int i) {
		String prefix = pPrefix;
		String oo = Integer.toString(i);
		int ooLen = oo.length();
		int maxNumericLength = pNumericLength;
		if(ooLen>maxNumericLength)
			pNumericLength = ooLen;
		String expression = "%0"+pNumericLength+"d";
		String numericPart = String.format(expression, i);
		String formNo = String.format(formTemplate, prefix,numericPart);
		return formNo;
	}
	
	private void assignForms() {	
		
		if(pUserID<=0 || pRangeStart>pRangeEnd || pRangeStart<=0 || pRangeEnd<=0)
			return;
		List<MSalesForm> forms = new Query(Env.getCtx(),MSalesForm.Table_Name, " Prefix = ? and serialno between ? and ?", null)
				.setParameters(pPrefix,(pRangeStart),(pRangeEnd))
				.list();
		for(MSalesForm form:forms) {
			form.setAD_User_ID(pUserID);
			form.save();
		}
	}
	
	private boolean isFormExist(String formNo) {
		
		MSalesForm form = (MSalesForm) (new Query(Env.getCtx(),MSalesForm.Table_Name, " Form_No = ? ", get_TrxName()).setParameters(formNo).firstOnly());
		if(form!=null && form.get_ID()>0 )
			return true;
		return false;
	}
	

}
