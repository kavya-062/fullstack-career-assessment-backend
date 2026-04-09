package com.career.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="assessment_result")

public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profile;

    private String message;

    private String skills;

    private Integer communication_score;
    private Integer creativity_score;
    private Integer logic_score;

    private String recommended_career;

    private Boolean completed;

    // date when student submitted test
    @Column(name="attempt_date")
    private LocalDateTime attempt_date;

    // link result with student
    @Column(name="student_id")
    private Long studentId;


    // getters setters

    public Long getId() {
        return id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Integer getCommunication_score() {
        return communication_score;
    }

    public void setCommunication_score(Integer communication_score) {
        this.communication_score = communication_score;
    }

    public Integer getCreativity_score() {
        return creativity_score;
    }

    public void setCreativity_score(Integer creativity_score) {
        this.creativity_score = creativity_score;
    }

    public Integer getLogic_score() {
        return logic_score;
    }

    public void setLogic_score(Integer logic_score) {
        this.logic_score = logic_score;
    }

    public String getRecommended_career() {
        return recommended_career;
    }

    public void setRecommended_career(String recommended_career) {
        this.recommended_career = recommended_career;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getAttempt_date() {
        return attempt_date;
    }

    public void setAttempt_date(LocalDateTime attempt_date) {
        this.attempt_date = attempt_date;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}