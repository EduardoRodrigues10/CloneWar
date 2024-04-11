package fr.starscience.clown.clown.service;

import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.model.Clone;
import fr.starscience.clown.clown.model.Hash;
import fr.starscience.clown.clown.repository.ArtefactRepository;
import fr.starscience.clown.clown.repository.CloneRepository;
import fr.starscience.clown.clown.repository.HashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloneService {
    @Autowired
    private CloneRepository cloneRepository;
    @Autowired
    private HashRepository hashRepository;
    @Autowired
    private ArtefactRepository artefactRepository;

    public List<Clone> getAll(){
        return cloneRepository.findAll();
    }

    public Clone get(int cloneId){
        return cloneRepository.findById(cloneId).orElseThrow();
    }

    public void insertArtefactClone(Artefact artefact, List<Hash> hashList){
        var artefactList = artefactRepository.findAll();
        artefactList.removeIf(a -> a.getId() == artefact.getId());
        for(var a: artefactList){
            hashRepository.identicalHash(artefact, a).forEach(hash -> cloneRepository.save(new Clone(artefact, hash)));
        }
    }

    public List<Artefact> getClosestArtefact(Artefact artefact){
        return null;
    }
}
