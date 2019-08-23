package com.hermant.program.instruction;

import com.hermant.machine.Machine;

/**
 * Instruction that might be executed without knowing it's type. It looks up it's functionality in static tables.
 * Might be useful to prevent high memory usage associated with creating multiple small objects, and avoid unnecessary
 * garbage collecting.
 */
public class ReusableInstruction extends Instruction {

    public ReusableInstruction(byte code, Byte reg1, Byte reg2, Short ramOffset) {
        super(code, reg1, reg2, ramOffset);
    }

    public void set(byte code, Byte reg1, Byte reg2, Short ramOffset){
        this.code = code;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.ramOffset = ramOffset;
    }

    @Override
    public boolean execute(Machine m, boolean debug) {
        return InstructionFactory.INSTRUCTION_ACTIONS[128 + code].execute(m, debug);
    }

    @Override
    public int instLength() {
        return InstructionFactory.INSTRUCTION_LENGTHS[128 + code];
    }

    @Override
    public String instCode() {
        return InstructionFactory.INSTRUCTION_CODES[128 + code];
    }
}
