package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputFloatInstruction extends Instruction implements MemoryOperation, OutputOperation {

    @Override
    public final void run(Machine m){
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.print(m.getRam().getFloat(ramAddress));
    }

    @Override
    public final byte code() {
        return OUTPUT_FLOAT;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "OUTPUT_FLOAT";
    }
}