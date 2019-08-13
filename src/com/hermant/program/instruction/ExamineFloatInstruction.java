package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExamineFloatInstruction extends Instruction implements MemoryOperation, FloatArithmetic {

    ExamineFloatInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.EXAMINE_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        examineFloat(m.getRam().getFloat(ramAddress), m.getFlagsRegister());
        return true;
    }

    @Override
    public String instCode() {
        return "EXAMINE_FLOAT";
    }
}