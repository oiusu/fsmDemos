package cn.chenc.fsm.otherdemo.javafsmdemos.demo3;

/**
 * Created by ChenC on 2016/9/6.
 */
/**
 * 空调Aircon1。使用状态模式重构Aircon0，使用enum State1编写状态类层次。
 * @author (yqj2065)
 * @version 0.1
 */
public class Aircon1{
    State1 state= State1.OFF;//private改默认，删除getState()。
    //两个Action
    public void power(){//按power键
        state.power(this);
    }
    public void cool(){//按制冷键
        state.cool(this);
    }
    /**
     * ACCtrl的代码。
     */
    public static void main(String[] args){
        Aircon1 ac = new Aircon1();
        System.out.println("Current State:" + ac.state.name());
        ac.cool();
        ac.power();
        ac.cool();
        ac.cool();
        ac.power();
        ac.power();
        ac.power();

    }
}
