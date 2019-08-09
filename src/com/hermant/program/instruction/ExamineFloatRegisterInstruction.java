package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class ExamineFloatRegisterInstruction extends Instruction {

    ExamineFloatRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.EXAMINE_FLOAT_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        examineFloat(m.getFPR().getFloat(reg1), m.getFlagsRegister());
        return true;
    }
}