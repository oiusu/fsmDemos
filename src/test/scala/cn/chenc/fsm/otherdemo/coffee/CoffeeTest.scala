package cn.chenc.fsm.otherdemo.coffee

import akka.actor._
import cn.chenc.fsm.otherdemo.coffee.CoffeeProtocol._

/**
  * Created by ChenC on 2016/9/4 0004.
  */
object CoffeeTest {
  def main(args: Array[String]) {
//  /**
//      *//val actorSystem = ActorSystem("CoffeeSystem")
//      *val as: ActorSystem = ActorSystem.create("coffeeSystem")
//      *//val coffeeMachine = as.actorOf(Props(new CoffeeMachine()), "coffeeMachine")
//      *//val ActorRef  = as.actorOf(Props.create(CoffeeMachine. class))
//      *val coffeeMachine = as.actorOf(Props(classOf[CoffeeMachine], this))
//      *coffeeMachine ! SetCostOfCoffee(3)
//      *coffeeMachine ! GetCostOfCoffee
//
//      *as.shutdown()
//    */

    //-----------------------
    val system = ActorSystem()

    val coffeeMachine =  system.actorOf(Props[CoffeeMachine], "coffeeMachine")

    coffeeMachine ! SetCostOfCoffee(3)
    coffeeMachine ! GetCostOfCoffee
    coffeeMachine ! SetCostOfCoffee(5)
    coffeeMachine ! SetNumberOfCoffee(10)
    //coffeeMachine ! SubscribeTransitionCallBack(testActor)

    //CurrentState(coffeeMachine, Open)

    coffeeMachine ! Deposit(2)
    coffeeMachine ! Deposit(2)
    coffeeMachine ! Deposit(2)

    //Transition(coffeeMachine, Open, ReadyToBuy)

    //coffeeMachine ! Cancel

//    expectMsgPF(){
//      case Balance(value)=>value==6
//    }

    //Transition(coffeeMachine, ReadyToBuy, Open)

    coffeeMachine ! GetNumberOfCoffee

    //expectMsg(10)

    //system.shutdown()
    system.awaitTermination()
    //-----------------------

  }

}
