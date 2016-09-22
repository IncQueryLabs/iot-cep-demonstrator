package com.incquerylabs.iot.leapmotion.zmq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.YellowPages;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;

import static com.incquerylabs.iot.leapmotion.transform.Leap2ProtoConverter.*;

public class FramePublisher extends Listener {
	
	int frametimemicros = 0;
	
	long nextframetimemicros = -1;
	
	boolean freerun = true;
	
	public FramePublisher(int fps) {
		if(fps > 0 && fps < 150) {
			frametimemicros = 1000000 / fps;
			freerun = false;
		} else 
			freerun = true;
	}
	
	public FramePublisher() {
		this(-1);
		freerun = true;
	}
	
	@Override
	public void onFrame(Controller controller) {
		try {
			com.leapmotion.leap.Frame frame = controller.frame();
			if(frame.timestamp() >= nextframetimemicros || freerun) {
				processFrame(frame);
				nextframetimemicros = frame.timestamp() + frametimemicros;
			}
		} catch (Exception e) {
			System.err.println(String.format("Exception during frame publishing: %s", e.getMessage()));
		}
	}
	
	public void processFrame(com.leapmotion.leap.Frame frame) throws PoolNotInitializedException, IOException {
		if(frame.isValid() && !frame.hands().isEmpty()) {
			publishFrame(frame);
		}
	}
	
	public static void publishFrame(com.leapmotion.leap.Frame frame) throws PoolNotInitializedException, IOException {
		Frame converted = convert(frame);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		converted.writeTo(stream);
		PublisherPool.getInstance().next(YellowPages.getFrameStreamAddress()).publish(stream.toByteArray(), 0);
		stream.close();
	}
	
}
