package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class XorInstruction extends Instruction {

    XorInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.XOR, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister());
        m.getRegister().setInteger(reg1, setFlagsAfterLogicalOp(m.getRegister().getInteger(reg1) ^ m.getRam().getInteger(ramAddress), m.getFlagsRegister()));
        return true;
    }
}