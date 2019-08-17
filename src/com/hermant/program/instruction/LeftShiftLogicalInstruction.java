package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class LeftShiftLogicalInstruction extends Instruction implements LogicalOperation, MemoryOperation {

    LeftShiftLogicalInstruction(Byte reg1, Byte reg2, Short ramOffset) {
        super(Instruction.LEFT_SHIFT_LOGICAL, reg1, reg2, ramOffset);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        m.getRegister().setInteger(reg1, logical(m.getRegister().getInteger(reg1) << m.getRam().getInteger(ramAddress), m.getFlagsRegister()));
        return true;
    }

    @Override
    public int instLength() {
        return 4;
    }

    @Override
    public String instCode() {
        return "LEFT_SHIFT_LOGICAL";
    }
}