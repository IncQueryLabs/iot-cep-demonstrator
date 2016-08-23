package com.incquerylabs.iot.leapmotion.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LMInputReader extends BufferedReader {
	
	private LMPrintStream output;
	
	public LMInputReader(InputStreamReader input, LMPrintStream output) {
		super(input);
		this.output = output;
	}

	@Override
	public String readLine() throws IOException {
		String line = super.readLine();
		output.writePrefix();
		return line;
	}
	
}
