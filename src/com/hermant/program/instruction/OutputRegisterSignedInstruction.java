package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputRegisterSignedInstruction extends Instruction implements OutputOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        System.out.print(m.getRegister().getInteger(reg1));
        return true;
    }

    @Override
    public final byte code() {
        return OUTPUT_REGISTER_SIGNED;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "OUTPUT_REGISTER_SIGNED";
    }
}