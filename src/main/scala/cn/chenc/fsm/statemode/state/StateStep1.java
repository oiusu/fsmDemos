package cn.chenc.fsm.statemode.state;

import cn.chenc.fsm.bean.ContextData;
import cn.chenc.fsm.statemode.StateModeContext;
import cn.chenc.fsm.statemode.State;

/**
 * Created by ChenC on 2016/9/8.
 */
public class StateStep1 extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("当前状态是 step1");
        ContextData data = context.getData();
        if(data.getFirst_()){
            System.out.println("step1 -> dimension(返回尺寸)");
            context.setState(new StateDimension());
        }else{
            System.out.println("step1 -> step2");
            context.setState(new StateStep2());
        }
    }
    @Override
    public boolean isFinalflag() {
        return false;
    }
}
