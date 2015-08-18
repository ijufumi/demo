package jp.ijufumi.demo.scala.springboot.web

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
 * Created by ijufumi on 2015/08/15.
 */
@RestController
class IndexController {
  val logger = LoggerFactory.getLogger(getClass)

  @RequestMapping(value = Array("/hello"))
  def index : String = {
    logger.info("Hello, World!!")
    return "{success:true}"
  }
}
