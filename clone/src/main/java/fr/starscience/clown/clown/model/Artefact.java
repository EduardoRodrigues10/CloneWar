package fr.starscience.clown.clown.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Entity
@Table(name = "artefact")
public class Artefact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artefactId;

    private String name;

    private String date;
    @Lob
    private byte[] sourceData;
    @Lob
    private byte[] classData;


    public Artefact() {}

    public Artefact(String name, byte[] sourceData, byte[] classData){
        this.name = name;
        this.sourceData = sourceData;
        this.classData = classData;
        this.date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

    public int getId() { return artefactId; }

    public String getName() { return name; }

    public byte[] getSourceData() { return sourceData; }

    public byte[] getClassData() { return classData; }

    public String getDate() { return date; }

    @Override
    public String toString() {
        return "{\n\t\"id\" : " + artefactId + "\n\t\"name\" : " + name + "\n\t\"data\": " + Arrays.toString(sourceData) + "\n" +"}";
    }
}