package com.incquerylabs.iot.leapmotion.frame;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;
import com.incquerylabs.iot.leapmotion.transform.Leap2ProtoConverter;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;

public class FrameRecorder extends Listener {
	
	DataOutputStream outputStream;
	
	public FrameRecorder(String outputfilepath) throws IOException {
		outputStream = new DataOutputStream(new FileOutputStream(outputfilepath));
	}
	
	@Override
	public void onFrame(Controller controller) {
		
		Frame frame = Leap2ProtoConverter.convert(controller.frame());
		
		if(!frame.getValid() || frame.getHandList().getEmpty()) return;
		
		try {
			frame.writeDelimitedTo(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			System.err.println(String.format("Unable to write frame (id / timestamp): %d / %d",frame.getId(), frame.getTimestamp()));
		}
		
	}
	
	public void dispose() throws IOException {
		outputStream.close();
	}
	
}
