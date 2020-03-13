package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputSignedInstruction extends Instruction implements MemoryOperation, OutputOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.print(m.getRam().getInteger(ramAddress));
    }

    @Override
    public final byte code() {
        return OUTPUT_SIGNED;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "OUTPUT_SIGNED";
    }
}