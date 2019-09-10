package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.GeneralPurposeRegister.STACK_FRAME_POINTER;
import static com.hermant.machine.register.GeneralPurposeRegister.STACK_POINTER;

public class EnterInstruction extends Instruction {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        m.getStack().push(m.getRegister().get(STACK_FRAME_POINTER));
        m.getRegister().set(STACK_FRAME_POINTER, m.getRegister().get(STACK_POINTER));
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