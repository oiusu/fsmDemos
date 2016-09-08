package cn.chenc.rpc

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

/**
  * Created by root on 2016/5/13.
  */
class Worker(val masterHost: String, val masterPort: Int, val memory: Int, val cores: Int) extends Actor{

  var master : ActorSelection = _
  val workerId = UUID.randomUUID().toString
  val HEART_INTERVAL = 10000

  //
  override def preStart(): Unit = {
    //跟Master建立连接
    master = context.actorSelection(s"akka.tcp://MasterSystem@$masterHost:$masterPort/user/Master")
    //向Master发送注册消息
    master ! RegisterWorker(workerId, memory, cores)
  }

  override def receive: Receive = {
    case RegisteredWorker(masterUrl) => {
      println(masterUrl)
      //启动定时器发送心跳
      import context.dispatcher
      context.system.scheduler.schedule(0 millis, HEART_INTERVAL millis, self, SendHeartbeat)
    }

    case SendHeartbeat => {
      println("send heartbeat to master")
      master ! Heartbeat(workerId)
    }
  }
}

object Worker {
  def main(args: Array[String]) {
    //localhost 9999 localhost 8888 20480 24
    val host = args(0)
    val port = args(1).toInt
    val masterHost = args(2)
    val masterPort = args(3).toInt
    val memory = args(4).toInt
    val cores = args(5).toInt
    // 准备配置
    val configStr =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = "$host"
         |akka.remote.netty.tcp.port = "$port"
       """.stripMargin
    val config = ConfigFactory.parseString(configStr)
    //ActorSystem老大，辅助创建和监控下面的Actor，他是单例的
    val actorSystem = ActorSystem("WorkerSystem", config)
    actorSystem.actorOf(Props(new Worker(masterHost, masterPort, memory, cores)), "Worker")
    actorSystem.awaitTermination()
  }
}
