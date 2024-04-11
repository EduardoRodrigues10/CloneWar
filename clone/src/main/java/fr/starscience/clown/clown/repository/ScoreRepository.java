package fr.starscience.clown.clown.repository;

import fr.starscience.clown.clown.model.SimilarityScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<SimilarityScore, Long> {
}
