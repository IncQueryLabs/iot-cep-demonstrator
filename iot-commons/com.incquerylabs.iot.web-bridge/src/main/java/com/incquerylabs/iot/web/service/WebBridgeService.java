package com.incquerylabs.iot.web.service;

import java.io.IOException;

import javax.inject.Inject;

import org.atmosphere.config.service.PathParam;
import org.atmosphere.cpr.MetaBroadcaster;

import com.incquerylabs.iot.web.proto.MessageBuilderService;

abstract public class WebBridgeService {

	@Inject
	protected MetaBroadcaster metaBroadcaster;

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

}
