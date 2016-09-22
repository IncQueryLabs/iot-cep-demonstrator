package com.incquerylabs.iot.leapmotion.processor;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.processor.AbstractProcessorComponent;
import com.incquerylabs.iot.processor.IDataProcessor;
import com.leapmotion.leap.Frame;

@Deprecated
public abstract class AbstractLeapFrameStream extends AbstractProcessorComponent implements IDataProcessor {

	public AbstractLeapFrameStream(IAddress sourceAddress) {
		super(sourceAddress);
		setProcessor(this);
	}

	@Override
	public void process(byte[] data) {
		Frame frame = new Frame();
		frame.deserialize(data);
		processFrame(frame);
	}
	
	abstract public void processFrame(Frame frame);
	
}
