package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class MultiplyFloatInstruction extends Instruction {

    MultiplyFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.MULTIPLY_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) * m.getRam().getFloat(ramAddress));
        return true;
    }

    @Override
    public String instCode() {
        return "MULTIPLY_FLOAT";
    }
}