package com.career.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.career.backend.model.Option;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByQuestionId(Long questionId);

}