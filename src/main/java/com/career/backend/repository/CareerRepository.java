package com.career.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.career.backend.model.Career;

public interface CareerRepository extends JpaRepository<Career, Long> {

}