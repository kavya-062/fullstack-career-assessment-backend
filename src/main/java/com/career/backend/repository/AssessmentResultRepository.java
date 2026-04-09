package com.career.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.career.backend.model.AssessmentResult;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

}