package com.incquerylabs.iot.leapmotion;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.incquerylabs.iot.communication.Address;
import com.incquerylabs.iot.leapmotion.frame.FrameStreamer;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;

public class FrameStreamerTest {
	
	FrameStreamer streamer;
	
	Controller controller;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException {
		streamer = new FrameStreamer("/tmp/leaptmp/stream_1470835280953.lmstream", new Address("*", 5556, "leapframes"));
		controller = new Controller();
	}
	
	@Test
	public void test() throws IOException {
		while(streamer.hasNextFrame()) {
			Frame frame = streamer.readNext();
			System.out.println(String.format("Readed frame (id / timestamp): %d / %d", frame.id(), frame.timestamp()));
		}
	}
	
	@After
	public void tearDown() {
		controller.delete();
	}
	
}
