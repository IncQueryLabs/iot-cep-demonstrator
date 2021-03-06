package com.incquerylabs.iot.leapmotion.proto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.YellowPages;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.proto.ComplexGestures.ComplexGesture;

public class GesturePublisher {
	
	public static void publishGesture(ComplexGesture gesture) throws IOException, PoolNotInitializedException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		gesture.writeTo(stream);
		PublisherPool.getInstance().next(YellowPages.INSTANCE.getGesturesStreamAddress()).publish(stream.toByteArray(), 0);
		stream.close();
	}
	
}
