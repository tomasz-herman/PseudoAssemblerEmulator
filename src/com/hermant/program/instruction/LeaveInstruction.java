package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.Register.STACK_FRAME_POINTER;
import static com.hermant.machine.Register.STACK_POINTER;

public class LeaveInstruction extends Instruction {

    LeaveInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.LEAVE, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getRegister().setInteger(STACK_POINTER, m.getRegister().getInteger(STACK_FRAME_POINTER));
        m.getRegister().setInteger(STACK_FRAME_POINTER, m.getStack().pop());
        return true;
    }

    @Override
    public String instCode() {
        return "LEAVE";
    }
}