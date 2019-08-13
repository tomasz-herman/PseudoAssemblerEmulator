package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class XorInstruction extends Instruction implements LogicalOperation, MemoryOperation {

    XorInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.XOR, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) ^ m.getRam().getInteger(ramAddress), m.getFlagsRegister()));
        return true;
    }

    @Override
    public String instCode() {
        return "XOR";
    }
}