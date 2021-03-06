package com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex;

import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.anonymous._AnonymousPattern_1;
import org.eclipse.viatra.cep.core.api.patterns.ParameterizableComplexEventPattern;
import org.eclipse.viatra.cep.core.metamodels.automaton.EventContext;
import org.eclipse.viatra.cep.core.metamodels.events.EventsFactory;
import org.eclipse.viatra.cep.core.metamodels.events.Timewindow;

@SuppressWarnings("all")
public class STOP_LEFT_Pattern extends ParameterizableComplexEventPattern {
  public STOP_LEFT_Pattern() {
    super();
    setOperator(EventsFactory.eINSTANCE.createFOLLOWS());
    
    // contained event patterns
    addEventPatternRefrence(new _AnonymousPattern_1(), 1);
    						
    Timewindow timewindow = EventsFactory.eINSTANCE.createTimewindow();
    timewindow.setTime(2000);
    setTimewindow(timewindow);
    	
    setId("com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.stop_left_pattern");setEventContext(EventContext.CHRONICLE);
  }
}
