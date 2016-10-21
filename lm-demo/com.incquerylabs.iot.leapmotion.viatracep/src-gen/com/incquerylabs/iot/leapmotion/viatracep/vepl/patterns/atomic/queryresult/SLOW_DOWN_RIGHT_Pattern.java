package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult;

import com.incquerylabs.iot.leapmotion.lmemf.Gesture;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SLOW_DOWN_RIGHT_Event;
import org.eclipse.viatra.cep.core.metamodels.events.impl.AtomicEventPatternImpl;

@SuppressWarnings("all")
public class SLOW_DOWN_RIGHT_Pattern extends AtomicEventPatternImpl {
  private Gesture gesture;
  
  public SLOW_DOWN_RIGHT_Pattern() {
    super();
    setType(SLOW_DOWN_RIGHT_Event.class.getCanonicalName());
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.slow_down_right_pattern");
  }
}
