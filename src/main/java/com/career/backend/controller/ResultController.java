package com.career.backend.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.career.backend.model.AssessmentResult;
import com.career.backend.repository.AssessmentResultRepository;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "http://localhost:5173")

public class ResultController {

    @Autowired
    private AssessmentResultRepository repo;


    @PostMapping("/results")
    public AssessmentResult saveResult(@RequestBody AssessmentResult result){

        // store latest attempt date
        result.setAttempt_date(LocalDateTime.now());

        // always create new record
        return repo.save(result);
    }

}