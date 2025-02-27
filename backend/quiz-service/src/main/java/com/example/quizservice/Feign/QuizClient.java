package com.example.quizservice.Feign;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizservice.DTO.QuestionDto;

//question-service:8082 FOR TEST MELLOM CONTAINER, localhost:8002 FOR TEST UTENOM 
@FeignClient(name = "question-service", url = "http://question-service:8082")
public interface QuizClient {

    // nøyaktig endepunkt som brukes i andre tjenesten :8080/api/question
    @GetMapping("/api/question")
    List<QuestionDto> getAllQuestions();

    @GetMapping("/api/question/{id}")
    public QuestionDto getQuestionById(@PathVariable String id);

    @GetMapping("api/question/byIds")
    public Set<QuestionDto> getQuestionsById(@RequestParam Set<String> ids);

}
