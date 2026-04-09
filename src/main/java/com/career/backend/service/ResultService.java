package com.career.backend.service;

import org.springframework.stereotype.Service;
import com.career.backend.model.AssessmentResult;

@Service
public class ResultService {

    public AssessmentResult calculateCareer(AssessmentResult r){

        // if logic score high → Software Developer
        if(r.getLogic_score() != null && r.getLogic_score() > 70){

            r.setRecommended_career("Software Developer");

            r.setSkills("Java, Problem Solving, Logical Thinking");
        }

        // if creativity score high → Designer
        else if(r.getCreativity_score() != null && r.getCreativity_score() > 70){

            r.setRecommended_career("Designer");

            r.setSkills("Creativity, UI Design, Innovation");
        }

        // if communication score high → Teacher
        else if(r.getCommunication_score() != null && r.getCommunication_score() > 70){

            r.setRecommended_career("Teacher");

            r.setSkills("Communication, Presentation, Explanation");
        }

        // default career
        else{

            r.setRecommended_career("General Career");

            r.setSkills("Basic Skills");
        }

        return r;
    }
}