package com.incquerylabs.iot.communication;

public class YellowPages {

	public static IAddress getFrameStreamAddress() {
		return new Address("192.168.1.148", 5555, "framestream");
	}
	
	public static IAddress getGesturesStreamAddress() {
		return new Address("192.168.1.148", 5556, "gesturesstream");
	}
	
}