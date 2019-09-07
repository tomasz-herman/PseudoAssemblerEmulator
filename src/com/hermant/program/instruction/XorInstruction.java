package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class XorInstruction extends Instruction implements LogicalOperation, MemoryOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) ^ m.getRam().getInteger(ramAddress), m.getFlagsRegister()));
        return true;
    }

    @Override
    public final byte code() {
        return XOR;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "XOR";
    }
}