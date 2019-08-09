package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class AddRegisterFloatInstruction extends Instruction {

    AddRegisterFloatInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.ADD_REGISTER_FLOAT, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        m.getFPR().setFloat(reg1, m.getFPR().getFloat(reg1) + m.getFPR().getFloat(reg2));
        return true;
    }
}