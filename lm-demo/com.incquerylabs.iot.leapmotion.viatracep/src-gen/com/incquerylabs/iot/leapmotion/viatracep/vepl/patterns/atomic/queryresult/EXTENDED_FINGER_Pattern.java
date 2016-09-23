package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult;

import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.EXTENDED_FINGER_Event;
import org.eclipse.viatra.cep.core.metamodels.events.impl.AtomicEventPatternImpl;

@SuppressWarnings("all")
public class EXTENDED_FINGER_Pattern extends AtomicEventPatternImpl {
  public EXTENDED_FINGER_Pattern() {
    super();
    setType(EXTENDED_FINGER_Event.class.getCanonicalName());
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.extended_finger_pattern");
  }
}
