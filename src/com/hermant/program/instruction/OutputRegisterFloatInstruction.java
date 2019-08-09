package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterFloatInstruction extends Instruction {

    OutputRegisterFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.OUTPUT_REGISTER_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.println(m.getFPR().getFloat(reg1));
        return true;
    }
}