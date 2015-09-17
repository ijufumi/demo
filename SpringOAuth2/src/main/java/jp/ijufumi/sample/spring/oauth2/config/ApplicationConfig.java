package jp.ijufumi.sample.spring.oauth2.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "jp.ijufumi.sample.spring.oauth2")
@Import({WebConfig.class, WebSecurityConfig.class})
public class ApplicationConfig {

    @Bean
    public MessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public PropertiesFactoryBean properties()
    {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setFileEncoding("UTF-8");
        propertiesFactoryBean.setLocation(new ClassPathResource("application.properties"));
        propertiesFactoryBean.setIgnoreResourceNotFound(true);

        return propertiesFactoryBean;
    }
}
