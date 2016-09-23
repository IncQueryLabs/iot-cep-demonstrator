package com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult;

import org.eclipse.viatra.cep.core.api.events.ParameterizableViatraQueryPatternEventInstance;
import org.eclipse.viatra.cep.core.metamodels.events.EventSource;

@SuppressWarnings("all")
public class EXTENDED_FINGER_Event extends ParameterizableViatraQueryPatternEventInstance {
  public EXTENDED_FINGER_Event(final EventSource eventSource) {
    super(eventSource);
    
  }
  
  @Override
  public boolean evaluateCheckExpression() {
    return true;
  }
}
