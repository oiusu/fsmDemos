package cn.chenc.fsm.enumstatemode

import cn.chenc.fsm.bean.ContextData
import cn.chenc.fsm.akkademo.service.PlatformMachineService

/**
  * Created by ChenC on 2016/9/8.
  */
object EnumScalaTest {
  private val service: PlatformMachineService = new PlatformMachineService

  def operate(arr :Array[Boolean]): Unit = {
    arr match {
      case Array(false,_,_,_) => service.notFound()
      case Array(true,true,_,_) => service.returnDimension()
      case Array(true,false,false,_) => service.preciseAdvertising()
      case Array(true,false,true,true) => service.top9()
      case Array(_) => println("step4")
    }
  }

  def main(args: Array[String]) {
    val arr = Array(true, false, false, false)
    operate(arr)
  }
}
