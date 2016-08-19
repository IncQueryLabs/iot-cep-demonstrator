package com.incquerylabs.iot.leapmotion.zmq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.YellowPages;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;
import com.incquerylabs.iot.leapmotion.transform.Leap2ProtoConverter;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;

public class FramePublisher extends Listener {
	
	@Override
	public void onFrame(Controller controller) {
		try {
			com.leapmotion.leap.Frame frame = controller.frame();
			if(frame.isValid() && !frame.hands().isEmpty()) {
				publishFrame(frame);
			}
		} catch (Exception e) {
			// TODO: logging
			e.printStackTrace();
		}
	}
	
	public static void publishFrame(com.leapmotion.leap.Frame frame) throws PoolNotInitializedException, IOException {
		Frame converted = Leap2ProtoConverter.convert(frame);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		converted.writeTo(stream);
		System.out.println("Publisher frame size (in byte): " + stream.size());
		PublisherPool.getInstance().next(YellowPages.getFrameStreamAddress()).publish(stream.toByteArray(), 0);
		stream.close();
	}
	
}
