package cn.chenc.fsm.akkademo

import akka.actor.{ActorRef, ActorSystem, Props}
import cn.chenc.fsm.akkademo.PlatformMachine._

/**
  * Created by ChenC on 2016/9/7.
  */
object PlatformTest extends App{
  private val begin: Long = System.currentTimeMillis()
  val system = ActorSystem()
  val machine: ActorRef = system.actorOf(Props[PlatformMachine],"plantformTest")
  machine ! SetInitData(true,false,true,true)
  machine ! ToNotFound
  machine ! ToReturnDimension
  machine ! ToPreciseAdvertising
  machine ! ToTop9
  Thread.sleep(100)
  system.shutdown()
  private val end: Long = System.currentTimeMillis()
  System.out.println("方法耗时："+(end-begin));
//  system.awaitTermination()
}
