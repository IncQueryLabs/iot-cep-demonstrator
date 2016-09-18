package com.incquerylabs.iot.communication;

public interface ISubscriber {
	
	public void registerCallback(IAddress address, ISubscriberCallback callback);
	
	public void unregisterCallback(ISubscriberCallback callback);
	
	public void disconnectAll();

}
