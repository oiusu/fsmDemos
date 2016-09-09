package cn.chenc.fsm.otherdemo.javafsmdemos.demo3;

/**
 * Created by ChenC on 2016/9/6.
 */
public class ACCtrl{
    public static void main(String[] args) {
        Aircon0 ac = new Aircon0();//power() cool()
        System.out.println("Current State:" + ac.getState());
        ac.cool();
        ac.power();
        ac.cool();
        ac.cool();
        ac.power();
        ac.power();
    }
}