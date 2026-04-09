package com.career.backend.model;

import jakarta.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    private String category;

    public Long getId(){ return id; }

    public String getQuestionText(){ return questionText; }

    public void setQuestionText(String questionText){
        this.questionText = questionText;
    }

    public String getCategory(){ return category; }

    public void setCategory(String category){
        this.category = category;
    }
}