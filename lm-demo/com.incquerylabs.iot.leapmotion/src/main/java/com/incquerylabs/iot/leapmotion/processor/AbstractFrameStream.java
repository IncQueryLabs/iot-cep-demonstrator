package com.incquerylabs.iot.leapmotion.processor;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.processor.AbstractProcessorComponent;
import com.incquerylabs.iot.processor.IDataProcessor;
import com.leapmotion.leap.Frame;

public abstract class AbstractFrameStream extends AbstractProcessorComponent implements IDataProcessor {

	public AbstractFrameStream(IAddress sourceAddress) {
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
