package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterFloatInstruction extends OutputOperation {

    OutputRegisterFloatInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.OUTPUT_REGISTER_FLOAT, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.print(m.getFPR().getFloat(reg1));
        if(debug) System.out.println();
        return true;
    }

    @Override
    public String instCode() {
        return "OUTPUT_REGISTER_FLOAT";
    }
}