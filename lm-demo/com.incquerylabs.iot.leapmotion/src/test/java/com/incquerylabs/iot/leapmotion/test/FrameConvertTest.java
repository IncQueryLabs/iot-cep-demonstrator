package com.incquerylabs.iot.leapmotion.test;

import org.junit.Before;
import org.junit.Test;

import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;
import com.incquerylabs.iot.leapmotion.transform.Leap2ProtoConverter;

public class FrameConvertTest {
	
	Frame converted;
	
	com.leapmotion.leap.Frame source;
	
	@Before
	public void setUp() {
		source = new com.leapmotion.leap.Frame();
	}
	
	@Test
	public void tearDown() {
		converted = Leap2ProtoConverter.convert(new com.leapmotion.leap.Frame());
	}
	
}
