package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult;

import com.incquerylabs.iot.leapmotion.lmemf.Gesture;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.NCLOCKWISE_Event;
import org.eclipse.viatra.cep.core.metamodels.events.impl.AtomicEventPatternImpl;

@SuppressWarnings("all")
public class NCLOCKWISE_Pattern extends AtomicEventPatternImpl {
  private Gesture gesture;
  
  public NCLOCKWISE_Pattern() {
    super();
    setType(NCLOCKWISE_Event.class.getCanonicalName());
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.nclockwise_pattern");
  }
}
