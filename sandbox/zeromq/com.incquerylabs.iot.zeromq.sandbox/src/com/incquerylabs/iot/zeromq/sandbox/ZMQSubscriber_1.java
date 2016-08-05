package com.incquerylabs.iot.zeromq.sandbox;

import org.zeromq.ZMQ;

public class ZMQSubscriber_1 {

	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(10);

        //  Socket to talk to clients
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        
        subscriber.connect("tcp://127.0.0.1:5555");
        subscriber.subscribe("A".getBytes());
        
        while(!Thread.currentThread().isInterrupted()) {
        	// Read envelope with address
            String address = new String(subscriber.recv(0));
            // Read message contents
            String contents = new String(subscriber.recv(0));
            System.out.println(address + " : " + contents);
        }
	
	}

}
