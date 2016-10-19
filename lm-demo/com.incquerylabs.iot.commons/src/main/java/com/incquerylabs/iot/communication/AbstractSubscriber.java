package com.incquerylabs.iot.communication;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractSubscriber implements ISubscriber, Runnable {
	
	protected AtomicReference<List<ISubscriberCallback>> callbacks = new AtomicReference<List<ISubscriberCallback>>(new LinkedList<ISubscriberCallback>());
	
	protected volatile boolean running;
	
	protected IAddress address;
	
	protected IExecutorPool pool;
	
	public AbstractSubscriber(IExecutorPool pool) {
		this.pool = pool;
		running = false;
	}
	
	@Override
	public void registerCallback(IAddress address, ISubscriberCallback callback) {
		callbacks.get().add(callback);
		
		if(!running) {
			pool.execute(this);
			running = true;
		}
	}

	@Override
	public void unregisterCallback(ISubscriberCallback callback) {
		callbacks.get().remove(callback);
	}

}
