package com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult;

import com.incquerylabs.iot.leapmotion.lmemf.Gesture;
import org.eclipse.viatra.cep.core.api.events.ParameterizableViatraQueryPatternEventInstance;
import org.eclipse.viatra.cep.core.metamodels.events.EventSource;

@SuppressWarnings("all")
public class TAP_Event extends ParameterizableViatraQueryPatternEventInstance {
  private Gesture gesture;
  
  public TAP_Event(final EventSource eventSource) {
    super(eventSource);
    getParameters().add(gesture);
    
  }
  
  public Gesture getGesture() {
    return this.gesture;
  }
  
  public void setGesture(final Gesture gesture) {
    this.gesture = gesture;
    getParameters().set(0, gesture);
  }
  
  @Override
  public boolean evaluateCheckExpression() {
    return true;
  }
}
