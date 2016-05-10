package jp.ijufumi.demo.springboot.url;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootUrlDemoApplication {

	@RequestMapping("/")
	public Map index(@RequestParam(value = "p1", required = false) String p1,
					 @RequestParam(value = "p2", required = false) String p2,
					 HttpServletRequest request)
	{
		Map<String, String> value = new HashMap<>();
		value.put("p1", p1);
		value.put("p2", p2);
		value.put("url", request.getRequestURL().toString());
        value.put("query", request.getQueryString());

		return value;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUrlDemoApplication.class, args);
	}
}
