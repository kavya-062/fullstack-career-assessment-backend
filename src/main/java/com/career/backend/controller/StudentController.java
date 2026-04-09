package com.career.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.career.backend.model.Student;
import com.career.backend.service.StudentService;

@RestController
@RequestMapping("/student")

@CrossOrigin
public class StudentController {

    @Autowired
    StudentService service;

    @PostMapping("/save")

    public Student save(@RequestBody Student s){

        return service.saveStudent(s);
    }
}