package com.hermant.program.instruction;

import com.hermant.machine.register.FlagsRegister;

public interface FloatArithmeticOperation {

    default void compareFloat(float a, float b, FlagsRegister flags){
        flags.resetSignFlag();
        if(a > b){
            flags.resetZeroFlag();
            flags.resetCarryFlag();
            flags.resetParityFlag();
        } else if(a < b){
            flags.resetZeroFlag();
            flags.setCarryFlag();
            flags.resetParityFlag();
        } else if(a == b){
            flags.setZeroFlag();
            flags.resetCarryFlag();
            flags.resetParityFlag();
        } else {
            flags.setZeroFlag();
            flags.setCarryFlag();
            flags.setParityFlag();
        }
    }

    default void examineFloat(float f, FlagsRegister flags){
        int bytes = Float.floatToIntBits(f);
        if((bytes & 0x80000000) != 0)flags.setSignFlag();
        else flags.resetSignFlag();
        if(Float.isInfinite(f)){//infinity
            flags.resetZeroFlag();
            flags.setCarryFlag();
            flags.setParityFlag();
        } else if(Float.isNaN(f)){//nan
            flags.resetZeroFlag();
            flags.setCarryFlag();
            flags.resetParityFlag();
        } else if(f == 0.0f){//zero
            flags.setZeroFlag();
            flags.resetCarryFlag();
            flags.resetParityFlag();
        } else if((bytes & 0x7F800000) == 0){//subnormal
            flags.setZeroFlag();
            flags.resetCarryFlag();
            flags.setParityFlag();
        } else if(Float.isFinite(f)){//finite
            flags.resetZeroFlag();
            flags.resetCarryFlag();
            flags.setParityFlag();
        } else {//else
            flags.resetZeroFlag();
            flags.resetCarryFlag();
            flags.resetParityFlag();
        }
    }
}
