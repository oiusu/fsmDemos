package cn.chenc.rpc

/**
  * Created by root on 2016/5/13.
  */
class WorkerInfo(val id: String, val memory: Int, val cores: Int) {

  //TODO 上一次心跳
  var lastHeartbeatTime : Long = _
}
