package fr.starscience.clown.clown.repository;

import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.model.Clone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CloneRepository extends JpaRepository<Clone, Integer> {

    @Query("SELECT c FROM Clone c WHERE c.artefactId = ?1")
    List<Clone> getCloneByArtefact(Artefact artefact);

}
