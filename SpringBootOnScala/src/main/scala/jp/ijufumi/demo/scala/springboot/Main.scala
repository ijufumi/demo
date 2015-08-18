package jp.ijufumi.demo.scala.springboot

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
/**
 * Created by ijufumi on 2015/08/15.
 */
object Main {
  val logger = LoggerFactory.getLogger(getClass)
  def main(args : Array[String]): Unit = {
    logger.info("logger : {}", args)
    val executor = new Executor
    executor.execute(args)
  }
}
