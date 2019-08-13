package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterInstruction extends OutputOperation {

    OutputRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.OUTPUT_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.println(Integer.toUnsignedString(m.getRegister().getInteger(reg1)));
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT_REGISTER";
    }
}