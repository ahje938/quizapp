package com.example.questionservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.questionservice.Model.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    
    
    boolean existsByQuestion(String name);

}
