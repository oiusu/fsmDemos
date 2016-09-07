package cn.chenc.javafsm.demo2.state;

import cn.chenc.javafsm.demo2.Door2;
import cn.chenc.javafsm.demo2.DoorState;

/**
 * Created by ChenC on 2016/9/4 0004.
 */
public class DoorStayOpen extends DoorState {
    public DoorStayOpen(Door2 door) {
        super(door);
    }

    public DoorState  touch() {

        //return DoorState.CLOSING;
        // 以前是 door.setState(door.CLOSING);
        //door.setState(door.CLOSING);
        return new DoorClosing(door);
    }
}
