package com.career.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.career.backend.model.Student;
import com.career.backend.service.StudentService;

import java.util.Map;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    StudentService service;

    // 🔹 SEND OTP (SIMULATED - NO EMAIL)
    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody Map<String, String> request) {

        String email = request.get("email");

        // Simulate OTP instead of sending email
        System.out.println("OTP requested for: " + email);

        return ResponseEntity.ok(Map.of(
                "message", "OTP sent (simulated) to " + email
        ));
    }

    // 🔹 SAVE STUDENT (OTP BYPASSED)
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Student s){

        // OTP validation bypassed for deployment
        boolean isValid = true;

        if(!isValid){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Invalid OTP"));
        }

        try {
            Student savedStudent = service.saveStudent(s);
            return ResponseEntity.ok(savedStudent);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "An account with this email already exists!"));
        }
    }
}