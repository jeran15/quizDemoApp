package com.jeran.quiz_service.service;

import com.jeran.quiz_service.dao.QuizDao;
import com.jeran.quiz_service.feign.QuizInterface;
import com.jeran.quiz_service.model.QuestionWrapper;
import com.jeran.quiz_service.model.Quiz;
import com.jeran.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz =quizDao.findById(id);
        List<Integer> questionIds = quiz.get().getQuestionsIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score =quizInterface.getScore(responses);
        return score;
    }
}
