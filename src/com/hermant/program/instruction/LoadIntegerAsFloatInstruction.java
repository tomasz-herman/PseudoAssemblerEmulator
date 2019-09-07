package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LoadIntegerAsFloatInstruction extends Instruction implements MemoryOperation{

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getFPR().setFloat(reg1, m.getRam().getInteger(ramAddress));
        return true;
    }

    @Override
    public final byte code() {
        return LOAD_INTEGER_AS_FLOAT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "LOAD_INTEGER_AS_FLOAT";
    }
}