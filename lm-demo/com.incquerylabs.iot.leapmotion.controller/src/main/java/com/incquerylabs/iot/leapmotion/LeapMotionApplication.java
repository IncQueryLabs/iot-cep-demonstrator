package com.incquerylabs.iot.leapmotion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import com.incquerylabs.iot.communication.YellowPages;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.cli.CommandOptions.Commands;
import com.incquerylabs.iot.leapmotion.frame.FrameRecorder;
import com.incquerylabs.iot.leapmotion.frame.FrameStreamer;
import com.incquerylabs.iot.leapmotion.frame.FrameUtils;
import com.incquerylabs.iot.leapmotion.zmq.FramePublisher;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture.Type;
import com.leapmotion.leap.Listener;

public class LeapMotionApplication extends Controller {
			
	public static enum MODE {
		RECORD,
		REPLAY,
		DEFAULT
	}
	
	Set<Listener> listeners;
	
	FrameStreamer streamer = null;
	
	public LeapMotionApplication(MODE mode, String filepath, int fps) throws IOException {
		
		listeners = new HashSet<Listener>();
		
		enableGesture(Type.TYPE_CIRCLE);
		enableGesture(Type.TYPE_SWIPE);
		enableGesture(Type.TYPE_KEY_TAP);
		enableGesture(Type.TYPE_SCREEN_TAP);
		enableGesture(Type.TYPE_INVALID);
		
		switch(mode) {
			case RECORD:
				listeners.add(new FramePublisher(fps));
				listeners.add(new FrameRecorder(prepareStreamFile(filepath)));
				break;
			case REPLAY:
				streamer = new FrameStreamer(prepareStreamFile(filepath), YellowPages.getFrameStreamAddress(), fps);
				break;
			case DEFAULT:
				listeners.add(new FramePublisher(fps));
		}
	
	}
	
	public LeapMotionApplication(int fps) throws IOException {
		this(MODE.DEFAULT, "", fps);
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
				streamer.start();
				break;
			case STEP:
				streamer.step();
				break;
			case PAUSE:
				streamer.pause();
				break;
			case RESET:
				streamer.reset();
				break;
			case EXIT:
				streamer.stop();
		}
	}
	
	public void start() {
		// Register leap motion listeners
		listeners.forEach(listener -> addListener(listener));
	}
	
	public void stop() {
		// Remove listeners & delete controller
		listeners.forEach(listener -> removeListener(listener));
		delete();
	}
	
}
