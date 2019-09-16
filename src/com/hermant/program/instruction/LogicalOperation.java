package com.hermant.program.instruction;

import com.hermant.machine.register.FlagsRegister;

public interface LogicalOperation extends ModifiesFlagsOperation {

    default int logical(int result, FlagsRegister flags){
        if((result & 0x80000000) != 0)flags.setSignFlag();
        else flags.resetSignFlag();
        if(result == 0)flags.setZeroFlag();
        else flags.resetZeroFlag();
        if((InstructionUtils.getPopulationCount(result & 0xff) & 0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
        flags.resetOverflowFlag();
        flags.resetCarryFlag();
        return result;
    }

}
