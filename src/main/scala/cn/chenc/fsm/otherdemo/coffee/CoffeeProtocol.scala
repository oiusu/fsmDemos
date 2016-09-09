package cn.chenc.fsm.otherdemo.coffee

/**
  * Created by ChenC on 2016/9/4 0004.
  */


//交互/消息
object CoffeeProtocol {

  trait UserInteraction  //用户：喝咖啡的人
  trait VendorInteraction//供应商：维护咖啡机做管理工作的人

  case class Deposit(value: Int) extends UserInteraction//存钱买一杯咖啡
  case class Balance(value: Int) extends UserInteraction//如果钱比咖啡的价格高那么可以得到找零
  case object Cancel extends UserInteraction            //在煮咖啡前取消交易过程并拿到所有的退款
  case object BrewCoffee extends UserInteraction        //如果存的钱正好或高于咖啡价格机器就可以让咖啡机做咖啡
  case object GetCostOfCoffee extends UserInteraction   //问机器查询咖啡的价格

  case class SetNumberOfCoffee(quantity: Int) extends VendorInteraction //设置咖啡机可以售卖最大的coffee数量
  case class SetCostOfCoffee(price: Int) extends VendorInteraction      //设置咖啡价格
  case object ShutDownMachine extends VendorInteraction                 //关机
  case object StartUpMachine extends VendorInteraction                  //开机
  case object GetNumberOfCoffee extends VendorInteraction               //获得已有咖啡数量

  case class MachineError(errorMsg:String)

}