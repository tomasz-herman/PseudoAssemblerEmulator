package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class TestRegisterInstruction extends Instruction implements LogicalOperation {

    TestRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.TEST_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        setFlagsAfterLogicalOp(m.getRegister().getInteger(reg1) & m.getRegister().getInteger(reg2), m.getFlagsRegister());
        return true;
    }

    @Override
    public String instCode() {
        return "TEST_REGISTER";
    }
}