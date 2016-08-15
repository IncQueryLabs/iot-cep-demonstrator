package com.incquerylabs.iot.leapmotion.cli;

import org.apache.commons.cli.Options;

public class CommandOptions extends Options {

	private static final long serialVersionUID = 1L;
	
	public static enum Commands {
		PAUSE("pause"),
		REPLAY("replay"),
		STEP("step"),
		RESET("reset"),
		EXIT("exit");
		
		private String name;
		
		private Commands(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return this.name;
		}
	}
	
	
}
