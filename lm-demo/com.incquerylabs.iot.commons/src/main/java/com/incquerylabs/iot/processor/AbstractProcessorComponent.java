package com.incquerylabs.iot.processor;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.ISubscriberCallback;
import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;

public class AbstractProcessorComponent implements ISubscriberCallback {
	
	IAddress sourceAddress;
	IAddress targetAddress;
	
	IDataProcessor processor;
	
	public AbstractProcessorComponent(IAddress sourceAddress, IAddress targetAddress, IDataProcessor processor) throws PoolNotInitializedException {
		
		this.sourceAddress = sourceAddress;
		this.targetAddress = targetAddress;
	
		processor = this.processor;
		
		SubscriberPool.getInstance().registerCallback(sourceAddress, this);
	}
	
	@Override
	public void messageArrived(IAddress address, byte[] data) {
		try {
			PublisherPool.getInstance().next(targetAddress).publish(processor.process(data), 0);
		} catch (PoolNotInitializedException e) {
			// TODO Logging ...
			e.printStackTrace();
		}
	}
	
}
