package com.incquerylabs.iot.leapmotion.test;

import java.util.concurrent.TimeUnit;

import org.zeromq.ZMQ;

import com.google.common.base.Stopwatch;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;

public class FrameStreamerTester {
	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(10);

        //  Socket to talk to clients
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        
        subscriber.connect("tcp://127.0.0.1:5555");
        
        subscriber.subscribe("framestream".getBytes());
        
        Controller controller = new Controller();
        
        Frame prevFrame = null;
        
        Stopwatch stopWatch = Stopwatch.createStarted();

        while(!Thread.currentThread().isInterrupted()) {
        	
        	// Read envelope with address
            String address = new String(subscriber.recv(0));

            // Read message contents
            byte[] contents = subscriber.recv(0);
            
            Frame frame = new Frame();
            frame.deserialize(contents);
            
            System.out.println(String.format("Frame (%d / %d)", frame.id(), frame.timestamp()));
            if(prevFrame != null) {
            	long elapsed = stopWatch.elapsed(TimeUnit.MILLISECONDS);
            	long elapsedMs = stopWatch.elapsedMillis();
            	System.out.println(String.format("Elapsed: %d, ElapsedMS: %d", elapsed, elapsedMs));
            	System.out.println(String.format("Diff: %d - (%d + %d) = %d us", frame.timestamp(), prevFrame.timestamp(), elapsed, frame.timestamp() - (prevFrame.timestamp() + elapsed)));
            }
            
            stopWatch.reset();
            prevFrame = frame;
        }
	
	}
}
