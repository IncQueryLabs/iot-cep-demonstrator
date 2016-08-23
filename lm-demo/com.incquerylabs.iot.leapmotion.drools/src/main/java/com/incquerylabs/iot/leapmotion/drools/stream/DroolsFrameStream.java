package com.incquerylabs.iot.leapmotion.drools.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

import com.incquerylabs.iot.communication.IAddress;
import com.incquerylabs.iot.leapmotion.processor.AbstractProtoFrameStream;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;

public class DroolsFrameStream extends AbstractProtoFrameStream {
	
	AtomicReference<KieSession> kieSession;
	
	AtomicReference<EntryPoint> stream;
	
	public static final String STREAM_ID = "FrameStream";
	
	List<Frame> accepted_grabs;
	
	long counter = 0;
		
	public DroolsFrameStream(IAddress sourceAddress, List<Frame> output) {
		super(sourceAddress);
		
		accepted_grabs = output;
		
		KieServices kieServices = KieServices.Factory.get();

		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieBaseConfiguration kieBaseConf = kieServices.newKieBaseConfiguration();
		kieBaseConf.setOption(EventProcessingOption.STREAM);
		
		KieBase kieBase = kieContainer.newKieBase(kieBaseConf);
		
		kieSession = new AtomicReference<KieSession>(kieBase.newKieSession());
		
		kieSession.get().setGlobal("accepted_grabs", accepted_grabs);
		
		stream = new AtomicReference<EntryPoint>(kieSession.get().getEntryPoint( STREAM_ID ));		
	}
	
	public DroolsFrameStream(IAddress sourceAddress) {
		this(sourceAddress, new ArrayList<Frame>());
	}

	@Override
	public void processFrame(Frame frame) {
		if(stream != null)
		stream.get().insert(frame);
		kieSession.get().fireAllRules();
	}
	
}
