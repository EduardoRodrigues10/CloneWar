package fr.starscience.clown.clown.analyzer;

import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.model.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RollingHash {
    private final record Key(String filename, String line){
        private Key {
            Objects.requireNonNull(filename);
            Objects.requireNonNull(line);
        }
    }
    private final int window;

    public RollingHash(int window){
        if(window <= 0) { throw new IllegalArgumentException(); }
        this.window = window;
    }

    private double hash(List<Opcode> opcodeList){
        Objects.requireNonNull(opcodeList);
        var hash = 0;
        for(var i = 0; i < Math.min(window, opcodeList.size()); i++){
            hash += opcodeList.get(i).opcode() * Math.pow(5, window - i - 1);
        }

        return hash;
    }

    private double computeHash(double hash, int oldFirst, int newLast){
        return (hash - oldFirst * Math.pow(5, window - 1))*5 + newLast;
    }

    public List<Hash> rollingHash(HashMap<String, List<Opcode>> map, Artefact artefactId){
        Objects.requireNonNull(map);
        var hashWithLines = new ArrayList<Hash>();
        for(var key: map.keySet()){
            var asm = map.get(key);
            var hash = hash(asm);
            hashWithLines.add(new Hash(key, 0, window, hash, artefactId));
            for(var i = 3; i < asm.size() - window; i++){
                hash = computeHash(hash, asm.get(i-window).opcode(), asm.get(i).opcode());
                hashWithLines.add(new Hash(key, i-window, i, hash, artefactId));
            }
        }
        return hashWithLines;
    }
}
