package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.anonymous;

import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.ALL_BENT_RIGHT_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.ALL_EXTENDED_RIGHT_Pattern;
import org.eclipse.viatra.cep.core.api.patterns.ParameterizableComplexEventPattern;
import org.eclipse.viatra.cep.core.metamodels.automaton.EventContext;
import org.eclipse.viatra.cep.core.metamodels.events.EventsFactory;

@SuppressWarnings("all")
public class _AnonymousPattern_2 extends ParameterizableComplexEventPattern {
  public _AnonymousPattern_2() {
    super();
    setOperator(EventsFactory.eINSTANCE.createFOLLOWS());
    
    // contained event patterns
    addEventPatternRefrence(new ALL_BENT_RIGHT_Pattern(), 1);
    addEventPatternRefrence(new ALL_EXTENDED_RIGHT_Pattern(), 1);
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.anonymous._anonymouspattern_2");setEventContext(EventContext.CHRONICLE);
  }
}
