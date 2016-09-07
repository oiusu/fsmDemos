package cn.chenc.fsm.platform

import akka.actor.{ActorRef, ActorSystem, Props}

/**
  * Created by ChenC on 2016/9/6.
  */
object ActorRefFactory extends App{

//  def main(args: Array[String]) {
//
//    val system = ActorSystem()
//    val platformMachine: ActorRef = system.actorOf(Props[PlatformMachine],"plantformTest")
//
//    platformMachine ! SetInitData(true,false,true,true)
//    platformMachine ! ToNotFound
//    platformMachine ! ToReturnDimension
//    platformMachine ! ToPreciseAdvertising
//    platformMachine ! ToTop9
//
//    system.awaitTermination()
//  }



    val system = ActorSystem()
    //var platformMachine =
//    val platformMachine: ActorRef = system.actorOf(Props[PlatformMachine],"plantformTest")
    system.awaitTermination()


//    def getPlatformMachine(): ActorRef = {
//      return platformMachine
//    }

    def getPlatformSystem(): ActorSystem = {
      return system
    }

}
