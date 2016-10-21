package com.incquerylabs.iot.leapmotion.viatracep.vepl;

import com.google.common.collect.Lists;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_BENT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_EXTENDED_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.EXTENDED_FINGER_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.GRAB_STRENGTH_HIGH_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SLOW_DOWN_LEFT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SLOW_DOWN_RIGHT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SPEED_UP_LEFT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SPEED_UP_RIGHT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.TAP_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.ALL_BENT_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.ALL_EXTENDED_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.EXTENDED_FINGER_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.GRAB_STRENGTH_HIGH_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.SLOW_DOWN_LEFT_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.SLOW_DOWN_RIGHT_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.SPEED_UP_LEFT_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.SPEED_UP_RIGHT_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.atomic.queryresult.TAP_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.GRAB_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.patterns.complex.SELECTION_Pattern;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.rules.GrabRule;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.rules.SelectionRule;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.rules.SlowDownLeftRule;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.rules.SlowDownRightRule;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.rules.SpeedUpLeftRule;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.rules.SpeedUpRightRule;
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
   * Factory method for event class {@link TAP_Event}.
   */
  public TAP_Event createTAP_Event(final EventSource eventSource) {
    return new TAP_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link TAP_Event}.
   */
  public TAP_Event createTAP_Event() {
    return new TAP_Event(null);
  }
  
  /**
   * Factory method for event class {@link SPEED_UP_LEFT_Event}.
   */
  public SPEED_UP_LEFT_Event createSPEED_UP_LEFT_Event(final EventSource eventSource) {
    return new SPEED_UP_LEFT_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link SPEED_UP_LEFT_Event}.
   */
  public SPEED_UP_LEFT_Event createSPEED_UP_LEFT_Event() {
    return new SPEED_UP_LEFT_Event(null);
  }
  
  /**
   * Factory method for event class {@link SLOW_DOWN_LEFT_Event}.
   */
  public SLOW_DOWN_LEFT_Event createSLOW_DOWN_LEFT_Event(final EventSource eventSource) {
    return new SLOW_DOWN_LEFT_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link SLOW_DOWN_LEFT_Event}.
   */
  public SLOW_DOWN_LEFT_Event createSLOW_DOWN_LEFT_Event() {
    return new SLOW_DOWN_LEFT_Event(null);
  }
  
  /**
   * Factory method for event class {@link SPEED_UP_RIGHT_Event}.
   */
  public SPEED_UP_RIGHT_Event createSPEED_UP_RIGHT_Event(final EventSource eventSource) {
    return new SPEED_UP_RIGHT_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link SPEED_UP_RIGHT_Event}.
   */
  public SPEED_UP_RIGHT_Event createSPEED_UP_RIGHT_Event() {
    return new SPEED_UP_RIGHT_Event(null);
  }
  
  /**
   * Factory method for event class {@link SLOW_DOWN_RIGHT_Event}.
   */
  public SLOW_DOWN_RIGHT_Event createSLOW_DOWN_RIGHT_Event(final EventSource eventSource) {
    return new SLOW_DOWN_RIGHT_Event(eventSource);
  }
  
  /**
   * Factory method for event class {@link SLOW_DOWN_RIGHT_Event}.
   */
  public SLOW_DOWN_RIGHT_Event createSLOW_DOWN_RIGHT_Event() {
    return new SLOW_DOWN_RIGHT_Event(null);
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
   * Factory method for atomic query result event pattern {@link TAP_Pattern}.
   */
  public TAP_Pattern createTAP_Pattern() {
    return new TAP_Pattern();
  }
  
  /**
   * Factory method for atomic query result event pattern {@link SPEED_UP_LEFT_Pattern}.
   */
  public SPEED_UP_LEFT_Pattern createSPEED_UP_LEFT_Pattern() {
    return new SPEED_UP_LEFT_Pattern();
  }
  
  /**
   * Factory method for atomic query result event pattern {@link SLOW_DOWN_LEFT_Pattern}.
   */
  public SLOW_DOWN_LEFT_Pattern createSLOW_DOWN_LEFT_Pattern() {
    return new SLOW_DOWN_LEFT_Pattern();
  }
  
  /**
   * Factory method for atomic query result event pattern {@link SPEED_UP_RIGHT_Pattern}.
   */
  public SPEED_UP_RIGHT_Pattern createSPEED_UP_RIGHT_Pattern() {
    return new SPEED_UP_RIGHT_Pattern();
  }
  
  /**
   * Factory method for atomic query result event pattern {@link SLOW_DOWN_RIGHT_Pattern}.
   */
  public SLOW_DOWN_RIGHT_Pattern createSLOW_DOWN_RIGHT_Pattern() {
    return new SLOW_DOWN_RIGHT_Pattern();
  }
  
  /**
   * Factory method for complex event pattern {@link SELECTION_Pattern}.
   */
  public SELECTION_Pattern createSELECTION_Pattern() {
    return new SELECTION_Pattern();
  }
  
  /**
   * Factory method for complex event pattern {@link GRAB_Pattern}.
   */
  public GRAB_Pattern createGRAB_Pattern() {
    return new GRAB_Pattern();
  }
  
  /**
   * Factory method for rule {@link SpeedUpLeftRule}.
   */
  public Class<? extends ICepRule> rule_SpeedUpLeftRule() {
    return SpeedUpLeftRule.class;
  }
  
  /**
   * Factory method for rule {@link SlowDownLeftRule}.
   */
  public Class<? extends ICepRule> rule_SlowDownLeftRule() {
    return SlowDownLeftRule.class;
  }
  
  /**
   * Factory method for rule {@link SpeedUpRightRule}.
   */
  public Class<? extends ICepRule> rule_SpeedUpRightRule() {
    return SpeedUpRightRule.class;
  }
  
  /**
   * Factory method for rule {@link SlowDownRightRule}.
   */
  public Class<? extends ICepRule> rule_SlowDownRightRule() {
    return SlowDownRightRule.class;
  }
  
  /**
   * Factory method for rule {@link SelectionRule}.
   */
  public Class<? extends ICepRule> rule_SelectionRule() {
    return SelectionRule.class;
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
    rules.add(SpeedUpLeftRule.class);
    rules.add(SlowDownLeftRule.class);
    rules.add(SpeedUpRightRule.class);
    rules.add(SlowDownRightRule.class);
    rules.add(SelectionRule.class);
    rules.add(GrabRule.class);
    return rules;
  }
}
