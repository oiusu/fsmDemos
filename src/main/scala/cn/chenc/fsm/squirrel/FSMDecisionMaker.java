package cn.chenc.fsm.squirrel;

import cn.chenc.fsm.squirrel.FSMController.FSMEvent;
import cn.chenc.fsm.squirrel.FSMController.FSMState;
import org.squirrelframework.foundation.fsm.UntypedAnonymousAction;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;

/**
 * Created by ChenC on 2016/9/14.
 */
public class FSMDecisionMaker extends UntypedAnonymousAction {
    final String name;
    FSMDecisionMaker(String name) {
        this.name = name;
    }
    @Override
    public String name() {
        return name;
    }
    @Override
    public void execute(Object from, Object to, Object event, Object context, UntypedStateMachine stateMachine) {
        FSMState typedTo = (FSMState)to;
        FSMContextData typedContext = (FSMContextData)context;

        if(typedTo == FSMState.Start){
            if(typedContext.isValid()) {//广告位是否有效
                stateMachine.fire(FSMEvent.ToStep1, context);//有效：step1
            } else {
                stateMachine.fire(FSMEvent.ToNotFound, context);//无效:404
            }
        }
        else if(typedTo == FSMState.Step1){
            if(typedContext.isPrivateAD()) {//是否私有广告位
                stateMachine.fire(FSMEvent.ToStep2, context);//是：step2
            } else {
                stateMachine.fire(FSMEvent.ToPublicDelivery, context);//否:公有投放
            }
        }
        else if(typedTo == FSMState.Step2){
            if(typedContext.isPrivateAdvertiser()) {//是否私有广告主
                stateMachine.fire(FSMEvent.ToStep3, context);//是:step3
            } else {
                stateMachine.fire(FSMEvent.ToMeteriaMatchLogic3, context);//否:物料匹配逻辑3
            }
        }
        else if(typedTo == FSMState.Step3){
            if(typedContext.isFixedDelivery()) {//广告位是否有定投广告计划
                stateMachine.fire(FSMEvent.ToMeteriaMatchLogic1, context);//是:物料匹配逻辑1
            } else {
                stateMachine.fire(FSMEvent.ToStep4, context);//否:step4
            }
        }
        else if(typedTo == FSMState.Step4){
            if(typedContext.isUnFixedPmp()) {//是否有非定投PMP广告计划
                stateMachine.fire(FSMEvent.ToMeteriaMatchLogic2, context);//是:物料匹配逻辑2
            } else {
                stateMachine.fire(FSMEvent.ToStep5, context);//否:step5
            }
        }
        else if(typedTo == FSMState.Step5){
            if(typedContext.isUnFixedUnPmp()) {//是否有非定投非PMP广告计划
                stateMachine.fire(FSMEvent.ToMeteriaMatchLogic3, context);//是:物料匹配逻辑3
            } else {
                stateMachine.fire(FSMEvent.ToPublicDelivery, context);//否:公有投放
            }
        }

    }
}
