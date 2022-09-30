package com.example.carmodel.controller;

import com.example.carmodel.entity.Make;
import com.example.carmodel.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MakeController {

    @Autowired
    private MakeRepository makeRepository;

    @GetMapping("/")
    public List<Make> all() {
        return makeRepository.findAll();
    }

}
