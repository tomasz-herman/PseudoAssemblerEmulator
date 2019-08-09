package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterByteInstruction extends Instruction {

    OutputRegisterByteInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.OUTPUT_REGISTER_BYTE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        System.out.println(m.getRegister().getInteger(reg1) & 0xFF);
        return true;
    }
}