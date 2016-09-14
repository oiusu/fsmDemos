package cn.chenc.fsm.statemode.state;

import cn.chenc.fsm.statemode.StateModeContext;
import cn.chenc.fsm.statemode.State;

/**
 * Created by ChenC on 2016/9/8.
 */
public class StatePreciseAdvertising extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("当前状态是 preciseAdvertising(精准广告)  do something");
    }

    @Override
    public boolean isFinalflag() {
        return true;
    }
}
