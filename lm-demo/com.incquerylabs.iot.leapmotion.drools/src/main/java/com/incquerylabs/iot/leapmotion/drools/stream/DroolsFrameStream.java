package com.incquerylabs.iot.leapmotion.drools.stream;

import java.util.concurrent.atomic.AtomicReference;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.leapmotion.processor.AbstractFrameStream;
import com.leapmotion.leap.Frame;

public class DroolsFrameStream extends AbstractFrameStream {
	
	AtomicReference<KieSession> kieSession;
	
	AtomicReference<EntryPoint> stream;
	
	public static final String STREAM_ID = "FrameStream";
	
	public DroolsFrameStream(IAddress sourceAddress) {
		super(sourceAddress);
		
		KieServices kieServices = KieServices.Factory.get();

		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieBaseConfiguration kieBaseConf = kieServices.newKieBaseConfiguration();
		kieBaseConf.setOption(EventProcessingOption.STREAM);
		
		KieBase kieBase = kieContainer.newKieBase(kieBaseConf);
		
		kieSession = new AtomicReference<KieSession>(kieBase.newKieSession());
		
		stream = new AtomicReference<EntryPoint>(kieSession.get().getEntryPoint( STREAM_ID ));
		
	}

	@Override
	public void processFrame(Frame frame) {
		if(stream != null)
		stream.get().insert(frame);
		kieSession.get().fireAllRules();
	}
	
}
