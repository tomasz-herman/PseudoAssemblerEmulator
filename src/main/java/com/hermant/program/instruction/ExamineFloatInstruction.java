package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExamineFloatInstruction extends Instruction implements MemoryOperation, FloatArithmeticOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        examineFloat(m.getRam().getFloat(ramAddress), m.getFlagsRegister());
    }

    @Override
    public final byte code() {
        return EXAMINE_FLOAT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "EXAMINE_FLOAT";
    }
}