package cn.chenc.fsm.statemode.state;

import cn.chenc.fsm.bean.ContextData;
import cn.chenc.fsm.statemode.StateModeContext;
import cn.chenc.fsm.statemode.State;

/**
 * Created by ChenC on 2016/9/8.
 */

public class StateOpen extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("当前状态是 open");
        ContextData data = context.getData();
        if(data.getValid_()){
            System.out.println("open -> step1");
            context.setState(new StateStep1());
        }else{
            System.out.println("open -> 404");
            context.setState(new State404());
        }
    }

    @Override
    public boolean isFinalflag() {
        return false;
    }

}