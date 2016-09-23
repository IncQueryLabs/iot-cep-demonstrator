package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult;

import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.GRAB_STRENGTH_HIGH_Event;
import org.eclipse.viatra.cep.core.metamodels.events.impl.AtomicEventPatternImpl;

@SuppressWarnings("all")
public class GRAB_STRENGTH_HIGH_Pattern extends AtomicEventPatternImpl {
  public GRAB_STRENGTH_HIGH_Pattern() {
    super();
    setType(GRAB_STRENGTH_HIGH_Event.class.getCanonicalName());
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.grab_strength_high_pattern");
  }
}
