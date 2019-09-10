package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftShiftLogicalInstruction extends Instruction implements LogicalOperation, MemoryOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().set(reg1, logical(m.getRegister().get(reg1) << m.getRam().getInteger(ramAddress), m.getFlagsRegister()));
    }

    @Override
    public final byte code() {
        return LEFT_SHIFT_LOGICAL;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "LEFT_SHIFT_LOGICAL";
    }
}