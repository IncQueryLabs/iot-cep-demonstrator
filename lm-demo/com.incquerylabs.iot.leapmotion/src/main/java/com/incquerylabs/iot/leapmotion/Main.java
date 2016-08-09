package com.incquerylabs.iot.leapmotion;

import com.incquerylabs.iot.communication.Address;
import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;
import com.incquerylabs.iot.leapmotion.zmq.ZmqFramePublisher;
import com.leapmotion.leap.Controller;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		// XXX:
		
		Controller controller = new Controller();
		PublisherPool.initializePool(new ZMQFactory());
		
		IAddress address = new Address("*", 5555, "leapframes");
		
		controller.addListener(new ZmqFramePublisher(address));
		
		Thread.currentThread();
		while(!Thread.interrupted()) {
			Thread.sleep(1000);
		}
		
		controller.delete();
	}

}
