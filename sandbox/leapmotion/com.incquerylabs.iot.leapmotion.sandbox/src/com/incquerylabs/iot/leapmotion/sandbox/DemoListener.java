package com.incquerylabs.iot.leapmotion.sandbox;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;

public class DemoListener extends Listener {
	
	@Override
	public void onConnect(Controller controller) {
		super.onConnect(controller);
		System.out.println("Connected to device!");
	}

	@Override
	public void onFocusGained(Controller controller) {
		super.onFocusGained(controller);
		System.out.println("Focus gained!");
	}

	@Override
	public void onFocusLost(Controller controller) {
		super.onFocusLost(controller);
		System.out.println("Focus lost!");
	}

	@Override
	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		
		GestureList gestures = frame.gestures();
		
		System.out.println(frame.currentFramesPerSecond());
		if(!gestures.isEmpty()) {
			System.out.println(gestures.get(0).type().name());
			
			for(Finger finger : frame.fingers()) {
				System.out.println(finger.type().name() + " : " + finger.isExtended());
			}
			
			System.out.println();
		}
		
	}
	
	
	
}
