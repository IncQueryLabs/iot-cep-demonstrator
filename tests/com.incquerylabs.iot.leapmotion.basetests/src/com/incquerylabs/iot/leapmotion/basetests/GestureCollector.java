package com.incquerylabs.iot.leapmotion.basetests;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.proto.AbstractProtoGestureStream;
import com.incquerylabs.iot.leapmotion.proto.ComplexGestures.ComplexGesture;

public class GestureCollector extends AbstractProtoGestureStream {
	
	List<ComplexGesture> gestures;
	
	public GestureCollector(IAddress sourceAddress) {
		super(sourceAddress);
		gestures = new LinkedList<>();
	}

	@Override
	public void processGesture(ComplexGesture gesture) {
		gestures.add(gesture);
	}
	
	public Collection<ComplexGesture> getGestures() {
		return gestures;
	}

	@Override
	public void stop() throws PoolNotInitializedException {
		super.stop();
		gestures.clear();
	}
	
	
}
