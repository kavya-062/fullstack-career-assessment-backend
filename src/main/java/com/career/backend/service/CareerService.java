package com.career.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.career.backend.model.Career;
import com.career.backend.repository.CareerRepository;

@Service

public class CareerService {

    @Autowired
    private CareerRepository repo;

    // get all careers

    public List<Career> getAllCareers(){

        return repo.findAll();
    }

    // add career

    public Career addCareer(Career c){

        return repo.save(c);
    }

}