package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.Register.STACK_FRAME_POINTER;
import static com.hermant.machine.register.Register.STACK_POINTER;

public class EnterInstruction extends Instruction {

    EnterInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.ENTER, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getStack().push(m.getRegister().getInteger(STACK_FRAME_POINTER));
        m.getRegister().setInteger(STACK_FRAME_POINTER, m.getRegister().getInteger(STACK_POINTER));
        return true;
    }

    @Override
    public String instCode() {
        return "ENTER";
    }
}