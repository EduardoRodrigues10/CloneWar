package fr.starscience.clown.clown.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hash")
public class Hash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hashId;

    private String fileName;
    private int startLine;
    private int endLine;
    private double hashValue;

    @OneToOne
    @JoinColumn(name = "artefactId", referencedColumnName = "artefactid")
    private Artefact artefactId;

    public Hash(String fileName, int startLine, int endLine, double hashValue, Artefact artefact){
        this.fileName = fileName;
        this.startLine = startLine;
        this.endLine = endLine;
        this.hashValue = hashValue;
        this.artefactId = artefact;
    }

    public Hash() {}

    public Long getHashId() {
        return hashId;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public Artefact getArterfactId() { return artefactId; }

    public double getHashValue() { return hashValue; }

    @Override
    public String toString() {
        return fileName + " " + startLine + "-" + endLine + ": " + hashValue;
    }


}
