package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult;

import com.incquerylabs.iot.leapmotion.lmemf.Gesture;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.CLOCKWISE_Event;
import org.eclipse.viatra.cep.core.metamodels.events.impl.AtomicEventPatternImpl;

@SuppressWarnings("all")
public class CLOCKWISE_Pattern extends AtomicEventPatternImpl {
  private Gesture gesture;
  
  public CLOCKWISE_Pattern() {
    super();
    setType(CLOCKWISE_Event.class.getCanonicalName());
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.clockwise_pattern");
  }
}
