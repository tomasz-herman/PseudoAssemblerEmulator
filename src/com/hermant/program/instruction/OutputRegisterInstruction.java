package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterInstruction extends OutputOperation {

    OutputRegisterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(OUTPUT_REGISTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.print(Integer.toUnsignedString(m.getRegister().getInteger(reg1)));
        if(debug) System.out.println();
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "OUTPUT_REGISTER";
    }
}