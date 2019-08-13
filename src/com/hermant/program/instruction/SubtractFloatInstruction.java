package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractFloatInstruction extends Instruction implements MemoryOperation {

    SubtractFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.SUBTRACT_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) - m.getRam().getFloat(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "SUBTRACT_FLOAT";
    }
}