package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterByteInstruction extends OutputOperation {

    OutputRegisterByteInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.OUTPUT_REGISTER_BYTE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.println(m.getRegister().getInteger(reg1) & 0xFF);
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT_REGISTER_BYTE";
    }
}