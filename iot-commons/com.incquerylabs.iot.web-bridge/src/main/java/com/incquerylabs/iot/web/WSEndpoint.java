package com.incquerylabs.iot.web;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.incquerylabs.iot.communication.Address;

public class WSEndpoint extends Address {
	
	protected int typeId;
	
	public static final String SEPARATOR = "/";
	
	public WSEndpoint(int typeId, String host, int port, String topic) {
		super(host, port, topic);
		this.typeId = typeId;
	}

	public int getTypeId() {
		return typeId;
	}
	
	public String toEndpointPath() {
		return String.format("/ws/%d/%s/%d/%s", typeId, host, port, topic );
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof WSEndpoint) {
			return super.equals(other) &&
				this.typeId == ((WSEndpoint) other).getTypeId();
		} else return false;
				
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				.append(typeId)
				.append(host)
				.append(port)
				.append(topic)
				.toHashCode();
	}
	
	public static WSEndpoint build(String type, String host, String port, String topic) {
		return build(Integer.parseInt(type), host, port, topic);
	}
	
	public static WSEndpoint build(int type, String host, String port, String topic) {
		return new WSEndpoint(type, host, Integer.parseInt(port), topic);
	}
	
	public static WSEndpoint build(String path) {
		String[] params = path.split(SEPARATOR);
		return new WSEndpoint(Integer.parseInt(params[0]), params[1], Integer.parseInt(params[2]), params[3]);
	}

}
