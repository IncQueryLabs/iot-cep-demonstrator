package com.incquerylabs.iot.leapmotion.viatracep.vepl.rules;

import com.google.common.collect.Lists;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.jobs.SlowDownRightRule_Job;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.SLOW_DOWN_RIGHT_Pattern;
import java.util.List;
import org.eclipse.viatra.cep.core.api.evm.CepActivationStates;
import org.eclipse.viatra.cep.core.api.patterns.IObservableComplexEventPattern;
import org.eclipse.viatra.cep.core.api.rules.CepJob;
import org.eclipse.viatra.cep.core.api.rules.ICepRule;
import org.eclipse.viatra.cep.core.metamodels.events.EventPattern;

@SuppressWarnings("all")
public class SlowDownRightRule implements ICepRule {
  private List<EventPattern> eventPatterns = Lists.newArrayList();
  
  private CepJob<IObservableComplexEventPattern> job = new SlowDownRightRule_Job(CepActivationStates.ACTIVE);
  
  public SlowDownRightRule() {
    eventPatterns.add(new SLOW_DOWN_RIGHT_Pattern());
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
