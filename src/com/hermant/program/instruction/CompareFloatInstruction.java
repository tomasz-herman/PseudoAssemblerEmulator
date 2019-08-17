package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class CompareFloatInstruction extends Instruction implements MemoryOperation, FloatArithmeticOperation {

    CompareFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.COMPARE_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        compareFloat(m.getFPR().getFloat(reg1), m.getRam().getFloat(ramAddress), m.getFlagsRegister());
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "COMPARE_FLOAT";
    }
}