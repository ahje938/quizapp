package com.example.quizservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class QuizserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizserviceApplication.class, args);
	}

}
