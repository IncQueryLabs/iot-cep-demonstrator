package com.incquerylabs.iot.leapmotion.drools;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;
import com.incquerylabs.iot.leapmotion.YellowPages;
import com.incquerylabs.iot.leapmotion.drools.stream.DroolsFrameStream;

public class Main {

	public static void main(String[] args) throws PoolNotInitializedException {
		SubscriberPool.initializePool(new ZMQFactory());
		PublisherPool.initializePool(new ZMQFactory());
		
		final DroolsFrameStream adapter = new DroolsFrameStream(YellowPages.getFrameStreamAddress());
		adapter.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					adapter.stop();
				} catch (PoolNotInitializedException e) {
					System.err.println(String.format("Pool not initialized: %s", e.getMessage()));
				}
			}
		});
	}

}
