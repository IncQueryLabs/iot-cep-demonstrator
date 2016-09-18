package com.incquerylabs.iot.communication;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class Address implements IAddress {
	
	protected String host;
	protected int port;
	protected String topic;
	
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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				.append(host)
				.append(port)
				.append(topic)
				.toHashCode();
	}
	
}
