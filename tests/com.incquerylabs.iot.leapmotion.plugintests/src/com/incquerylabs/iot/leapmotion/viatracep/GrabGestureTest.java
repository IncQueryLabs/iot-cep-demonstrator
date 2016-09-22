package com.incquerylabs.iot.leapmotion.viatracep;

import org.eclipse.viatra.query.runtime.base.exception.ViatraBaseException;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import static org.junit.Assert.*;

import com.incquerylabs.iot.communication.exception.PoolNotInitializedException;
import com.incquerylabs.iot.leapmotion.basetests.grab.AGrabGestureTest;

public class GrabGestureTest extends AGrabGestureTest {
	
	LeapCepApplication cep;
	
	@Override
	protected void initializeCEPComponent() {
		try {
			cep = new LeapCepApplication();
		} catch (ViatraQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(cep);
	}

	@Override
	protected void startCepComponent() {
		try {
			cep.start();
		} catch (PoolNotInitializedException | ViatraQueryException | ViatraBaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected void stopCepComponent() {
		try {
			cep.stop();
		} catch (PoolNotInitializedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
