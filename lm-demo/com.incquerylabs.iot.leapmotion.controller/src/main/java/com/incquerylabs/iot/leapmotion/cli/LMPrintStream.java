package com.incquerylabs.iot.leapmotion.cli;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class LMPrintStream extends PrintStream {

	public LMPrintStream(OutputStream out) {
		super(out);
	}

	@Override
	public void println(String x) {
		super.println(x);
		writePrefix();
	}
	
	@Override
	public void println() {
		super.println();
		writePrefix();
	}
	
	public void writePrefix() {
		try {
			out.write("> ".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
