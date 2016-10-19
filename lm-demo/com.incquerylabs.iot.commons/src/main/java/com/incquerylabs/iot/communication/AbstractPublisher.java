package com.incquerylabs.iot.communication;

public abstract class AbstractPublisher implements IPublisher {
	
	protected volatile boolean inprogress = false;
	
	protected String topic = "";
	
	@Override
	public void connect(IAddress address) {
		this.topic = address.getTopic();
	}
	
	@Override
	public boolean isReady() {
		return !inprogress;
	}

}
