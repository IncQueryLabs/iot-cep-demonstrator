package com.incquerylabs.iot.leapmotion.visualizer;

import org.atmosphere.container.Jetty9AsyncSupportWithWebSocket;
import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.AtmosphereServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;
import com.incquerylabs.iot.web.proto.MessageBuilderService;

public class Main {

	public static void main(String[] args) throws Exception {
		SubscriberPool.initializePool(new ZMQFactory());
		PublisherPool.initializePool(new ZMQFactory());
		Main main = new Main();
		main.run();
	}

	private void run() throws Exception {
		Server server = new Server();
		MessageBuilderService mbSrv = new MessageBuilderService();
		mbSrv.load();
		
		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory());
		http.setPort(8080);
		http.setIdleTimeout(30000);

		server.setConnectors(new Connector[] { http });
		
		ServletHolder atmosphereServletHolder = new ServletHolder(AtmosphereServlet.class);
		atmosphereServletHolder.setInitParameter(ApplicationConfig.ANNOTATION_PACKAGE, "com.incquerylabs.iot.web.service");
		atmosphereServletHolder.setInitParameter(ApplicationConfig.WEBSOCKET_CONTENT_TYPE, "application/json");
		atmosphereServletHolder.setInitParameter(ApplicationConfig.PROPERTY_COMET_SUPPORT, Jetty9AsyncSupportWithWebSocket.class.getName());

		atmosphereServletHolder.setAsyncSupported(true);
		
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/");
		servletContextHandler.addServlet(atmosphereServletHolder, "/ws/*");
		
		ResourceHandler cpr = new ResourceHandler();
		cpr.setBaseResource(Resource.newClassPathResource("/"));
		
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { cpr, servletContextHandler });

		server.setHandler(handlers);
		server.setStopAtShutdown(true);
		server.start();
		server.join();
	}

}
