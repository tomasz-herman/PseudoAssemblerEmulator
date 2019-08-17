package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterByteInstruction extends OutputOperation {

    OutputRegisterByteInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.OUTPUT_REGISTER_BYTE, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.print(m.getRegister().getInteger(reg1) & 0xFF);
        if(debug) System.out.println();
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "OUTPUT_REGISTER_BYTE";
    }
}