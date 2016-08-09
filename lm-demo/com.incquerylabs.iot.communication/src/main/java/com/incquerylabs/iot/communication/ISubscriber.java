package com.incquerylabs.iot.communication;

public interface ISubscriber {
	
	public void subscribe(IAddress address, ISubscriberCallback callback);
	
	public void disconnectAll();

}
