package com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs;

import com.google.common.collect.Iterables;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.EXTENDED_FINGER_Event;
import java.util.List;
import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.cep.core.metamodels.events.Event;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.transformation.evm.api.Activation;
import org.eclipse.viatra.transformation.evm.api.Context;
import org.eclipse.viatra.transformation.evm.api.event.ActivationState;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class SelectionRule_Job extends CepJob<IObservableComplexEventPattern> {
  public SelectionRule_Job(final ActivationState activationState) {
    super(activationState);
  }
  
  @Override
  public void execute(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Context context) {
    IObservableComplexEventPattern _atom = ruleInstance.getAtom();
    List<Event> _observedAtomicEventInstances = _atom.getObservedAtomicEventInstances();
    Iterable<EXTENDED_FINGER_Event> _filter = Iterables.<EXTENDED_FINGER_Event>filter(_observedAtomicEventInstances, EXTENDED_FINGER_Event.class);
    final EXTENDED_FINGER_Event event = ((EXTENDED_FINGER_Event[])Conversions.unwrapArray(_filter, EXTENDED_FINGER_Event.class))[0];
    BasePatternMatch _patternMatch = event.getPatternMatch();
    final Object id = _patternMatch.get("n");
  }
  
  @Override
  public void handleError(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Exception exception, final Context context) {
    //not gonna happen
  }
}
