package com.incquerylabs.iot.leapmotion.frame;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.zmq.FramePublisher;
import com.leapmotion.leap.Frame;

public class FrameStreamer implements Runnable {

	DataInputStream inputStream;

	IAddress target;

	private volatile boolean reachedStreamEnd = false;

	private volatile boolean autostream;

	Frame nextFrame;

	Stopwatch stopWatch;
	
	ExecutorService executor = Executors.newCachedThreadPool();
	
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
		} catch (EOFException eof) {
			reachedStreamEnd = true;
		} finally {
			if (data != null)
				frame.deserialize(data);
		}

		return frame;
	}

	public void reset() throws IOException {
		if (inputStream != null)
			inputStream.reset();
		reachedStreamEnd = false;
		autostream = false;
	}

	public boolean hasNextFrame() {
		return !reachedStreamEnd;
	}

	@Override
	public void run() {
		try {
			autostream = true;
			long currentTimestamp = Long.MAX_VALUE;
			stopWatch = Stopwatch.createStarted();
			
			// Read first
			if (nextFrame == null)
				nextFrame = readNext();
			
			while (autostream && !reachedStreamEnd) {

				long delay = nextFrame.timestamp() - currentTimestamp - stopWatch.elapsed(TimeUnit.MICROSECONDS);
				if (delay > 0)
					Thread.sleep(delay / 1000);

				stopWatch.reset();
				FramePublisher.publishFrame(nextFrame);
				currentTimestamp = nextFrame.timestamp();
				nextFrame = readNext();
			}
		} catch (Exception e) {
			System.err.println(String.format("An error occured during replaying frame stream: %s", e.getMessage()));
		}
	}
	
	public void start() {
		executor.execute(this);
	}
	
	public void step() throws PoolNotInitializedException, IOException {
		if(nextFrame == null && hasNextFrame())
			nextFrame = readNext();
		FramePublisher.publishFrame(nextFrame);
		nextFrame = readNext();
	}

	public void pause() {
		autostream = false;
	}

}
