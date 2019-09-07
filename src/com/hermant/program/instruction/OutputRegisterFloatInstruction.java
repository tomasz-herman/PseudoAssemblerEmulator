package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterFloatInstruction extends Instruction implements OutputOperation {

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.print(m.getFPR().getFloat(reg1));
        if(debug) System.out.println();
        return true;
    }

    @Override
    public byte code() {
        return OUTPUT_REGISTER_FLOAT;
    }

    @Override
    public int instLength() {
        return 2;
    }

    @Override
    public String instCode() {
        return "OUTPUT_REGISTER_FLOAT";
    }
}