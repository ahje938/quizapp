package com.example.quizservice.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizservice.DTO.QuestionDto;
import com.example.quizservice.DTO.QuizDto;
import com.example.quizservice.Exception.DuplicateResourceException;
import com.example.quizservice.Exception.ResourceNotFoundException;
import com.example.quizservice.Feign.QuizClient;
import com.example.quizservice.Model.Quiz;
import com.example.quizservice.Repository.QuizRepository;


@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizClient quizClient;

    public QuizService(QuizRepository quizRepository, QuizClient quizClient){
        this.quizRepository = quizRepository;
        this.quizClient = quizClient;

    }

    //Hente alle quizzer
    public List<Quiz> getAllQuizzes(){
        return quizRepository.findAll();
    }

    //Hente questions byIds via question-service
    public Set<QuestionDto> getAllQuestionsByIds(Set<String> ids){
        return quizClient.getQuestionsById(ids);
    }


    //Hente quiz byId med alle tilsvarende spørsmål (questionIds)
    public QuizDto getQuizWithQuestions(String id){
        
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fant ingen quiz med ID: " +id));

        Set<String> questionIds = quiz.getQuestionIds();

        Set<QuestionDto> questions = quizClient.getQuestionsById(questionIds);

        QuizDto quizDto = new QuizDto(quiz.getName(), quiz.getCategory(), questionIds, questions);
        
        return quizDto;
    }

    //Slette quiz by id
    public void deleteQuizById(String id){

        quizRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fant ingen quzi med ID: "+id));

        quizRepository.deleteById(id);


    }

    //Legge til quiz 
    public Quiz addQuiz(Quiz quiz){    
        
        if(quizRepository.existsByName(quiz.getName())){
            throw new DuplicateResourceException("Quiz med navn: "+quiz.getName()+ " eksisterer allerede");
        }

        return quizRepository.save(quiz);

    }

    public ResponseEntity<Quiz> addQuestionsToQuizById(String quizId, Map<String, Set<String>> updates){

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Fant ingen quiz med id: "+quizId));

        Set<String> existingQuestionIds = quiz.getQuestionIds();
        Set<String> newQuestionIds = updates.get("questionIds");

        if (existingQuestionIds == null) {
                existingQuestionIds = new HashSet<>();
        }

        existingQuestionIds.addAll(newQuestionIds);
        quiz.setQuestionIds(existingQuestionIds);
        quizRepository.save(quiz);
        
        return ResponseEntity.ok(quiz);

    }

}
