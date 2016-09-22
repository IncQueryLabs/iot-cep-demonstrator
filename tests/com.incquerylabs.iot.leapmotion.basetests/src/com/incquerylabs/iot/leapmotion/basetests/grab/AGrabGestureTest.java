package com.incquerylabs.iot.leapmotion.basetests.grab;

import com.incquerylabs.iot.communication.YellowPages;
import com.incquerylabs.iot.leapmotion.basetests.BaseGestureTest;
import com.incquerylabs.iot.leapmotion.basetests.GestureCollector;

import static org.junit.Assert.*;

public abstract class AGrabGestureTest extends BaseGestureTest {
	
	GestureCollector collector = new GestureCollector(YellowPages.getGesturesStreamAddress());
	
	@Override
	protected void performAssertions() {
		assertEquals(1, collector.getGestures().size());
		// TODO: add further assertions
	}

}
