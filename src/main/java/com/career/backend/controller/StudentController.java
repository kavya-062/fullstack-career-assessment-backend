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
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private OtpService otpService;

    // 🔹 SEND OTP
    @PostMapping("/request-otp")
    public ResponseEntity<?> requestOtp(@RequestBody Map<String, String> request) {

        String email = request.get("email");

        System.out.println("📩 Incoming OTP request for: " + email);

        // 🔥 Call OTP service (this should trigger mail sending logs)
        otpService.generateAndSendOtp(email);

        System.out.println("✅ OTP service method executed");

        return ResponseEntity.ok(Map.of(
                "message", "OTP sent to " + email
        ));
    }

    // 🔹 SAVE STUDENT
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Student s){

        System.out.println("🔍 Verifying OTP for: " + s.getEmail());

        // Validate OTP
        boolean isValid = otpService.validateOtp(s.getEmail(), s.getOtp());

        if(!isValid){
            System.out.println("❌ OTP validation failed");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Invalid OTP"));
        }

        try {
            System.out.println("✅ OTP verified, saving student");

            Student savedStudent = service.saveStudent(s);

            return ResponseEntity.ok(savedStudent);

        } catch (org.springframework.dao.DataIntegrityViolationException e) {

            System.out.println("⚠️ Duplicate email error");

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "An account with this email already exists!"));
        }
    }
}