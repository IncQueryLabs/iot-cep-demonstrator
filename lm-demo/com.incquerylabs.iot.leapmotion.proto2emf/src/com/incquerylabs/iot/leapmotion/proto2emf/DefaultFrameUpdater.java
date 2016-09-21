package com.incquerylabs.iot.leapmotion.proto2emf;

import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;

public class DefaultFrameUpdater implements ILeapModelUpdater {
	
	private com.incquerylabs.iot.leapmotion.lmemf.Frame currentFrame;
	
	public DefaultFrameUpdater(com.incquerylabs.iot.leapmotion.lmemf.Frame currentFrame) {
		this.currentFrame = currentFrame;
	}
	
	@Override
	public void updateModel(Frame frame) {
		
		
	}

}
