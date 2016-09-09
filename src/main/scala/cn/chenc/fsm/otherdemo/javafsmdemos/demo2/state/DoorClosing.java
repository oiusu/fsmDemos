package cn.chenc.fsm.otherdemo.javafsmdemos.demo2.state;

import cn.chenc.fsm.otherdemo.javafsmdemos.demo2.DoorState;
import cn.chenc.fsm.otherdemo.javafsmdemos.demo2.Door2;

/**
 * Created by ChenC on 2016/9/4 0004.
 */
public class DoorClosing extends DoorState {

    public DoorClosing(Door2 door) {
        super(door);
    }

    public DoorState  touch() {

        //return DoorState.CLOSING;
        // 以前是 door.setState(door.OPENING);
        //door.setState(door.OPENING);
        return new DoorOpening(door);
    }
}
