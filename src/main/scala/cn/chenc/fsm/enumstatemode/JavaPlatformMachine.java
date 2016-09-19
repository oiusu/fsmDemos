package cn.chenc.fsm.enumstatemode;

import cn.chenc.fsm.bean.ContextData;

/**
 * Created by ChenC on 2016/9/6.
 */
public class JavaPlatformMachine {
    ContextData data = new ContextData();
    JavaPlatformState state = JavaPlatformState.OPEN;
    //Action
    public void valid(){
        state.valid(this);
    }
    public void first(){
        state.first(this);
    }
    public void businessLine(){
        state.businessLine(this);
    }
    public void district(){
        state.district(this);
    }
}
