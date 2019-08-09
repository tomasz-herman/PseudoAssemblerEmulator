package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterInstruction extends Instruction {

    OutputRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.OUTPUT_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.println(Integer.toUnsignedString(m.getRegister().getInteger(reg1)));
        return true;
    }
}