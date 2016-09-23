package com.incquerylabs.iot.leapmotion.viatracep.vepl;

import com.google.common.collect.Lists;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_BENT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_EXTENDED_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.EXTENDED_FINGER_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.GRAB_STRENGTH_HIGH_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.ALL_BENT_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.ALL_EXTENDED_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.EXTENDED_FINGER_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.GRAB_STRENGTH_HIGH_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.GRAB_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.rules.GrabRule;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.rules.GrabStrengthRule;
import java.util.List;
import org.eclipse.viatra.cep.core.api.rules.ICepRule;
import org.eclipse.viatra.cep.core.metamodels.events.EventSource;

@SuppressWarnings("all")
public class CepFactory {
  private static CepFactory instance;
  
  public static CepFactory getInstance() {
    if(instance == null){
    	instance = new CepFactory();
    }
    return instance;
  }
  
  /**
   * Factory method for event class {@link ALL_EXTENDED_Event}.
   */
  public ALL_EXTENDED_Event createALL_EXTENDED_Event(final EventSource eventSource) {
    return new ALL_EXTENDED_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link ALL_EXTENDED_Event}.
   */
  public ALL_EXTENDED_Event createALL_EXTENDED_Event() {
    return new ALL_EXTENDED_Event(null);
  }
  
  /**
   * Factory method for event class {@link ALL_BENT_Event}.
   */
  public ALL_BENT_Event createALL_BENT_Event(final EventSource eventSource) {
    return new ALL_BENT_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link ALL_BENT_Event}.
   */
  public ALL_BENT_Event createALL_BENT_Event() {
    return new ALL_BENT_Event(null);
  }
  
  /**
   * Factory method for event class {@link EXTENDED_FINGER_Event}.
   */
  public EXTENDED_FINGER_Event createEXTENDED_FINGER_Event(final EventSource eventSource) {
    return new EXTENDED_FINGER_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link EXTENDED_FINGER_Event}.
   */
  public EXTENDED_FINGER_Event createEXTENDED_FINGER_Event() {
    return new EXTENDED_FINGER_Event(null);
  }
  
  /**
   * Factory method for event class {@link GRAB_STRENGTH_HIGH_Event}.
   */
  public GRAB_STRENGTH_HIGH_Event createGRAB_STRENGTH_HIGH_Event(final EventSource eventSource) {
    return new GRAB_STRENGTH_HIGH_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link GRAB_STRENGTH_HIGH_Event}.
   */
  public GRAB_STRENGTH_HIGH_Event createGRAB_STRENGTH_HIGH_Event() {
    return new GRAB_STRENGTH_HIGH_Event(null);
  }
  
  /**
   * Factory method for atomic query result event pattern {@link ALL_EXTENDED_Pattern}.
   */
  public ALL_EXTENDED_Pattern createALL_EXTENDED_Pattern() {
    return new ALL_EXTENDED_Pattern();
  }
  
  /**
   * Factory method for atomic query result event pattern {@link ALL_BENT_Pattern}.
   */
  public ALL_BENT_Pattern createALL_BENT_Pattern() {
    return new ALL_BENT_Pattern();
  }
  
  /**
   * Factory method for atomic query result event pattern {@link EXTENDED_FINGER_Pattern}.
   */
  public EXTENDED_FINGER_Pattern createEXTENDED_FINGER_Pattern() {
    return new EXTENDED_FINGER_Pattern();
  }
  
  /**
   * Factory method for atomic query result event pattern {@link GRAB_STRENGTH_HIGH_Pattern}.
   */
  public GRAB_STRENGTH_HIGH_Pattern createGRAB_STRENGTH_HIGH_Pattern() {
    return new GRAB_STRENGTH_HIGH_Pattern();
  }
  
  /**
   * Factory method for complex event pattern {@link GRAB_Pattern}.
   */
  public GRAB_Pattern createGRAB_Pattern() {
    return new GRAB_Pattern();
  }
  
  /**
   * Factory method for rule {@link GrabStrengthRule}.
   */
  public Class<? extends ICepRule> rule_GrabStrengthRule() {
    return GrabStrengthRule.class;
  }
  
  /**
   * Factory method for rule {@link GrabRule}.
   */
  public Class<? extends ICepRule> rule_GrabRule() {
    return GrabRule.class;
  }
  
  /**
   * Factory method for instantiating every defined rule.
   */
  public List<Class<? extends ICepRule>> allRules() {
    List<Class<? extends ICepRule>> rules = Lists.newArrayList();
    rules.add(GrabStrengthRule.class);
    rules.add(GrabRule.class);
    return rules;
  }
}
