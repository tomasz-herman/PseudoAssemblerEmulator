package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterInstruction extends Instruction implements OutputOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        System.out.print(Integer.toUnsignedString(m.getRegister().get(reg1)));
    }

    @Override
    public final byte code() {
        return OUTPUT_REGISTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "OUTPUT_REGISTER";
    }
}