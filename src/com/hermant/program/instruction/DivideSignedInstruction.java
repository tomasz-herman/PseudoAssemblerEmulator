package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.Register.REMAINDER;

public class DivideSignedInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    DivideSignedInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.DIVIDE_SIGNED, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int b = m.getRam().getInteger(ramAddress);
        int a = m.getRegister().getInteger(reg1);
        DivisionResult result = divideSigned(a, b, m.getFlagsRegister());
        m.getRegister().setInteger(reg1, result.result);
        m.getRegister().setInteger(REMAINDER, result.remainder);
        return true;
    }

    @Override
    public String instCode() {
        return "DIVIDE_SIGNED";
    }
}