package com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs;

import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.transformation.evm.api.Activation;
import org.eclipse.viatra.transformation.evm.api.Context;
import org.eclipse.viatra.transformation.evm.api.event.ActivationState;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class NotClockwiseRule_Job extends CepJob<IObservableComplexEventPattern> {
  public NotClockwiseRule_Job(final ActivationState activationState) {
    super(activationState);
  }
  
  @Override
  public void execute(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Context context) {
    InputOutput.<String>println("NOT CLOCKWISE ROLL");
  }
  
  @Override
  public void handleError(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Exception exception, final Context context) {
    //not gonna happen
  }
}
