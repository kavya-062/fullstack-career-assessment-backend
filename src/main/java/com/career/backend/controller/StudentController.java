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
    private StudentService service;

    // 🔹 SAVE STUDENT (NO OTP)
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Student s){

        System.out.println("📩 Saving student: " + s.getEmail());

        try {
            Student savedStudent = service.saveStudent(s);

            System.out.println("✅ Student saved successfully");

            return ResponseEntity.ok(savedStudent);

        } catch (org.springframework.dao.DataIntegrityViolationException e) {

            System.out.println("⚠️ Duplicate email error");

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "An account with this email already exists!"));
        }
    }
}