package com.hermant.program;

import com.hermant.program.declaration.Declaration;
import com.hermant.program.instruction.Instruction;

import java.util.ArrayList;

public class Program {
    public ArrayList<Declaration> declarations = new ArrayList<>();
    public ArrayList<Instruction> instructions = new ArrayList<>();

    public void addInstruction(Instruction ins){
        instructions.add(ins);
    }

    public void addDeclaration(Declaration dec){
        declarations.add(dec);
    }
}
