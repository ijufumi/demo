package jp.ijufumi.sample.spring.cache.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;

@Component
@EnableSpringConfigured
@Configuration
@ComponentScan("jp.ijufumi.sample.spring.cache")
@Import({CacheConfig.class, ServletConfig.class})
public class ApplicationConfig {}
