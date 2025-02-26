package com.example.quizservice.DTO;

import java.util.Set;

import org.springframework.data.annotation.Id;


public class QuizDto {
    
    @Id
    private String id;
    public String name;
    public String category;
    public Set<String> questionIds;
    public Set<QuestionDto> questions;
    
  

    public QuizDto () {};

 


    public QuizDto(String name, String category, Set<String> questionIds, Set<QuestionDto> questions){
        this.name = name;
        this.category = category;
        this.questionIds = questionIds;
        this.questions = questions;
    };

    public Set<QuestionDto> getQuestions() {
        return questions;
    }


    public void setQuestions(Set<QuestionDto> questions) {
        this.questions = questions;
    }



    public Set<String> getQuestionIds() {
        return questionIds;
    }


    public void setQuestionIds(Set<String> questionIds) {
        this.questionIds = questionIds;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getname(){
        return name;
    };

    public void setName(String name){
        this.name = name;
    };

    public void setCategory(String category){
        this.category = category;
    };

    public String Category(){
        return category;
        
    };

    
    
    
}
