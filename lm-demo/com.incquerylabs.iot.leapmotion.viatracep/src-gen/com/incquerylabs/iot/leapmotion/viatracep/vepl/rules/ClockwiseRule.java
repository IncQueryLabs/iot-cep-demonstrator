package com.incquerylabs.iot.leapmotion.viatracep.vepl.rules;

import com.google.common.collect.Lists;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs.ClockwiseRule_Job;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.CLOCKWISE_Pattern;
import java.util.List;
import org.eclipse.viatra.cep.core.api.evm.CepActivationStates;
import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.cep.core.api.rules.ICepRule;
import org.eclipse.viatra.cep.core.metamodels.events.EventPattern;

@SuppressWarnings("all")
public class ClockwiseRule implements ICepRule {
  private List<EventPattern> eventPatterns = Lists.newArrayList();
  
  private CepJob<IObservableComplexEventPattern> job = new ClockwiseRule_Job(CepActivationStates.ACTIVE);
  
  public ClockwiseRule() {
    eventPatterns.add(new CLOCKWISE_Pattern());
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
