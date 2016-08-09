package com.incquerylabs.iot.leapmotion.zmq;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

public class ZmqFramePublisher extends Listener {
	
	private IAddress address;
	
	public ZmqFramePublisher(IAddress address) {
		this.address = address;
	}
	
	@Override
	public void onFrame(Controller controller) {
		try {
			Frame frame = controller.frame();
			PublisherPool.getInstance().next(address).publish(frame.serialize(), 0);
			System.out.println(frame.currentFramesPerSecond());
		} catch (PoolNotInitializedException e) {
			// TODO: logging
			e.printStackTrace();
		}
	}
	
}
