package fr.starscience.clown.clown.analyzer;

import fr.starscience.clown.clown.model.Artefact;
import fr.starscience.clown.clown.model.Hash;
import org.objectweb.asm.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CloneDetection {

    private static final class FileReader {

       /* private final List<String> filenames;
        private final ModuleReference ref;


        private FileReader(List<String> filenames, ModuleReference ref){
            this.filenames = filenames;
            this.ref = ref;
        }*/

        private static HashMap<String, List<Opcode>> read(byte[] data) throws IOException {
            try(var input = new ByteArrayInputStream(data);
                var zip = new ZipInputStream(input)) {
                ZipEntry entry;
                var map = new HashMap<String, List<Opcode>>();
                while ((entry = zip.getNextEntry()) != null) {
                    if (!entry.getName().endsWith(".class")) {
                        continue;
                    }
                    var filename = entry.getName();
                    var opcodes = new ArrayList<Opcode>();
                    try (var inputStream = new ByteArrayInputStream(zip.readAllBytes())) {
                        new ClassReader(inputStream).accept(new ClassVisitor(Opcodes.ASM9) {
                            @Override
                            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                                return new MethodVisitor(Opcodes.ASM9) {
                                    int opcodeLine;
                                    @Override
                                    public void visitLineNumber(int line, Label start) {
                                        opcodeLine = line;
                                    }

                                    @Override
                                    public void visitInsn(int opcode) {
                                        opcodes.add(new Opcode(filename, opcodeLine, opcode, null));
                                    }

                                    @Override
                                    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                                        opcodes.add(new Opcode(filename, opcodeLine, opcode, name));
                                    }

                                    @Override
                                    public void visitIntInsn(int opcode, int operand) {
                                        opcodes.add(new Opcode(filename, opcodeLine, opcode, null));
                                    }

                                    @Override
                                    public void visitJumpInsn(int opcode, Label label) {
                                        opcodes.add(new Opcode(filename, opcodeLine, opcode, null));
                                    }

                                    @Override
                                    public void visitTypeInsn(int opcode, String type) {
                                        opcodes.add(new Opcode(filename, opcodeLine, opcode, null));
                                    }
                                };
                            }
                        }, 0);
                        map.put(filename, opcodes);
                    }
                }
                return map;
            }
        }

        /*private List<Opcode> read() throws IOException {
            Objects.requireNonNull(ref);
            for(var filename: filenames){
                if(!filename.endsWith(".class")){ continue; }
                try(var reader = ref.open();
                    var input = reader.open(filename).orElseThrow()){

                    new ClassReader(input).accept(new ClassVisitor(Opcodes.ASM9) {
                        @Override
                        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                            return new MethodVisitor(Opcodes.ASM9) {
                                int opcodeLine;
                                @Override
                                public void visitLineNumber(int line, Label start) {
                                    opcodeLine = line;
                                }

                                @Override
                                public void visitInsn(int opcode) {
                                    opcodes.add(new Opcode(filename, opcodeLine, opcode, null));
                                }

                                @Override
                                public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                                    opcodes.add(new Opcode(filename, opcodeLine, opcode, name));
                                }

                                @Override
                                public void visitIntInsn(int opcode, int operand) {
                                    opcodes.add(new Opcode(filename, opcodeLine, opcode, null));;
                                }

                                @Override
                                public void visitJumpInsn(int opcode, Label label) {
                                    opcodes.add(new Opcode(filename, opcodeLine, opcode, null));
                                }

                                @Override
                                public void visitTypeInsn(int opcode, String type) {
                                    opcodes.add(new Opcode(filename, opcodeLine, opcode, null));
                                }
                            };
                        }
                    }, 0);
                }
            }
            return opcodes;
        }*/
    }

    private final Artefact artefact;
    private final HashMap<String, List<Opcode>> opcodes;
    private final RollingHash rollingHash = new RollingHash(3);


    public CloneDetection(Artefact artefact) throws IOException {
        this.artefact = artefact;
        this.opcodes = open(artefact.getSourceData());
    }

    public List<Hash> scan(){
        return rollingHash.rollingHash(opcodes, artefact);
    }

    private HashMap<String, List<Opcode>> open(byte[] data) throws IOException {
        return FileReader.read(data);
    }

    @Override
    public String toString() {
        return opcodes.toString();
    }

}
