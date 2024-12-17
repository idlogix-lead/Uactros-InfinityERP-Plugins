package org.infinity.uactros;

import org.adempiere.base.Core;
import org.adempiere.webui.Extensions;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		Core.getMappedProcessFactory().scan(context, "org.infinity.processes");
		Core.getMappedModelFactory().scan(context, "org.infinity.models");
		Core.getMappedColumnCalloutFactory().scan(context, "org.infinity.callouts");
		Extensions.getMappedFormFactory().scan(context, "org.infinity.form");

	}


	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
