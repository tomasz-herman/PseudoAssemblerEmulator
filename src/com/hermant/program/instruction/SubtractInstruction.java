package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class SubtractInstruction extends Instruction implements MemoryOperation, IntegerArithmeticOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRam().getInteger(ramAddress);
        m.getRegister().setInteger(reg1, subtract(a, b, m.getFlagsRegister()));
        return true;
    }

    @Override
    public final byte code() {
        return SUBTRACT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "SUBTRACT";
    }
}