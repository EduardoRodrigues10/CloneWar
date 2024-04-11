package fr.starscience.clown.clown.controller;

import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.repository.ArtefactRepository;
import fr.starscience.clown.clown.service.ArtefactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class ArtefactController {
    @Autowired
    private ArtefactService service;

    @GetMapping("/artefact")
    private List<Artefact> get(){
        return service.getArtefacts();
    }
    @GetMapping("/artefact/{id}")
    private Artefact get(@PathVariable int id){
        return service.getArtefact(id);
    }

    @GetMapping("/artefact/where")
    private String where(){
        return Thread.currentThread().toString();
    }

    @PostMapping(value = "/artefact", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    private Artefact save(@RequestParam("artefactName") String artefactName, @RequestParam("sources") MultipartFile sources, @RequestParam("classes") MultipartFile classes) throws IOException {
        return service.saveFile(artefactName, sources, classes);
    }
}
