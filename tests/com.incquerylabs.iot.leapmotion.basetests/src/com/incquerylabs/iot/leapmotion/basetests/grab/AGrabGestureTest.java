package com.incquerylabs.iot.leapmotion.basetests.grab;

import com.incquerylabs.iot.leapmotion.basetests.BaseGestureTest;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Gesture;

public abstract class AGrabGestureTest extends BaseGestureTest {
	
	public AGrabGestureTest() {
		super(Gesture.Type.TYPE_GRAB);
	}

}
