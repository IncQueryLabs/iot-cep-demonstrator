package com.incquerylabs.iot.leapmotion.viatracep.vepl.mapping;

import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllBentMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllBentMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllExtendedMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.AllExtendedMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.ClockwiseCircleGestureMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.ClockwiseCircleGestureMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.ExtendedFingerMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.ExtendedFingerMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.GrabStrengthHighMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.GrabStrengthHighMatcher;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.NonClockwiseCircleGestureMatch;
import com.incquerylabs.iot.leapmotion.proto2emf.queries.NonClockwiseCircleGestureMatcher;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_BENT_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.ALL_EXTENDED_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.CLOCKWISE_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.EXTENDED_FINGER_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.GRAB_STRENGTH_HIGH_Event;
import com.incquerylabs.iot.leapmotion.viatracep.vepl.events.queryresult.NCLOCKWISE_Event;
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
    	createallExtended_MappingRule(), 
    	createallBent_MappingRule(), 
    	createclockwiseCircleGesture_MappingRule(), 
    	creategrabStrengthHigh_MappingRule(), 
    	createnonClockwiseCircleGesture_MappingRule(), 
    	createextendedFinger_MappingRule()
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
  
  public EventDrivenTransformationRule<ClockwiseCircleGestureMatch, ClockwiseCircleGestureMatcher> createclockwiseCircleGesture_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<ClockwiseCircleGestureMatch, ClockwiseCircleGestureMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(ClockwiseCircleGestureMatcher.querySpecification());
      
      IMatchProcessor<ClockwiseCircleGestureMatch> actionOnAppear_0 = new IMatchProcessor<ClockwiseCircleGestureMatch>() {
        public void process(final ClockwiseCircleGestureMatch matchedPattern) {
          CLOCKWISE_Event event = new CLOCKWISE_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<ClockwiseCircleGestureMatch> actionOnDisappear_0 = new IMatchProcessor<ClockwiseCircleGestureMatch>() {
        public void process(final ClockwiseCircleGestureMatch matchedPattern) {
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
  
  public EventDrivenTransformationRule<NonClockwiseCircleGestureMatch, NonClockwiseCircleGestureMatcher> createnonClockwiseCircleGesture_MappingRule() {
    try{
      EventDrivenTransformationRuleFactory.EventDrivenTransformationRuleBuilder<NonClockwiseCircleGestureMatch, NonClockwiseCircleGestureMatcher> builder = new EventDrivenTransformationRuleFactory().createRule();
      builder.addLifeCycle(Lifecycles.getDefault(false, true));
      builder.precondition(NonClockwiseCircleGestureMatcher.querySpecification());
      
      IMatchProcessor<NonClockwiseCircleGestureMatch> actionOnAppear_0 = new IMatchProcessor<NonClockwiseCircleGestureMatch>() {
        public void process(final NonClockwiseCircleGestureMatch matchedPattern) {
          NCLOCKWISE_Event event = new NCLOCKWISE_Event(null);
          event.setQueryMatch(matchedPattern);
          eventStream.push(event);
        }
      };
      builder.action(CRUDActivationStateEnum.CREATED, actionOnAppear_0);
      
      IMatchProcessor<NonClockwiseCircleGestureMatch> actionOnDisappear_0 = new IMatchProcessor<NonClockwiseCircleGestureMatch>() {
        public void process(final NonClockwiseCircleGestureMatch matchedPattern) {
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
  
  public void dispose() {
    this.transformation = null;
  }
}
