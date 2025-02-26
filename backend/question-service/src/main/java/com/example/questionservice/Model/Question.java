package com.example.questionservice.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = ("questions"))
public class Question {

    @Id
    public String id;
    public String question;
    public String category;
    public List<String> correctAnswers;
    public List<String> wrongAnswers;

    
    
    
    public Question(String question, String category, List<String> wrongAnswers, List<String> correctAnswers){
        this.question = question;
        this.category = category;
        this.wrongAnswers = wrongAnswers;
        this.correctAnswers = correctAnswers;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setCorrectAnswers(List<String> correctAnswers){
        this.correctAnswers = correctAnswers;
    }

    public List<String> getCorrectAnswers(){
        return correctAnswers;
    }

    public void setWrongAnswers(List<String> wrongAnswers){
        this.wrongAnswers = wrongAnswers;
    }

    public List<String> getWrongAnswers(){
        return wrongAnswers;
    }

}
