package com.jeran.question_service.controller;

import com.jeran.question_service.model.Question;
import com.jeran.question_service.model.QuestionWrapper;
import com.jeran.question_service.model.Response;
import com.jeran.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
       return questionService.addQuestion(question);
    }

    @DeleteMapping("question/{questionId}")
    public String deleteJob(@PathVariable int questionId){
        questionService.deleteJob(questionId);
        return "Deleted";
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz
            (@RequestParam String categoryName,@RequestParam Integer numQuestions){
        return questionService.getQuestionForQuiz(categoryName,numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsFromId(questionIds);
    }
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore (@RequestBody List<Response> responses){
        return questionService.getScore(responses);

    }
}
//why we do this ? coz maintain the question in individual service
//genrate
//getQuestion/{questionId}
//getScore