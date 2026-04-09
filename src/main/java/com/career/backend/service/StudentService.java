package com.career.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.career.backend.model.Student;
import com.career.backend.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    public Student saveStudent(Student s){

        return repo.save(s);
    }
}