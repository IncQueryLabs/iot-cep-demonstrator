package com.incquerylabs.iot.leapmotion.drools;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.YellowPages;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;
import com.incquerylabs.iot.leapmotion.drools.stream.DroolsFrameStream;
import com.incquerylabs.iot.leapmotion.frame.FrameStreamer;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;
import com.leapmotion.leap.Controller;

public class GrabPatternTest {
	
	private String testStreamPath = "../../lm-demo/test_recordings/grab.lmstream";
	
	DroolsFrameStream drools;
	
	FrameStreamer streamer;
	
	List<Frame> output;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException {
		PublisherPool.initializePool(new ZMQFactory());
		SubscriberPool.initializePool(new ZMQFactory());
		output = new ArrayList<Frame>();
		drools = new DroolsFrameStream(YellowPages.getFrameStreamAddress(), output);
		streamer = new FrameStreamer(testStreamPath, YellowPages.getFrameStreamAddress(), 10);
		new Controller();
	}
	
	@Test
	public void testGrabRecognition() throws PoolNotInitializedException, IOException, InterruptedException {
		drools.start();
		streamer.start();
		
		// Wait for finishing stream replay 
		while(streamer.hasNextFrame())
			Thread.sleep(100);
		
		assertEquals(1, output.size());
	}
	
	@After
	public void tearDown() throws IOException, PoolNotInitializedException {
		streamer.stop();
		drools.stop();
		output.clear();
	}
}
