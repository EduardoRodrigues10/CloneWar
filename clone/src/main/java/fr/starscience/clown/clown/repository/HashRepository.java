package fr.starscience.clown.clown.repository;

import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.model.Hash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HashRepository extends JpaRepository<Hash, Integer> {
    @Query("SELECT a FROM Hash a WHERE a.artefactId = ?1")
    List<Hash> findByArtefact(Artefact artefact);

    @Query("SELECT h1 FROM Hash h1 JOIN Hash h2 ON h1.hashValue = h2.hashValue WHERE h1.artefactId = ?1 AND h2.artefactId = ?2")
    List<Hash> identicalHash(Artefact a1, Artefact a2);
}
