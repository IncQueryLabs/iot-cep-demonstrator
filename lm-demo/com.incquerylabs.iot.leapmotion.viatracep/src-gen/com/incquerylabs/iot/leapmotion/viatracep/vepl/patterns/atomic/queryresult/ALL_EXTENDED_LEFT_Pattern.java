package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult;

import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_EXTENDED_LEFT_Event;
import org.eclipse.viatra.cep.core.metamodels.events.impl.AtomicEventPatternImpl;

@SuppressWarnings("all")
public class ALL_EXTENDED_LEFT_Pattern extends AtomicEventPatternImpl {
  public ALL_EXTENDED_LEFT_Pattern() {
    super();
    setType(ALL_EXTENDED_LEFT_Event.class.getCanonicalName());
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.all_extended_left_pattern");
  }
}
