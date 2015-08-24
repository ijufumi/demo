package jp.ijufumi.sample.spring.oidc.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableSpringConfigured
@ComponentScan(basePackages = "jp.ijufumi.sample.spring.oidc")
@EnableAspectJAutoProxy
@Import({WebApplicationConfig.class, WebSecurityConfig.class})
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
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource("application.properties"));
        factoryBean.setIgnoreResourceNotFound(true);

        return factoryBean;
    }
}
