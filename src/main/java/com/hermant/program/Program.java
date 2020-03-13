package com.hermant.program;

import com.hermant.program.declaration.Declaration;
import com.hermant.program.instruction.LoadableInstruction;
import com.hermant.serializer.Serializable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Program implements Serializable {

    private final ArrayList<Declaration> declarations = new ArrayList<>();
    private final ArrayList<LoadableInstruction> instructions = new ArrayList<>();

    public void addInstruction(LoadableInstruction ins){
        instructions.add(ins);
    }

    public void addDeclaration(Declaration dec){
        declarations.add(dec);
    }

    public ArrayList<Declaration> getDeclarations() {
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
        for (Declaration d : declarations) buffer.writeBytes(d.serialize());
        for (LoadableInstruction inst : instructions) buffer.writeBytes(inst.serialize());
        return buffer.toByteArray();
    }
}
