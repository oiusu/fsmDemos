package cn.chenc.fsm.statemode.state;

import cn.chenc.fsm.statemode.StateModeContext;
import cn.chenc.fsm.statemode.State;

/**
 * Created by ChenC on 2016/9/8.
 */
public class StateDimension  extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("当前状态是 Dimension(返回尺寸)  do something");
    }

    @Override
    public boolean isFinalflag() {
        return true;
    }
}
