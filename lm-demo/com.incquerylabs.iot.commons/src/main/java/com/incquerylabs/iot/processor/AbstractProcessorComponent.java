package com.incquerylabs.iot.processor;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.ISubscriberCallback;
import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;

public class AbstractProcessorComponent implements ISubscriberCallback {
	
	IAddress sourceAddress;
	
	IDataProcessor processor;
	IDataProcessor processorCache;
	
	protected final static String ON_ERROR = "Exception occured during data processing %s : %s";
	
	private boolean connectionTested = false;
	
	public AbstractProcessorComponent(IAddress sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	
	protected void setProcessor(IDataProcessor processor) {
		this.processor = processor;
		this.processorCache = new TestConnection();
	}
	
	@Override
	public void messageArrived(IAddress address, byte[] data) {
		try {
			processor.process(data);
		} catch (Exception e) {
			if(processorCache != null) {
				try {
					processorCache.process(data);
					processorCache = null;
				} catch (Exception e1) {
					System.out.println(String.format(ON_ERROR, address.getFullAddress(), e.getMessage()));
				}
			} else {
				System.out.println(String.format(ON_ERROR, address.getFullAddress(), e.getMessage()));
			}
			
		}	
	}
	
	public void start() throws PoolNotInitializedException {
		SubscriberPool.getInstance().registerCallback(sourceAddress, this);
		PublisherPool.getInstance().next(sourceAddress).publish((this.getClass().getName() + " connection test!").getBytes(), 0);
	}
	
	public void stop() throws PoolNotInitializedException {
		SubscriberPool.getInstance().unregisterCallback(sourceAddress, this);
	}
	
	private static class TestConnection implements IDataProcessor {
				
		@Override
		public void process(byte[] data) throws Exception {
			System.out.println(new String(data));
		}
		
	}
}
