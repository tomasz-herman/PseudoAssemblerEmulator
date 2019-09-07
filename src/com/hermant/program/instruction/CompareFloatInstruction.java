package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareFloatInstruction extends Instruction implements MemoryOperation, FloatArithmeticOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        compareFloat(m.getFPR().getFloat(reg1), m.getRam().getFloat(ramAddress), m.getFlagsRegister());
        return true;
    }

    @Override
    public final byte code() {
        return COMPARE_FLOAT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "COMPARE_FLOAT";
    }
}