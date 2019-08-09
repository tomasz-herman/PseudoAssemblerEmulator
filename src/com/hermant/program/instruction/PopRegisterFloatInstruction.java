package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class PopRegisterFloatInstruction extends Instruction {

    PopRegisterFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.POP_REGISTER_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setInteger(reg1, m.getStack().pop());
        return true;
    }
}
