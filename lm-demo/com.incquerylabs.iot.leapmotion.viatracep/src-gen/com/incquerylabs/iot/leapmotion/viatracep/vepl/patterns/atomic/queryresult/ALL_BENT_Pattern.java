package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult;

import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_BENT_Event;
import org.eclipse.viatra.cep.core.metamodels.events.impl.AtomicEventPatternImpl;

@SuppressWarnings("all")
public class ALL_BENT_Pattern extends AtomicEventPatternImpl {
  public ALL_BENT_Pattern() {
    super();
    setType(ALL_BENT_Event.class.getCanonicalName());
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.all_bent_pattern");
  }
}
