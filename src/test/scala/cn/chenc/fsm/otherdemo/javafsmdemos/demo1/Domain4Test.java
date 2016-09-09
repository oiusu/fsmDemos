package cn.chenc.fsm.otherdemo.javafsmdemos.demo1;

import cn.chenc.fsm.otherdemo.javafsmdemos.demo1.Door;

/**
 * Created by ChenC on 2016/9/4 0004.
 */
public class Domain4Test {

    public static void main(String[] args) {
        Door door=new Door();

        //1. 初始状态
        System.out.println(door.status());

        //2. 转移到Opening状态
        door.touch();
        System.out.println(door.status());

        //3. 转移到Open状态
        door.complete();
        System.out.println(door.status());

        //4. 转移到Closing状态
        door.timeout();
        System.out.println(door.status());

        //5. 回到Closed状态
        door.complete();
        System.out.println(door.status());
    }
}
