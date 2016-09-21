package com.incquerylabs.iot.leapmotion.proto;

import java.io.ByteArrayInputStream;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;
import com.incquerylabs.iot.processor.AbstractProcessorComponent;
import com.incquerylabs.iot.processor.IDataProcessor;

public abstract class AbstractProtoFrameStream extends AbstractProcessorComponent implements IDataProcessor {

	public AbstractProtoFrameStream(IAddress sourceAddress) {
		super(sourceAddress);
		setProcessor(this);
	}

	@Override
	public void process(byte[] data) throws Exception {
		ByteArrayInputStream stream = new ByteArrayInputStream(data);
		Frame frame = Frame.parseFrom(data);
		stream.close();
		stream = null;
		processFrame(frame);		
	}
	
	abstract public void processFrame(Frame frame);
	
}
