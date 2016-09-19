package com.incquerylabs.iot.web.service;

import java.io.IOException;

import javax.inject.Inject;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.PathParam;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.MetaBroadcaster;

import com.incquerylabs.iot.web.proto.MessageBuilderService;

abstract public class WebBridgeService {

	@Inject
	protected MetaBroadcaster metaBroadcaster;
	
    @Inject
    private AtmosphereResource r;
    
	protected MessageBuilderService builders;

	protected int endpointType;

	public WebBridgeService() throws IOException {
		System.out.println("Web bridge service started!");
		builders = new MessageBuilderService();
		builders.load();
	}
	
	@PathParam("ehost")
	protected String endpointHost;

	@PathParam("eport")
	protected String endpointPort;

	@PathParam("etopic")
	protected String endpointTopic;
	
	@Ready
	public void onReady() {
		System.out.println("Atmosphere resource connected: " + r.uuid());
	}
	
	@Disconnect
	public void onDisconnect() {
		System.out.println("Atmosphere resource disconnected: " + r.uuid());
	}

}
