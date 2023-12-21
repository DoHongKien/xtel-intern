package com.example.demo.service;

import com.example.demo.entity.Animal;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AnimalService {
    List<Animal> findAllAnimal();

    Animal getAnimal(int id);

    Animal saveAnimal(Animal animal);

    Animal callProcedureFindAnimalById(Integer id);

    List<Animal> callProcedureAnimal();

    String callProcedureInsert(Animal animal);

    String callProcedureDeleteAnimalById(Integer id);
}
