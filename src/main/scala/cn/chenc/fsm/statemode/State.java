package cn.chenc.fsm.statemode;

/**
 * Created by ChenC on 2016/9/8.
 */

/// 抽象状态类，定义一个接口以封装与Context的一个特定状态相关的行为
public abstract class State {
    public abstract void Handle(StateModeContext context );
    public abstract boolean isFinalflag();
}