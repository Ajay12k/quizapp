package com.example.quizapp.service;

import com.example.quizapp.entity.Question;
import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<Question> getAllQuestions();
    Optional<Question> getQuestionById(Long id);
    Question createQuestion(Question question);
    Question updateQuestion(Long id, Question updatedQuestion);
    void deleteQuestion(Long id);
}
