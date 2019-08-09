package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.Register.REMAINDER;

public class DivideSignedRegisterInstruction extends Instruction {

    DivideSignedRegisterInstruction(Byte reg1, Byte reg2, Integer ramAddress) {
        super(Instruction.DIVIDE_SIGNED_REGISTER, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int b = m.getRegister().getInteger(reg2);
        if(b == 0){
            m.getFlagsRegister().setOverflowFlag();
            m.getFlagsRegister().setCarryFlag();
            m.getRegister().setInteger(reg1, 0);
            m.getRegister().setInteger(REMAINDER, 0);
            return true;
        }
        m.getFlagsRegister().resetOverflowFlag();
        m.getFlagsRegister().resetCarryFlag();
        int a = m.getRegister().getInteger(reg1);
        int result = a / b;
        int remainder = a % b;
        m.getRegister().setInteger(reg1, result);
        m.getRegister().setInteger(REMAINDER, remainder);
        return true;
    }
}