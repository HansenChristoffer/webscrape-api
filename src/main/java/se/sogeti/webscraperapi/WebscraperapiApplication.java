package se.sogeti.webscraperapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("se.sogeti.webscraperapi.repositories")
public class WebscraperapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebscraperapiApplication.class, args);
	}
}
