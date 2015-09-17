package jp.ijufumi.sample.spring.oauth2.web.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorPageController {
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request)
    {
        Exception e = (Exception) request.getAttribute("exception");
        if (e != null)
        {
            model.addAttribute("message", ExceptionUtils.getMessage(e));
        }

        return "error";
    }
}
