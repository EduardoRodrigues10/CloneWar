package fr.starscience.clown.clown.service;

import fr.starscience.clown.clown.analyzer.CloneDetection;
import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.repository.ArtefactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ArtefactService {
    @Autowired
    private ArtefactRepository artefactRepository;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private HashService hashService;

    @Autowired
    private CloneService cloneService;

    public Artefact saveFile(String artefactName, MultipartFile sources, MultipartFile classes) throws IOException {
        Objects.requireNonNull(artefactName);
        var artefact = artefactRepository.save(new Artefact(artefactName, sources.getBytes(), classes.getBytes()));
        var hashList = hashService.saveAll(new CloneDetection(artefact).scan());
        cloneService.insertArtefactClone(artefact, hashList);
        scoreService.saveScoreByArtefact(artefact);
        return artefact;
    }

    public Artefact getArtefact(int id){
        return artefactRepository.findById(id).orElseThrow();
    }

    public List<Artefact> getArtefacts(){
        return artefactRepository.findAll().stream().toList();
    }
}
