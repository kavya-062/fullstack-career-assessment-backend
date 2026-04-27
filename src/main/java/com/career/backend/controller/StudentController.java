package com.career.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.career.backend.model.Student;
import com.career.backend.service.StudentService;
import com.career.backend.service.OtpService;

import java.util.Map;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    StudentService service;

    @Autowired
    OtpService otpService;

    // 🔹 NEW: Send OTP to email
    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody Map<String, String> request) {

        String email = request.get("email");

        otpService.generateAndSendOtp(email);

        return ResponseEntity.ok(Map.of(
                "message", "OTP sent to " + email
        ));
    }

    // 🔹 MODIFIED: Save student with OTP verification
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Student s){

        // 1. Verify OTP
        boolean isValid = otpService.validateOtp(s.getEmail(), s.getOtp());

        if(!isValid){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Invalid OTP"));
        }

        // 2. If OTP is correct → save student
        try {
            Student savedStudent = service.saveStudent(s);
            return ResponseEntity.ok(savedStudent);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict
                    .body(Map.of("message", "An account with this email already exists!"));
        }
    }
}