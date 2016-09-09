package cn.chenc.fsm.akkademo

import akka.actor.{ActorRef, ActorSystem, Props}
import cn.chenc.fsm.akkademo.PlatformMachine._

/**
  * Created by ChenC on 2016/9/7.
  */
object PlatformTest extends App{
  val system = ActorSystem()
//  val machine: ActorRef = ActorRefFactory.getPlatformMachine()
  //private val system: ActorSystem = ActorRefFactory.getPlatformSystem()
  val machine: ActorRef = system.actorOf(Props[PlatformMachine],"plantformTest")
  machine ! SetInitData(true,false,true,true)
  machine ! ToNotFound
  machine ! ToReturnDimension
  machine ! ToPreciseAdvertising
  machine ! ToTop9

//  system.awaitTermination()
  Thread.sleep(500)
  system.shutdown()
}
