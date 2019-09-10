package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterByteInstruction extends Instruction implements OutputOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        System.out.print(m.getRegister().get(reg1) & 0xFF);
        return true;
    }

    @Override
    public final byte code() {
        return OUTPUT_REGISTER_BYTE;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "OUTPUT_REGISTER_BYTE";
    }
}