package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExamineFloatInstruction extends Instruction implements MemoryOperation, FloatArithmeticOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        examineFloat(m.getRam().getFloat(ramAddress), m.getFlagsRegister());
        return true;
    }

    @Override
    public byte code() {
        return EXAMINE_FLOAT;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "EXAMINE_FLOAT";
    }
}