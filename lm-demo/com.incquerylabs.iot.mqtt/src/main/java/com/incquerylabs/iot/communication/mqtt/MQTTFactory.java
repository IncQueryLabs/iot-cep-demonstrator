package com.incquerylabs.iot.communication.mqtt;

import com.incquerylabs.iot.communication.CommunicationComponentFactory;
import com.incquerylabs.iot.communication.IExecutorPool;
import com.incquerylabs.iot.communication.IPublisher;
import com.incquerylabs.iot.communication.ISubscriber;

public class MQTTFactory extends CommunicationComponentFactory {
	
	@Override
	public IPublisher createPublisher() {
		return new MQTTPublisher();
	}

	@Override
	public ISubscriber createSubscriber(IExecutorPool pool) {
		return new MQTTSubscriber(pool);
	}
	
}
