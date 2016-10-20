package com.incquerylabs.iot.leapmotion.viatracep.vepl.rules;

import com.google.common.collect.Lists;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs.NotClockwiseRule_Job;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.NCLOCKWISE_Pattern;
import java.util.List;
import org.eclipse.viatra.cep.core.api.evm.CepActivationStates;
import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.cep.core.api.rules.ICepRule;
import org.eclipse.viatra.cep.core.metamodels.events.EventPattern;

@SuppressWarnings("all")
public class NotClockwiseRule implements ICepRule {
  private List<EventPattern> eventPatterns = Lists.newArrayList();
  
  private CepJob<IObservableComplexEventPattern> job = new NotClockwiseRule_Job(CepActivationStates.ACTIVE);
  
  public NotClockwiseRule() {
    eventPatterns.add(new NCLOCKWISE_Pattern());
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
