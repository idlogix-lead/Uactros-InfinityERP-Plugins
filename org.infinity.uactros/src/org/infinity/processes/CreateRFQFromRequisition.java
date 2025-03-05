package org.infinity.processes;

import java.util.logging.Level;

import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MRfQ;
import org.compiere.model.MRfQLine;
import org.compiere.model.MRfQLineQty;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Env;

@org.adempiere.base.annotation.Process
public class CreateRFQFromRequisition  extends SvrProcess {

	int Requisition_ID=0;
	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Requisition_ID"))
				Requisition_ID = para[i].getParameterAsInt();

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		 
		
		MRequisition requisition= new MRequisition(getCtx(), Requisition_ID, get_TrxName());
		
		String whereClause = "M_Requisition_ID=?";
		MRfQ existingRfQ = new Query(getCtx(), MRfQ.Table_Name, whereClause, get_TrxName())
		        .setParameters(Requisition_ID)
		        .first();

		// If RFQ already exists, return from the method
		if (existingRfQ != null) {
			throw new AdempiereUserError ("RfQ Against this Requistion  "+ requisition.getDocumentNo() +"  is Already Created");
		}
		
		
		MRfQ rfq = new MRfQ(getCtx(), 0, get_TrxName());
		rfq.setName(requisition.getDescription());
		rfq.setDateResponse(requisition.getDateRequired()); 
		rfq.setC_RfQ_Topic_ID(1000000);
		rfq.setC_Currency_ID(306);
		rfq.setSalesRep_ID(Env.getAD_User_ID(getCtx()));
		rfq.set_ValueOfColumn("M_Requisition_ID", Requisition_ID);
		rfq.saveEx();
		
		
		MRequisitionLine[] reqlines= requisition.getLines();
		
		for (MRequisitionLine line : reqlines ) {
			
			MRfQLine rfql = new MRfQLine(getCtx(), 0, get_TrxName());
			rfql.setM_Product_ID(line.getM_Product_ID());
			rfql.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
			rfql.setLine(line.getLine());
			rfql.setC_RfQ_ID(rfq.get_ID());
			rfql.saveEx();
			
			MRfQLineQty rfql_qty= new MRfQLineQty(getCtx(), 0, get_TrxName());
			rfql_qty.setC_RfQLine_ID(rfql.get_ID());
			rfql_qty.setC_UOM_ID(line.getC_UOM_ID());
			rfql_qty.setQty(line.getQty());
			rfql_qty.setBenchmarkPrice(line.getPriceActual());
			rfql_qty.setIsPurchaseQty(true);
			rfql_qty.saveEx();
			
		}
		
		requisition.set_ValueOfColumn("C_RfQ_ID", rfq.get_ID());
		requisition.saveEx();
		
		
		return "RFQ Created SuccessFully";
	}

}
