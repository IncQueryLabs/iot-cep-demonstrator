package com.incquerylabs.iot.leapmotion.proto;

import java.io.ByteArrayInputStream;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.leapmotion.proto.ComplexGestures.ComplexGesture;
import com.incquerylabs.iot.processor.AbstractProcessorComponent;
import com.incquerylabs.iot.processor.IDataProcessor;

public abstract class AbstractProtoGestureStream extends AbstractProcessorComponent implements IDataProcessor {

	public AbstractProtoGestureStream(IAddress sourceAddress) {
		super(sourceAddress);
		setProcessor(this);
	}

	@Override
	public void process(byte[] data) throws Exception {
		ByteArrayInputStream stream = new ByteArrayInputStream(data);
		ComplexGesture gesture = ComplexGesture.parseFrom(data);
		stream.close();
		stream = null;
		processGesture(gesture);
	}
	
	abstract public void processGesture(ComplexGesture gesture);

}
