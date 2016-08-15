package com.incquerylabs.iot.leapmotion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.cli.CommandOptions.Commands;
import com.incquerylabs.iot.leapmotion.frame.FrameRecorder;
import com.incquerylabs.iot.leapmotion.frame.FrameStreamer;
import com.incquerylabs.iot.leapmotion.frame.FrameUtils;
import com.incquerylabs.iot.leapmotion.zmq.ZmqFramePublisher;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture.Type;

public class LeapMotionApplication extends Controller {
		
	private ZmqFramePublisher framePublisher = null;
	
	private FrameRecorder frameRecorder = null;
	
	private FrameStreamer frameStreamer = null;
	
	public static enum MODE {
		RECORD,
		REPLAY,
		DEFAULT
	}
	
	public LeapMotionApplication(MODE mode, String filepath) throws IOException {

		enableGesture(Type.TYPE_CIRCLE);
		enableGesture(Type.TYPE_SWIPE);
		enableGesture(Type.TYPE_KEY_TAP);
		enableGesture(Type.TYPE_SCREEN_TAP);
		enableGesture(Type.TYPE_INVALID);
		
		switch(mode) {
			case RECORD:
				framePublisher = new ZmqFramePublisher(YellowPages.getFrameStreamAddress());
				frameRecorder = new FrameRecorder(prepareStreamFile(filepath));
				break;
			case REPLAY:
				frameStreamer = new FrameStreamer(prepareStreamFile(filepath), YellowPages.getFrameStreamAddress());
				break;
			case DEFAULT:
				framePublisher = new ZmqFramePublisher(YellowPages.getFrameStreamAddress());
		}
	}
	
	public LeapMotionApplication() throws IOException {
		this(MODE.DEFAULT, "");
	}
	
	private String prepareStreamFile(String streampath) throws IOException {
		File streamfile = new File(streampath);
		if(streamfile.exists() && streamfile.isDirectory()) {
			streampath += String.format("stream_%d%s", System.currentTimeMillis(), FrameUtils.FRAME_STREAM_EXTENSION);
		} else if(!streampath.endsWith(FrameUtils.FRAME_STREAM_EXTENSION)) {
			streampath += FrameUtils.FRAME_STREAM_EXTENSION;
		}
		streamfile = new File(streampath);
		Files.createDirectories(streamfile.getParentFile().toPath());
		if(!streamfile.exists())
			Files.createFile(streamfile.toPath());
		return streampath;
	}
	
	public void performCommand(Commands command) throws PoolNotInitializedException, IOException {
		switch(command) {
			case REPLAY:
				frameStreamer.start();
				break;
			case STEP:
				frameStreamer.step();
				break;
			case PAUSE:
				frameStreamer.pause();
				break;
			case RESET:
				frameStreamer.reset();
				break;
			default:
		}
	}
	
	public void start() {
		// Register leap motion listeners
		if(framePublisher != null)
			addListener(framePublisher);
		if(frameRecorder != null)
			addListener(frameRecorder);
	}
	
	public void stop() {
		// Remove listeners & delete controller
		if(framePublisher != null)
			removeListener(framePublisher);
		if(frameRecorder != null)
			removeListener(frameRecorder);
		delete();
	}
	
}
