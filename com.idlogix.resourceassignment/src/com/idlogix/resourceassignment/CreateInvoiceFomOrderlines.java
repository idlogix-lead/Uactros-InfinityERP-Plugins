package com.idlogix.resourceassignment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class CreateInvoiceFomOrderlines extends SvrProcess {
	MInvoice invoice=null;
	MInOut shipment = null;
	Timestamp DateInvoiced=null;
	Timestamp DateAccount=null;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DateInvoiced"))
				DateInvoiced = para[i].getParameterAsTimestamp();
			else if (name.equals("DateAcct"))
				DateAccount = para[i].getParameterAsTimestamp();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		
		 List<MInOut> createdShipments = new ArrayList<>();
		    List<MInvoice> createdInvoices = new ArrayList<>();

		
		List<MOrderLine> orderLines = getSelectedPlanLines();
		
		Map<Integer, List<MOrderLine>> orderLinesByOrder = orderLines.stream()
			    .collect(Collectors.groupingBy(MOrderLine::getC_Order_ID));
			    for (Map.Entry<Integer, List<MOrderLine>> entry : orderLinesByOrder.entrySet()) {
			        Integer orderId = entry.getKey();
			        List<MOrderLine> lines = entry.getValue();

			        shipment = null;
		for (MOrderLine line : lines) {
            if (shipment == null) {
                createShipmentHeader(line);
            }
            createShipmentLine(line, shipment);
        }
        if (shipment != null) {
            if (!shipment.processIt(MInOut.DOCACTION_Complete)) {
                throw new AdempiereException("Failed to complete shipment: " + shipment.getProcessMsg());
            }
            shipment.saveEx();
            createdShipments.add(shipment);
        }

        createInvoiceFromShipment(shipment);

        if (invoice != null) {
            if (!invoice.processIt(MInvoice.DOCACTION_Complete)) {
                throw new AdempiereException("Failed to complete invoice: " + invoice.getProcessMsg());
            }
            invoice.saveEx();
            createdInvoices.add(invoice);
        }
        
			    }        
			    for (int i = 0; i < createdInvoices.size(); i++) {
			    	MInvoice invoice = createdInvoices.get(i);
			    	MInOut shipment = createdShipments.get(i);
			    	
			    	addBufferLog(
			    			invoice.get_ID(), 
			    			null, 
			    			null, 
			    			"Invoice Created - " + invoice.getDocumentNo() + 
			    			" | Shipment Created - " + shipment.getDocumentNo(),
			    			invoice.get_Table_ID(), 
			    			invoice.get_ID()
			    			);
			    }
		return null;
	}

	
	List<MOrderLine>  getSelectedPlanLines(){

		List<Integer> ids = new ArrayList<Integer>();
		    
		String sql = "select * from t_selection where t_selection.ad_pinstance_id = ?";
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try
			{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setInt(1, getAD_PInstance_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				int T_Selection_ID = rs.getInt("T_Selection_ID");	
				ids.add(T_Selection_ID);
	       
			}
		}
		catch (Exception e)
		{
			throw new AdempiereException(e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		  String filter  = MOrderLine.COLUMNNAME_C_OrderLine_ID +" IN (" +
						  ids.stream()
		                 .map(String::valueOf)
		                 .collect(Collectors.joining(","))
		 				  +")";
		  
		  return new Query(getCtx(), MOrderLine.Table_Name, filter, get_TrxName()).list();

	}
	
	
	 void createShipmentHeader(MOrderLine orderLine) {
	        shipment = new MInOut(getCtx(), 0, get_TrxName());
	        shipment.setC_Order_ID(orderLine.getC_Order_ID());
	        shipment.setC_DocType_ID(1000012);
	        shipment.setDateAcct(DateAccount);
	        shipment.setMovementDate(DateInvoiced);
	        shipment.setM_Warehouse_ID(orderLine.getM_Warehouse_ID());
	        shipment.setC_BPartner_ID(orderLine.getC_BPartner_ID());
	        shipment.setC_Order_ID(orderLine.getC_Order_ID());
	        shipment.setC_BPartner_Location_ID(orderLine.getC_BPartner_Location_ID());
	        shipment.setMovementType(MInOut.MOVEMENTTYPE_CustomerShipment);
	        shipment.saveEx();
	    }

	    void createShipmentLine(MOrderLine orderLine, MInOut shipment) {
	        if (shipment.get_ID() > 0) {
	            MInOutLine shipmentLine = new MInOutLine(getCtx(), 0, get_TrxName());
	            shipmentLine.setM_Product_ID(orderLine.getM_Product_ID());
	            shipmentLine.setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
	            shipmentLine.setC_UOM_ID(orderLine.getC_UOM_ID());
	            shipmentLine.setQty(orderLine.getQtyOrdered());
	            shipmentLine.setMovementQty(orderLine.getQtyOrdered());
	            shipmentLine.setM_InOut_ID(shipment.get_ID());
	            shipmentLine.saveEx();
	        }
	        
	    }
	
	    private void createInvoiceFromShipment(MInOut shipment) {
	        invoice = new MInvoice(getCtx(), 0, get_TrxName());
	        invoice.setC_Order_ID(shipment.getC_Order_ID());
	        invoice.setC_DocType_ID(1000002); 
	        invoice.setC_DocTypeTarget_ID(1000002);
	        invoice.setDateInvoiced(DateInvoiced);
	        invoice.setDateAcct(DateAccount);
	        invoice.setC_BPartner_ID(shipment.getC_BPartner_ID());
	        invoice.setC_BPartner_Location_ID(shipment.getC_BPartner_Location_ID());
	        invoice.saveEx();

	        for (MInOutLine shipmentLine : shipment.getLines()) {
	            MInvoiceLine invoiceLine = new MInvoiceLine(getCtx(), 0, get_TrxName());
	            invoiceLine.setM_Product_ID(shipmentLine.getM_Product_ID());
	            invoiceLine.setM_InOutLine_ID(shipmentLine.get_ID());
	            invoiceLine.setC_OrderLine_ID(shipmentLine.getC_OrderLine_ID());
	            invoiceLine.setC_UOM_ID(shipmentLine.getC_UOM_ID());
	            invoiceLine.setQtyEntered(shipmentLine.getMovementQty());
	            invoiceLine.setQty(shipmentLine.getMovementQty());
	            invoiceLine.setPriceEntered(shipmentLine.getC_OrderLine().getPriceEntered());
	            invoiceLine.setPriceActual(shipmentLine.getC_OrderLine().getPriceActual());
	            invoiceLine.setC_Invoice_ID(invoice.get_ID());
	            invoiceLine.saveEx();
	        }
	    }
}
