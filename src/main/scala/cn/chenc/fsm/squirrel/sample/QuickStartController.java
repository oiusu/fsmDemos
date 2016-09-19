package cn.chenc.fsm.squirrel.sample;

import cn.chenc.fsm.akkademo.service.PlatformMachineService;
import cn.chenc.fsm.squirrel.FSMContextData;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedAnonymousAction;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

/**
 * Created by ChenC on 2016/9/12.
 * state machine(T), state(S), event(E) and context(C)
 T代表实现的状态机类型。
 S代表实现的状态类型。
 E代表实现的事件类型。
 C代表实现的外部上下文类型。
 */
/**
public class QuickStartController {

    // 1. Define State Machine Event
    enum FSMEvent {
        ToNotFound, ToReturnDimension, ToPreciseAdvertising, ToTop9,
        ToStep1,ToStep2,ToStep3,ToStep4
    }


    enum FSMState {
        Open,Off,Step1,Step2,Step3,Step4,
        NotFound,ReturnDimension,PreciseAdvertising,Top9

    }

    // 2. Define State Machine Class

    // 第二种定义注解注册 callMethod="fromOpenToNotFound", callMethod="fromOpenToStep1",
    @Transitions({
            @Transit(from="Open", to="NotFound", on="ToNotFound",  whenMvel="MyCondition:::(!context.getValid_())"),
            @Transit(from="Open", to="Step1", on="ToStep1",  whenMvel="MyCondition:::(context.getValid_())"),
    })
    @States({
            // @State(name="A", exitCallMethod="leftA"),
            @State(name="NotFound", entryCallMethod="ontoNotFound"),
            @State(name="Step1", entryCallMethod="ontoStep1"),
            @State(name="ReturnDimension", entryCallMethod="ontoReturnDimension"),
            @State(name="Step2", entryCallMethod="ontoStep2"),
            @State(name="PreciseAdvertising", entryCallMethod="ontoPreciseAdvertising"),
            @State(name="Step3", entryCallMethod="ontoStep3"),
            @State(name="Top9", entryCallMethod="ontoTop9"),
            @State(name="Step4", entryCallMethod="ontoStep4"),
    })
    @StateMachineParameters(stateType=FSMState.class, eventType=FSMEvent.class, contextType=FSMContextData.class)
    static class StateMachineSample extends AbstractUntypedStateMachine {
        PlatformMachineService service = new PlatformMachineService();
        //open -> NotFound
//        protected void fromOpenToNotFound(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
//            System.out.println(from+" --> "+to+" on event "+event+" with context "+context+" .");
//        }

        protected void ontoNotFound(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
            System.out.println("进入"+to+".");
            service.notFound();
        }
        //open -> Step1
//        protected void fromOpenToStep1(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
//            System.out.println(from+" --> "+to+" on event "+event+" with context "+context+" .");
//        }

        protected void ontoStep1(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
            System.out.println("进入"+to+".");
        }

        //Step1 -> ReturnDimension
//        protected void fromStep1ToReturnDimension(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
//            System.out.println(from+" --> "+to+" on event "+event+" with context "+context+" .");
//        }

        protected void ontoReturnDimension(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
            System.out.println("进入"+to+".");
            service.returnDimension();
        }

        //Step1 -> Step2
//        protected void fromStep1ToStep2(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
//            System.out.println(from+" --> "+to+" on event "+event+" with context "+context+" .");
//        }

        protected void ontoStep2(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
            System.out.println("进入"+to+".");
        }

        //Step2 -> PreciseAdvertising
//        protected void fromStep2ToPreciseAdvertising(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
//            System.out.println(from+" --> "+to+" on event "+event+" with context "+context+" .");
//        }

        protected void ontoPreciseAdvertising(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
            System.out.println("进入"+to+".");
            service.preciseAdvertising();
        }

        //Step2 -> Step3
//        protected void fromStep2ToStep3(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
//            System.out.println(from+" --> "+to+" on event "+event+" with context "+context+" .");
//        }

        protected void ontoStep3(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
            System.out.println("进入"+to+".");
        }

        //Step3 -> Top9
//        protected void fromStep3ToTop9(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
//            System.out.println(from+" --> "+to+" on event "+event+" with context "+context+" .");
//        }

        protected void ontoTop9(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
            System.out.println("进入"+to+".");
            service.top9();
        }

        //Step3 -> Step4
//        protected void fromStep3ToStep4(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
//            System.out.println(from+" --> "+to+" on event "+event+" with context "+context+" .");
//        }

        protected void ontoStep4(FSMState from, FSMState to, FSMEvent event, FSMContextData context) {
            System.out.println("进入"+to+".");
        }

    }


    static class DecisionMaker extends UntypedAnonymousAction {
        // wrap any local state in action
       // int transitionCount = 0;
        final String name;
        DecisionMaker(String name) {
            this.name = name;
        }
        @Override
        public void execute(Object from, Object to, Object event, Object context, UntypedStateMachine stateMachine) {
            FSMState typedFrom = (FSMState)from;
            FSMState typedTo = (FSMState)to;
            FSMEvent typedEvent = (FSMEvent)event;
            FSMContextData typedContext = (FSMContextData)context;

            if(typedTo == FSMState.Step1){
                if(typedContext.getFirst_()) {
                    stateMachine.fire(FSMEvent.ToReturnDimension, context);
                } else {
                    stateMachine.fire(FSMEvent.ToStep2, context);
                }
            }
            else if(typedTo == FSMState.Step2){
                if(!typedContext.getBusinessLine_()) {
                    stateMachine.fire(FSMEvent.ToPreciseAdvertising, context);
                } else {
                    stateMachine.fire(FSMEvent.ToStep3, context);
                }
            }
            else if(typedTo == FSMState.Step3){
                if(typedContext.getDistrict_()) {
                    stateMachine.fire(FSMEvent.ToTop9, context);
                } else {
                    stateMachine.fire(FSMEvent.ToStep4, context);
                }
            }

        }
        @Override
        public String name() {
            return name;
        }
    }


    public static void main(String[] args) {
        FSMContextData contextData = new FSMContextData(true,false,false,false);
        DecisionMaker decisionMaker = new DecisionMaker("DecisionMaker");
        // 3. Build State Transitions
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(StateMachineSample.class);
        //坑？这里when 只是内部条件判断，只是从from状态A to 状态B 转移中进行条件判断 判断不过则不执行状态转移
        //第一种 普通注册方法
//        builder.externalTransition().from(FSMState.Open).to(FSMState.NotFound).on(FSMEvent.ToNotFound)
//                .whenMvel("MyCondition:::(!context.getValid_())")
//                .callMethod("fromOpenToNotFound");

//        builder.externalTransition().from(FSMState.Open).to(FSMState.Step1).on(FSMEvent.ToStep1)
//                .whenMvel("MyCondition:::(context.getValid_())")
//                .callMethod("fromOpenToStep1");

//        builder.onEntry(FSMState.NotFound).callMethod("ontoNotFound");
//        builder.onEntry(FSMState.Step1).callMethod("ontoStep1");
        //第三种  同时注册多个
        builder.onEntry(FSMState.Step1).perform(decisionMaker);
        builder.onExit(FSMState.Step1).perform(decisionMaker);
        builder.transitions().from(FSMState.Step1).toAmong(FSMState.ReturnDimension, FSMState.Step2)
                .onEach(FSMEvent.ToReturnDimension, FSMEvent.ToStep2);
                //.callMethod("fromStep1ToReturnDimension|fromStep1ToStep2");

        builder.onEntry(FSMState.Step2).perform(decisionMaker);
        builder.onExit(FSMState.Step2).perform(decisionMaker);
        builder.transitions().from(FSMState.Step2).toAmong(FSMState.PreciseAdvertising, FSMState.Step3)
                .onEach(FSMEvent.ToPreciseAdvertising, FSMEvent.ToStep3);
                //.callMethod("fromStep2ToPreciseAdvertising|fromStep2ToStep3");

        builder.onEntry(FSMState.Step3).perform(decisionMaker);
        builder.onExit(FSMState.Step3).perform(decisionMaker);
        builder.transitions().from(FSMState.Step3).toAmong(FSMState.Top9, FSMState.Step4)
                .onEach(FSMEvent.ToTop9, FSMEvent.ToStep4);
                //.callMethod("fromStep3ToTop9|fromStep3ToStep4");


        // 4. Use State Machine
        UntypedStateMachine fsm = builder.newStateMachine(FSMState.Open);
        fsm.fire(FSMEvent.ToNotFound, contextData);
        fsm.fire(FSMEvent.ToStep1, contextData);
//        fsm.fire(FSMEvent.ToReturnDimension, contextData);
//        fsm.fire(FSMEvent.ToStep2, contextData);

        System.out.println("Current state is "+fsm.getCurrentState());
        fsm.terminate();
    }
}
 */