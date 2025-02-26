package com.example.quizservice.Feign;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizservice.DTO.QuestionDto;


//port 8080 grunnet kommunikasjon mellom tjenester, 8002 hvis ekstern
@FeignClient(name = "question-service1" , url="http://question-service1:8080" )
public interface QuizClient {
    
    //nøyaktig endepunkt som brukes i andre tjenesten :8080/api/question
    @GetMapping("/api/question")
    List<QuestionDto> getAllQuestions();

    @GetMapping("/api/question/{id}")
    public QuestionDto getQuestionById(@PathVariable String id);

    @GetMapping("api/question/byIds")
    public Set<QuestionDto> getQuestionsById(@RequestParam Set<String> ids);
    
}
