package jp.ijufumi.sample.spring.oidc.web;

import org.mitre.openid.connect.model.OIDCAuthenticationToken;
import org.mitre.openid.connect.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String index(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        logger.info("authentication : {} ", authentication);

        if (authentication != null && authentication instanceof OIDCAuthenticationToken)
        {
            UserInfo userInfo = ((OIDCAuthenticationToken) authentication).getUserInfo();
            model.addAttribute("name", userInfo.getName());
            model.addAttribute("email", userInfo.getEmail());

            logger.info("UserInfo : {}", userInfo.toJson().toString());
        }

        return "index";
    }
}
