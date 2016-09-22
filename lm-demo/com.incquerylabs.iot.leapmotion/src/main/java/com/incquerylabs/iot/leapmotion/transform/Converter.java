package com.incquerylabs.iot.leapmotion.transform;

import java.lang.reflect.Method;

import com.google.protobuf.MessageOrBuilder;
import com.leapmotion.leap.Interface;

public class Converter<F extends Interface, T extends MessageOrBuilder> {
	
	
	public Converter() {
		
	}
	
	public void convert(F from, T to) {
		
		for(Method method : to.getClass().getMethods()) {
			if(method.getName().startsWith("set") && method.getParameterCount() == 1) {
				
				System.out.println(method.getName());
			}
		};
		
	}
	
}
