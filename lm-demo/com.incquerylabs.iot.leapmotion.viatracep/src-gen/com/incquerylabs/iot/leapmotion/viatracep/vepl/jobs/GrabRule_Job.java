package com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs;

import com.google.common.collect.Iterables;
import com.incquerylabs.iot.leapmotion.proto.LeapMotionProtos;
import com.incquerylabs.iot.leapmotion.viatracep.util.GestureUtils;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_BENT_Event;
import java.util.List;
import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.cep.core.metamodels.events.Event;
import org.eclipse.viatra.transformation.evm.api.Activation;
import org.eclipse.viatra.transformation.evm.api.Context;
import org.eclipse.viatra.transformation.evm.api.event.ActivationState;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class GrabRule_Job extends CepJob<IObservableComplexEventPattern> {
  public GrabRule_Job(final ActivationState activationState) {
    super(activationState);
  }
  
  @Override
  public void execute(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Context context) {
    try {
      IObservableComplexEventPattern _atom = ruleInstance.getAtom();
      List<Event> _observedAtomicEventInstances = _atom.getObservedAtomicEventInstances();
      final Iterable<ALL_BENT_Event> bentEvents = Iterables.<ALL_BENT_Event>filter(_observedAtomicEventInstances, ALL_BENT_Event.class);
      boolean _isEmpty = IterableExtensions.isEmpty(bentEvents);
      boolean _not = (!_isEmpty);
      if (_not) {
        ALL_BENT_Event _last = IterableExtensions.<ALL_BENT_Event>last(bentEvents);
        final long timestamp = _last.getTimestamp();
        GestureUtils.INSTANCE.performGesture(LeapMotionProtos.Gesture.Type.TYPE_GRAB, timestamp);
      }
      InputOutput.<String>println("Grab gesture!");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public void handleError(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Exception exception, final Context context) {
    //not gonna happen
  }
}
