package cn.chenc.fsm.otherdemo.elevator

import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
 * Created by tbray on 6/23/14.
 */
trait StopSystemAfterAll extends BeforeAndAfterAll{
  this: TestKit with Suite =>
  override protected def afterAll() {
    super.afterAll()
    system.shutdown()
  }

}
