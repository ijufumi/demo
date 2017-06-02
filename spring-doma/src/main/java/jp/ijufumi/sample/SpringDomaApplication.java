package jp.ijufumi.sample;

import jp.ijufumi.sample.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("jp.ijufumi.sample")
@SpringBootApplication
public class SpringDomaApplication {

	@Autowired
	BookDao bookDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringDomaApplication.class, args);
	}
}
