package com.career.backend.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.career.backend.model.Student;
import com.career.backend.repository.StudentRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")

public class AuthController {

    @Autowired
    private StudentRepository repo;


    // OLD METHOD (keep)
    @PostMapping("/login")
    public String login(@RequestBody Student student){

        Student s = repo.findByEmail(student.getEmail());

        if(s != null && s.getPassword().equals(student.getPassword())){

            // update login time every login
            s.setLoginTime(LocalDateTime.now());

            repo.save(s);

            return "Login Success";
        }

        return "Invalid Email or Password";
    }


    // IMPORTANT METHOD
    @PostMapping("/login-user")
    public Student loginUser(@RequestBody Student student){

        Student s = repo.findByEmail(student.getEmail());

        if(s != null && s.getPassword().equals(student.getPassword())){

            // update login time every login
            s.setLoginTime(LocalDateTime.now());

            repo.save(s);

            return s;
        }

        return null;
    }

}