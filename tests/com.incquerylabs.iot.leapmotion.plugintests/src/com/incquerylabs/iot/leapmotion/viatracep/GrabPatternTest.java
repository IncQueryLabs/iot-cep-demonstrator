package com.incquerylabs.iot.leapmotion.viatracep;

import org.junit.Before;
import org.junit.Test;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;

public class GrabPatternTest {
	
	@Before
	public void setUp() {
		PublisherPool.initializePool(new ZMQFactory());
		SubscriberPool.initializePool(new ZMQFactory());
	}
	
	@Test
	public void test() {
		
	}
	
}
