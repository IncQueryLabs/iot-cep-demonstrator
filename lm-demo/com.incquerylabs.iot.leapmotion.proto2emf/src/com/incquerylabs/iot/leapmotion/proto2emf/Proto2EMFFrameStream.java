package com.incquerylabs.iot.leapmotion.proto2emf;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.leapmotion.proto.AbstractProtoFrameStream;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;

public class Proto2EMFFrameStream extends AbstractProtoFrameStream {

	private ILeapModelUpdater updater;
	
	public Proto2EMFFrameStream(IAddress sourceAddress, ILeapModelUpdater updater) {
		super(sourceAddress);
		this.updater = updater;
	}

	@Override
	public void processFrame(Frame frame) {
		updater.updateModel(frame);
	}

}
