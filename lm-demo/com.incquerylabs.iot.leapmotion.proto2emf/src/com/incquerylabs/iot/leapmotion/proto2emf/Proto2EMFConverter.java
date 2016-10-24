package com.incquerylabs.iot.leapmotion.proto2emf;

import com.incquerylabs.iot.leapmotion.lmemf.Finger;
import com.incquerylabs.iot.leapmotion.lmemf.FingerList;
import com.incquerylabs.iot.leapmotion.lmemf.Gesture;
import com.incquerylabs.iot.leapmotion.lmemf.GestureState;
import com.incquerylabs.iot.leapmotion.lmemf.GestureType;
import com.incquerylabs.iot.leapmotion.lmemf.Hand;
import com.incquerylabs.iot.leapmotion.lmemf.HandList;
import com.incquerylabs.iot.leapmotion.lmemf.LmemfFactory;
import com.incquerylabs.iot.leapmotion.lmemf_base.Lmemf_baseFactory;
import com.incquerylabs.iot.leapmotion.lmemf_base.PointableList;
import com.incquerylabs.iot.leapmotion.lmemf_base.Vector;

// XXX: Temporary minimal implementation - only convert the needed part of the model. Will be replaced with reflective solution
public class Proto2EMFConverter {
	
	/**
	 * Convert vector
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
		source.getElementList().forEach(sourceHand->{
			target.getElements().add(convert(sourceHand));
		});
		return target;
	}
	
	/**
	 * Hand conversion (Proto -> EMF)
	 */
	public static Hand convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Hand source) {
		Hand target = LmemfFactory.eINSTANCE.createHand();
		target.setGrabStrength(source.getGrabStrength());
		target.setPalmNormal(convert(source.getPalmNormal()));
		target.setPalmPosition(convert(source.getPalmPosition()));
		target.setPalmVelocity(convert(source.getPalmVelocity()));
		target.setLeft(source.getLeft());
		target.setRight(source.getRight());
		target.setFingers(convert(source.getFingers()));
		return target;
	}
	
	/**
	 * PointableList conversion
	 */
	public static PointableList<Finger> convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.PointableList source) {
		PointableList<Finger> target = LmemfFactory.eINSTANCE.createFingerList();
		target.setFrontmost(convert(source.getFrontmost()));
		source.getExtendedList().forEach(finger -> {
			target.getExtended().add(convert(finger));
		});
		return target;
	}
	
	/**
	 * Pointable conversion
	 */
	public static Finger convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Pointable source) {
		Finger target = LmemfFactory.eINSTANCE.createFinger();
		target.setDirection(convert(source.getDirection()));
		target.setLength(source.getLength());
		return target;
	}
	
	/**
	 * FingerList
	 */
	public static FingerList convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.FingerList source) {
		FingerList target = LmemfFactory.eINSTANCE.createFingerList();
		target.setCount(source.getCount());
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
	
	/**
	 * Gesture conversion
	 */
	public static Gesture convert(com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Gesture source) {
		Gesture target = LmemfFactory.eINSTANCE.createGesture();
		return merge(target, source);
	}
	
	public static Gesture merge(Gesture target, com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos.Gesture source) {
		target.setType(GestureType.valueOf(source.getType().name()));
		target.setState(GestureState.valueOf(source.getState().name()));
		target.setDuration(source.getDuration());
		target.setNormal(convert(source.getNormal()));
		target.setPointables(convert(source.getPointables()));
		target.setHands(convert(source.getHands()));
		return target;
	}
	
}
