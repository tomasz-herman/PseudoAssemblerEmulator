package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestInstruction extends Instruction implements LogicalOperation, MemoryOperation {

    TestInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.TEST, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        logical(m.getRegister().getInteger(reg1) & m.getRam().getInteger(ramAddress), m.getFlagsRegister());
        return true;
    }

    @Override
    public String instCode() {
        return "TEST";
    }
}