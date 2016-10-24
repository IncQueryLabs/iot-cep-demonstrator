package com.incquerylabs.iot.leapmotion.viatracep.vepl.mapping;

import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllBentLeftMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllBentLeftMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllBentMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllBentMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllBentRightMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllBentRightMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllExtendedLeftMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllExtendedLeftMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllExtendedMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllExtendedMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllExtendedRightMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllExtendedRightMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.ExtendedFingerMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.ExtendedFingerMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.GrabStrengthHighMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.GrabStrengthHighMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.LeftSlowDownMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.LeftSlowDownMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.LeftSpeedUpMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.LeftSpeedUpMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.RightSlowDownMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.RightSlowDownMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.RightSpeedUpMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.RightSpeedUpMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.TapGestureMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.TapGestureMatcher;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_BENT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_BENT_LEFT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_BENT_RIGHT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_EXTENDED_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_EXTENDED_LEFT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_EXTENDED_RIGHT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.EXTENDED_FINGER_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.GRAB_STRENGTH_HIGH_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SLOW_DOWN_LEFT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SLOW_DOWN_RIGHT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SPEED_UP_LEFT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.SPEED_UP_RIGHT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.TAP_Event;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.viatra.cep.core.streams.EventStream;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.transformation.evm.specific.Lifecycles;
import org.eclipse.viatra.transformation.evm.specific.crud.CRUDActivationStateEnum;
import org.eclipse.viatra.transformation.runtime.emf.rules.EventDrivenTransformationRuleGroup;
import org.eclipse.viatra.transformation.runtime.emf.rules.eventdriven.EventDrivenTransformationRule;
import org.eclipse.viatra.transformation.runtime.emf.rules.eventdriven.EventDrivenTransformationRuleFactory;
import org.eclipse.viatra.transformation.runtime.emf.transformation.eventdriven.EventDrivenTransformation;
import org.eclipse.viatra.transformation.runtime.emf.transformation.eventdriven.InconsistentEventSemanticsException;

@SuppressWarnings("all")
public class QueryEngine2ViatraCep {
  private EventStream eventStream;
  
  private ResourceSet resourceSet;
  
  private EventDrivenTransformation transformation;
  
  private QueryEngine2ViatraCep(final ResourceSet resourceSet, final EventStream eventStream) {
    this.resourceSet = resourceSet;
    this.eventStream = eventStream;
    registerRules();
  }
  
  public static QueryEngine2ViatraCep register(final ResourceSet resourceSet, final EventStream eventStream) {
    return new QueryEngine2ViatraCep(resourceSet, eventStream);
  }
  
  public EventDrivenTransformationRuleGroup getRules() {
    EventDrivenTransformationRuleGroup ruleGroup = new EventDrivenTransformationRuleGroup(
    	createrightSlowDown_MappingRule(), 
    	creategrabStrengthHigh_MappingRule(), 
    	createallExtendedRight_MappingRule(), 
    	createallBent_MappingRule(), 
    	createallExtendedLeft_MappingRule(), 
    	createleftSpeedUp_MappingRule(), 
    	createleftSlowDown_MappingRule(), 
    	createrightSpeedUp_MappingRule(), 
    	createallExtended_MappingRule(), 
    	createtapGesture_MappingRule(), 
    	createallBentRight_MappingRule(), 
    	createextendedFinger_MappingRule(), 
    	createallBentLeft_MappingRule()
    );
    return ruleGroup;
  }
  
  private void registerRules() {
    try {
    	transformation = EventDrivenTransformation.forScope(new EMFScope(resourceSet)).addRules(getRules()).build();
    } catch (ViatraQueryException e) {
    	e.printStackTrace();
    }
  }
  
  public EventDrivenTransformationRule<RightSlowDownMatch, RightSlowDownMatcher> createrightSlowDown_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<RightSlowDownMatch, RightSlowDownMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(RightSlowDownMatcher.querySpecification());
      
      IMatchProcessor<RightSlowDownMatch> actionOnAppear_0 = new IMatchProcessor<RightSlowDownMatch>() {
        public void process(final RightSlowDownMatch matchedPattern) {
          SLOW_DOWN_RIGHT_Event event = new SLOW_DOWN_RIGHT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<RightSlowDownMatch> actionOnDisappear_0 = new IMatchProcessor<RightSlowDownMatch>() {
        public void process(final RightSlowDownMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<GrabStrengthHighMatch, GrabStrengthHighMatcher> creategrabStrengthHigh_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<GrabStrengthHighMatch, GrabStrengthHighMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(GrabStrengthHighMatcher.querySpecification());
      
      IMatchProcessor<GrabStrengthHighMatch> actionOnAppear_0 = new IMatchProcessor<GrabStrengthHighMatch>() {
        public void process(final GrabStrengthHighMatch matchedPattern) {
          GRAB_STRENGTH_HIGH_Event event = new GRAB_STRENGTH_HIGH_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<GrabStrengthHighMatch> actionOnDisappear_0 = new IMatchProcessor<GrabStrengthHighMatch>() {
        public void process(final GrabStrengthHighMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<AllExtendedRightMatch, AllExtendedRightMatcher> createallExtendedRight_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<AllExtendedRightMatch, AllExtendedRightMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(AllExtendedRightMatcher.querySpecification());
      
      IMatchProcessor<AllExtendedRightMatch> actionOnAppear_0 = new IMatchProcessor<AllExtendedRightMatch>() {
        public void process(final AllExtendedRightMatch matchedPattern) {
          ALL_EXTENDED_RIGHT_Event event = new ALL_EXTENDED_RIGHT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<AllExtendedRightMatch> actionOnDisappear_0 = new IMatchProcessor<AllExtendedRightMatch>() {
        public void process(final AllExtendedRightMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<AllBentMatch, AllBentMatcher> createallBent_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<AllBentMatch, AllBentMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(AllBentMatcher.querySpecification());
      
      IMatchProcessor<AllBentMatch> actionOnAppear_0 = new IMatchProcessor<AllBentMatch>() {
        public void process(final AllBentMatch matchedPattern) {
          ALL_BENT_Event event = new ALL_BENT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<AllBentMatch> actionOnDisappear_0 = new IMatchProcessor<AllBentMatch>() {
        public void process(final AllBentMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<AllExtendedLeftMatch, AllExtendedLeftMatcher> createallExtendedLeft_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<AllExtendedLeftMatch, AllExtendedLeftMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(AllExtendedLeftMatcher.querySpecification());
      
      IMatchProcessor<AllExtendedLeftMatch> actionOnAppear_0 = new IMatchProcessor<AllExtendedLeftMatch>() {
        public void process(final AllExtendedLeftMatch matchedPattern) {
          ALL_EXTENDED_LEFT_Event event = new ALL_EXTENDED_LEFT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<AllExtendedLeftMatch> actionOnDisappear_0 = new IMatchProcessor<AllExtendedLeftMatch>() {
        public void process(final AllExtendedLeftMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<LeftSpeedUpMatch, LeftSpeedUpMatcher> createleftSpeedUp_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<LeftSpeedUpMatch, LeftSpeedUpMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(LeftSpeedUpMatcher.querySpecification());
      
      IMatchProcessor<LeftSpeedUpMatch> actionOnAppear_0 = new IMatchProcessor<LeftSpeedUpMatch>() {
        public void process(final LeftSpeedUpMatch matchedPattern) {
          SPEED_UP_LEFT_Event event = new SPEED_UP_LEFT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<LeftSpeedUpMatch> actionOnDisappear_0 = new IMatchProcessor<LeftSpeedUpMatch>() {
        public void process(final LeftSpeedUpMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<LeftSlowDownMatch, LeftSlowDownMatcher> createleftSlowDown_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<LeftSlowDownMatch, LeftSlowDownMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(LeftSlowDownMatcher.querySpecification());
      
      IMatchProcessor<LeftSlowDownMatch> actionOnAppear_0 = new IMatchProcessor<LeftSlowDownMatch>() {
        public void process(final LeftSlowDownMatch matchedPattern) {
          SLOW_DOWN_LEFT_Event event = new SLOW_DOWN_LEFT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<LeftSlowDownMatch> actionOnDisappear_0 = new IMatchProcessor<LeftSlowDownMatch>() {
        public void process(final LeftSlowDownMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<RightSpeedUpMatch, RightSpeedUpMatcher> createrightSpeedUp_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<RightSpeedUpMatch, RightSpeedUpMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(RightSpeedUpMatcher.querySpecification());
      
      IMatchProcessor<RightSpeedUpMatch> actionOnAppear_0 = new IMatchProcessor<RightSpeedUpMatch>() {
        public void process(final RightSpeedUpMatch matchedPattern) {
          SPEED_UP_RIGHT_Event event = new SPEED_UP_RIGHT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<RightSpeedUpMatch> actionOnDisappear_0 = new IMatchProcessor<RightSpeedUpMatch>() {
        public void process(final RightSpeedUpMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<AllExtendedMatch, AllExtendedMatcher> createallExtended_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<AllExtendedMatch, AllExtendedMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(AllExtendedMatcher.querySpecification());
      
      IMatchProcessor<AllExtendedMatch> actionOnAppear_0 = new IMatchProcessor<AllExtendedMatch>() {
        public void process(final AllExtendedMatch matchedPattern) {
          ALL_EXTENDED_Event event = new ALL_EXTENDED_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<AllExtendedMatch> actionOnDisappear_0 = new IMatchProcessor<AllExtendedMatch>() {
        public void process(final AllExtendedMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<TapGestureMatch, TapGestureMatcher> createtapGesture_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<TapGestureMatch, TapGestureMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(TapGestureMatcher.querySpecification());
      
      IMatchProcessor<TapGestureMatch> actionOnAppear_0 = new IMatchProcessor<TapGestureMatch>() {
        public void process(final TapGestureMatch matchedPattern) {
          TAP_Event event = new TAP_Event(null);event.setGesture((com.incquerylabs.iot.leapmotion.lmemf.Gesture)matchedPattern.get(0));
          
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<TapGestureMatch> actionOnDisappear_0 = new IMatchProcessor<TapGestureMatch>() {
        public void process(final TapGestureMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<AllBentRightMatch, AllBentRightMatcher> createallBentRight_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<AllBentRightMatch, AllBentRightMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(AllBentRightMatcher.querySpecification());
      
      IMatchProcessor<AllBentRightMatch> actionOnAppear_0 = new IMatchProcessor<AllBentRightMatch>() {
        public void process(final AllBentRightMatch matchedPattern) {
          ALL_BENT_RIGHT_Event event = new ALL_BENT_RIGHT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<AllBentRightMatch> actionOnDisappear_0 = new IMatchProcessor<AllBentRightMatch>() {
        public void process(final AllBentRightMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<ExtendedFingerMatch, ExtendedFingerMatcher> createextendedFinger_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<ExtendedFingerMatch, ExtendedFingerMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(ExtendedFingerMatcher.querySpecification());
      
      IMatchProcessor<ExtendedFingerMatch> actionOnAppear_0 = new IMatchProcessor<ExtendedFingerMatch>() {
        public void process(final ExtendedFingerMatch matchedPattern) {
          EXTENDED_FINGER_Event event = new EXTENDED_FINGER_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<ExtendedFingerMatch> actionOnDisappear_0 = new IMatchProcessor<ExtendedFingerMatch>() {
        public void process(final ExtendedFingerMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public EventDrivenTransformationRule<AllBentLeftMatch, AllBentLeftMatcher> createallBentLeft_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<AllBentLeftMatch, AllBentLeftMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(AllBentLeftMatcher.querySpecification());
      
      IMatchProcessor<AllBentLeftMatch> actionOnAppear_0 = new IMatchProcessor<AllBentLeftMatch>() {
        public void process(final AllBentLeftMatch matchedPattern) {
          ALL_BENT_LEFT_Event event = new ALL_BENT_LEFT_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<AllBentLeftMatch> actionOnDisappear_0 = new IMatchProcessor<AllBentLeftMatch>() {
        public void process(final AllBentLeftMatch matchedPattern) {
        }
      };
      builder.action(CRUDActivationStateEnum.DELETED, actionOnDisappear_0);
      
      return builder.build();
    } catch (ViatraQueryException e) {
      e.printStackTrace();
    } catch (InconsistentEventSemanticsException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public void dispose() {
    this.transformation = null;
  }
}
