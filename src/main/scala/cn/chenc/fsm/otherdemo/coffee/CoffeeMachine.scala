package cn.chenc.fsm.otherdemo.coffee

import akka.actor.FSM
import CoffeeMachine._
import CoffeeProtocol._
import org.slf4j.LoggerFactory

/**
 * Created by ChenC on 09/03/16.
 */

object CoffeeMachine {

  sealed trait MachineState
  //定义三个State  打开 可买 关闭
  case object Open extends MachineState
  case object ReadyToBuy extends MachineState
  case object PoweredOff extends MachineState
  //机器中咖啡的数量（coffeesLeft），每杯咖啡的价格（costOfCoffee），咖啡机存放的零钱（currentTxTotal）
  //如果零钱比咖啡价格低，机器就不卖咖啡，如果多，那么我们能找回零钱。
  case class MachineData(currentTxTotal: Int, costOfCoffee: Int, coffeesLeft: Int)

}



class CoffeeMachine extends FSM[MachineState, MachineData] {

  private val logger = LoggerFactory.getLogger(classOf[CoffeeMachine])

  startWith(Open, MachineData(currentTxTotal = 0, costOfCoffee =  5, coffeesLeft = 10))

  when(Open) {
    //case Event(deposit: Deposit, MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) => {...}
    case Event(_,MachineData(_,_,coffeesLeft)) if (coffeesLeft<=0) =>{
      logger.warn("No more coffee")
      sender ! MachineError("No more coffee")
      goto(PoweredOff)
    }
    //存钱买一杯咖啡
    case Event(Deposit(value), MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) if (value + currentTxTotal) >= stateData.costOfCoffee => {
      goto(ReadyToBuy) using stateData.copy(currentTxTotal = currentTxTotal + value)
    }
    //If the total deposit is less than than the price of the coffee, then stay in the current state with the current deposit amount incremented.
    case Event(Deposit(value), MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) if (value + currentTxTotal) < stateData.costOfCoffee => {
      val cumulativeValue = currentTxTotal + value //累积的钱
      logger.debug(s"staying at open with currentTxTotal $cumulativeValue")
      stay using stateData.copy(currentTxTotal = cumulativeValue)
    }
    //设置咖啡机可以售卖最大的coffee数量
    case Event(SetNumberOfCoffee(quantity), _) => stay using stateData.copy(coffeesLeft = quantity)
    //获得已有咖啡数量
    case Event(GetNumberOfCoffee, _) => sender ! (stateData.coffeesLeft); stay()
    //设置咖啡价格
    case Event(SetCostOfCoffee(price), _) =>
      println(s"SetCostOfCoffee:$price")
      stay using stateData.copy(costOfCoffee = price)


    //问机器查询咖啡的价格
    case Event(GetCostOfCoffee, _) =>
      println("GetCostOfCoffee:"+stateData.costOfCoffee)
      sender ! (stateData.costOfCoffee); stay()
  }


  when(ReadyToBuy) {
    case Event(BrewCoffee, MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) => {
      val balanceToBeDispensed = currentTxTotal - costOfCoffee
      logger.debug(s"Balance is $balanceToBeDispensed")
      if (balanceToBeDispensed > 0) { //投在咖啡机里的钱大于咖啡价格 进行找零
        sender ! Balance(value = balanceToBeDispensed)
        goto(Open) using stateData.copy(currentTxTotal = 0, coffeesLeft = coffeesLeft - 1)
      }
      else goto(Open) using stateData.copy(currentTxTotal = 0, coffeesLeft = coffeesLeft - 1)
    }
  }

  when(PoweredOff) {
    case (Event(StartUpMachine, _)) => goto(Open)
    case _ => {
      logger.warn("Machine Powered down.  Please start machine first with StartUpMachine")
      sender ! MachineError("Machine Powered down.  Please start machine first with StartUpMachine")
      stay()
    }
  }

  //fallback handler when an Event is unhandled by none of the States.
  //消息中匹配到了when中第二个参数的模式会被一个特定状态来处理。
  //如果没有匹配到，FSM Actor会尝试将我们的消息与whenUnhandled块中的模式进行匹配。
  // 理论上，所有在模式中没有匹配到的消息都会被whenUnhandled处理。
  // (我倒不太想建议编码风格不过你可以声明小点的PartialFunction并用andThen组合使用它，这样你就能在选好的状态中重用模式匹配。）
  whenUnhandled {
    case Event(ShutDownMachine, MachineData(currentTxTotal, costOfCoffee, coffeesLeft)) => {
      sender ! Balance(value = currentTxTotal)
      goto(PoweredOff) using stateData.copy(currentTxTotal = 0)
    }
    case Event(Cancel, MachineData(currentTxTotal, _, _)) => {   //取消交易 进行找零
      logger.debug(s"Balance is $currentTxTotal")
      sender ! Balance(value = currentTxTotal)
      goto(Open) using stateData.copy(currentTxTotal = 0)
    }
  }

  onTransition {
    case Open -> ReadyToBuy => logger.debug("From Transacting to ReadyToBuy")
    case ReadyToBuy -> Open => logger.debug("From ReadyToBuy to Open")
  }


}
