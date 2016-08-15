package com.incquerylabs.iot.leapmotion.cli;

import org.apache.commons.cli.Options;

public class ControllerOptions extends Options {

	private static final long serialVersionUID = 1L;
	
	public static final String RECORD = "record";
	public static final String REPLAY = "replay";
	
	public ControllerOptions() {
		addOption(RECORD, true, "Record frames stream into given directory");
		addOption(REPLAY, true, "Replay frames stream");
	}
	
}
