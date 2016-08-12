package com.incquerylabs.iot.leapmotion;

import java.io.IOException;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;
import com.incquerylabs.iot.leapmotion.zmq.ZmqFramePublisher;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;

public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		// XXX. read from args
		String workingDirectory = String.format("%s/leapmotion", System.getProperty("user.dir"));
		
		String streampath = String.format("%s/stream_%d.lmstream", workingDirectory, System.currentTimeMillis());
		
		Controller controller = new Controller();
		
		controller.frame(30);
		
		PublisherPool.initializePool(new ZMQFactory());
		
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		
		ZmqFramePublisher framePublisher = new ZmqFramePublisher(YellowPages.getFrameStreamAddress());
		
//		FrameRecorder recorder = new FrameRecorder(streampath);
		
		controller.addListener(framePublisher);
//		controller.addListener(recorder);
		
		Thread.currentThread();
		while(!Thread.interrupted()) {
			Thread.sleep(1000);
		}
		
		controller.delete();
	}

}
