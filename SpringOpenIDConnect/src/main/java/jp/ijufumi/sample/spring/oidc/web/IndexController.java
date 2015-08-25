package jp.ijufumi.sample.spring.oidc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
}
