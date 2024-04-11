package fr.starscience.clown.clown.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"artefactId", "hashId"}))
public class Clone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cloneId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artefactId", referencedColumnName = "ARTEFACTID")
    private Artefact artefactId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hashId", referencedColumnName = "HASHID")
    private Hash hash;

    public Clone(Artefact artefact, Hash hash){
        this.artefactId = artefact;
        this.hash = hash;
    }
     public Clone() {}

    public long getCloneId() { return cloneId; }

    public Artefact getArtefact() { return artefactId; }

    public Hash getHash() { return hash; }
}
