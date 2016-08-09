package com.incquerylabs.iot.communication.zmq;

import java.util.HashMap;
import java.util.Map;

import org.zeromq.ZMQ;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.IExecutorPool;
import com.incquerylabs.iot.communication.ISubscriber;
import com.incquerylabs.iot.communication.ISubscriberCallback;

public class ZSubscriber implements ISubscriber, Runnable {
	
	private ZMQ.Context context;
	
	private ZMQ.Socket sub;
	
	private Multimap<String, ISubscriberCallback> callbacks = LinkedListMultimap.create();
	
	// XXX: workaround - unable to get zmq socket address; store registered addresses by topic name
	private Map<String, IAddress> addresses = new HashMap<>();
	
	private volatile boolean running;
	
	private IExecutorPool pool;
	
	public ZSubscriber(IExecutorPool pool) {
		context = ZMQ.context(10);
		sub = context.socket(ZMQ.SUB);
		this.pool = pool;
		running = false;
	}
	
	@Override
	public void subscribe(IAddress address, ISubscriberCallback callback) {
		sub.connect(String.format("tcp://%s:%d", address.getHost(), address.getPort()));
		sub.subscribe(address.getTopic().getBytes());
		callbacks.put(address.getTopic(), callback);
		addresses.put(address.getFullAddress(), address);
		if(!running)
			pool.execute(this);
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
			try {
				String topic = new String(sub.recv(ZMQ.NOBLOCK));
				byte[] data = sub.recv();
				IAddress address = addresses.get(topic);
				callbacks.get(topic).forEach(callback -> callback.messageArrived(address));
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
			}
		}
	}

}
