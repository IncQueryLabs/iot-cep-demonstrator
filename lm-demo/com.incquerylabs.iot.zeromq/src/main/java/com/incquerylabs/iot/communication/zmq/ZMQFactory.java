package com.incquerylabs.iot.communication.zmq;

import com.incquerylabs.iot.communication.CommunicationComponentFactory;
import com.incquerylabs.iot.communication.IExecutorPool;
import com.incquerylabs.iot.communication.IPublisher;
import com.incquerylabs.iot.communication.ISubscriber;

public class ZMQFactory extends CommunicationComponentFactory {

	public IPublisher createPublisher() {
		return new ZPublisher();
	}

	@Override
	public ISubscriber createSubscriber(IExecutorPool pool) {
		return new ZSubscriber(pool);
	}

}
