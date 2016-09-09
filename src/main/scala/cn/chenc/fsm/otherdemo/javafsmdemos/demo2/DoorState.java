package cn.chenc.fsm.otherdemo.javafsmdemos.demo2;

/**
 * Created by ChenC on 2016/9/4 0004.
 */
public abstract class DoorState {
    protected Door2 door;

    public abstract DoorState touch();

    public void complete() {
        System.out.println("complete......");
    }

    public void timeout() {
        System.out.println("timeout......");
    }

    public String status() {
        String s = getClass().getName();
        return s.substring(s.lastIndexOf('.') + 1);
    }

    public DoorState(Door2 door) {
        this.door = door;
    }
}
