package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterFloatInstruction extends OutputOperation {

    OutputRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.OUTPUT_REGISTER_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.println(m.getFPR().getFloat(reg1));
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT_REGISTER_FLOAT";
    }
}