package fr.starscience.clown.clown.analyzer;

import java.util.Objects;

public record Opcode(String filename, int line, int opcode, String function) {
    public Opcode{
        Objects.requireNonNull(filename);
        if(line < 0) { throw new IllegalArgumentException(); }
        if(opcode < 0) { throw new IllegalArgumentException(); }
    }
}
