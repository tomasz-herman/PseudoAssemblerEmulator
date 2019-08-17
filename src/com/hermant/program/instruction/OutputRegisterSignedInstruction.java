package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterSignedInstruction extends OutputOperation {

    OutputRegisterSignedInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.OUTPUT_REGISTER_SIGNED, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.print(m.getRegister().getInteger(reg1));
        if(debug) System.out.println();
        return true;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "OUTPUT_REGISTER_SIGNED";
    }
}