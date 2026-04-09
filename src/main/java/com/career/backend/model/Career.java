package com.career.backend.model;

import jakarta.persistence.*;

@Entity
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String careerName;

    private String description;

    private String requiredSkills;


    // default constructor (IMPORTANT)
    public Career() {
    }


    public Long getId() {
        return id;
    }


    public String getCareerName() {
        return careerName;
    }


    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getRequiredSkills() {
        return requiredSkills;
    }


    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

}