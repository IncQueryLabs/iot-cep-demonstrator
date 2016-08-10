package com.incquerylabs.iot.communication;

public interface ISubscriberCallback {
	
	public void messageArrived(IAddress address, byte[] data);

}
