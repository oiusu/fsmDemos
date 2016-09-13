package cn.chenc.fsm.squirrel.demo;

import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * Created by ChenC on 2016/9/13.
 */
public class SampleStart {


    public static void main(String[] args) {
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(MyStateMachine.class, TestState.class, TestEvent.class, MyContext.class);

        builder.externalTransition().from(TestState.C).to(TestState.D).on(TestEvent.ToD).whenMvel(
                "MyCondition:::(context!=null && context.getValue()>80)").callMethod("fromCToD");


    }
}
