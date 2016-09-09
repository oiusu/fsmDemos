package cn.chenc.fsm.otherdemo.javafsmdemos.demo2.state;

import cn.chenc.fsm.otherdemo.javafsmdemos.demo2.Door2;
import cn.chenc.fsm.otherdemo.javafsmdemos.demo2.DoorState;

/**
 * Created by ChenC on 2016/9/4 0004.
 */
public class DoorClosed extends DoorState {

    public DoorClosed(Door2 door) {
        super(door);
    }

    public DoorState  touch() {

        //return DoorState.OPENING;
        // 以前是 door.setState(door.OPENING);
        //door.setState(door.OPENING);
        return new DoorOpening(door);
    }
}
