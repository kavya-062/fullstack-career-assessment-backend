package com.career.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessment")

@CrossOrigin

public class AssessmentController {

    @PostMapping

    public String submitAssessment(){

        return "Assessment Submitted";

    }

}