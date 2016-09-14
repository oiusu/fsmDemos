package cn.chenc.fsm.statemode.state;

import cn.chenc.fsm.bean.ContextData;
import cn.chenc.fsm.statemode.StateModeContext;
import cn.chenc.fsm.statemode.State;

/**
 * Created by ChenC on 2016/9/8.
 */
public class StateStep2 extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("当前状态是 step2");
        ContextData data = context.getData();
        if(!data.getBusinessLine_()){
            System.out.println("step2 -> preciseAdvertising(精准广告)");
            context.setState(new StatePreciseAdvertising());
        }else{
            System.out.println("step2 -> step3");
            context.setState(new StateStep3());
        }
    }

    @Override
    public boolean isFinalflag() {
        return false;
    }
}
