package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OrRegisterInstruction extends Instruction implements LogicalOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) | m.getRegister().getInteger(reg2), m.getFlagsRegister()));
        return true;
    }

    @Override
    public byte code() {
        return OR_REGISTER;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "OR_REGISTER";
    }
}