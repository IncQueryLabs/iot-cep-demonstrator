package com.incquerylabs.iot.leapmotion.sandbox;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;

public class Main {
	
	public static volatile boolean running = false;
	
	public static void main(String[] args) {
		
		DemoListener demo = new DemoListener();
		
		Controller controller = new Controller();
				
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		
		controller.addListener(demo);
		
		running = true;
		
		while(running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		controller.removeListener(demo);
		controller.delete();
	}

}
