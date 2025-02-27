package com.example.questionservice.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.questionservice.Model.Question;

@FeignClient(name = "quiz-service", url = "http://quiz-service:8081")
public interface MyFeignClient {

    @GetMapping("/api/quiz/{id}")
    Question getQuizById(@PathVariable String id);
}
