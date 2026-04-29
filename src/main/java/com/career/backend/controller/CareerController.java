package com.career.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.career.backend.model.Career;
import com.career.backend.service.CareerService;

@RestController
@RequestMapping("/career")
@CrossOrigin(origins = "*")   // ✅ ONLY CHANGE

public class CareerController {

    @Autowired
    private CareerService service;

    @GetMapping("/all")

    public List<Career> getAll(){

        return service.getAllCareers();
    }

    @PostMapping("/add")

    public Career add(@RequestBody Career c){

        return service.addCareer(c);
    }

}