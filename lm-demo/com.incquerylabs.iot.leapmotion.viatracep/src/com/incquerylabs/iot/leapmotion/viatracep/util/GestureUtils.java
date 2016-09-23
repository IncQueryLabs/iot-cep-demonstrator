package com.incquerylabs.iot.leapmotion.viatracep.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.proto.GesturePublisher;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Gesture;

public class GestureUtils {

	private GestureUtils() {
	}

	private Map<Gesture.Type, Gesture> latestGestures = new HashMap<>();

	public final static GestureUtils INSTANCE = new GestureUtils();
	public static GestureUtils getInstance() {
		return INSTANCE;
	}
	public void performGesture(Gesture.Type type, long timestamp) throws IOException, PoolNotInitializedException {
		Gesture gesture = Gesture.newBuilder()
				.setType(type)
				.setTimestamp(timestamp)
				.setValid(true)
				.setId(timestamp)
				.buildPartial();
		if(!latestGestures.containsKey(type))
			acceptGesture(gesture);
		else {
			long latestGestureTimestamp = latestGestures.get(type).getTimestamp();
			if(Math.abs(timestamp - latestGestureTimestamp) > 2000)
				acceptGesture(gesture);
		}			
	}

	private void acceptGesture(Gesture gesture) throws IOException, PoolNotInitializedException {
		latestGestures.put(gesture.getType(), gesture);
		System.out.println("Gesture accepted: " + gesture.getTimestamp());
		GesturePublisher.publishGesture(gesture);
	}
}
