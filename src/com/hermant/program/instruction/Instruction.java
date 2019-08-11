package com.hermant.program.instruction;

import com.hermant.machine.*;
import com.hermant.machine.register.FlagsRegister;
import com.hermant.machine.register.InstructionPointer;
import com.hermant.machine.register.Register;

import java.io.Serializable;
import java.util.function.BiFunction;

public abstract class Instruction implements Serializable {
    //instruction codes
    public static final byte RETURN = (byte)0b00000001;

    public static final byte LOAD = (byte)0b10000010;
    public static final byte LOAD_REGISTER = (byte)0b00000010;
    public static final byte LOAD_BYTE = (byte)0b10000011;
    public static final byte LOAD_BYTE_UNSIGNED = (byte)0b10000100;
    public static final byte LOAD_FLOAT = (byte)0b10000101;
    public static final byte LOAD_REGISTER_FLOAT = (byte)0b00000101;
    public static final byte STORE = (byte)0b10000110;
    public static final byte STORE_BYTE = (byte)0b10000111;
    public static final byte STORE_FLOAT = (byte)0b10001000;
    public static final byte LOAD_ADDRESS = (byte)0b10001001;
    public static final byte OUTPUT = (byte)0b10001010;
    public static final byte OUTPUT_REGISTER = (byte)0b00001010;
    public static final byte OUTPUT_SIGNED = (byte)0b10001011;
    public static final byte OUTPUT_REGISTER_SIGNED = (byte)0b00001011;
    public static final byte OUTPUT_FLOAT = (byte)0b10001100;
    public static final byte OUTPUT_REGISTER_FLOAT = (byte)0b00001100;
    public static final byte OUTPUT_CHAR = (byte)0b10001101;
    public static final byte OUTPUT_REGISTER_CHAR = (byte)0b00001101;
    public static final byte OUTPUT_BYTE = (byte)0b10001110;
    public static final byte OUTPUT_REGISTER_BYTE = (byte)0b00001110;
    public static final byte PUSH = (byte)0b10001111;
    public static final byte PUSH_REGISTER = (byte)0b00001111;
    public static final byte PUSH_REGISTER_FLOAT = (byte)0b00010000;
    public static final byte PUSH_FLAGS = (byte)0b00010001;
    public static final byte POP = (byte)0b10010010;
    public static final byte POP_REGISTER = (byte)0b00010010;
    public static final byte POP_REGISTER_FLOAT = (byte)0b00010011;
    public static final byte POP_FLAGS = (byte)0b00010100;
    public static final byte EXCHANGE = (byte)0b10010101;
    public static final byte EXCHANGE_REGISTER = (byte)0b00010101;
    public static final byte EXCHANGE_FLOAT = (byte)0b10010110;
    public static final byte EXCHANGE_REGISTER_FLOAT = (byte)0b00010110;
    public static final byte NO_OPERATION = (byte)0b00010111;
    public static final byte STORE_FLOAT_AS_INTEGER = (byte)0b10011000;
    public static final byte LOAD_INTEGER_AS_FLOAT = (byte)0b10011001;
    public static final byte RANDOM = (byte)0b10011010;
    public static final byte RANDOM_REGISTER = (byte)0b00011010;
    public static final byte ENTER = (byte)0b00011011;
    public static final byte LEAVE = (byte)0b00011100;

    public static final byte ADD = (byte)0b10100000;
    public static final byte ADD_REGISTER = (byte)0b00100000;
    public static final byte SUBTRACT = (byte)0b10100001;
    public static final byte SUBTRACT_REGISTER = (byte)0b00100001;
    public static final byte MULTIPLY = (byte)0b10100010;
    public static final byte MULTIPLY_REGISTER = (byte)0b00100010;
    public static final byte DIVIDE = (byte)0b10100100;
    public static final byte DIVIDE_REGISTER = (byte)0b00100100;
    public static final byte DIVIDE_SIGNED = (byte)0b10100101;
    public static final byte DIVIDE_SIGNED_REGISTER = (byte)0b00100101;
    public static final byte COMPARE = (byte)0b10101000;
    public static final byte COMPARE_REGISTER = (byte)0b00101000;
    public static final byte INCREMENT = (byte)0b10101001;
    public static final byte INCREMENT_REGISTER = (byte)0b00101001;
    public static final byte DECREMENT = (byte)0b10101010;
    public static final byte DECREMENT_REGISTER = (byte)0b00101010;
    public static final byte NEGATE = (byte)0b10101011;
    public static final byte NEGATE_REGISTER = (byte)0b00101011;

    public static final byte ADD_FLOAT = (byte)0b10110000;
    public static final byte ADD_REGISTER_FLOAT = (byte)0b00110000;
    public static final byte SUBTRACT_FLOAT = (byte)0b10110001;
    public static final byte SUBTRACT_REGISTER_FLOAT = (byte)0b00110001;
    public static final byte MULTIPLY_FLOAT = (byte)0b10110010;
    public static final byte MULTIPLY_REGISTER_FLOAT = (byte)0b00110010;
    public static final byte DIVIDE_FLOAT = (byte)0b10110011;
    public static final byte DIVIDE_REGISTER_FLOAT = (byte)0b00110011;
    public static final byte COMPARE_FLOAT = (byte)0b10110100;
    public static final byte COMPARE_REGISTER_FLOAT = (byte)0b00110100;
    public static final byte SQUARE_ROOT_FLOAT = (byte)0b00110101;
    public static final byte ABSOLUTE_FLOAT = (byte)0b00110110;
    public static final byte SINE_FLOAT = (byte)0b00110111;
    public static final byte COSINE_FLOAT = (byte)0b00111000;
    public static final byte TANGENT_FLOAT = (byte)0b00111001;
    public static final byte EXAMINE_FLOAT = (byte)0b10111010;
    public static final byte EXAMINE_FLOAT_REGISTER = (byte)0b00111010;
    public static final byte TEST_FLOAT = (byte)0b10111011;
    public static final byte TEST_FLOAT_REGISTER = (byte)0b00111011;

    public static final byte AND = (byte)0b11000000;
    public static final byte OR = (byte)0b11000001;
    public static final byte XOR = (byte)0b11000010;
    public static final byte TEST = (byte)0b11000011;
    public static final byte NOT = (byte)0b11000100;
    public static final byte RIGHT_SHIFT_LOGICAL = (byte)0b11000101;
    public static final byte LEFT_SHIFT_LOGICAL = (byte)0b11000110;
    public static final byte RIGHT_SHIFT_ARITHMETIC = (byte)0b11000111;
    public static final byte LEFT_SHIFT_ARITHMETIC = (byte)0b11000110;
    public static final byte RIGHT_ROTATE = (byte)0b11001000;
    public static final byte LEFT_ROTATE = (byte)0b11001001;
    public static final byte AND_REGISTER = (byte)0b01000000;
    public static final byte OR_REGISTER = (byte)0b01000001;
    public static final byte XOR_REGISTER = (byte)0b01000010;
    public static final byte TEST_REGISTER = (byte)0b01000011;
    public static final byte NOT_REGISTER = (byte)0b01000100;
    public static final byte RIGHT_SHIFT_LOGICAL_REGISTER = (byte)0b01000101;
    public static final byte LEFT_SHIFT_LOGICAL_REGISTER = (byte)0b01000110;
    public static final byte RIGHT_SHIFT_ARITHMETIC_REGISTER = (byte)0b01000111;
    public static final byte LEFT_SHIFT_ARITHMETIC_REGISTER = (byte)0b01000110;
    public static final byte RIGHT_ROTATE_REGISTER = (byte)0b01001000;
    public static final byte LEFT_ROTATE_REGISTER = (byte)0b01001001;

    public static final byte JUMP = (byte)0b11100000;
    public static final byte JUMP_EQUAL = (byte)0b11100001;
    public static final byte JUMP_NOT_EQUAL = (byte)0b11100010;
    public static final byte JUMP_GREATER = (byte)0b11100011;
    public static final byte JUMP_GREATER_OR_EQUAL = (byte)0b11100100;
    public static final byte JUMP_LESSER = (byte)0b11100101;
    public static final byte JUMP_LESS_OR_EQUAL = (byte)0b11100110;
    public static final byte JUMP_ABOVE = (byte)0b11100111;
    public static final byte JUMP_ABOVE_OR_EQUAL = (byte)0b11101000;
    public static final byte JUMP_BELOW = (byte)0b11101001;
    public static final byte JUMP_BELOW_OR_EQUAL = (byte)0b11101010;
    public static final byte JUMP_OVERFLOW = (byte)0b11101011;
    public static final byte JUMP_NOT_OVERFLOW = (byte)0b11101100;
    public static final byte JUMP_SIGNED = (byte)0b11101101;
    public static final byte JUMP_NOT_SIGNED = (byte)0b11101110;
    public static final byte JUMP_PARITY = (byte)0b11101111;
    public static final byte JUMP_NOT_PARITY = (byte)0b11110000;
    public static final byte LOOP = (byte)0b11110001;
    public static final byte CALL = (byte)0b11110010;

    Byte code;
    Byte reg1;
    Byte reg2;
    Integer ramOffset;

    Instruction(byte code, Byte reg1, Byte reg2, Integer ramAddress){
        this.code = code;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.ramOffset = ramAddress;
    }

    int getMemoryAddress(Register reg){ return (code & 0x10000000) != 0 ? reg.getInteger(reg2) + ramOffset : 0; }

    public boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getInstructionPointer());
        setInstructionPointer(m.getInstructionPointer());
        return true;
    }

    public void debug(InstructionPointer instructionPointer){
        System.out.println(String.format("%1$08X",instructionPointer.get()) + " | " + this);
    }

    private void setInstructionPointer(InstructionPointer instructionPointer){
        int instructionLength = (code & 0x10000000) == 0 ? 2 : 4;
        instructionPointer.set(instructionPointer.get() + instructionLength);
    }

    void compare(int a, int b, FlagsRegister flags, BiFunction<Long, Long, Long> bi){
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

    void compareFloat(float a, float b, FlagsRegister flags){
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

    void examineFloat(float f, FlagsRegister flags){
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

    public abstract String instCode();

    @Override
    public String toString() {
        String instCode = instCode();
        String hexCode = String.format("%1$02X",code);
        String r1Hex = String.format("%1$01X",reg1);
        String r2Hex = String.format("%1$01X",reg2);
        String memHex = String.format("%1$04X",ramOffset);
        instCode = String.format("%-32s", instCode);
        return hexCode + " " + r1Hex + " " + r2Hex + " " + memHex + " | " + instCode;
    }
}
