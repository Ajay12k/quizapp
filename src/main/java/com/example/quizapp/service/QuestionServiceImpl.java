package com.example.quizapp.service;

import com.example.quizapp.entity.Question;
import com.example.quizapp.exception.ResourceNotFoundException;
import com.example.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        try {
            return questionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching questions: " + e.getMessage());
        }
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        try {
            return questionRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching question by ID: " + e.getMessage());
        }
    }

    @Override
    public Question createQuestion(Question question) {
        try {
            return questionRepository.save(question);
        } catch (Exception e) {
            throw new RuntimeException("Error creating question: " + e.getMessage());
        }
    }

    @Override
    public Question updateQuestion(Long id, Question updatedQuestion) {
        try {
            return questionRepository.findById(id).map(q -> {
                q.setQuestionTitle(updatedQuestion.getQuestionTitle());
                q.setOption1(updatedQuestion.getOption1());
                q.setOption2(updatedQuestion.getOption2());
                q.setOption3(updatedQuestion.getOption3());
                q.setOption4(updatedQuestion.getOption4());
                q.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
                q.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
                q.setCategory(updatedQuestion.getCategory());
                return questionRepository.save(q);
            }).orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error updating question: " + e.getMessage());
        }
    }

    @Override
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
		    throw new ResourceNotFoundException("Question not found with ID: " + id);
		}
		questionRepository.deleteById(id);
    }
}

