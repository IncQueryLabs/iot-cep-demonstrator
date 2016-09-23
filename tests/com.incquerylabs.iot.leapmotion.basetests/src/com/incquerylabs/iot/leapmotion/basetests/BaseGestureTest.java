package com.incquerylabs.iot.leapmotion.basetests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.YellowPages;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;
import com.incquerylabs.iot.leapmotion.frame.FrameStreamer;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Gesture;
import com.leapmotion.leap.Controller;

public abstract class BaseGestureTest {
	
	public final static String streamPathProperty = "stream.path";
	
	protected String streamPath = "";
	
	FrameStreamer streamer;
	
	protected GestureCollector collector;
	
	protected Gesture.Type expectedType;
	
	public BaseGestureTest(Gesture.Type type) {
		this.expectedType = type;
	}
	
	@Before
	public void setUp() throws FileNotFoundException, IOException {
		streamPath = System.getProperty(streamPathProperty);
		PublisherPool.initializePool(new ZMQFactory());
		SubscriberPool.initializePool(new ZMQFactory());
		
		initializeCEPComponent();
		
		streamer = new FrameStreamer(streamPath, YellowPages.getFrameStreamAddress(), 10);
		new Controller();
		
		collector = new GestureCollector(YellowPages.getGesturesStreamAddress());
	}
	
	@Test
	public void singleGestureTest() throws InterruptedException, PoolNotInitializedException {
		System.out.println(this.getClass().getSimpleName() + " gesture test started!");
		collector.start();
		startCepComponent();
		streamer.start();
		
		// Wait for finishing stream replay 
		while(streamer.hasNextFrame())
			Thread.sleep(100);
		
		assertEquals(1, collector.getGestures().size());
		assertEquals(expectedType, collector.gestures.get(0).getType());
	}
	
	@After
	public void tearDown() throws PoolNotInitializedException, IOException {
		streamer.stop();
		stopCepComponent();
		collector.stop();
		PublisherPool.getInstance().dispose();
	}
		
	protected abstract void initializeCEPComponent();
	protected abstract void startCepComponent();
	protected abstract void stopCepComponent();
		
}
