package com.hermant.program.instruction;

import com.hermant.machine.Machine;

public class MultiplyInstruction extends Instruction implements MemoryOperation {

    MultiplyInstruction(Byte reg1, Byte reg2, Short ramAddress) {
        super(Instruction.MULTIPLY, reg1, reg2, ramAddress);
    }

    @Override
    public boolean execute(Machine m, boolean debug){
        super.execute(m, debug);
        int ramAddress = getMemoryAddress(m.getRegister(), reg2, ramOffset);
        int a = m.getRegister().getInteger(reg1);
        int b = m.getRam().getInteger(ramAddress);
        int result = a * b;
        if(result != ((long)a * (long)b)){
            m.getFlagsRegister().setOverflowFlag();
            m.getFlagsRegister().setCarryFlag();
        }else {
            m.getFlagsRegister().resetOverflowFlag();
            m.getFlagsRegister().resetCarryFlag();
        }
        m.getRegister().setInteger(reg1, result);
        return true;
    }

    @Override
    public String instCode() {
        return "MULTIPLY";
    }
}