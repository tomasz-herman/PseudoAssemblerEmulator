package com.hermant.program.instruction;

import com.hermant.machine.Machine;

import static com.hermant.machine.register.GeneralPurposeRegister.REMAINDER;

public class DivideInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int b = m.getRam().getInteger(ramAddress);
        int a = m.getRegister().get(reg1);
        DivisionResult result = divide(a, b, m.getFlagsRegister());
        m.getRegister().set(reg1, result.result);
        m.getRegister().set(REMAINDER, result.remainder);
        return true;
    }

    @Override
    public final byte code() {
        return DIVIDE;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "DIVIDE";
    }
}