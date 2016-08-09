package com.incquerylabs.iot.communication;

abstract public class CommunicationComponentFactory {
	
	public abstract IPublisher createPublisher();
	
	public abstract ISubscriber createSubscriber(IExecutorPool pool);

}
