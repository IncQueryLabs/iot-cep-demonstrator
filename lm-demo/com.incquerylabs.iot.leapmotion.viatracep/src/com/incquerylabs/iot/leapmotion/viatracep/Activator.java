package com.incquerylabs.iot.leapmotion.viatracep;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	private LeapCepApplication application;
	
	static BundleContext getContext() {
		return context;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		PublisherPool.initializePool(new ZMQFactory());
		SubscriberPool.initializePool(new ZMQFactory());
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
