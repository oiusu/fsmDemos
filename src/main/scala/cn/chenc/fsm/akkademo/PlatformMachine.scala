package cn.chenc.fsm.akkademo

import akka.actor.FSM
import PlatformMachine._
import cn.chenc.fsm.akkademo.service.PlatformMachineService


/**
  * Created by ChenC on 2016/9/5.
  * State(S) x Event(E) -> Actions (A), State(S’)
  * 这些关系的意思可以这样理解:
  * 如果我们当前处于状态S，发生了E事件, 我们应执行操作A，然后将状态转换为S’
  */
//FSM Object： FSM Trait 的伴生对象，这里定义了用于FSM Trait 的一些数据结构
//伴生对象中 声明了状态和数据
object PlatformMachine{

  sealed trait PlatformState
  //定义6个State
  case object Open extends PlatformState
  case object Off extends PlatformState

  case object Step1 extends PlatformState
  case object Step2 extends PlatformState
  case object Step3 extends PlatformState
  case object Step4 extends PlatformState

  // state data container
  case class PlatformData(isValid: Boolean, isFirst: Boolean, isBusinessLine: Boolean,district:Boolean)

  //定义交互消息
  sealed trait UserMessage
  case object ToHoldOn extends  UserMessage
  case object ToNotFound extends  UserMessage
  case object ToReturnDimension extends UserMessage
  case object ToPreciseAdvertising extends UserMessage
  case object ToTop9 extends UserMessage

  case class SetInitData(isValid: Boolean, isFirst: Boolean, isBusinessLine: Boolean,district:Boolean) extends UserMessage

}

//FSM ACTOR
//状态和数据:在FSM中，有两个东西是一直存在的 - 任何时间点都有状态 ，和在状态中进行共享的数据。
//在Akka FSM，想要校验哪个是自己的数据，哪个是状态机的数据，我们只要检查这个声明。
//这代表所有的fsm的状态继承自PlatformState，而所有在状态间共享的数据就是PlatformData
class PlatformMachine extends FSM[PlatformState,PlatformData]{
  //调用java业务代码
  val service = new PlatformMachineService()

  startWith(Open, PlatformData(false,false,false,false))

  //Handlers of State
  when(Open){
    case Event(SetInitData(isValid_,isFirst_,isBusinessLine_,district_), _) =>{
      println(s"SetInitData:$isValid_ , $isFirst_ ,$isBusinessLine_ ,$district_  ")
      stay using stateData.copy(isValid = isValid_,isFirst=isFirst_,isBusinessLine=isBusinessLine_,district=district_)
    }

    case Event(ToNotFound, PlatformData(isValid,_,_,_))   => {
      if (isValid equals  false)  {
        println("goto NotFound!")
        service.notFound()
        goto(Off)
      }else{
        println("goto step1")
        goto(Step1)
      }
    }
  }

  when(Off){
    //接收off状态下的所有Event
    case _ => {
      println("end !")
      stay()
    }
  }

  when(Step1){
    case Event(ToReturnDimension, PlatformData(_,isFirst,_,_))   => {
      //是否第一次请求 /是  返回广告位大小尺寸数据
      if (isFirst equals  true){
        println("goto ReturnDimension!")
        service.returnDimension()
        goto(Off)
      }else{
        println("goto step2")
        goto(Step2)
      }
    }
  }

  when(Step2){
    case Event(ToPreciseAdvertising, PlatformData(_,_,isBusinessLine,_))   => {
      //是否业务线广告位 /是  返回精准投放
      if (isBusinessLine equals  false){
        println("goto PreciseAdvertising!")
        service.preciseAdvertising()
        goto(Off)
      }else{
        println("goto step3")
        goto(Step3)
      }
    }
  }

  when(Step3){
    case Event(ToTop9, PlatformData(_,_,_,district))   => {
      //是否有地域 /是  返回9素材
      if (district equals  true){
        println("goto Top9!")
        service.top9()
        goto(Off)
      }else{
        println("goto step4")
        goto(Step4)
      }
    }
  }

  when(Step4){
    //接收off状态下的所有Event
    case _ => {
      println("Step4 end !")
      stay()
    }
  }

  //如果没有匹配到，FSM Actor会尝试将我们的消息与whenUnhandled块中的模式进行匹配。
  whenUnhandled {
    case _ => {
      goto(Open)
    }
  }

  //在 transition 时 就是state change时可以做点啥？
  onTransition {
    case Open  -> Step1 => println("-------------------------onTransition : from Open to Step1  ! ")
    case Open  -> Off   => println("-------------------------onTransition : from Open to OFF    ! ")
    case Step1 -> Step2 => println("-------------------------onTransition : from Step1 to Step2 ! ")
    case Step1 -> Off   => println("-------------------------onTransition : from Step1 to OFF   ! ")
    case Step2 -> Step3 => println("-------------------------onTransition : from Step2 to Step3 ! ")
    case Step2 -> Off   => println("-------------------------onTransition : from Step2 to OFF   ! ")
    case Step3 -> Step4 => println("-------------------------onTransition : from Step3 to Step4 ! ")
    case Step3 -> Off   => println("-------------------------onTransition : from Step3 to OFF   ! ")
  }

}
