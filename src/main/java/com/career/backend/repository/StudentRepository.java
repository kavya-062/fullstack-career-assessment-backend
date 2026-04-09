package com.career.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.career.backend.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

}