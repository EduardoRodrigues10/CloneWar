package fr.starscience.clown.clown.service;

import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.model.SimilarityScore;
import fr.starscience.clown.clown.repository.ArtefactRepository;
import fr.starscience.clown.clown.repository.CloneRepository;
import fr.starscience.clown.clown.repository.HashRepository;
import fr.starscience.clown.clown.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private ArtefactRepository artefactRepository;
    @Autowired
    private CloneRepository cloneRepository;

    public void saveScoreByArtefact(Artefact artefact){
        var cloneListA1 = cloneRepository.getCloneByArtefact(artefact);
        var artefactList = artefactRepository.findAll();
        for(var a: artefactList){
            var cloneListA2 = cloneRepository.getCloneByArtefact(a);
            var score = (cloneListA1.size() > 0) ? (float) cloneListA2.stream()
                    .filter(clone -> cloneListA1
                            .stream()
                            .map(c -> c.getHash().getHashValue())
                            .toList()
                            .contains(clone.getHash().getHashValue()))
                    .count() /  ((float) cloneListA1.size()) : 0;
            scoreRepository.save(new SimilarityScore(artefact, a, score*100));
        }
    }

    public List<SimilarityScore> getAll(){
        return scoreRepository.findAll();
    }
}
