package jp.ijufumi.spring;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Thymeleaf 3 を Spring Boot で使うための Configuration クラス。
 */
@Configuration
@ConditionalOnClass(SpringTemplateEngine.class)
@EnableConfigurationProperties(ThymeleafProperties.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class ThymeleafConfig implements ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(getClass());

    ApplicationContext context;

    @Autowired
    ThymeleafProperties thymeleafProperties;

    public void setApplicationContext(ApplicationContext context)
    {
        this.context = context;
    }

    @Bean
    public ViewResolver viewResolver()
    {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");

        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine()
    {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());

        return templateEngine;
    }

    private ITemplateResolver templateResolver()
    {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(context);

        templateResolver.setPrefix(thymeleafProperties.getPrefix());
        templateResolver.setSuffix(thymeleafProperties.getSuffix());
        templateResolver.setTemplateMode(thymeleafProperties.getMode());
        templateResolver.setCacheable(thymeleafProperties.isCache());
        templateResolver.setCharacterEncoding(thymeleafProperties.getEncoding().name());

        return templateResolver;
    }

    @PostConstruct
    public void postConstruct()
    {
        logger.info("{} : {}", "suffix", thymeleafProperties.getSuffix());
        logger.info("{} : {}", "prefix", thymeleafProperties.getPrefix());
        logger.info("{} : {}", "characterEncoding", thymeleafProperties.getEncoding());
        logger.info("{} : {}", "templateMode", thymeleafProperties.getMode());
        logger.info("{} : {}", "cacheable", thymeleafProperties.isCache());
    }
}
