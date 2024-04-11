package fr.starscience.clown.clown.service;

import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.model.Hash;
import fr.starscience.clown.clown.repository.HashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HashService {

    @Autowired
    private HashRepository repository;

    public List<Hash> saveAll(List<Hash> hashList){ return repository.saveAll(hashList); }

    public List<Hash> getAll(){ return repository.findAll(); }

    public Hash get(int id){ return repository.findById(id).orElseThrow(); }

    public List<Hash> getArtefactHash(Artefact artefact){ return repository.findByArtefact(artefact); }

    public List<Hash> getIdenticalHash(Artefact artefact1, Artefact artefact2){
        return repository.identicalHash(artefact1, artefact2);
    }
}
