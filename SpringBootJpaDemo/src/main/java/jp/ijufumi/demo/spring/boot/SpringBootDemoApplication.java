package jp.ijufumi.demo.spring.boot;

import jp.ijufumi.demo.spring.boot.jpa.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

@SpringBootApplication
public class SpringBootDemoApplication {

    @Autowired
    @Qualifier("insert2")
    Insert insert;

    static Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try (ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args)){
            stopWatch.stop();
            logger.info("execute time : {}", stopWatch.toString());
        }
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return x -> {
            insert.insert();
        };
    }
}
