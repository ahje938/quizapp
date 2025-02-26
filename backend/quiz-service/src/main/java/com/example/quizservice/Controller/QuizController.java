package com.example.quizservice.Controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizservice.DTO.QuestionDto;
import com.example.quizservice.DTO.QuizDto;

import com.example.quizservice.Feign.QuizClient;
import com.example.quizservice.Model.Quiz;
import com.example.quizservice.Repository.QuizRepository;
import com.example.quizservice.Service.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizService quizService;

    

    private final QuizClient quizClient;

    @Autowired
    public QuizController(QuizClient quizClient) {
        this.quizClient = quizClient;
    }


    //via question service || test
    @GetMapping("/questions")
    public List<QuestionDto> getAllQuestions(){
        return quizClient.getAllQuestions();
    }

    //via question-service || test
    @GetMapping("/questions/{id}")
    public QuestionDto getQuestionById(@PathVariable String id){
        return quizClient.getQuestionById(id);
    }



    //via question-service
    //http://localhost:8001/api/quiz/questions/byIds?ids=xxxx&ids=xxxx&ids=xxxx
    @GetMapping("/questions/byIds")
    public Set<QuestionDto> getQuestionsById(@RequestParam Set<String> ids){
        return quizService.getAllQuestionsByIds(ids);
    }

    @GetMapping("/{id}")
    public QuizDto getQuizWithQuestions(@PathVariable String id){
        return quizService.getQuizWithQuestions(id);
    }
    
    
    @GetMapping()
    public List<Quiz> getQuizs(){
        return quizRepository.findAll();

    }

    @PostMapping()
    public Quiz addQuiz(@RequestBody Quiz quiz){
        return quizService.addQuiz(quiz);
        
        
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable String id){
        quizService.deleteQuizById(id);
    }


    
    @PatchMapping("/{id}")
    public ResponseEntity<Quiz> addQuestionsToQuizById(@PathVariable String id,  @RequestBody Map<String, Set<String>> updates){
        return quizService.addQuestionsToQuizById(id, updates);
    }
    

}
