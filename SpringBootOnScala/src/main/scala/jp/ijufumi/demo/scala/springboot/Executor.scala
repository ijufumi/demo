package jp.ijufumi.demo.scala.springboot

import java.util.concurrent.TimeUnit

import jp.ijufumi.demo.scala.springboot.Main._
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.{ComponentScan, Configuration}
import scala.collection.JavaConversions._

/**
 * Created by ijufumi on 2015/08/15.
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
class Executor {
  val logger = LoggerFactory.getLogger(getClass)
  def execute(args : Array[String]): Unit = {
    val context  = SpringApplication.run(getClass)
    try
    {
      logger.info("execute start");
      TimeUnit.SECONDS.sleep(30)
      logger.info("execute end");
    }
    finally {
      context.close();
    }
  }
}
