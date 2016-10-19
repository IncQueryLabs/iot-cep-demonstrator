package com.incquerylabs.iot.communication.mqtt;

import java.util.Iterator;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.incquerylabs.iot.communication.AbstractSubscriber;
import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.communication.IExecutorPool;
import com.incquerylabs.iot.communication.ISubscriberCallback;

public class MQTTSubscriber extends AbstractSubscriber implements MqttCallback {
	
	private MqttClient client;
	
	public MQTTSubscriber(IExecutorPool pool) {
		super(pool);
	}
	
	public void registerCallback(IAddress address, ISubscriberCallback callback) {
		if(client == null) {
			try {
				client = new MqttClient(String.format("tcp://%s:%d", address.getHost(), address.getPort()), address.getFullAddress() + System.nanoTime());
				client.connect();
				client.setCallback(this);
				client.subscribe(address.getTopic());
			} catch (MqttException e) {
				System.err.println("Unable to create MQTT client: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		super.registerCallback(address, callback);
	}

	@Override
	public void disconnectAll() {
		if(client != null && client.isConnected())
			try {
				client.disconnect();
			} catch (MqttException e) {
				System.err.println("Exception occurrred during MQTT disconnection: " + e.getMessage());
			}
	}

	@Override
	public void run() {} // Not used in MQTT transport implementation

	@Override
	public void connectionLost(Throwable t) {}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		Iterator<ISubscriberCallback> cit = callbacks.get().iterator();
		while(cit.hasNext())
			cit.next().messageArrived(address, message.getPayload());
	}

}
