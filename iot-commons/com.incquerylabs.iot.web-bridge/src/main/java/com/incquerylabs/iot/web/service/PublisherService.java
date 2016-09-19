package com.incquerylabs.iot.web.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;

import com.google.protobuf.Message.Builder;
import com.google.protobuf.util.JsonFormat;
import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.web.WSEndpoint;

@ManagedService(path = "/ws/1/{ehost}/{eport: [1-9][0-9][0-9]*}/{etopic}")
public class PublisherService extends WebBridgeService {

	public PublisherService() throws IOException {
		super();
		endpointType = 1;
	}
	
	/**
	 * Invoked when message arrived on endpoint.
	 * 
	 */
	@Message
	public void onMessage(String message) {
		try {
			IAddress address = WSEndpoint.build(endpointType, endpointHost, endpointPort, endpointTopic);
			Iterator<Builder> bIt = builders.getBuildersForAddress(address).iterator();
			boolean validProtocol = false;
			byte[] serializedMessage = null;
			while (bIt.hasNext() && !validProtocol) {
				try {
					Builder builder = bIt.next();
					JsonFormat.parser().merge(message, builder.clear());
					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					builder.build().writeTo(bao);
					serializedMessage = bao.toByteArray();
					bao.close();
					validProtocol = true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (validProtocol && serializedMessage != null) {
				PublisherPool.getInstance().next(address).publish(serializedMessage, 0);
				System.out.println("Message published to " + address.getFullAddress() + ": \n"+ message);
			}
		} catch (PoolNotInitializedException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
