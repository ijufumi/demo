package jp.ijufumi.demo.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ijufumi on 2015/08/15.
 */
@RestController
public class IndexController {
    @RequestMapping("/hello")
    public String index()
    {
        return "Hello, World.";
    }
}
