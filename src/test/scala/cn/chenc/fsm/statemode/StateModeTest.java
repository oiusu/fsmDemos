package cn.chenc.fsm.statemode;

import cn.chenc.fsm.bean.ContextData;
import cn.chenc.fsm.statemode.state.StateOpen;

/**
 * Created by ChenC on 2016/9/8.
 */
public class StateModeTest {
    public static void main(String[] args) {
        // 设置Context的初始状态为ConcreteStateA
        ContextData data = new ContextData(true,false,true,true);
        StateModeContext context = new StateModeContext(new StateOpen(),data);
        // 不断地进行请求，同时更改状态
        int size = 4;// 请求迭代数
        for(int i = 0 ; i< size+1; i++){
            if(context.trueFlag){
                context.Request();
            }
        }
    }
}
