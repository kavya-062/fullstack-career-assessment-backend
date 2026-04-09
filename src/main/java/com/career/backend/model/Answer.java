package com.career.backend.model;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Long questionId;

    private Long selectedOption;

    public Long getStudentId(){ return studentId; }

    public void setStudentId(Long studentId){
        this.studentId = studentId;
    }

    public Long getQuestionId(){ return questionId; }

    public void setQuestionId(Long questionId){
        this.questionId = questionId;
    }

    public Long getSelectedOption(){ return selectedOption; }

    public void setSelectedOption(Long selectedOption){
        this.selectedOption = selectedOption;
    }
}