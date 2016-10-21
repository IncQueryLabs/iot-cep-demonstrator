package com.incquerylabs.iot.communication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class YellowPages {
	
	private static boolean loaded = false;

	public static final YellowPages INSTANCE = new YellowPages(); 
	
	private Properties addresses;
	
	private InputStream input = null;
	
	private YellowPages() {
		addresses = new Properties();
	}
	
	private boolean load() {
		if(loaded) return true;
		try {
			input = new FileInputStream(CONFIG_FILENAME);
			addresses.load(input);
			loaded = true;
		} catch (IOException e) {
			loaded = false;
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return loaded;
	}
	
	
	public static final String CONFIG_FILENAME = "yellowpages.properties";
	
	public static final int FRAME_STREAM_PORT = 5555;
	public static final String FRAME_STREAM_TOPIC = "framestream";

	public static final int GESTURE_STREAM_PORT = 5556;
	public static final String GESTURE_STREAM_TOPIC = "gesturetream";
	
	public IAddress getFrameStreamAddress() {
		if (load()) {
			return new Address(addresses.getProperty(FRAME_STREAM_TOPIC), FRAME_STREAM_PORT, FRAME_STREAM_TOPIC);
		} else
			return new Address("127.0.0.1", FRAME_STREAM_PORT, FRAME_STREAM_TOPIC);
	}

	public IAddress getGesturesStreamAddress() {
		if (load()) {
			return new Address(addresses.getProperty(GESTURE_STREAM_TOPIC), GESTURE_STREAM_PORT, GESTURE_STREAM_TOPIC);
		}
		return new Address("127.0.0.1", GESTURE_STREAM_PORT, GESTURE_STREAM_TOPIC);
	}

}