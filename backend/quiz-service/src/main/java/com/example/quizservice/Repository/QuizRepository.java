package com.example.quizservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.quizservice.Model.Quiz;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String>{
    
    //Sjekk for quiz-duplikatnavn
    boolean existsByName(String name);

    String findByName(String name);

}
