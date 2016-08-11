package com.incquerylabs.iot.processor;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.ISubscriberCallback;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;

public class AbstractProcessorComponent implements ISubscriberCallback {
	
	IAddress sourceAddress;
	
	IDataProcessor processor;
	
	public AbstractProcessorComponent(IAddress sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	
	protected void setProcessor(IDataProcessor processor) {
		this.processor = processor;
	}
	
	@Override
	public void messageArrived(IAddress address, byte[] data) {
		processor.process(data);		
	}
	
	public void start() throws PoolNotInitializedException {
		SubscriberPool.getInstance().registerCallback(sourceAddress, this);
	}
	
	public void stop() throws PoolNotInitializedException {
		SubscriberPool.getInstance().unregisterCallback(this);
	}
	
}
