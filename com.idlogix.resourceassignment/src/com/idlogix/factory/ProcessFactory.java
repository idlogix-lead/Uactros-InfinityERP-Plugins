package com.idlogix.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.idlogix.resourceassignment.CreateInvoiceFomOrderlines;
import com.idlogix.resourceassignment.SalesOrderFromResource;

public class ProcessFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		
		if(className.equals("com.idlogix.factory.SalesOrderFromResource"))
			return new SalesOrderFromResource();
		if(className.equals("com.idlogix.factory.CreateInvoiceFomOrderlines"))
			return new CreateInvoiceFomOrderlines();
		return null;
	}

}
