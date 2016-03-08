package jp.ijufumi.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by iju on 2016/03/08.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String index()
    {
        return "index";
    }
}
