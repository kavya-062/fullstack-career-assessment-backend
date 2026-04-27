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
        Student existingStudent = repo.findByEmail(s.getEmail());
        if (existingStudent != null) {
            // If email exists, update the existing record instead of crashing (great for testing)
            existingStudent.setName(s.getName());
            existingStudent.setPassword(s.getPassword());
            existingStudent.setEducation(s.getEducation());
            return repo.save(existingStudent);
        }
        
        return repo.save(s);
    }
}