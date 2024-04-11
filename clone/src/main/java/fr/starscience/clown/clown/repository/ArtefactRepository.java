package fr.starscience.clown.clown.repository;

import fr.starscience.clown.clown.model.Artefact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
public interface ArtefactRepository extends JpaRepository<Artefact, Integer> {
}
