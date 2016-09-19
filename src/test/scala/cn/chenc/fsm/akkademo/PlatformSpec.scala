package cn.chenc.fsm.akkademo

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import cn.chenc.fsm.otherdemo.elevator.StopSystemAfterAll
import cn.chenc.fsm.akkademo.PlatformMachine._
import org.scalatest.{FunSpecLike, MustMatchers}

/**
 * Created by ChenC on 09/03/16.
 */
class PlatformSpec extends TestKit(ActorSystem("platform-system"))
  with MustMatchers  //must描述assertion,比如"hello" must (contain("hello"))
  with FunSpecLike
  //with StopSystemAfterAll
  with ImplicitSender {
  val begin: Long = System.currentTimeMillis()
  describe("just 4 test") {
    it("TestKit Demo") {
      val platformMachine = TestActorRef(Props(new PlatformMachine()))
      platformMachine ! SetInitData(true,false,false,true)
      platformMachine ! ToNotFound
      platformMachine ! ToReturnDimension
      platformMachine ! ToPreciseAdvertising
      platformMachine ! ToTop9
    }
  }
  val end: Long = System.currentTimeMillis()
  System.out.println("方法耗时："+(end-begin));
}
