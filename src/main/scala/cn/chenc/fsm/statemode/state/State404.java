package cn.chenc.fsm.statemode.state;

import cn.chenc.fsm.bean.ContextData;
import cn.chenc.fsm.statemode.Context;
import cn.chenc.fsm.statemode.State;
import scala.reflect.internal.Trees;

/**
 * Created by ChenC on 2016/9/8.
 */
public class State404 extends State {



    @Override
    public void Handle(Context context) {

        System.out.println("当前状态是 404  do something");
    }

    @Override
    public boolean isFinalflag() {
        return true;
    }
}
