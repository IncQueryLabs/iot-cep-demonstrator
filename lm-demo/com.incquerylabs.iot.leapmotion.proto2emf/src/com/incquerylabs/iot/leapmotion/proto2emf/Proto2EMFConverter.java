package com.incquerylabs.iot.leapmotion.proto2emf;

import com.incquerylabs.iot.leapmotion.lmemf.Finger;
import com.incquerylabs.iot.leapmotion.lmemf.FingerList;
import com.incquerylabs.iot.leapmotion.lmemf.Hand;
import com.incquerylabs.iot.leapmotion.lmemf.HandList;
import com.incquerylabs.iot.leapmotion.lmemf.LmemfFactory;
import com.incquerylabs.iot.leapmotion.lmemf_base.Lmemf_baseFactory;
import com.incquerylabs.iot.leapmotion.lmemf_base.Vector;

public class Proto2EMFConverter {
	
	/**
	 * Convert vector.
	 */
	public static Vector convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Vector source) {
		Vector target = Lmemf_baseFactory.eINSTANCE.createVector();
		target.setX(source.getX());
		target.setY(source.getY());
		target.setZ(source.getZ());
		
		return target;
	}
	
	/**
	 * Convert HandList
	 */
	public static HandList convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.HandList source) {
		HandList target = LmemfFactory.eINSTANCE.createHandList();
		target.setCount(source.getCount());
		target.setEmpty(source.getEmpty());
		target.setFrontmost(convert(source.getFrontmost()));
		return target;
	}
	
	/**
	 * Hand conversion (Proto -> EMF)
	 */
	public static Hand convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Hand source) {
		Hand target = LmemfFactory.eINSTANCE.createHand();
		target.setGrabStrength(source.getGrabStrength());
		return target;
	}
	
	/**
	 * FingerList
	 */
	public static FingerList convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.FingerList source) {
		FingerList target = LmemfFactory.eINSTANCE.createFingerList();
		target.setCount(source.getExtendedCount());
		target.setEmpty(source.getEmpty());
		source.getExtendedList().forEach(finger -> {
			target.getExtended().add(convert(finger));
		});
		return target;
	}
	
	/**
	 * Finger conversion
	 */
	public static Finger convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Finger source) {
		Finger target = LmemfFactory.eINSTANCE.createFinger();
		target.setExtended(source.getExtended());
		target.setLength(source.getLength());
		return target;
	}
	
}
