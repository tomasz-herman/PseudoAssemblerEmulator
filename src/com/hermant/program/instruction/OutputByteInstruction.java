package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class OutputByteInstruction extends Instruction implements MemoryOperation, OutputOperation {

    @Override
    public final boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        final int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        System.out.print(Byte.toUnsignedInt(m.getRam().getByte(ramAddress)));
        if(debug) System.out.println();
        return true;
    }

    @Override
    public final byte code() {
        return OUTPUT_BYTE;
    }

    @Override
    public final int instLength() {
        return 4;
    }

    @Override
    public final String instCode() {
        return "OUTPUT_BYTE";
    }
}