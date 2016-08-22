package com.incquerylabs.iot.leapmotion.proto2emf;

import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;

public interface ILeapModelUpdater {
	
	public void updateModel(Frame frame);

}
