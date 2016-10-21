package com.incquerylabs.iot.leapmotion.basetests.grab;

import com.incquerylabs.iot.leapmotion.basetests.BaseGestureTest;
import com.incquerylabs.iot.leapmotion.proto.ComplexGestures.ComplexGesture;

public abstract class AGrabGestureTest extends BaseGestureTest {
	
	public AGrabGestureTest() {
		super(ComplexGesture.Type.TYPE_GRAB);
	}

}
