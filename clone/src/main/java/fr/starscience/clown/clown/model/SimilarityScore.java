package fr.starscience.clown.clown.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SCORE")
public class SimilarityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double similarityScore;

    @OneToOne
    @JoinColumn(name = "firstArtefact", referencedColumnName = "ARTEFACTID")
    private Artefact firstArtefact;

    @OneToOne
    @JoinColumn(name = "secondArtefact", referencedColumnName = "ARTEFACTID")
    private Artefact secondArtefact;

    public SimilarityScore(Artefact firstArtefact, Artefact secondArtefact, double similarityScore){
        this.firstArtefact = firstArtefact;
        this.secondArtefact = secondArtefact;
        this.similarityScore = similarityScore;
    }

    public SimilarityScore(){}

    public Artefact getFirstArtefact() { return firstArtefact; }

    public Artefact getSecondArtefact() { return secondArtefact; }

    public long getId() {
        return id;
    }
}
