package com.example.quizservice.DTO;

public class QuizAnswerDto {
    private String questionId;
    private String selectedAnswer;

    public QuizAnswerDto() {
    };

    public QuizAnswerDto(String questionId, String selectedAnswer) {
        this.questionId = questionId;
        this.selectedAnswer = selectedAnswer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
