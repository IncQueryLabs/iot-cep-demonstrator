package com.incquerylabs.iot.web.proto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Properties;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.protobuf.Message.Builder;
import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.web.WSEndpoint;

public class MessageBuilderService {
	
	public static final String BUILDERS_FILENAME = "endpoints.properties";
	
	private Multimap<IAddress, Builder> messageBuilders;
		
	public MessageBuilderService() {
		messageBuilders = LinkedListMultimap.create();
	}
	
	public void load() throws IOException {
		
		messageBuilders.clear();
		
		Properties props = new Properties();
		InputStream stream = MessageBuilderService.class.getClassLoader().getResourceAsStream(BUILDERS_FILENAME);
		
		if(stream == null)
			throw new FileNotFoundException(BUILDERS_FILENAME + " not found in the classpath!");
		
		props.load(stream);
		
		props.stringPropertyNames().forEach(key -> {
			try {
				Class<?> messageClass = getClass().getClassLoader().loadClass(props.getProperty(key));
				Method newBuilder = messageClass.getMethod("newBuilder"); // XXX: protobuf api sensitive
				Builder builder = (Builder) newBuilder.invoke(null);
				messageBuilders.put(WSEndpoint.build(key), builder);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		stream.close();
	}
	
	public Collection<Builder> getBuildersForAddress(IAddress address) {
		return messageBuilders.get(address);
	}
	
	public Collection<IAddress> getAllAddress() {
		return messageBuilders.keySet();
	}
}
