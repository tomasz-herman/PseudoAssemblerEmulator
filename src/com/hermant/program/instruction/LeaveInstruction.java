package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.Register.STACK_FRAME_POINTER;
import static com.hermant.machine.register.Register.STACK_POINTER;

public class LeaveInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().setInteger(STACK_POINTER, m.getRegister().getInteger(STACK_FRAME_POINTER));
        m.getRegister().setInteger(STACK_FRAME_POINTER, m.getStack().pop());
        return true;
    }

    @Override
    public final byte code() {
        return LEAVE;
    }

    @Override
    public final int instLength() {
        return 2;
    }

    @Override
    public final String instCode() {
        return "LEAVE";
    }
}