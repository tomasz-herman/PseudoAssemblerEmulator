package com.hermant.program.instruction;

import com.hermant.machine.*;

import static com.hermant.machine.Register.INSTRUCTION_POINTER;

public class ReturnInstruction extends Instruction {

    ReturnInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.RETURN, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        if(m.getStack().empty()) return false;
        else m.getRegister().setInteger(INSTRUCTION_POINTER, m.getStack().pop());
        return true;
    }
}
