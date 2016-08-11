package com.incquerylabs.iot.communication;

public interface IAddress {
	
	public String getHost();
	
	public int getPort();
	
	public String getTopic();
	
	public String getFullAddress();
	
}
