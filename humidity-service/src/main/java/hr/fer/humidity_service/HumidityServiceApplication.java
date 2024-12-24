package hr.fer.humidity_service;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HumidityServiceApplication {

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HumidityServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("spring.config.additional-location", "/config/"));
        app.run(args);
	}

}
