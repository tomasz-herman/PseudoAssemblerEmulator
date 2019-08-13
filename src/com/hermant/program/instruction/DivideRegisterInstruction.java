package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.Register.REMAINDER;

public class DivideRegisterInstruction extends Instruction {

    DivideRegisterInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.DIVIDE_REGISTER, reg1, reg2, ramAddress);
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
        int result = Integer.divideUnsigned(a, b);
        int remainder = Integer.remainderUnsigned(a, b);
        m.getRegister().setInteger(reg1, result);
        m.getRegister().setInteger(REMAINDER, remainder);
        return true;
    }

    @Override
    public String instCode() {
        return "DIVIDE_REGISTER";
    }
}