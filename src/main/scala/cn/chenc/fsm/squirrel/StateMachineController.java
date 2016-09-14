package cn.chenc.fsm.squirrel;

import cn.chenc.fsm.squirrel.StateMachineController.FSMEvent;
import cn.chenc.fsm.squirrel.StateMachineController.FSMState;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

/**
 * Created by ChenC on 2016/9/14.
 state machine(T), state(S), event(E) and context(C)
 T代表实现的状态机类型。
 S代表实现的状态类型。
 E代表实现的事件类型。
 C代表实现的外部上下文类型。
 */

@Transitions({
        @Transit(from="Open", to="NotFound", on="ToNotFound",  whenMvel="MyCondition:::(!context.getValid_())"),
        @Transit(from="Open", to="Step1", on="ToStep1",  whenMvel="MyCondition:::(context.getValid_())")
})
@States({
        @State(name="NotFound", entryCallMethod="ontoNotFound"),
        @State(name="Step1", entryCallMethod="ontoStep1"),
        @State(name="ReturnDimension", entryCallMethod="ontoReturnDimension"),
        @State(name="Step2", entryCallMethod="ontoStep2"),
        @State(name="PreciseAdvertising", entryCallMethod="ontoPreciseAdvertising"),
        @State(name="Step3", entryCallMethod="ontoStep3"),
        @State(name="Top9", entryCallMethod="ontoTop9"),
        @State(name="Step4", entryCallMethod="ontoStep4")
})
@StateMachineParameters(stateType=FSMState.class, eventType=FSMEvent.class, contextType=FSMContextData.class)
public  class StateMachineController extends AbstractUntypedStateMachine {

    public enum FSMEvent {
        ToNotFound, ToReturnDimension, ToPreciseAdvertising, ToTop9,
        ToStep1,ToStep2,ToStep3,ToStep4
    }
    public enum FSMState {
        Open,Step1,Step2,Step3,Step4,
        NotFound,ReturnDimension,PreciseAdvertising,Top9
    }

    protected void ontoNotFound(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep1(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoReturnDimension(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep2(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoPreciseAdvertising(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep3(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoTop9(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep4(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
}