package jp.ijufumi.sample.spring.oauth2.web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Component
public class ExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String exceptionHandling(Exception e, HttpServletRequest request){

        logger.error(e.getMessage(), e);
        request.setAttribute("exception", e);

        return "forward:/error";
    }
}
