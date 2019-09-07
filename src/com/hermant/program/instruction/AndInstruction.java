package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AndInstruction extends Instruction implements LogicalOperation, MemoryOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) & m.getRam().getInteger(ramAddress), m.getFlagsRegister()));
        return true;
    }

    @Override
    public byte code() {
        return AND;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "AND";
    }
}