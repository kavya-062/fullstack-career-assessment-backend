package com.career.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessment")

@CrossOrigin(origins = "*")   // ✅ ONLY CHANGE

public class AssessmentController {

    @PostMapping

    public String submitAssessment(){

        return "Assessment Submitted";

    }

}