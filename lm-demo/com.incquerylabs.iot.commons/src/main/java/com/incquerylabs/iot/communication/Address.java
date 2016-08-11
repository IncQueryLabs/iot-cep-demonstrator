package com.incquerylabs.iot.communication;

public class Address implements IAddress {
	
	private String host;
	private int port;
	private String topic;
	
	public Address(String host, int port, String topic) {
		this.host = host;
		this.port = port;
		this.topic = topic;
	}
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getTopic() {
		return topic;
	}
	
	@Override
	public String getFullAddress() {
		return String.format("%s:%d/%s", host, port, topic);
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Address) {
			return 
					this.host.equals(((Address) other).getHost()) &&
					this.port == ((Address) other).getPort() &&
					this.topic.equals(((Address) other).getTopic());
		} else return false;
	}

}
