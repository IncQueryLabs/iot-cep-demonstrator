package com.incquerylabs.iot.leapmotion.frame;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

public class FrameRecorder extends Listener {
	
	DataOutputStream outputStream;
	
	public FrameRecorder(String outputfilepath) throws IOException {
		outputStream = new DataOutputStream(new FileOutputStream(outputfilepath));
	}
	
	@Override
	public void onFrame(Controller controller) {
		
		Frame frame = controller.frame();
		
		if(!frame.isValid() || frame.hands().isEmpty()) return;
		
		try {
			outputStream.writeInt(frame.serializeLength());
			outputStream.write(frame.serialize());
			outputStream.flush();
		} catch (IOException e) {
			System.err.println(String.format("Unable to write frame (id / timestamp): %d / %d",frame.id(), frame.timestamp()));
		}
		
	}
	
	public void dispose() throws IOException {
		outputStream.close();
	}
	
}
