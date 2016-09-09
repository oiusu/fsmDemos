package cn.chenc.fsm.otherdemo.javafsmdemos.demo2;
import cn.chenc.fsm.otherdemo.javafsmdemos.demo2.state.*;

import java.util.Observable;
//import DoorOpening;

/**
 * Created by ChenC on 2016/9/4 0004.
 */

public class Door2 extends Observable {
    public final DoorState CLOSED = new DoorClosed(this);
    public final DoorState CLOSING = new DoorClosing(this);
    public final DoorState OPEN = new DoorOpen(this);
    public final DoorState OPENING = new DoorOpening(this);
    public final DoorState STAYOPEN = new DoorStayOpen(this);

    private DoorState state = CLOSED;

    public void touch() {
        //state.touch();
        state=state.touch();
        // 以前是 state.touch();
        // 即将转移状态的工作留给状态实例来做，事不关己高高挂起
    }

    public void complete() {
        state.complete();
    }

    public void timeout() {
        state.timeout();
    }

    public String status() {
        return state.status();
    }

    public void setState(DoorState state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }

}