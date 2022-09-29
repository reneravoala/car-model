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
    public ResponseEntity<?> findByMakeName(@PathVariable String name) throws MakeNotFoundException {
        Make make = makeRepository.findByNameIgnoreCase(name);
        if (make == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(make.getModels(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/models/make-id/{id}")
    public ResponseEntity<?> findByMakeId(@PathVariable long id) throws MakeNotFoundException {
        Make make = makeRepository.findById(id);
        if (make == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(make.getModels(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/models/id/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) throws MakeNotFoundException {
        Model model = modelRepository.findOneById(id);
        if (model == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(model, HttpStatus.ACCEPTED);
    }

    @GetMapping("/models")
    public ResponseEntity<?> findBy(@RequestParam("name") Optional<String> name,
                                    @RequestParam("year") Optional<Integer> year,
                                    @RequestParam("category") Optional<String> category) throws MakeNotFoundException {
        String n = name.orElse("");
        Integer y = year.orElse(0);
        String c = category.orElse("");
        List<Model> models;
        if (y != 0) {
            models = modelRepository.findByNameAndCategoryAndYear(n, c, y);
        } else {
            models = modelRepository.findByNameAndCategory(n, c);
        }
        return new ResponseEntity<>(models, HttpStatus.ACCEPTED);
    }

//    @GetMapping("/models/year/{year}")
//    public ResponseEntity<?> findByYear(@PathVariable long year) throws MakeNotFoundException {
//        List<Model> models = modelRepository.findByYear(year);
//        if (models == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(models, HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping("/models/category/{category}")
//    public ResponseEntity<?> findByCategory(@PathVariable String category) throws MakeNotFoundException {
//        List<Model> models = modelRepository.findByCategory(category);
//        if (models == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(models, HttpStatus.ACCEPTED);
//    }

    @PostMapping("/models/create/{makeId}/")
    public ResponseEntity<?> create(@Valid @PathVariable long makeId, @Valid @RequestBody Model model) throws MakeNotFoundException {
        model.setMake(makeRepository.findById(makeId));
        return new ResponseEntity<>(modelRepository.save(model), HttpStatus.CREATED);
    }

    @PutMapping("/models/update")
    public ResponseEntity<?> update(@RequestBody Model model) {
        Model updated = modelRepository.findOneById(model.getId());

        if (updated == null)
            return ResponseEntity.notFound().build();

        updated.update(model);

        return new ResponseEntity<>(modelRepository.save(updated), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/models/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Model model = modelRepository.findOneById(id);

        if (model == null) {
            return ResponseEntity.notFound().build();
        }
        modelRepository.delete(model);

        return ResponseEntity.ok().build();
    }
}
