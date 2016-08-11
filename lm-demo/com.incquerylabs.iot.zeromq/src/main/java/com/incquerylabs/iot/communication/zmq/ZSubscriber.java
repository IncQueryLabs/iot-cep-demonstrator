package com.incquerylabs.iot.communication.zmq;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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
	
	private AtomicReference<Multimap<String, ISubscriberCallback>> callbacks = new AtomicReference(LinkedListMultimap.create());
	
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
	public void registerCallback(IAddress address, ISubscriberCallback callback) {
		sub.connect(String.format("tcp://%s:%d", address.getHost(), address.getPort()));
		sub.subscribe(address.getTopic().getBytes());
		callbacks.get().put(address.getTopic(), callback);
		addresses.put(address.getFullAddress(), address);
		if(!running) {
			running = true;
			pool.execute(this);
		}
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
				String topic = new String(sub.recv());
				byte[] data = sub.recv();
				IAddress address = addresses.get(topic);
				Iterator<ISubscriberCallback> cit = callbacks.get().get(topic).iterator();
				while(cit.hasNext())
					cit.next().messageArrived(address, data);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.err.println(String.format("Subscriber thread interrupted: %s", e.getCause()));
			}
		}
	}

	@Override
	public void unregisterCallback(IAddress address, ISubscriberCallback callback) {
		if(callbacks.get().containsValue(callback)) {
			//TODO: implement unregistration method
			
		}
	}

}
