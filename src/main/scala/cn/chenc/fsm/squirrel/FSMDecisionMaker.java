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
//        FSMState typedFrom = (FSMState)from;
//        FSMEvent typedEvent = (FSMEvent)event;
        FSMState typedTo = (FSMState)to;
        FSMContextData typedContext = (FSMContextData)context;

        if(typedTo == FSMState.Open){
            stateMachine.fire(FSMEvent.ToStart, context);
        }else
        if(typedTo == FSMState.Start){
            if(!typedContext.getValid_()) {
                stateMachine.fire(FSMEvent.ToNotFound, context);
            } else {
                stateMachine.fire(FSMEvent.ToStep1, context);
            }
        }
//        else
//        if(typedTo == FSMState.Step1){
//            if(typedContext.getFirst_()) {
//                stateMachine.fire(FSMEvent.ToReturnDimension, context);
//            } else {
//                stateMachine.fire(FSMEvent.ToStep2, context);
//            }
//        }
//        else if(typedTo == FSMState.Step2){
//            if(!typedContext.getBusinessLine_()) {
//                stateMachine.fire(FSMEvent.ToPreciseAdvertising, context);
//            } else {
//                stateMachine.fire(FSMEvent.ToStep3, context);
//            }
//        }
//        else if(typedTo == FSMState.Step3){
//            if(typedContext.getDistrict_()) {
//                stateMachine.fire(FSMEvent.ToTop9, context);
//            } else {
//                stateMachine.fire(FSMEvent.ToStep4, context);
//            }
//        }
    }
}
