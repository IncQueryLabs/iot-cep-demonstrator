package com.incquerylabs.iot.leapmotion.viatracep.vepl.rules;

import com.google.common.collect.Lists;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs.GrabStrengthRule_Job;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.GRAB_STRENGTH_HIGH_Pattern;
import java.util.List;
import org.eclipse.viatra.cep.core.api.evm.CepActivationStates;
import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.cep.core.api.rules.ICepRule;
import org.eclipse.viatra.cep.core.metamodels.events.EventPattern;

@SuppressWarnings("all")
public class GrabStrengthRule implements ICepRule {
  private List<EventPattern> eventPatterns = Lists.newArrayList();
  
  private CepJob<IObservableComplexEventPattern> job = new GrabStrengthRule_Job(CepActivationStates.ACTIVE);
  
  public GrabStrengthRule() {
    eventPatterns.add(new GRAB_STRENGTH_HIGH_Pattern());
  }
  
  @Override
  public List<EventPattern> getEventPatterns() {
    return this.eventPatterns;
  }
  
  @Override
  public CepJob<IObservableComplexEventPattern> getJob() {
    return this.job;
  }
}
