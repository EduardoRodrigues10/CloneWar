package fr.starscience.clown.clown.controller;

import fr.starscience.clown.clown.model.Hash;
import fr.starscience.clown.clown.service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HashController {

    @Autowired
    private HashService service;
    @GetMapping("/hash")
    private List<Hash> get(){
        return service.getAll();
    }
    @GetMapping("/hash/{id}")
    private Hash get(@PathVariable int id){
        return service.get(id);
    }
}
