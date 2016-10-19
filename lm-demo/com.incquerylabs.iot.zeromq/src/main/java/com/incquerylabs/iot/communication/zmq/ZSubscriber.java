package com.incquerylabs.iot.communication.zmq;

import java.util.Iterator;

import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import com.incquerylabs.iot.communication.AbstractSubscriber;
import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.IExecutorPool;
import com.incquerylabs.iot.communication.ISubscriberCallback;

public class ZSubscriber extends AbstractSubscriber {
	
	private ZMQ.Context context;
	
	private ZMQ.Socket sub;
	
	public ZSubscriber(IExecutorPool pool) {
		super(pool);
		context = ZMQ.context(10);
		sub = context.socket(ZMQ.SUB);
	}
	
	@Override
	public void registerCallback(IAddress _address, ISubscriberCallback callback) {
		
		// If no existing subscribe to the address yet
		if(address == null) {
			this.address = _address;
			try {
				sub.bind(String.format("tcp://%s:%d", address.getHost(), address.getPort()));
			} catch(ZMQException ex) {
				sub.connect(String.format("tcp://%s:%d", address.getHost(), address.getPort()));				
			}
			sub.subscribe(address.getTopic().getBytes());
		}
		
		super.registerCallback(_address, callback);
	}
	
	@Override
	public void disconnectAll() {
		running = false;
		sub.close();
        context.term();
	}

	@Override
	public void run() {
		while(running) {
			String topic = new String(sub.recv());
			byte[] data = sub.recv();
			Iterator<ISubscriberCallback> cit = callbacks.get().iterator();
			while(cit.hasNext())
				cit.next().messageArrived(address, data);
		}
	}
	
}
