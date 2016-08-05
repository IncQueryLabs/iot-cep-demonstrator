package com.incquerylabs.iot.zeromq.sandbox;

import org.zeromq.ZMQ;

public class ZMQPublisher_1 {

	public static void main(String[] args) throws InterruptedException {
		ZMQ.Context context = ZMQ.context(10);

        //  Socket to talk to clients
        ZMQ.Socket publisher = context.socket(ZMQ.PUB);
        
        publisher.bind("tcp://*:5555");
        
        int i=0;

        while (!Thread.currentThread().isInterrupted()) {
            
        	publisher.sendMore("A");
        	
        	publisher.send("Message" + i++);
        	
        	System.out.println("Data sent: " + i);

            Thread.sleep(1000);

        }
        
        publisher.close();
        context.term();

	}

}
