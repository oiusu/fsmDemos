package cn.chenc.fsm.squirrel.demo;

//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InOrder;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
import org.squirrelframework.foundation.fsm.*;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;

public class DeclarativeStateMachineTest /*extends AbstractStateMachineTest*/ {

    @States({
        @State(name="A", entryCallMethod="entryStateA", exitCallMethod="exitStateA"),
        @State(name="B", entryCallMethod="entryStateB", exitCallMethod="exitStateB"),
        @State(name="C", entryCallMethod="entryStateC", exitCallMethod="exitStateC"),
        @State(name="D", entryCallMethod="entryStateD", exitCallMethod="exitStateD")
    })
    @Transitions({
        @Transit(from="A", to="B", on="ToB", callMethod="fromStateAToStateBOnGotoB"),
        @Transit(from="A", to="A", on="InternalA", callMethod="fromStateAToStateAOnInternalA", type= TransitionType.INTERNAL),
        @Transit(from="B", to="D", on="ToC"),
        @Transit(from="C", to="D", on="ToD", when=ExcellentCondition.class),
        @Transit(from="C", to="D", on="ToD", whenMvel="FailedCondition:::(context!=null && context>=0 && context<60)"),
        @Transit(from="D", to="A", on="ToA", callMethod="transitionWithException"),
        @Transit(from="D", to="Final", on="ToEnd", callMethod="fromStateDToFinalOnToEnd", isTargetFinal=true)
    })
    interface DeclarativeStateMachine extends StateMachine<DeclarativeStateMachine, TestState, TestEvent, Integer> {
        // entry states
        void entryStateA(TestState from, TestState to, TestEvent event, Integer context);
        void entryStateB(TestState from, TestState to, TestEvent event, Integer context);
        void entryC(TestState from, TestState to, TestEvent event, Integer context);
        void entryStateD(TestState from, TestState to, TestEvent event, Integer context);

        // transitions
        void fromStateAToStateBOnGotoB(TestState from, TestState to, TestEvent event, Integer context);
        void fromStateAToStateAOnInternalA(TestState from, TestState to, TestEvent event, Integer context);
        void transitFromBToCOnToC(TestState from, TestState to, TestEvent event, Integer context);
        void transitFromCToDOnToDWhenExcellentCondition(TestState from, TestState to, TestEvent event, Integer context);
        void transitFromCToDOnToDWhenFailedCondition(TestState from, TestState to, TestEvent event, Integer context);
        void transitionWithException(TestState from, TestState to, TestEvent event, Integer context);
        void fromStateDToFinalOnToEnd(TestState from, TestState to, TestEvent event, Integer context);

        // exit states
        void exitStateA(TestState from, TestState to, TestEvent event, Integer context);
        void exitStateB(TestState from, TestState to, TestEvent event, Integer context);
        void exitC(TestState from, TestState to, TestEvent event, Integer context);
        void exitStateD(TestState from, TestState to, TestEvent event, Integer context);

        void beforeTransitionBegin(TestState from, TestEvent event, Integer context);
        void afterTransitionCompleted(TestState from, TestState to, TestEvent event, Integer context);
        void afterTransitionDeclined(TestState from, TestEvent event, Integer context);
        void afterTransitionCausedException(TestState fromState, TestState toState, TestEvent event, Integer context);

        void start(Integer context);
        void terminate(Integer context);
    }

    static class ExcellentCondition extends AnonymousCondition<Integer> {
        @Override
        public boolean isSatisfied(Integer context) {
            return context!=null && context>80;
        }
    }

    @SuppressWarnings("serial")
    static class DeclarativeStateMachineException extends RuntimeException {
    }

    public static class DeclarativeStateMachineImpl
            extends AbstractStateMachine<DeclarativeStateMachine, TestState, TestEvent, Integer>
            implements DeclarativeStateMachine {

        private DeclarativeStateMachine monitor;

        protected void postConstruct(DeclarativeStateMachine delegator) {
            this.monitor = delegator;
        }

        @Override
        public void entryStateA(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.entryStateA(from, to, event, context);
        }

        @Override
        public void entryStateB(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.entryStateB(from, to, event, context);
        }

        @Override
        public void entryC(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.entryC(from, to, event, context);
        }

        @Override
        public void entryStateD(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.entryStateD(from, to, event, context);
        }

        @Override
        public void fromStateAToStateBOnGotoB(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.fromStateAToStateBOnGotoB(from, to, event, context);
        }

        @Override
        public void fromStateAToStateAOnInternalA(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.fromStateAToStateAOnInternalA(from, to, event, context);
        }

        @Override
        @LogExecTime
        public void transitFromBToCOnToC(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.transitFromBToCOnToC(from, to, event, context);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("How dare you to wake me up?");
            }
        }

        @Override
        public void transitFromCToDOnToDWhenExcellentCondition(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.transitFromCToDOnToDWhenExcellentCondition(from, to, event, context);
        }

        @Override
        public void transitionWithException(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.transitionWithException(from, to, event, context);
            throw new IllegalArgumentException("This exception is thrown on purpose.");
        }

        @Override
        public void fromStateDToFinalOnToEnd(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.fromStateDToFinalOnToEnd(from, to, event, context);
        }

        @Override
        public void exitStateA(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.exitStateA(from, to, event, context);
        }

        @Override
        public void exitStateB(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.exitStateB(from, to, event, context);
        }

        @Override
        public void exitC(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.exitC(from, to, event, context);
        }

        @Override
        public void exitStateD(TestState from, TestState to, TestEvent event, Integer context) {
            monitor.exitStateD(from, to, event, context);
        }

        @Override
        public void beforeTransitionBegin(TestState from, TestEvent event, Integer context) {
            super.beforeTransitionBegin(from, event, context);
            monitor.beforeTransitionBegin(from, event, context);
        }

        @Override
        public void afterTransitionCompleted(TestState from, TestState to, TestEvent event, Integer context) {
            super.afterTransitionCompleted(from, to, event, context);
            monitor.afterTransitionCompleted(from, to, event, context);
        }

        @Override
        public void afterTransitionDeclined(TestState from, TestEvent event, Integer context) {
            super.afterTransitionDeclined(from, event, context);
            monitor.afterTransitionDeclined(from, event, context);
        }

        @Override
        public void afterTransitionCausedException(TestState fromState, 
                TestState toState, TestEvent event, Integer context) {
            if(getLastException().getTargetException().getMessage().equals("This exception is thrown on purpose.")) 
                return;
            super.afterTransitionCausedException(fromState, toState, event, context);
        }

        @Override
        public void start(Integer context) {
            super.start(context);
            monitor.start(context);
        }

        @Override
        public void terminate(Integer context) {
            super.terminate(context);
            monitor.terminate(context);
        }

        @Override
        public void transitFromCToDOnToDWhenFailedCondition(TestState from,
                TestState to, TestEvent event, Integer context) {
            monitor.transitFromCToDOnToDWhenFailedCondition(from, to, event, context);
        }
    }


    public static void main(String[] args) {

        StateMachineBuilder<DeclarativeStateMachine, TestState, TestEvent, Integer> builder =
                StateMachineBuilderFactory.<DeclarativeStateMachine, TestState, TestEvent, Integer>
                        create(DeclarativeStateMachineImpl.class, TestState.class,
                        TestEvent.class, Integer.class, DeclarativeStateMachine.class);
        DeclarativeStateMachine stateMachine = builder.newStateMachine(TestState.A);
        StateMachineLogger fsmLogger = new StateMachineLogger(stateMachine);
        fsmLogger.startLogging();

    }


}
