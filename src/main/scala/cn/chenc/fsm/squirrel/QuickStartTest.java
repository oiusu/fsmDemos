package cn.chenc.fsm.squirrel;

import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * Created by ChenC on 2016/9/14.
 */
public class QuickStartTest {


    public static void main(String[] args) {
        FSMContextData contextData = new FSMContextData(true,false,false,false);
        FSMDecisionMaker decisionMaker = new FSMDecisionMaker("DecisionMaker");
        // 3. Build State Transitions
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(StateMachineController.class);
        //坑？这里when 只是内部条件判断，只是从from状态A to 状态B 转移中进行条件判断 判断不过则不执行状态转移
        //第三种  同时注册多个
//        builder.onEntry(StateMachineController.FSMState.Open).perform(decisionMaker);
//        builder.transitions().from(StateMachineController.FSMState.Open).toAmong(StateMachineController.FSMState.NotFound, StateMachineController.FSMState.Step1)
//                .onEach(StateMachineController.FSMEvent.ToNotFound, StateMachineController.FSMEvent.ToStep1);
        // Step1 -> Step2 ; Step1 -> ReturnDimension
        builder.onEntry(StateMachineController.FSMState.Step1).perform(decisionMaker);
        builder.transitions().from(StateMachineController.FSMState.Step1).toAmong(StateMachineController.FSMState.ReturnDimension, StateMachineController.FSMState.Step2)
                .onEach(StateMachineController.FSMEvent.ToReturnDimension, StateMachineController.FSMEvent.ToStep2);
        // Step2 -> Step3 ; Step2 -> PreciseAdvertising
        builder.onEntry(StateMachineController.FSMState.Step2).perform(decisionMaker);
        builder.transitions().from(StateMachineController.FSMState.Step2).toAmong(StateMachineController.FSMState.PreciseAdvertising, StateMachineController.FSMState.Step3)
                .onEach(StateMachineController.FSMEvent.ToPreciseAdvertising, StateMachineController.FSMEvent.ToStep3);
        // Step3 -> Step4 ; Step3 -> Top9
        builder.onEntry(StateMachineController.FSMState.Step3).perform(decisionMaker);
        builder.onExit(StateMachineController.FSMState.Step3).perform(decisionMaker);
        builder.transitions().from(StateMachineController.FSMState.Step3).toAmong(StateMachineController.FSMState.Top9, StateMachineController.FSMState.Step4)
                .onEach(StateMachineController.FSMEvent.ToTop9, StateMachineController.FSMEvent.ToStep4);

        // 4. Use State Machine
        UntypedStateMachine fsm = builder.newStateMachine(StateMachineController.FSMState.Open);
        fsm.fire(StateMachineController.FSMEvent.ToNotFound, contextData);
        fsm.fire(StateMachineController.FSMEvent.ToStep1, contextData);

        System.out.println("Current state is "+fsm.getCurrentState());
        fsm.terminate();
    }
}
