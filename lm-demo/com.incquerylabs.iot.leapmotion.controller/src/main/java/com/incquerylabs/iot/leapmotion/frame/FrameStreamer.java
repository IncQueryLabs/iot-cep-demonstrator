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

	DataInputStream inputStream = null;

	IAddress target;

	private volatile boolean reachedStreamEnd = false;

	private volatile boolean autostream;

	Frame nextFrame = null;

	Stopwatch stopWatch;
	
	ExecutorService executor = Executors.newCachedThreadPool();

	private boolean freerun = true;

	private int frametimemicros = -1;
	
	private long nextframetimemicros = -1;
	
	private String inputfilepath = "";
	
	public FrameStreamer(String inputfilepath, IAddress target, int fps) throws FileNotFoundException, IOException {
		this.inputfilepath = inputfilepath;
		this.target = target;
		if(fps > 0 && fps < 150) {
			frametimemicros  = 1000000 / fps;
			freerun = false;
		} else 
			freerun = true;
		reset();
	}
	
	public FrameStreamer(String inputfilepath, IAddress target) throws FileNotFoundException, IOException {
		this(inputfilepath, target, -1);
	}
	
	public Frame readNext() throws IOException {

		Frame frame = null;
		byte[] data = null;

		try {
			int size = inputStream.readInt();
			long timestamp = inputStream.readLong();
			while(!freerun && timestamp <= nextframetimemicros) {
				inputStream.skipBytes(size);
				size = inputStream.readInt();
				timestamp = inputStream.readLong();
			}
			nextframetimemicros = timestamp + frametimemicros;
			data = new byte[size];
			inputStream.read(data);
		} catch (EOFException eof) {
			reachedStreamEnd = true;
			System.out.println("Reached the end of the frame stream!");
		} finally {
			if (data != null) {
				frame = new Frame();
				frame.deserialize(data);
			}
		}

		return frame;
	}
	
	public void reset() throws IOException {
		if(inputStream != null)
			inputStream.close();
		inputStream = new DataInputStream(new FileInputStream(inputfilepath));
		reachedStreamEnd = false;
		autostream = false;
		nextframetimemicros = -1;
		nextFrame = null;
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
	
	public void stop() throws IOException {
		if(inputStream!=null)
			inputStream.close();
		autostream = false;
	}
}
