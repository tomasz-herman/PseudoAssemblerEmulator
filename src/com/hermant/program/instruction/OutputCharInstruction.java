package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputCharInstruction extends Instruction implements MemoryOperation, OutputOperation {

    @Override
    public final boolean run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.print((char)m.getRam().getByte(ramAddress));
        return true;
    }

    @Override
    public final byte code() {
        return OUTPUT_CHAR;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "OUTPUT_CHAR";
    }
}