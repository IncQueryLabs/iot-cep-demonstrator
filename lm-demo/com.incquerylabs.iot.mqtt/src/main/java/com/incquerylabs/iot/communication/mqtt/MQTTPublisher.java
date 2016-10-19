package com.incquerylabs.iot.communication.mqtt;

import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.incquerylabs.iot.communication.AbstractPublisher;
import com.incquerylabs.iot.communication.IAddress;

public class MQTTPublisher extends AbstractPublisher {
	
	private MqttClient client;
	
	public void connect(IAddress address) {
		try {
			client = new MqttClient(String.format("tcp://%s:%d", address.getHost(), address.getPort()), address.getFullAddress() + System.nanoTime());
		} catch (MqttException e) {
			System.err.println("Error while creating MQTT client: " + e.getMessage());
		}
		super.connect(address);
	}

	public void publish(byte[] message, int qos) {
		try {
			client.publish(topic, message, qos, false);
		} catch (MqttException e) {
			System.err.println("Error occured while publishing message via MQTT: " + e.getMessage());
		}
	}

	public void publish(String message, int qos) {
		this.publish(message.getBytes(StandardCharsets.UTF_8), qos);
	}

	public void disconnect() {
		try {
			client.disconnect();
		} catch (MqttException e) {
			System.err.println("Error occured while disconnecting MQTT client: " + e.getMessage());
		}
	}
	
}
