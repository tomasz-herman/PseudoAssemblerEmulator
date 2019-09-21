package com.hermant.machine.register;

public final class FlagsRegister {

    private static final int overflowFlag = 0x0800;
    private static final int signFlag = 0x0080;
    private static final int zeroFlag = 0x0040;
    private static final int parityFlag = 0x0004;
    private static final int carryFlag = 0x0001;

    private boolean overflow = false;
    private boolean sign = false;
    private boolean zero = false;
    private boolean parity = false;
    private boolean carry = false;

    public int getFlags(){
        int flags = 0;
        if(overflow) flags |= overflowFlag;
        if(sign) flags |= signFlag;
        if(zero) flags |= zeroFlag;
        if(parity) flags |= parityFlag;
        if(carry) flags |= carryFlag;
        return flags;
    }

    public void setFlags(int flags){
        overflow = (flags & overflowFlag) != 0;
        sign = (flags & signFlag) != 0;
        zero = (flags & zeroFlag) != 0;
        parity = (flags & parityFlag) != 0;
        carry = (flags & carryFlag) != 0;
    }

    public void setOverflowFlag(){ overflow = true; }
    public void resetOverflowFlag(){ overflow = false; }
    public void setSignFlag(){ sign = true; }
    public void resetSignFlag(){ sign = false; }
    public void setZeroFlag(){ zero = true; }
    public void resetZeroFlag(){ zero = false; }
    public void setParityFlag(){ parity = true; }
    public void resetParityFlag(){ parity = false; }
    public void setCarryFlag(){ carry = true; }
    public void resetCarryFlag(){ carry = false; }

    public boolean isEqual(){ return zero; }
    public boolean isNotEqual(){ return !zero; }
    public boolean isGreaterOrEqual(){ return overflow == sign; }
    public boolean isGreater(){ return overflow == sign && !zero; }
    public boolean isLesser(){ return overflow != sign; }
    public boolean isLessOrEqual(){ return overflow != sign || zero; }
    public boolean isAbove(){ return !zero && !carry; }
    public boolean isBelow(){ return carry; }
    public boolean isBelowOrEqual(){ return zero || carry; }
    public boolean isAboveOrEqual(){ return !carry; }
    public boolean isOverflow(){ return overflow; }
    public boolean isNotOverflow(){ return !overflow; }
    public boolean isSigned(){ return sign; }
    public boolean isNotSigned(){ return !sign; }
    public boolean isParityEven(){ return parity; }
    public boolean isParityOdd(){ return !parity; }

    @Override
    public String toString() {
        return "OF=" + (overflow ? 1 : 0) + ", SF=" + (sign ? 1 : 0) + ", ZF=" + (zero ? 1 : 0) +
                ", PF=" + (parity ? 1 : 0) + ", CF=" + (carry ? 1 : 0);
    }
}
