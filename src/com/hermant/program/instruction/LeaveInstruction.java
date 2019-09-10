package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.GeneralPurposeRegister.STACK_FRAME_POINTER;
import static com.hermant.machine.register.GeneralPurposeRegister.STACK_POINTER;

public class LeaveInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getRegister().set(STACK_POINTER, m.getRegister().get(STACK_FRAME_POINTER));
        m.getRegister().set(STACK_FRAME_POINTER, m.getStack().pop());
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