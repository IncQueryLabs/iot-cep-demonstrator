package com.incquerylabs.iot.communication.zmq;

import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import com.incquerylabs.iot.communication.AbstractPublisher;
import com.incquerylabs.iot.communication.IAddress;

public class ZPublisher extends AbstractPublisher {
	
	private ZMQ.Context context;
	
	private ZMQ.Socket publisher;
		
	public ZPublisher() {
		context = ZMQ.context(10);
		publisher = context.socket(ZMQ.PUB);
	}
	
	@Override
	public void connect(IAddress address) {
		try {
			publisher.bind(String.format("tcp://%s:%d", address.getHost(), address.getPort()));
		} catch(ZMQException ex) {
			publisher.connect(String.format("tcp://%s:%d", address.getHost(), address.getPort()));			
		}
	}
	
	@Override
	public void publish(byte[] message, int qos) {
		inprogress = true;
		publisher.sendMore(topic);
		publisher.send(message, 0);
		inprogress = false;
	}

	@Override
	public void publish(String message, int qos) {
		inprogress = true;
		publisher.sendMore(topic);
		publisher.send(message);
		inprogress = false;
	}

	@Override
	public void disconnect() {
		publisher.close();
		context.close();
		context.term();
	}

}
