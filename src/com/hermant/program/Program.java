package com.hermant.program;

import com.hermant.program.declaration.Declaration;
import com.hermant.program.instruction.LoadableInstruction;

import java.io.Serializable;
import java.util.ArrayList;

public class Program implements Serializable {
    public ArrayList<Declaration> declarations = new ArrayList<>();
    public ArrayList<LoadableInstruction> instructions = new ArrayList<>();

    public void addInstruction(LoadableInstruction ins){
        instructions.add(ins);
    }

    public void addDeclaration(Declaration dec){
        declarations.add(dec);
    }
}
