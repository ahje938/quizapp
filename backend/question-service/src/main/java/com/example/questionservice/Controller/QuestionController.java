package com.example.questionservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionservice.Feign.MyFeignClient;
import com.example.questionservice.Model.Question;
import com.example.questionservice.Repository.QuestionRepository;
import com.example.questionservice.Service.QuestionService;


@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    MyFeignClient feignClient;

    @Autowired
    //private final QuestionRepository questionRepository;
    private final QuestionService questionService;


    
    public QuestionController(QuestionRepository questionRepository, QuestionService questionService){
        //this.questionRepository = questionRepository;
        this.questionService = questionService;
    }



   

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
       return questionService.addQuestion(question);
    }

    @GetMapping()
    public List<Question> getAllQuestions(){
       return questionService.getAllQuestions();
    };
    

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id);
    }

    
    //http://localhost:8002/api/question/byIds?ids=id,id,id 
    @GetMapping("/byIds")
    public List<Question> getQuestionsById(@RequestParam List<String> ids){ 
        return questionService.getQuestionsById(ids);
    }


    
    @DeleteMapping("/{id}")
    public void deleteQuestionById(@PathVariable String id){
        questionService.deletQuestionById(id);
    }

  
}
