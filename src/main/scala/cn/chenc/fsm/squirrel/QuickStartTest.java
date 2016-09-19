package cn.chenc.fsm.squirrel;

import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * Created by ChenC on 2016/9/14.
 */
public class QuickStartTest {


    public static void main(String[] args) {
        long begin=System.currentTimeMillis();
        //1.广告位是否有效 2.是否私有广告位 3.是否私有广告主 4.广告位是否有定投广告计划
        //5.是否有非定投PMP广告计划 6.是否有非定投非PMP广告计划 7.是否没有素材可投
        FSMContextData contextData = new FSMContextData(true,true,true,false,true,false,false);
        FSMDecisionMaker decisionMaker = new FSMDecisionMaker("DecisionMaker");
        //Build State Transitions
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(FSMController.class);

        // Start -> Step1 ; Start -> NotFound
        builder.onEntry(FSMController.FSMState.Start).perform(decisionMaker);
        builder.transitions().from(FSMController.FSMState.Start).toAmong(FSMController.FSMState.NotFound, FSMController.FSMState.Step1)
                .onEach(FSMController.FSMEvent.ToNotFound, FSMController.FSMEvent.ToStep1);
        // Step1 -> Step2 ; Step1 -> PublicDelivery
        builder.onEntry(FSMController.FSMState.Step1).perform(decisionMaker);
        builder.transitions().from(FSMController.FSMState.Step1).toAmong(FSMController.FSMState.PublicDelivery, FSMController.FSMState.Step2)
                .onEach(FSMController.FSMEvent.ToPublicDelivery, FSMController.FSMEvent.ToStep2);

        // Step2 -> Step3 ; Step2 -> MeteriaMatchLogic3
        builder.onEntry(FSMController.FSMState.Step2).perform(decisionMaker);
        builder.transitions().from(FSMController.FSMState.Step2).toAmong(FSMController.FSMState.MeteriaMatchLogic3, FSMController.FSMState.Step3)
                .onEach(FSMController.FSMEvent.ToMeteriaMatchLogic3, FSMController.FSMEvent.ToStep3);
        // Step3 -> Step4 ; Step3 -> MeteriaMatchLogic1
        builder.onEntry(FSMController.FSMState.Step3).perform(decisionMaker);
        builder.transitions().from(FSMController.FSMState.Step3).toAmong(FSMController.FSMState.MeteriaMatchLogic1, FSMController.FSMState.Step4)
                .onEach(FSMController.FSMEvent.ToMeteriaMatchLogic1, FSMController.FSMEvent.ToStep4);

        // Step4 -> Step5 ; Step4 -> MeteriaMatchLogic2
        builder.onEntry(FSMController.FSMState.Step4).perform(decisionMaker);
        builder.transitions().from(FSMController.FSMState.Step4).toAmong(FSMController.FSMState.MeteriaMatchLogic2, FSMController.FSMState.Step5)
                .onEach(FSMController.FSMEvent.ToMeteriaMatchLogic2, FSMController.FSMEvent.ToStep5);


        // Step5 -> PublicDelivery ; Step5 -> MeteriaMatchLogic3
        builder.onEntry(FSMController.FSMState.Step5).perform(decisionMaker);
        builder.transitions().from(FSMController.FSMState.Step5).toAmong(FSMController.FSMState.MeteriaMatchLogic3, FSMController.FSMState.PublicDelivery)
                .onEach(FSMController.FSMEvent.ToMeteriaMatchLogic3, FSMController.FSMEvent.ToPublicDelivery);

        //Use State Machine
        UntypedStateMachine fsm = builder.newStateMachine(FSMController.FSMState.Open);
        //Open -> Start
        fsm.fire(FSMController.FSMEvent.ToStart, contextData);

        //PublicDelivery -> MeteriaMatchLogicDiagram
        fsm.fire(FSMController.FSMEvent.ToMeteriaMatchLogicDiagram, contextData);

        //MeteriaMatchLogic1 -> Step4   没有素材可投
        fsm.fire(FSMController.FSMEvent.ToStep4, contextData);

        //MeteriaMatchLogic2 -> Step5   没有素材可投
        fsm.fire(FSMController.FSMEvent.ToStep5, contextData);

        System.out.println("Current state is "+fsm.getCurrentState());
        fsm.terminate();
        long end=System.currentTimeMillis();
        System.out.println("方法耗时："+(end-begin));
    }
}