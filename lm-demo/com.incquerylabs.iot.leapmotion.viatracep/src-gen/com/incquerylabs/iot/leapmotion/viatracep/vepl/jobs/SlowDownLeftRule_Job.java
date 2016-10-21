package com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs;

import com.incquerylabs.iot.leapmotion.proto.ComplexGestures;
import com.incquerylabs.iot.leapmotion.viatracep.util.GestureUtils;
import java.util.List;
import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.cep.core.metamodels.events.Event;
import org.eclipse.viatra.transformation.evm.api.Activation;
import org.eclipse.viatra.transformation.evm.api.Context;
import org.eclipse.viatra.transformation.evm.api.event.ActivationState;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class SlowDownLeftRule_Job extends CepJob<IObservableComplexEventPattern> {
  public SlowDownLeftRule_Job(final ActivationState activationState) {
    super(activationState);
  }
  
  @Override
  public void execute(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Context context) {
    try {
      IObservableComplexEventPattern _atom = ruleInstance.getAtom();
      List<Event> _observedAtomicEventInstances = _atom.getObservedAtomicEventInstances();
      Event _get = _observedAtomicEventInstances.get(0);
      final long t = _get.getTimestamp();
      GestureUtils.INSTANCE.processGesture(ComplexGestures.ComplexGesture.Type.TYPE_SLOW_DOWN, t, 10);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public void handleError(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Exception exception, final Context context) {
    //not gonna happen
  }
}
