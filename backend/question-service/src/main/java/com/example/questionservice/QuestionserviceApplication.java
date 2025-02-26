package com.example.questionservice;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.questionservice.Feign")
public class QuestionserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionserviceApplication.class, args);
    }
}
