package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.anonymous;

import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.ALL_BENT_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.ALL_EXTENDED_Pattern;
import org.eclipse.viatra.cep.core.api.patterns.ParameterizableComplexEventPattern;
import org.eclipse.viatra.cep.core.metamodels.automaton.EventContext;
import org.eclipse.viatra.cep.core.metamodels.events.EventsFactory;

@SuppressWarnings("all")
public class _AnonymousPattern_1 extends ParameterizableComplexEventPattern {
  public _AnonymousPattern_1() {
    super();
    setOperator(EventsFactory.eINSTANCE.createFOLLOWS());
    
    // contained event patterns
    addEventPatternRefrence(new ALL_EXTENDED_Pattern(), 1);
    addEventPatternRefrence(new ALL_BENT_Pattern(), 1);
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.anonymous._anonymouspattern_1");setEventContext(EventContext.CHRONICLE);
  }
}
