package cn.chenc.fsm.statemode;

import cn.chenc.fsm.bean.ContextData;
import cn.chenc.fsm.statemode.Context;
import cn.chenc.fsm.statemode.state.StateOpen;

/**
 * Created by ChenC on 2016/9/8.
 */
public class StateModeTest {

    public static void main(String[] args) {
        // 设置Context的初始状态为ConcreteStateA
        ContextData data = new ContextData(true,false,false,false);
        Context context = new Context(new StateOpen(),data);

        // 不断地进行请求，同时更改状态
        int size = 4;
        for(int i = 0 ; i< size+1; i++){
//            System.out.println("request!");
            context.Request();
        }

    }
}
