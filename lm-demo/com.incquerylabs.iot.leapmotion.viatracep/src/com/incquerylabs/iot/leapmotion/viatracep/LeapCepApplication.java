package com.incquerylabs.iot.leapmotion.viatracep;

import org.eclipse.viatra.cep.core.api.engine.CEPEngine;
import org.eclipse.viatra.cep.core.metamodels.automaton.EventContext;
import org.eclipse.viatra.cep.core.streams.EventStream;
import org.eclipse.viatra.query.runtime.base.exception.ViatraBaseException;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

import com.incquerylabs.iot.communication.SubscriberPool;
import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.communication.zmq.ZMQFactory;
import com.incquerylabs.iot.leapmotion.YellowPages;
import com.incquerylabs.iot.leapmotion.proto2emf.LeapModelManager;
import com.incquerylabs.iot.leapmotion.proto2emf.Proto2EMFFrameStream;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.CepFactory;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.mapping.QueryEngine2ViatraCep;

public class LeapCepApplication {
	
	private CEPEngine engine;
	
	private QueryEngine2ViatraCep mapping;
	
	private EventStream eventStream;
	
	private Proto2EMFFrameStream stream;
	
	public LeapCepApplication() throws ViatraQueryException {
		engine = CEPEngine.newEngine().eventContext(EventContext.CHRONICLE).rules(CepFactory.getInstance().allRules()).prepare();
		stream = new Proto2EMFFrameStream(YellowPages.getFrameStreamAddress(), LeapModelManager.getInstance());
		eventStream = engine.getStreamManager().newEventStream();
	}
	
	public void start() throws PoolNotInitializedException, ViatraQueryException, ViatraBaseException {
		SubscriberPool.initializePool(new ZMQFactory());
		mapping  = QueryEngine2ViatraCep.register(LeapModelManager.getInstance().initialize(), eventStream);
		stream.start();
	}
	
	public void stop() throws PoolNotInitializedException {
		stream.stop();
		engine.reset();
	}
	
}
