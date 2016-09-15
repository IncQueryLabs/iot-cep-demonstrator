package com.incquerylabs.iot.web.service;

import java.io.IOException;

import javax.inject.Inject;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.Heartbeat;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.PathParam;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceFactory;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.MetaBroadcaster;

@ManagedService(path = "/ws/{etype}/{ehost}/{eport: [1-9][0-9][0-9]*}/{etopic}")
public class WebBridgeService {
	
	public WebBridgeService() {
		System.out.println("Web bridge service started!");
	}
	
    @Inject
    private BroadcasterFactory factory;
    
    @Inject
    private Broadcaster broadcaster;
    
    @Inject
    private MetaBroadcaster metaBroadcaster;

    @Inject
    private AtmosphereResourceEvent event;
    
    @Inject
    private AtmosphereResourceFactory resourceFactory;
    
    @Inject
    private AtmosphereResource r;
    
    @PathParam("ehost")
    private String endpointHost;
    
    @PathParam("eport")
    private String endpointPort;
    
    @PathParam("etype")
    private String endpointType;
    
    @PathParam("etopic")
    private String endpointTopic;
    
    @Heartbeat
    public void onHeartbeat(final AtmosphereResourceEvent event) {
        System.out.println(String.format("Heartbeat send by %s", event.getResource()));
    }
    
    public void broadcast(String path, byte[] message) {
    }
    
    /**
     * Invoked when the connection has been fully established and suspended, that is, ready for receiving messages.
     */
    @Ready
    public void onReady(/* In you don't want injection AtmosphereResource r */) {
        System.out.println("On ready: " + r.uuid());
        System.out.println("BroadcasterFactory used " + factory.getClass().getName());
        System.out.println("Broadcaster injected {}" + broadcaster.getID());
    }

    /**
     * Invoked when the client disconnects or when the underlying connection is closed unexpectedly.
     *
     */
    @Disconnect
    public void onDisconnect(/** If you don't want to use injection AtmosphereResourceEvent event*/) {
        if (event.isCancelled()) {
            System.out.println("Browser {} unexpectedly disconnected" + event.getResource().uuid());
        } else if (event.isClosedByClient()) {
            System.out.println("Browser {} closed the connection" + event.getResource().uuid());
        }
    }

    /**
     * Simple annotated class that demonstrate how {@link org.atmosphere.config.managed.Encoder} and {@link org.atmosphere.config.managed.Decoder
     * can be used.
     *
     * @param message an instance of {@link Message}
     * @return
     * @throws IOException
     */
    @Message
    public String onMessage(String message) throws IOException {
        System.out.println("Message arrived from browser: " + message + " on " + endpointType + " socket " + endpointHost + ":"+ endpointPort + "/" + endpointTopic  );
        return message;
    }

    
}
