package jp.ijufumi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class)
//@Import(ThymeleafConfig.class)
@ComponentScan("jp.ijufumi.spring")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
