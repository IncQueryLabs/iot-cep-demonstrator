package com.incquerylabs.iot.processor;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.ISubscriberCallback;
import com.incquerylabs.iot.communication.PublisherPool;
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
		try {
			processor.process(data);
		} catch (Exception e) {
			System.out.println(String.format("Exception occured during data processing %s : %s", address.getFullAddress(), e.getMessage()));
		}	
	}
	
	public void start() throws PoolNotInitializedException {
		SubscriberPool.getInstance().registerCallback(sourceAddress, this);
		IDataProcessor proc = this.processor;
		this.processor = new TestConnection();
		PublisherPool.getInstance().next(sourceAddress).publish(this.getClass().getName() + " connection test!", 0);
		this.processor = proc;
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
