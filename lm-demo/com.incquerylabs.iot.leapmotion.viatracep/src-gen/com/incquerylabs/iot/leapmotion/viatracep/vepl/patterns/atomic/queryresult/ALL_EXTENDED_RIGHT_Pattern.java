package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult;

import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_EXTENDED_RIGHT_Event;
import org.eclipse.viatra.cep.core.metamodels.events.impl.AtomicEventPatternImpl;

@SuppressWarnings("all")
public class ALL_EXTENDED_RIGHT_Pattern extends AtomicEventPatternImpl {
  public ALL_EXTENDED_RIGHT_Pattern() {
    super();
    setType(ALL_EXTENDED_RIGHT_Event.class.getCanonicalName());
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.all_extended_right_pattern");
  }
}
