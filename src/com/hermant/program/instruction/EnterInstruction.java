package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.Register.STACK_FRAME_POINTER;
import static com.hermant.machine.Register.STACK_POINTER;

public class EnterInstruction extends Instruction {

    EnterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.ENTER, reg1, reg2, ramAddress);
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