package jp.ijufumi.sample.spring.oauth2.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {

    private static final String SEPARATOR = "--------------- {} : {} ---------------";
    private static final String FORMAT = "{} : {}";

    Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info(SEPARATOR, "Header", "START");
        Enumeration<String> keys = request.getHeaderNames();
        while(keys.hasMoreElements())
        {
            String key = keys.nextElement();
            logger.info(FORMAT, key, request.getHeader(key));
        }
        logger.info(SEPARATOR, "Header", "END  ");
        logger.info(SEPARATOR, "Parameter", "START");
        keys = request.getParameterNames();
        while(keys.hasMoreElements())
        {
            String key = keys.nextElement();
            logger.info(FORMAT, key, request.getParameter(key));
        }
        logger.info(SEPARATOR, "Parameter", "END  ");
        logger.info(SEPARATOR, "Attribute", "START");
        keys = request.getAttributeNames();
        while(keys.hasMoreElements())
        {
            String key = keys.nextElement();
            logger.info(FORMAT, key, request.getAttribute(key));
        }
        logger.info(SEPARATOR, "Attribute", "END  ");
        return true;
    }

}
