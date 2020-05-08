package com.hermant.program;

import com.hermant.program.declaration.Declaration;
import com.hermant.program.instruction.LoadableInstruction;
import com.hermant.serializer.Serializable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Program implements Serializable {

    public static Program of(List<Declaration<?>> declarations, List<LoadableInstruction> instructions){
        Program program = new Program();
        program.declarations.addAll(declarations);
        program.instructions.addAll(instructions);
        return program;
    }

    public static Program of(Serializable... serializable){
        Program program = new Program();
        for (Serializable s : serializable) {
            if(s instanceof Program){
                program.declarations.addAll(((Program) s).declarations);
                program.instructions.addAll(((Program) s).instructions);
            } else if(s instanceof LoadableInstruction)
                program.instructions.add((LoadableInstruction) s);
            else if(s instanceof Declaration<?>)
                program.declarations.add((Declaration<?>) s);
        }
        return program;
    }

    private final ArrayList<Declaration<?>> declarations = new ArrayList<>();
    private final ArrayList<LoadableInstruction> instructions = new ArrayList<>();

    public void addInstruction(LoadableInstruction ins){
        instructions.add(ins);
    }

    public void addDeclaration(Declaration<?> dec){
        declarations.add(dec);
    }

    public ArrayList<Declaration<?>> getDeclarations() {
        return declarations;
    }

    public ArrayList<LoadableInstruction> getInstructions() {
        return instructions;
    }

    @Override
    public byte[] serialize() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int declarationNum = declarations.size();
        buffer.write((byte)declarationNum);
        buffer.write(declarationNum >> 8);
        for (Declaration<?> d : declarations) buffer.writeBytes(d.serialize());
        for (LoadableInstruction inst : instructions) buffer.writeBytes(inst.serialize());
        return buffer.toByteArray();
    }
}
