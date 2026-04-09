package com.career.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.career.backend.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}