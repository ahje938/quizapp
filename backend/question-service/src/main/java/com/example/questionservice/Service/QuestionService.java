package com.example.questionservice.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.questionservice.Exception.ResourceNotFoundException;
import com.example.questionservice.Model.Question;
import com.example.questionservice.Repository.QuestionRepository;



@Service
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;
    
    @Autowired
    QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    //Hente alle spørsmål
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
        
    }

    //Hente alle spørsmål by id
    public List<Question> getQuestionsById(List<String> ids){
        return questionRepository.findAllById(ids);

    }

    //Legge til spørsmål
    public ResponseEntity<Question> addQuestion(Question question){
        
        if(questionRepository.existsByQuestion(question.getQuestion())){
            throw new RuntimeException("Et identisk spørsmål finnes allerede");
        }
        
        questionRepository.save(question);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    //Hente spørsmål med id
    public ResponseEntity<Question> getQuestionById(String id){
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fant ikke spørsmål med ID: "+id));

        return ResponseEntity.status(HttpStatus.FOUND).body(question);
    }

    //Slette spørsmål med id
    public void deletQuestionById(String id){
        questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fant ikke spørsmål med ID: "+id));

        questionRepository.deleteById(id);    
    }



        
}


