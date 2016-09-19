package cn.chenc.fsm.squirrel;

import cn.chenc.fsm.squirrel.FSMController.FSMEvent;
import cn.chenc.fsm.squirrel.FSMController.FSMState;
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
        @Transit(from="Open", to="Start", on="ToStart"),
        @Transit(from="PublicDelivery", to="MeteriaMatchLogicDiagram", on="ToMeteriaMatchLogicDiagram"),
        @Transit(from="MeteriaMatchLogic1", to="Step4", on="ToStep4",whenMvel="MyCondition:::(context!=null && context.isNoMaterialDelivery())"),
        @Transit(from="MeteriaMatchLogic2", to="Step5", on="ToStep5",whenMvel="MyCondition:::(context!=null && context.isNoMaterialDelivery())"),
})
@States({
        @State(name="Start", entryCallMethod="ontoStart"),
        @State(name="Step1", entryCallMethod="ontoStep1"),
        @State(name="Step2", entryCallMethod="ontoStep2"),
        @State(name="Step3", entryCallMethod="ontoStep3"),
        @State(name="Step4", entryCallMethod="ontoStep4"),
        @State(name="Step5", entryCallMethod="ontoStep5"),
        @State(name="NotFound", entryCallMethod="ontoNotFound"),
        @State(name="PublicDelivery", entryCallMethod="ontoPublicDelivery"),
        @State(name="MeteriaMatchLogicDiagram", entryCallMethod="ontoMeteriaMatchLogicDiagram"),
        @State(name="MeteriaMatchLogic1", entryCallMethod="ontoMeteriaMatchLogic1"),
        @State(name="MeteriaMatchLogic2", entryCallMethod="ontoMeteriaMatchLogic2"),
        @State(name="MeteriaMatchLogic3", entryCallMethod="ontoMeteriaMatchLogic3")
})
@StateMachineParameters(stateType=FSMState.class, eventType=FSMEvent.class, contextType=FSMContextData.class)
public  class FSMController extends AbstractUntypedStateMachine {

    public enum FSMEvent {
        ToStart,ToStep1,ToStep2,ToStep3,ToStep4,ToStep5,
        ToNotFound, ToPublicDelivery,ToMeteriaMatchLogicDiagram,
        ToMeteriaMatchLogic1,ToMeteriaMatchLogic2,ToMeteriaMatchLogic3
    }
    public enum FSMState {
        Open,Start,Step1,Step2,Step3,Step4,Step5,
        NotFound,PublicDelivery,MeteriaMatchLogicDiagram,
        MeteriaMatchLogic1,MeteriaMatchLogic2,MeteriaMatchLogic3
    }

    protected void ontoStart(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep1(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep2(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep3(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep4(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoStep5(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoNotFound(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoPublicDelivery(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoMeteriaMatchLogicDiagram(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoMeteriaMatchLogic1(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoMeteriaMatchLogic2(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
    protected void ontoMeteriaMatchLogic3(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
        System.out.println("进入"+to+".");
    }
}