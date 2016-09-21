package com.incquerylabs.iot.communication;

public class YellowPages {

	public static IAddress getFrameStreamAddress() {
		return new Address("127.0.0.1", 5555, "framestream");
	}
	
	public static IAddress getGesturesStreamAddress() {
		return new Address("127.0.0.1", 5556, "gesturesstream");
	}
	
}