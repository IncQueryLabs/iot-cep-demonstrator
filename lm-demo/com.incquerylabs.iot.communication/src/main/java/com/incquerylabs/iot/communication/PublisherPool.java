package com.incquerylabs.iot.communication;

import java.util.Iterator;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;

public class PublisherPool {
	
	private static PublisherPool INSTANCE = null;
	
	Multimap<String, IPublisher> publishers = LinkedListMultimap.create();
	
	private CommunicationComponentFactory factory;
	
	private static volatile boolean initialized;
	
	public static void initializePool(CommunicationComponentFactory factory) {
		if(factory != null) {
			INSTANCE = new PublisherPool(factory);
			initialized = true;
		}
	}
	
	private PublisherPool(CommunicationComponentFactory factory) {
		this.factory = factory;
	}
	
	private IPublisher addPublisher(IAddress address) {
		IPublisher newPublisher = factory.createPublisher();
		newPublisher.connect(address);
		publishers.get(address.getFullAddress()).add(newPublisher);
		return newPublisher;
	}
	
	public static PublisherPool getInstance() throws PoolNotInitializedException {
		if(initialized) {
			return INSTANCE;
		} else throw new PoolNotInitializedException();
		
	}
	
	public IPublisher next(IAddress address) {
		Iterator<IPublisher> selector = publishers.get(address.getFullAddress()).iterator();
		IPublisher next = null;
		while (selector.hasNext() && !(next = selector.next()).isReady());
		if(next !=null && next.isReady())
			return next;
		else 
			return addPublisher(address);
	}

	public void dispose() {
		Iterator<IPublisher> it = publishers.values().iterator();
		while(it.hasNext()) {
			it.next().disconnect();
		}
		publishers.clear();
	}

	public void getFullSize() {
		publishers.size();
	}
	
}
