package cn.chenc.fsm.statemode.state;

import cn.chenc.fsm.bean.ContextData;
import cn.chenc.fsm.statemode.StateModeContext;
import cn.chenc.fsm.statemode.State;

/**
 * Created by ChenC on 2016/9/8.
 */
public class StateStep3 extends State {
    @Override
    public void Handle(StateModeContext context) {
        System.out.println("当前状态是 step3");
        ContextData data = context.getData();
        if(data.getDistrict_()){
            System.out.println("step3 -> Top9(返回9素材)");
            context.setState(new StateTop9());
        }else{
            System.out.println("step3 -> step4");
            context.setState(new StateStep4());
        }
    }

    @Override
    public boolean isFinalflag() {
        return false;
    }
}
