package com.idlogix.resourceassignment;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MResourceAssignment;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.infinity.models.MAssignResource;

public class SalesOrderFromResource extends SvrProcess  {

	int C_bpartner_ID= 0;
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
		C_bpartner_ID=getRecord_ID();		
	}

	@Override
	protected String doIt() throws Exception {
	   
		List<MAssignResource> assign_Res = new Query(getCtx(), MAssignResource.Table_Name, "C_BPartner_ID=? AND IsConfirmed='Y'", get_TrxName())
	            .setParameters(C_bpartner_ID).list();

	    for (MAssignResource as : assign_Res) {
	        MBPartnerLocation bpl = new Query(getCtx(), MBPartnerLocation.Table_Name, "C_BPartner_ID=?", get_TrxName())
	                .setParameters(C_bpartner_ID).first();

	        Timestamp StartDate = as.getAssignDateFrom();
	        Timestamp EndDate = as.getAssignDateTo();
	        MProduct prod = new Query(getCtx(), MProduct.Table_Name, "S_Resource_ID=?", get_TrxName())
	                .setParameters(as.getS_Resource_ID()).first();

	        if (StartDate == null || EndDate == null) {
	            return "StartDate or EndDate is missing.";
	        }

	        if (prod == null) {
	            return "No product found for the resource.";
	        }

	        MProductPrice pp = new Query(getCtx(), MProductPrice.Table_Name, "M_Product_ID=?", get_TrxName())
	                .setParameters(prod.get_ID()).first();

	        if (pp == null) {
	            return "No price found for the product.";
	        }

	        MOrder od = new MOrder(getCtx(), 0, get_TrxName());
	        od.setC_BPartner_ID(C_bpartner_ID);
	        od.setM_Warehouse_ID(Env.getContextAsInt(getCtx(), "#M_Warehouse_ID"));
	        od.setC_BPartner_Location_ID(bpl.get_ID());
	        od.setC_DocType_ID(1000032);
	        od.setC_PaymentTerm_ID(1000000);
	        od.saveEx();

	        MResourceAssignment ra = new MResourceAssignment(getCtx(), 0, get_TrxName());
	        ra.setName(prod.getName());
	        ra.setQty(as.getQty());
	        ra.setAssignDateFrom(StartDate);
	        ra.setAssignDateTo(EndDate);
	        ra.setIsConfirmed(true);
	        ra.setS_Resource_ID(as.getS_Resource_ID());
	        ra.saveEx();

	        LocalDate startDate = StartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate endDate = EndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	        long monthsBetween = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));

	        for (long i = 0; i <= monthsBetween; i++) {
	            LocalDate monthDate = startDate.plusMonths(i).withDayOfMonth(1);
	            Timestamp datePromised = Timestamp.valueOf(monthDate.atStartOfDay());

	            MOrderLine orderLine = new MOrderLine(od);
	            orderLine.setQty(as.getQty());
	            orderLine.setM_Product_ID(prod.get_ID());
	            orderLine.setPriceList(pp.getPriceList());
	            orderLine.setPriceEntered(pp.getPriceStd());
	            orderLine.setPriceLimit(pp.getPriceLimit());
	            orderLine.setDateOrdered(new Timestamp(System.currentTimeMillis()));
	            orderLine.setDatePromised(datePromised);
	            orderLine.setS_ResourceAssignment_ID(ra.get_ID());
	            orderLine.setC_Order_ID(od.get_ID());
	            orderLine.saveEx();

	            System.out.println("Order Line Created for: " + datePromised);
	        }

			od.setDocAction("CO");
			od.processIt("CO");
			
	        addBufferLog(od.get_ID(), null, null, "Order Created - " + od.getDocumentNo(), od.Table_ID, od.get_ID());
	    }

	    return null;
	}
}
