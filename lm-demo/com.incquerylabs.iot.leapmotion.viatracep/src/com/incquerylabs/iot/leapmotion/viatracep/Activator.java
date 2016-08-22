package com.incquerylabs.iot.leapmotion.viatracep;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	private LeapCepApplication application;
	
	static BundleContext getContext() {
		return context;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		application = new LeapCepApplication();
		application.start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.context = null;
		application.stop();
		application = null;
	}
	
	
}
