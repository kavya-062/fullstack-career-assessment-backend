package com.career.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.career.backend.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
