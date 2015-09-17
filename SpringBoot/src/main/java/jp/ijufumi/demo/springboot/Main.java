package jp.ijufumi.demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ijufumi on 2015/08/15.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Main {
    public static void main(String...args)
    {
        SpringApplication.run(Main.class, args);
    }
}
