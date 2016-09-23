package com.incquerylabs.iot.leapmotion.proto2emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.GenericQueryGroup;
import org.eclipse.viatra.query.runtime.api.IQueryGroup;
import org.eclipse.viatra.query.runtime.base.api.NavigationHelper;
import org.eclipse.viatra.query.runtime.base.api.ViatraBaseFactory;
import org.eclipse.viatra.query.runtime.base.exception.ViatraBaseException;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

import com.incquerylabs.iot.leapmotion.lmemf.FingerList;
import com.incquerylabs.iot.leapmotion.lmemf.HandList;
import com.incquerylabs.iot.leapmotion.lmemf.LmemfFactory;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Frame;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.Leapqueries;

import static com.incquerylabs.iot.leapmotion.proto2emf.Proto2EMFConverter.*;

public class LeapModelManager implements ILeapModelUpdater {

	private Resource resource;
	private ResourceSet resourceSet;
	
	private AdvancedViatraQueryEngine engine;
	private NavigationHelper navigationHelper;
	
	private Leapqueries queries;
	
	private com.incquerylabs.iot.leapmotion.lmemf.Frame currentFrame;
	
	private static LeapModelManager INSTANCE = null;
	
	private LeapModelManager() throws ViatraQueryException {
		if(resourceSet == null) {
			resourceSet = new ResourceSetImpl();
			resource = resourceSet.createResource(URI.createURI("leap.lmemf"));
		}
		queries = Leapqueries.instance();
	}
	
	public static LeapModelManager getInstance() throws ViatraQueryException {
		if(INSTANCE == null) {
			INSTANCE = new LeapModelManager();
		}
		return INSTANCE;
	}
	
	public synchronized ResourceSet initialize() throws ViatraQueryException, ViatraBaseException {
		if(currentFrame != null) {
			EcoreUtil.delete(currentFrame, true);
		}
		
		if(engine == null)
			engine = AdvancedViatraQueryEngine.createUnmanagedEngine(new EMFScope(resourceSet));
		
		currentFrame = LmemfFactory.eINSTANCE.createFrame();
		
		navigationHelper = ViatraBaseFactory.getInstance().createNavigationHelper(currentFrame, true, null);
		
		resource.getContents().add(currentFrame);
		
		IQueryGroup queryGroup = GenericQueryGroup.of(queries);
		queryGroup.prepare(engine);
		
		return resourceSet;
	}
	
	@Override
	public synchronized void updateModel(Frame frame) {
		currentFrame.setCurrentFramePerSecond(frame.getCurrentFramePerSecond());
		currentFrame.setValid(frame.getValid());
		currentFrame.setTimestamp(frame.getTimestamp());

		FingerList fingers = convert(frame.getFingers());
		currentFrame.setFingers(fingers);
				
		HandList hands = convert(frame.getHands());
		currentFrame.setHands(hands);

	}
	
}
