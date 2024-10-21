package com.google.googleTransalateApi;

import com.google.googleTransalateApi.service.TransalateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GoogleTransalateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleTransalateApiApplication.class, args);
	}

	@Bean
	TransalateService transalateService(){
		return new TransalateService();
	}
}
