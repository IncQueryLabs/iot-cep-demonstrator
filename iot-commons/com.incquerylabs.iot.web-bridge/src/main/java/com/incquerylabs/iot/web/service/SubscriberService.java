package com.incquerylabs.iot.web.service;

import java.io.IOException;
import java.util.Iterator;

import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Singleton;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.util.JsonFormat;
import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.ISubscriberCallback;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.web.WSEndpoint;

@Singleton
@ManagedService(path = "/ws/2/{ehost}/{eport: [1-9][0-9][0-9]*}/{etopic}")
public class SubscriberService extends WebBridgeService implements ISubscriberCallback {
	
	public SubscriberService() throws IOException {
		super();
		endpointType = 2;
		builders.getAllAddress().forEach(address -> {
			if(address instanceof WSEndpoint) {
				if(((WSEndpoint) address).getTypeId() == 2) {
					try {
						SubscriberPool.getInstance().registerCallback(address, this);
					} catch (PoolNotInitializedException e) {
						System.err.print("Subscriber pool not initialized!");
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	 * Invoked when message arrived from communication endpoint.
	 */
	@Override
	public void messageArrived(IAddress address, byte[] data) {
		if (address instanceof WSEndpoint) {
			Iterator<Builder> bIt = builders.getBuildersForAddress(address).iterator();
			boolean validProtocol = false;
			String messageAsJson = "";
			while (bIt.hasNext() && !validProtocol) {
				try {
					messageAsJson = JsonFormat.printer().print(bIt.next().clear().mergeFrom(data).build());
					validProtocol = true;
				} catch (InvalidProtocolBufferException e) {
					e.printStackTrace();
				}
			}
			if (validProtocol)
				metaBroadcaster.broadcastTo(((WSEndpoint) address).toEndpointPath(), messageAsJson);
			else
				System.err.println(
						"Unable to forward message over websocket: could not find appropriate builder for message on endpoint: "
								+ address.getFullAddress());
		}
	}
	
}
