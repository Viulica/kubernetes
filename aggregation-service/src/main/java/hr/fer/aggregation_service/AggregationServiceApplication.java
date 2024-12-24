package hr.fer.aggregation_service;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AggregationServiceApplication.class);
		app.setDefaultProperties(Collections.singletonMap("spring.config.additional-location", "/config/"));
        app.run(args);
	}

}
