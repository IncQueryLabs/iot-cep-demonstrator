package com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult;

import org.eclipse.viatra.cep.core.api.events.ParameterizableViatraQueryPatternEventInstance;
import org.eclipse.viatra.cep.core.metamodels.events.EventSource;

@SuppressWarnings("all")
public class ALL_BENT_LEFT_Event extends ParameterizableViatraQueryPatternEventInstance {
  public ALL_BENT_LEFT_Event(final EventSource eventSource) {
    super(eventSource);
    
  }
  
  @Override
  public boolean evaluateCheckExpression() {
    return true;
  }
}
