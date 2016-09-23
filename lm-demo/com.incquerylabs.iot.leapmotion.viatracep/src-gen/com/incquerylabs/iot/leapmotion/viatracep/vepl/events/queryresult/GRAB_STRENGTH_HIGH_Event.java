package com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult;

import org.eclipse.viatra.cep.core.api.events.ParameterizableViatraQueryPatternEventInstance;
import org.eclipse.viatra.cep.core.metamodels.events.EventSource;

@SuppressWarnings("all")
public class GRAB_STRENGTH_HIGH_Event extends ParameterizableViatraQueryPatternEventInstance {
  public GRAB_STRENGTH_HIGH_Event(final EventSource eventSource) {
    super(eventSource);
    
  }
  
  @Override
  public boolean evaluateCheckExpression() {
    return true;
  }
}
