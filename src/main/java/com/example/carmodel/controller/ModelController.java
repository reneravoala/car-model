package com.example.carmodel.controller;

import com.example.carmodel.entity.Make;
import com.example.carmodel.entity.Model;
import com.example.carmodel.exception.MakeNotFoundException;
import com.example.carmodel.repository.MakeRepository;
import com.example.carmodel.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ModelController {


    @Autowired
    private MakeRepository makeRepository;
    @Autowired
    private ModelRepository modelRepository;

    @GetMapping("/models/make-name/{name}")
    public List<Model> findByName(@PathVariable String name) throws MakeNotFoundException {
        Make make = makeRepository.findByName(name);
        if (make != null) {
            return make.getModels();
        }
        return null;
    }

    @GetMapping("/models/make-id/{id}")
    public List<Model> findByName(@PathVariable long id) throws MakeNotFoundException {
        Make make = makeRepository.findById(id);
        if (make != null) {
            return make.getModels();
        }
        return null;
    }

    @PostMapping("/model/{makeId}/")
    public ResponseEntity<?> create(@PathVariable long makeId, @Valid @RequestBody Model model) throws MakeNotFoundException {
        model.setMake(makeRepository.findById(makeId));
        return new ResponseEntity<>(modelRepository.saveAndFlush(model), HttpStatus.CREATED);
    }

    @PutMapping("/model/update/{id}")
    public ResponseEntity<?> update(@RequestBody Model model, @PathVariable long id) {
        Optional<Model> modelOptional = modelRepository.findById(id);

        if (modelOptional.isEmpty())
            return ResponseEntity.notFound().build();

        model.setId(id);
        model.setMake(modelOptional.get().getMake());

        modelRepository.save(model);

        return ResponseEntity.noContent().build();
    }
}
