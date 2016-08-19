package com.incquerylabs.iot.leapmotion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import com.incquerylabs.iot.communication.PublisherPool;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;
import com.incquerylabs.iot.leapmotion.cli.CommandOptions.Commands;
import com.incquerylabs.iot.leapmotion.cli.ControllerOptions;

public class Main {
	
	private static LeapMotionApplication application;
	
	public static void main(String[] args) throws IOException, InterruptedException {

		PublisherPool.initializePool(new ZMQFactory());
		
		CommandLineParser parser = new DefaultParser();
		
		CommandLine cli = null;
		
		try {
			cli = parser.parse(new ControllerOptions(), args);
		} catch (ParseException e) {
			System.err.println("Argument parsing failed: " + e.getMessage());
			System.exit(-1);
		}
		
		if(cli.hasOption(ControllerOptions.RECORD) && cli.hasOption(ControllerOptions.REPLAY)) {
			System.err.println("Conflicting arguments: both record and replay are used! ");
			System.exit(-1);
		}
		
		if(cli.hasOption(ControllerOptions.RECORD)) {
			String streampath = cli.getOptionValue(ControllerOptions.RECORD);
			application = new LeapMotionApplication(LeapMotionApplication.MODE.RECORD, streampath);
		} else if(cli.hasOption(ControllerOptions.REPLAY)) {
			String streampath = cli.getOptionValue(ControllerOptions.REPLAY);
			application = new LeapMotionApplication(LeapMotionApplication.MODE.REPLAY, streampath);
		} else {
			application = new LeapMotionApplication();
		}
		
		application.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				application.stop();
			}
		});
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		String commandLine = "";
		System.out.print("> ");
		while(!(commandLine = console.readLine().trim()).equals(Commands.EXIT)) {
			try {
				String command = commandLine.split("\\s+")[0];
				if(command.length() > 0)
					application.performCommand(Commands.valueOf(command.toUpperCase()));
			} catch (Exception e) {
				System.err.println(String.format("Failed to perform command: %s. Cause: %s", commandLine, e.getMessage()));
				System.out.println();
			}
			System.out.print("> ");
		}

	}

}
