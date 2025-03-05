package org.infinity.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MPaySchedule;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.MProductPrice;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.infinity.models.MSalesForm;
import org.infinity.models.MSalesFormSchedule;

@org.adempiere.base.annotation.Process
public class CreateSalesFormsSchedule extends SvrProcess {

	private MPaySchedule[] m_schedule;
	int salesform_id = 0;
    Timestamp StartDate=null;
    boolean IsProjectDate=false;
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("IsProjectDate"))
				IsProjectDate = para[i].getParameterAsBoolean();

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		salesform_id = getRecord_ID();

	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub

		BigDecimal due=Env.ZERO;
		MSalesForm sales_form = new MSalesForm(getCtx(), salesform_id, get_TrxName());

		MProductPrice pp= new Query(getCtx(), MProductPrice.Table_Name, "M_Product_ID=? AND M_PriceList_Version_ID=? ", get_TrxName())
				.setParameters(sales_form.getM_Product_ID(),1000001)
				.first();
		
		if(IsProjectDate) {
			StartDate=sales_form.getC_Project().getDateContract();
		}else {
			StartDate= sales_form.getDateIssued();
		}
		
		MPaymentTerm pt = (MPaymentTerm) sales_form.getC_PaymentTerm();
		m_schedule = pt.getSchedule(true);

		if (m_schedule.length == 0)
			return "";

		for (int i = 0; i < m_schedule.length; i++) {

			due=pp.getPriceStd();
			
			MSalesFormSchedule sfs = new Query(getCtx(), MSalesFormSchedule.Table_Name, "SalesForm_ID=? AND C_PaySchedule_ID=?", get_TrxName())
	                .setParameters(salesform_id, m_schedule[i].get_ID())
	                .first();

	        if (sfs == null) {

	            sfs = new MSalesFormSchedule(getCtx(), 0, get_TrxName());
	        }			
	        
			Timestamp dueDate = TimeUtil.addDays(StartDate, m_schedule[i].getNetDays());
			Timestamp discountDate = TimeUtil.addDays(StartDate, m_schedule[i].getDiscountDays());
			
			due = due.multiply(m_schedule[i].getPercentage()).divide(Env.ONEHUNDRED, 10,
					RoundingMode.HALF_UP);
			
			BigDecimal discount = due.multiply(m_schedule[i].getDiscount()).divide(Env.ONEHUNDRED, 10,
					RoundingMode.HALF_UP);

			sfs.setAD_Org_ID(m_schedule[i].getAD_Org_ID());
			sfs.setSalesForm_ID(salesform_id);
			sfs.setC_PaySchedule_ID(m_schedule[i].get_ID());	
			sfs.setDueAmt(due);
			sfs.setDiscountAmt(discount);
			sfs.setDueDate (dueDate);
			sfs.setDiscountDate (discountDate);	
			sfs.setIsValid(true);
			sfs.saveEx();
		}

		return null;
	}

}
