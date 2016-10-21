package com.incquerylabs.iot.leapmotion.viatracep.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.proto.ComplexGestures.ComplexGesture;
import com.incquerylabs.iot.leapmotion.proto.ComplexGestures.ComplexGesture.Type;
import com.incquerylabs.iot.leapmotion.proto.GesturePublisher;

public class GestureUtils {

	private GestureUtils() {
		initializeDurations();
	}

	private Map<ComplexGesture.Type, ComplexGesture> latestGestures = new HashMap<>();
	
	private Map<ComplexGesture.Type, Long> gestureDurationsMs = new HashMap<>();
	
	private void initializeDurations() {
		gestureDurationsMs.put(Type.TYPE_CIRCLE, new Long(500));
		gestureDurationsMs.put(Type.TYPE_GRAB, new Long(2000));
		gestureDurationsMs.put(Type.TYPE_KEY_TAP, new Long(1000));
		gestureDurationsMs.put(Type.TYPE_SCREEN_TAP, new Long(1000));
		gestureDurationsMs.put(Type.TYPE_SWIPE, new Long(1000));
		gestureDurationsMs.put(Type.TYPE_SLOW_DOWN, new Long(500));
		gestureDurationsMs.put(Type.TYPE_SPEED_UP, new Long(500));
	}
	
	public final static GestureUtils INSTANCE = new GestureUtils();
	public static GestureUtils getInstance() {
		return INSTANCE;
	}
	
	public void processGesture(ComplexGesture.Type type, long timestamp, int id) throws IOException, PoolNotInitializedException {
		ComplexGesture gesture = ComplexGesture.newBuilder()
				.setType(type)
				.setTimestamp(timestamp)
				.setValid(true)
				.setId(id)
				.buildPartial();
		if(!latestGestures.containsKey(type))
			acceptGesture(gesture);
		else {
			long latestGestureTimestamp = latestGestures.get(type).getTimestamp();
			if(Math.abs(timestamp - latestGestureTimestamp) > gestureDurationsMs.get(type))
				acceptGesture(gesture);
		}
	}
	
	private void acceptGesture(ComplexGesture gesture) throws IOException, PoolNotInitializedException {
		latestGestures.put(gesture.getType(), gesture);
		System.out.println(String.format("%s gesture accepted with id: %d", gesture.getType().name(), gesture.getId()));
		GesturePublisher.publishGesture(gesture);
	}

	
}
