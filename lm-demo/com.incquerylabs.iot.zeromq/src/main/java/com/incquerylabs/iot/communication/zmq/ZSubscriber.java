package com.incquerylabs.iot.communication.zmq;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.IExecutorPool;
import com.incquerylabs.iot.communication.ISubscriber;
import com.incquerylabs.iot.communication.ISubscriberCallback;

public class ZSubscriber implements ISubscriber, Runnable {
	
	private ZMQ.Context context;
	
	private ZMQ.Socket sub;
	
	private AtomicReference<List<ISubscriberCallback>> callbacks = new AtomicReference<List<ISubscriberCallback>>(new LinkedList<ISubscriberCallback>());
	
	private IAddress address;
		
	private volatile boolean running;
	
	private IExecutorPool pool;
	
	public ZSubscriber(IExecutorPool pool) {
		context = ZMQ.context(10);
		sub = context.socket(ZMQ.SUB);
		this.pool = pool;
		running = false;
	}
	
	@Override
	public void registerCallback(IAddress _address, ISubscriberCallback callback) {
		
		// If not subscribed yet
		if(address == null) {
			this.address = _address;
			try {
				sub.bind(String.format("tcp://%s:%d", address.getHost(), address.getPort()));
			} catch(ZMQException ex) {
				sub.connect(String.format("tcp://%s:%d", address.getHost(), address.getPort()));				
			}
			sub.subscribe(address.getTopic().getBytes());
		}
		callbacks.get().add(callback);
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
				Iterator<ISubscriberCallback> cit = callbacks.get().iterator();
				while(cit.hasNext())
					cit.next().messageArrived(address, data);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.err.println(String.format("Subscriber thread interrupted: %s", e.getCause()));
			}
		}
	}

	@Override
	public void unregisterCallback(ISubscriberCallback callback) {
		callbacks.get().remove(callback);
	}

}
