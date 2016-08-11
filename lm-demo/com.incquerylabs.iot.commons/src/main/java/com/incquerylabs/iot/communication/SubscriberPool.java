package com.incquerylabs.iot.communication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;

public class SubscriberPool {

	private static SubscriberPool INSTANCE = null;
	
	private IExecutorPool pool;
	
	private CommunicationComponentFactory factory;
	
	private static volatile boolean initialized = false;

	private Map<String, ISubscriber> subscribers = new HashMap<>();
	
	private SubscriberPool(CommunicationComponentFactory factory) {
		pool = new DefaultExecutor();
		this.factory = factory;
		initialized = false;
	}
	
	public static void initializePool(CommunicationComponentFactory factory) {
		if(factory != null) {
			INSTANCE = new SubscriberPool(factory);
			initialized = true;
		}
	}
	
	public static SubscriberPool getInstance() throws PoolNotInitializedException {
		if(initialized) {
			return INSTANCE;
		} else throw new PoolNotInitializedException();
	}
	
	public void registerCallback(IAddress address, ISubscriberCallback callback) {
		if(subscribers.containsKey(address.getFullAddress())) {
			subscribers.get(address.getFullAddress()).registerCallback(address, callback);
		} else {
			ISubscriber subscriber = factory.createSubscriber(pool);
			subscriber.registerCallback(address, callback);
			subscribers.put(address.getFullAddress(), subscriber);
		}	
	}
	
	public void unregisterCallback(ISubscriberCallback callback) {
		// TODO: 
	}
	
	private static class DefaultExecutor extends ThreadPoolExecutor implements IExecutorPool {
		public DefaultExecutor() {
			super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		}
	}
	
}
