package com.career.backend.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.career.backend.model.Student;
import com.career.backend.repository.StudentRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")

public class AuthController {

    @Autowired
    private StudentRepository repo;


    // OLD METHOD (kept same)
    @PostMapping("/login")
    public String login(@RequestBody Student student){

        Student s = repo.findByEmail(student.getEmail());

        if(s != null && s.getPassword().equals(student.getPassword())){

            s.setLoginTime(LocalDateTime.now());
            repo.save(s);

            return "Login Success";
        }

        return "Invalid Email or Password";
    }


    // IMPORTANT METHOD (used by frontend)
    @PostMapping("/login-user")
    public Student loginUser(@RequestBody Student student){

        System.out.println("🔐 Login attempt: " + student.getEmail());

        Student s = repo.findByEmail(student.getEmail());

        if(s != null && s.getPassword().equals(student.getPassword())){

            s.setLoginTime(LocalDateTime.now());
            repo.save(s);

            System.out.println("✅ Login success");

            return s;
        }

        System.out.println("❌ Invalid credentials");
        return null;
    }
}