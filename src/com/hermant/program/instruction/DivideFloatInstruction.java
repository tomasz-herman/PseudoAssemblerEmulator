package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class DivideFloatInstruction extends Instruction implements MemoryOperation {

    DivideFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.DIVIDE_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) / m.getRam().getFloat(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "DIVIDE_FLOAT";
    }
}