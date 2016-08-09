package com.incquerylabs.iot.communication;

public interface IPublisher {
	
	public void connect(IAddress address);
	
	public boolean isReady();
	
	public void publish(byte[] message, int qos);
	
	public void publish(String message, int qos);
	
	public void disconnect();
	
}
