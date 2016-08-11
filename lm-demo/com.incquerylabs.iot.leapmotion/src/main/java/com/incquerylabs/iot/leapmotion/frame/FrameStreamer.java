package com.incquerylabs.iot.leapmotion.frame;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.PublisherPool;
import com.leapmotion.leap.Frame;

public class FrameStreamer implements Runnable {
	
	DataInputStream inputStream;
	
	IAddress target;
	
	private boolean reachedStreamEnd = false;
	
	private volatile boolean autostream;
	
	Frame nextFrame;
	
	public FrameStreamer(String inputfilepath, IAddress target) throws FileNotFoundException, IOException {
		this.target = target;
		inputStream = new DataInputStream(new FileInputStream(inputfilepath));
		reachedStreamEnd = false;
	}
	
	public Frame readNext() throws IOException {
		
		Frame frame = new Frame();
		
		byte[] data = null;
		
		try {
			int size = inputStream.readInt();
			data = new byte[size];
			inputStream.read(data);
		} catch(EOFException eof) {
			reachedStreamEnd = true;
		} finally {
			if(data!=null)
				frame.deserialize(data);
		}
		
		return frame;
	}
	
	public void reset() throws IOException {
		if(inputStream != null)
			inputStream.reset();
	}
	
	public boolean hasNextFrame() {
		return !reachedStreamEnd;
	}

	@Override
	public void run() {
		while(autostream) {
			try {
				if(nextFrame == null) {	
					nextFrame = readNext();
				}

				PublisherPool.getInstance().next(target).publish(nextFrame.serialize(), 0);
				
			} catch(Exception e) {
				System.err.println(e);
			}
		}
	}
	
	public void step() {
		
	}
	
	public void pause() {
		
	}
	
}
