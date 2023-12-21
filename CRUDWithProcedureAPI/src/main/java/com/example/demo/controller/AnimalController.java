package com.example.demo.controller;

import com.example.demo.entity.Animal;
import com.example.demo.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // Api get all animal from procedure
    @GetMapping("/check")
    public ResponseEntity<?> getProcedure() {
        return ResponseEntity.ok(animalService.callProcedureAnimal());
    }

    @GetMapping("/get-list")
    public ResponseEntity<?> findAllPlayer() {
        return ResponseEntity.ok(animalService.findAllAnimal());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findPlayer(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(animalService.callProcedureFindAnimalById(id));
    }

    // Api save animal from procedure
    @PostMapping("/save")
    public ResponseEntity<?> savePlayer(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.callProcedureInsert(animal));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable("id") Integer animalId) {
        return ResponseEntity.ok(animalService.callProcedureDeleteAnimalById(animalId));
    }
}
