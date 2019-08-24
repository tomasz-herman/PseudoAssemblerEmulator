package com.hermant.program.instruction;

import com.hermant.machine.register.FlagsRegister;

import java.util.function.BiFunction;

public interface IntegerArithmeticOperation {

    default void compare(int a, int b, FlagsRegister flags, BiFunction<Long, Long, Long> bi){
        long signed = bi.apply((long)a, (long)b);
        long unsigned = bi.apply(Integer.toUnsignedLong(a), Integer.toUnsignedLong(b));
        if((signed & 0x80000000) != 0)flags.setSignFlag();
        else flags.resetSignFlag();
        if(signed == 0)flags.setZeroFlag();
        else flags.resetZeroFlag();
        if(signed < Integer.MIN_VALUE || signed > Integer.MAX_VALUE) flags.setOverflowFlag();
        else flags.resetOverflowFlag();
        if((unsigned & 0x100000000L) != 0)flags.setCarryFlag();
        else flags.resetCarryFlag();
        if((Integer.bitCount((int)signed)&0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
    }

    default int decrement(int a, FlagsRegister flags){
        int result = a - 1;
        if(result == 0) flags.setZeroFlag();
        else flags.resetZeroFlag();
        if(a == Integer.MIN_VALUE) flags.setOverflowFlag();
        else flags.resetOverflowFlag();
        if((Integer.bitCount(result)&0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
        if((result & 0x80000000) != 0)flags.setSignFlag();
        else flags.resetSignFlag();
        return result;
    }

    default int increment(int a, FlagsRegister flags){
        int result = a + 1;
        if(result == 0) flags.setZeroFlag();
        else flags.resetZeroFlag();
        if(a == Integer.MAX_VALUE) flags.setOverflowFlag();
        else flags.resetOverflowFlag();
        if((Integer.bitCount(result)&0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
        if((result & 0x80000000) != 0)flags.setSignFlag();
        else flags.resetSignFlag();
        return result;
    }

    default DivisionResult divide(int a, int b, FlagsRegister flags){
        if(b == 0){
            flags.setOverflowFlag();
            flags.setCarryFlag();
            return new DivisionResult(0, 0);
        }
        flags.resetOverflowFlag();
        flags.resetCarryFlag();
        return new DivisionResult(Integer.divideUnsigned(a, b), Integer.remainderUnsigned(a, b));
    }

    default DivisionResult divideSigned(int a, int b, FlagsRegister flags){
        if(b == 0){
            flags.setOverflowFlag();
            flags.setCarryFlag();
            return new DivisionResult(0, 0);
        }
        flags.resetOverflowFlag();
        flags.resetCarryFlag();
        return new DivisionResult(a / b, a % b);
    }

    class DivisionResult{
        int result;
        int remainder;
        DivisionResult(int result, int remainder){
            this.result = result;
            this.remainder = remainder;
        }
    }

    default int multiply(int a, int b, FlagsRegister flags){
        int result = a * b;
        if(result != ((long)a * (long)b)){
            flags.setOverflowFlag();
            flags.setCarryFlag();
        }else {
            flags.resetOverflowFlag();
            flags.resetCarryFlag();
        }
        return result;
    }

    default int add(int a, int b, FlagsRegister flags){
        compare(a, b, flags, Long::sum);
        return a + b;
    }

    default int subtract(int a, int b, FlagsRegister flags){
        compare(a, b, flags, (x, y) -> x - y);
        return a - b;
    }

    default int negate(int a, FlagsRegister flags){
        int result = -a;
        if(a == 0){
            flags.setCarryFlag();
            flags.setZeroFlag();
        }else {
            flags.resetCarryFlag();
            flags.resetZeroFlag();
        }
        if(a == Integer.MIN_VALUE) flags.setOverflowFlag();
        else flags.resetOverflowFlag();
        if((Integer.bitCount(result)&0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
        if((result & 0x80000000) != 0)flags.setSignFlag();
        else flags.resetSignFlag();
        return result;
    }
}
