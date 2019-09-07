package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.Register.STACK_FRAME_POINTER;
import static com.hermant.machine.register.Register.STACK_POINTER;

public class EnterInstruction extends Instruction {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        m.getStack().push(m.getRegister().getInteger(STACK_FRAME_POINTER));
        m.getRegister().setInteger(STACK_FRAME_POINTER, m.getRegister().getInteger(STACK_POINTER));
        return true;
    }

    @Override
    public final byte code() {
        return ENTER;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "ENTER";
    }
}