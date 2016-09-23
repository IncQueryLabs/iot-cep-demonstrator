package com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs;

import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.transformation.evm.api.Activation;
import org.eclipse.viatra.transformation.evm.api.Context;
import org.eclipse.viatra.transformation.evm.api.event.ActivationState;

@SuppressWarnings("all")
public class GrabStrengthRule_Job extends CepJob<IObservableComplexEventPattern> {
  public GrabStrengthRule_Job(final ActivationState activationState) {
    super(activationState);
  }
  
  @Override
  public void execute(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Context context) {
    System.out.println("Grab strength high!");
  }
  
  @Override
  public void handleError(final Activation<? extends IObservableComplexEventPattern> ruleInstance, final Exception exception, final Context context) {
    //not gonna happen
  }
}
