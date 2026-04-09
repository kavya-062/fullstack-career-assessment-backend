package com.career.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.career.backend.model.Question;
import com.career.backend.repository.QuestionRepository;

@Service

public class AssessmentService {

    @Autowired
    private QuestionRepository repo;

    // get all questions

    public List<Question> getQuestions(){

        return repo.findAll();
    }

    // add question

    public Question addQuestion(Question q){

        return repo.save(q);
    }

}