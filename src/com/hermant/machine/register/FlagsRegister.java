package com.hermant.machine.register;

public class FlagsRegister {

    private static final int overflowFlag = 0x0800;
    private static final int signFlag = 0x0080;
    private static final int zeroFlag = 0x0040;
    private static final int auxiliaryFlag = 0x0010;
    private static final int parityFlag = 0x0004;
    private static final int carryFlag = 0x0001;

    private int flags = 0;

    public int getFlags(){return flags;}
    public void setFlags(int flags){this.flags = flags;}

    public void setOverflowFlag(){ flags |= overflowFlag; }
    public void resetOverflowFlag(){ flags &= ~overflowFlag; }
    public void setSignFlag(){ flags |= signFlag; }
    public void resetSignFlag(){ flags &= ~signFlag; }
    public void setZeroFlag(){ flags |= zeroFlag; }
    public void resetZeroFlag(){ flags &= ~zeroFlag; }
    @SuppressWarnings("unused")
    public void setAuxiliaryFlag(){ flags |= auxiliaryFlag; }
    @SuppressWarnings("unused")
    public void resetAuxiliaryFlag(){ flags &= ~auxiliaryFlag; }
    public void setParityFlag(){ flags |= parityFlag; }
    public void resetParityFlag(){ flags &= ~parityFlag; }
    public void setCarryFlag(){ flags |= carryFlag; }
    public void resetCarryFlag(){ flags &= ~carryFlag; }

    private boolean isOverflowFlag(){ return (flags & overflowFlag) != 0; }
    private boolean isSignFlag(){ return (flags & signFlag) != 0; }
    private boolean isZeroFlag(){ return (flags & zeroFlag) != 0; }
    private boolean isAuxiliaryFlag(){ return (flags & auxiliaryFlag) != 0; }
    private boolean isParityFlag(){ return (flags & parityFlag) != 0; }
    private boolean isCarryFlag(){ return (flags & carryFlag) != 0; }

    public boolean isEqual(){ return isZeroFlag(); }
    public boolean isNotEqual(){ return !isZeroFlag(); }
    public boolean isGreaterOrEqual(){ return isOverflowFlag() == isSignFlag(); }
    public boolean isGreater(){ return isOverflowFlag() == isSignFlag() && !isZeroFlag(); }
    public boolean isLesser(){ return isOverflowFlag() != isSignFlag(); }
    public boolean isLessOrEqual(){ return isOverflowFlag() != isSignFlag() || isZeroFlag(); }
    public boolean isAbove(){ return !isZeroFlag() && !isCarryFlag(); }
    public boolean isBelow(){ return isCarryFlag(); }
    public boolean isBelowOrEqual(){ return isZeroFlag() || isCarryFlag(); }
    public boolean isAboveOrEqual(){ return !isCarryFlag(); }
    public boolean isOverflow(){ return isOverflowFlag(); }
    public boolean isNotOverflow(){ return !isOverflowFlag(); }
    public boolean isSigned(){ return isSignFlag(); }
    public boolean isNotSigned(){ return !isSignFlag(); }
    public boolean isParityEven(){ return isParityFlag(); }
    public boolean isParityOdd(){ return !isParityFlag(); }

    @Override
    public String toString() {
        int of = isOverflowFlag() ? 1 : 0;
        int sf = isSignFlag() ? 1 : 0;
        int zf = isZeroFlag() ? 1 : 0;
        int af = isAuxiliaryFlag() ? 1 : 0;
        int pf = isParityFlag() ? 1 : 0;
        int cf = isCarryFlag() ? 1 : 0;
        return "OF = " + of + ", SF = " + sf + ", ZF = " + zf + ", AF = " + af + ", PF = " + pf + ", CF = " + cf;
    }
}
