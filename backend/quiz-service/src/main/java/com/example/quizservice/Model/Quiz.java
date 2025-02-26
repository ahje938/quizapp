package com.example.quizservice.Model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = ("quizzes"))
public class Quiz {
    
    @Id
    private String id;
    public String name;
    public String category;
    public Set<String> questionIds;

    public Quiz () {};

 


    public Quiz(String name, String category, Set<String> questionIds){
        this.name = name;
        this.category = category;
        this.questionIds = questionIds;
    };


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

    public String getName(){
        return name;
    };

    public void setName(String name){
        this.name = name;
    };

    public void setCategory(String category){
        this.category = category;
    };

    public String getCategory(){
        return category;
        
    };

    
    
    
}
