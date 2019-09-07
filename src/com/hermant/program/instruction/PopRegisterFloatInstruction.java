package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopRegisterFloatInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getFPR().setInteger(reg1, m.getStack().pop());
        return true;
    }

    @Override
    public final byte code() {
        return POP_REGISTER_FLOAT;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "POP_REGISTER_FLOAT";
    }
}
