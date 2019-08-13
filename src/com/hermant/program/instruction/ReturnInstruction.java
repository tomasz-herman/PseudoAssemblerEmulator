package com.hermant.program.instruction;

import com.hermant.machine.*;

public class ReturnInstruction extends Instruction {

    ReturnInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.RETURN, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        if(m.getStack().empty()) return false;
        else m.getInstructionPointer().set(m.getStack().pop());
        return true;
    }

    @Override
    public String instCode() {
        return "RETURN";
    }
}
