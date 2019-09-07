package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterCharInstruction extends Instruction implements OutputOperation{

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        System.out.print((char)(byte)m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public final byte code() {
        return OUTPUT_REGISTER_CHAR;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "OUTPUT_REGISTER_CHAR";
    }
}