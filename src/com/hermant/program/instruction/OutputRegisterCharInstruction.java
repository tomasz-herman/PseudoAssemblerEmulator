package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterCharInstruction extends Instruction {

    OutputRegisterCharInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.OUTPUT_REGISTER_CHAR, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.println((char)(byte)m.getRegister().getInteger(reg1));
        return true;
    }
}