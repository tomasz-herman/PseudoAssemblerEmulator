package com.hermant.program;

import com.hermant.machine.RandomAccessMemory;
import com.hermant.machine.Register;
import com.hermant.machine.FlagsRegister;
import com.hermant.machine.Stack;

import java.util.function.BiFunction;

import static com.hermant.machine.Register.*;

public class Instruction {
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

    private Byte code;
    private Byte reg1;
    private Byte reg2;
    private Integer ramOffset;

    public Instruction(RandomAccessMemory ram, Register reg){ this(ram, reg.getInteger(INSTRUCTION_POINTER)); }

    private Instruction(RandomAccessMemory ram, int address){ loadFromMemory(ram, address); }

    public Instruction(byte code, Byte reg1, Byte reg2, Integer ramAddress){ create(code, reg1, reg2, ramAddress); }

    public int loadIntoMemory(RandomAccessMemory ram, int address){
        ram.setByte(address, code);
        ram.setByte(address + 1, (byte)(((reg1 & 0xf)<<4) | (reg2 & 0xf)));
        int length = (code & 0x10000000) == 0 ? 2 : 4;
        if(length == 2) return address + length;
        switch(ram.endianness){
            case LittleEndian, MiddleEndian -> {
                ram.setByte(address + 3, (byte)((ramOffset & 0xff00)>>8));
                ram.setByte(address + 2, (byte)(ramOffset & 0xff));
            }
            case BigEndian -> {
                ram.setByte(address + 2, (byte)((ramOffset & 0xff00)>>8));
                ram.setByte(address + 3, (byte)(ramOffset & 0xff));
            }
        }
        return address + length;
    }

    public void loadFromMemory(RandomAccessMemory ram, Register reg){
        loadFromMemory(ram, reg.getInteger(INSTRUCTION_POINTER));
    }

    private void loadFromMemory(RandomAccessMemory ram, int address){
        code = (byte)ram.getByte(address);
        reg1 = (byte)((ram.getByte(address+1)&0xf0)>>4);
        reg2 = (byte)(ram.getByte(address+1)&0xf);
        if ((code & 0x10000000)==0) {ramOffset = null; return;}
        switch(ram.endianness){
            case LittleEndian, MiddleEndian ->
                    ramOffset = (ram.getByte(address+3)<<8) + ram.getByte(address + 2);
            case BigEndian ->
                    ramOffset = (ram.getByte(address+2)<<8) + ram.getByte(address + 3);
        }
    }

    private void create(byte code, Byte reg1, Byte reg2, Integer ramAddress){
        this.code = code;
        this.reg1 = reg1;
        this.reg2 = reg2;
        this.ramOffset = ramAddress;
    }

    private int getMemoryAddress(Register reg){ return (code & 0x10000000) != 0 ? reg.getInteger(reg2) + ramOffset : 0; }

    public boolean execute(RandomAccessMemory ram, Register reg, Register fpr, FlagsRegister flags, Stack stack, boolean debug){
        if(debug) debug(reg);

        setInstructionPointer(reg);
        int ramAddress = getMemoryAddress(reg);

        switch(code) {
            case RETURN -> { return ret(reg, stack); }
            case LOAD -> reg.setInteger(reg1, ram.getInteger(ramAddress));
            case LOAD_REGISTER -> reg.setInteger(reg1, reg.getInteger(reg2));
            case STORE -> ram.setInteger(ramAddress, reg.getInteger(reg1));
            case LOAD_FLOAT -> fpr.setInteger(reg1, ram.getInteger(ramAddress));
            case LOAD_REGISTER_FLOAT -> fpr.setInteger(reg1, fpr.getInteger(reg2));
            case STORE_FLOAT -> ram.setInteger(ramAddress, fpr.getInteger(reg1));
            case LOAD_ADDRESS -> reg.setInteger(reg1, ramAddress);
            case EXCHANGE -> exchangeRegMem(reg, ram, ramAddress);
            case EXCHANGE_REGISTER -> exchangeRegReg(reg);
            case EXCHANGE_FLOAT -> exchangeRegMem(fpr, ram, ramAddress);
            case EXCHANGE_REGISTER_FLOAT -> exchangeRegReg(fpr);
            case STORE_BYTE -> ram.setByte(ramAddress, (byte)reg.getInteger(reg1));
            case LOAD_BYTE_UNSIGNED -> reg.setInteger(reg1, ram.getByte(ramAddress));
            case LOAD_BYTE -> reg.setInteger(reg1, (byte)ram.getByte(ramAddress));
            case LOAD_INTEGER_AS_FLOAT -> fpr.setFloat(reg1, ram.getInteger(ramAddress));
            case STORE_FLOAT_AS_INTEGER -> ram.setInteger(ramAddress, (int)fpr.getFloat(reg1));
            case RANDOM -> ram.setInteger(ramAddress, ram.random.nextInt());
            case RANDOM_REGISTER -> reg.setInteger(reg1, ram.random.nextInt());
            case ENTER -> enter(stack, reg);
            case LEAVE -> leave(stack, reg);
            case OUTPUT -> output(Integer.toUnsignedString(ram.getInteger(ramAddress)));
            case OUTPUT_SIGNED -> output(Integer.toString(ram.getInteger(ramAddress)));
            case OUTPUT_FLOAT ->output(Float.toString(ram.getFloat(ramAddress)));
            case OUTPUT_BYTE -> output(Integer.toString(ram.getByte(ramAddress)));
            case OUTPUT_CHAR -> output("" + (char)(byte)ram.getByte(ramAddress));
            case OUTPUT_REGISTER -> output(Integer.toUnsignedString(reg.getInteger(reg1)));
            case OUTPUT_REGISTER_SIGNED -> output(Integer.toString(reg.getInteger(reg1)));
            case OUTPUT_REGISTER_FLOAT -> output(Float.toString(fpr.getFloat(reg1)));
            case OUTPUT_REGISTER_BYTE -> output(Integer.toString(reg.getInteger(reg1) & 0xFF));
            case OUTPUT_REGISTER_CHAR -> output("" + (char)(byte)reg.getInteger(reg1));
            case NO_OPERATION -> {}

            case PUSH -> stack.push(ram.getInteger(ramAddress));
            case PUSH_REGISTER -> stack.push(reg.getInteger(reg1));
            case PUSH_REGISTER_FLOAT -> stack.push(fpr.getInteger(reg1));
            case PUSH_FLAGS -> stack.push(flags.getFlags());
            case POP -> ram.setInteger(ramAddress, stack.pop());
            case POP_REGISTER -> reg.setInteger(reg1, stack.pop());
            case POP_REGISTER_FLOAT -> fpr.setInteger(reg1, stack.pop());
            case POP_FLAGS -> flags.setFlags(stack.pop());

            case ADD -> addAndSetFlags(ram.getInteger(ramAddress), reg, flags);
            case ADD_REGISTER -> addAndSetFlags(reg.getInteger(reg2), reg, flags);
            case SUBTRACT -> subAndSetFlags(ram.getInteger(ramAddress), reg, flags);
            case SUBTRACT_REGISTER -> subAndSetFlags(reg.getInteger(reg2), reg, flags);
            case MULTIPLY -> multiplyAndSetFlags(ram.getInteger(ramAddress), reg, flags);
            case MULTIPLY_REGISTER -> multiplyAndSetFlags(reg.getInteger(reg2), reg, flags);
            case DIVIDE -> divideUnsignedAndSetFlags(ram.getInteger(ramAddress), reg, flags);
            case DIVIDE_REGISTER -> divideUnsignedAndSetFlags(reg.getInteger(reg2), reg, flags);
            case DIVIDE_SIGNED -> divideAndSetFlags(ram.getInteger(ramAddress), reg, flags);
            case DIVIDE_SIGNED_REGISTER -> divideAndSetFlags(reg.getInteger(reg2), reg, flags);
            case COMPARE -> compare(reg.getInteger(reg1), ram.getInteger(ramAddress), flags, (a, b) -> a - b);
            case COMPARE_REGISTER -> compare(reg.getInteger(reg1), reg.getInteger(reg2), flags, (a, b) -> a - b);

            case COMPARE_FLOAT -> compareFloat(fpr.getFloat(reg1), ram.getFloat(ramAddress), flags);
            case COMPARE_REGISTER_FLOAT -> compareFloat(fpr.getFloat(reg1), fpr.getFloat(reg2), flags);

            case ADD_FLOAT -> fpr.setFloat(reg1, fpr.getFloat(reg1) + ram.getFloat(ramAddress));
            case ADD_REGISTER_FLOAT -> fpr.setFloat(reg1, fpr.getFloat(reg1) + fpr.getFloat(reg2));
            case SUBTRACT_FLOAT -> fpr.setFloat(reg1, fpr.getFloat(reg1) - ram.getFloat(ramAddress));
            case SUBTRACT_REGISTER_FLOAT -> fpr.setFloat(reg1, fpr.getFloat(reg1) - fpr.getFloat(reg2));
            case MULTIPLY_FLOAT -> fpr.setFloat(reg1, fpr.getFloat(reg1) * ram.getFloat(ramAddress));
            case MULTIPLY_REGISTER_FLOAT -> fpr.setFloat(reg1, fpr.getFloat(reg1) * fpr.getFloat(reg2));
            case DIVIDE_FLOAT -> fpr.setFloat(reg1, fpr.getFloat(reg1) / ram.getFloat(ramAddress));
            case DIVIDE_REGISTER_FLOAT -> fpr.setFloat(reg1, fpr.getFloat(reg1) / fpr.getFloat(reg2));
            case SQUARE_ROOT_FLOAT -> fpr.setFloat(reg1, (float)Math.sqrt(fpr.getFloat(reg1)));
            case ABSOLUTE_FLOAT -> fpr.setFloat(reg1, Math.abs(fpr.getFloat(reg1)));
            case SINE_FLOAT -> fpr.setFloat(reg1, (float)Math.sin(fpr.getFloat(reg1)));
            case COSINE_FLOAT -> fpr.setFloat(reg1, (float)Math.cos(fpr.getFloat(reg1)));
            case TANGENT_FLOAT -> fpr.setFloat(reg1, (float)Math.tan(fpr.getFloat(reg1)));
            case EXAMINE_FLOAT -> examineFloat(ram.getFloat(ramAddress), flags);
            case EXAMINE_FLOAT_REGISTER -> examineFloat(fpr.getFloat(reg1), flags);
            case TEST_FLOAT -> compareFloat(ram.getFloat(ramAddress), 0f, flags);
            case TEST_FLOAT_REGISTER -> compareFloat(fpr.getFloat(reg1), 0f, flags);

            case NEGATE -> ram.setInteger(ramAddress, negateAndSetFlags(ram.getInteger(ramAddress), flags));
            case NEGATE_REGISTER -> reg.setInteger(reg1, negateAndSetFlags(reg.getInteger(reg1), flags));
            case INCREMENT -> ram.setInteger(ramAddress, incrementAndSetFlags(ram.getInteger(ramAddress), flags));
            case INCREMENT_REGISTER -> reg.setInteger(reg1, incrementAndSetFlags(reg.getInteger(reg1), flags));
            case DECREMENT -> ram.setInteger(ramAddress, decrementAndSetFlags(ram.getInteger(ramAddress), flags));
            case DECREMENT_REGISTER -> reg.setInteger(reg1, decrementAndSetFlags(reg.getInteger(reg1), flags));

            case TEST -> setFlagsAfterLogicalOp(reg.getInteger(reg1) & ram.getInteger(ramAddress), flags);
            case TEST_REGISTER -> setFlagsAfterLogicalOp(reg.getInteger(reg1) & reg.getInteger(reg2), flags);
            case AND -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) & ram.getInteger(ramAddress), flags));
            case AND_REGISTER -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) & reg.getInteger(reg2), flags));
            case OR -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) | ram.getInteger(ramAddress), flags));
            case OR_REGISTER -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) | reg.getInteger(reg2), flags));
            case XOR -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) ^ ram.getInteger(ramAddress), flags));
            case XOR_REGISTER -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) ^ reg.getInteger(reg2), flags));
            case RIGHT_SHIFT_LOGICAL -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) >>> ram.getInteger(ramAddress), flags));
            case LEFT_SHIFT_LOGICAL -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) << ram.getInteger(ramAddress), flags));
            case RIGHT_SHIFT_LOGICAL_REGISTER -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) >>> reg.getInteger(reg2), flags));
            case LEFT_SHIFT_LOGICAL_REGISTER -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) << reg.getInteger(reg2), flags));
            case RIGHT_SHIFT_ARITHMETIC -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) >> ram.getInteger(ramAddress), flags));
            case RIGHT_SHIFT_ARITHMETIC_REGISTER -> reg.setInteger(reg1, setFlagsAfterLogicalOp(reg.getInteger(reg1) >> reg.getInteger(reg2), flags));
            case RIGHT_ROTATE -> reg.setInteger(reg1, setFlagsAfterLogicalOp(Integer.rotateRight(reg.getInteger(reg1), ram.getInteger(ramAddress)), flags));
            case LEFT_ROTATE -> reg.setInteger(reg1, setFlagsAfterLogicalOp(Integer.rotateLeft(reg.getInteger(reg1), ram.getInteger(ramAddress)), flags));
            case RIGHT_ROTATE_REGISTER -> reg.setInteger(reg1, setFlagsAfterLogicalOp(Integer.rotateRight(reg.getInteger(reg1), reg.getInteger(reg2)), flags));
            case LEFT_ROTATE_REGISTER -> reg.setInteger(reg1, setFlagsAfterLogicalOp(Integer.rotateLeft(reg.getInteger(reg1), reg.getInteger(reg2)), flags));

            case NOT_REGISTER -> reg.setInteger(reg1, ~reg.getInteger(reg1));
            case NOT -> ram.setInteger(ramAddress, ~ram.getInteger(ramAddress));

            case JUMP -> jump(reg, ramAddress);
            case JUMP_EQUAL -> { if(flags.isEqual()) jump(reg, ramAddress); }
            case JUMP_NOT_EQUAL -> { if(flags.isNotEqual()) jump(reg, ramAddress); }
            case JUMP_GREATER -> { if(flags.isGreater()) jump(reg, ramAddress); }
            case JUMP_GREATER_OR_EQUAL -> { if(flags.isGreaterOrEqual()) jump(reg, ramAddress); }
            case JUMP_LESSER -> { if(flags.isLesser()) jump(reg, ramAddress); }
            case JUMP_LESS_OR_EQUAL -> { if(flags.isLessOrEqual()) jump(reg, ramAddress); }
            case JUMP_ABOVE -> { if(flags.isAbove()) jump(reg, ramAddress); }
            case JUMP_ABOVE_OR_EQUAL -> { if(flags.isAboveOrEqual()) jump(reg, ramAddress); }
            case JUMP_BELOW -> { if(flags.isBelow()) jump(reg, ramAddress); }
            case JUMP_BELOW_OR_EQUAL -> { if(flags.isBelowOrEqual()) jump(reg, ramAddress); }
            case JUMP_OVERFLOW -> { if(flags.isOverflow()) jump(reg, ramAddress); }
            case JUMP_NOT_OVERFLOW -> { if(flags.isNotOverflow()) jump(reg, ramAddress); }
            case JUMP_SIGNED -> { if(flags.isSigned()) jump(reg, ramAddress); }
            case JUMP_NOT_SIGNED -> { if(flags.isNotSigned()) jump(reg, ramAddress); }
            case JUMP_PARITY -> { if(flags.isParityEven()) jump(reg, ramAddress); }
            case JUMP_NOT_PARITY -> { if(flags.isParityOdd()) jump(reg, ramAddress); }
            case LOOP -> { if(loop(reg)) jump(reg, ramAddress); }
            case CALL -> { stack.push(reg.getInteger(INSTRUCTION_POINTER)); jump(reg, ramAddress); }

            default -> throw new IllegalStateException("Unrecognizable instruction code " + String.format("%1$02X",code));
        }
        return true;
    }

    private void debug(Register reg){
        if(code != OUTPUT && code != OUTPUT_FLOAT && code != OUTPUT_REGISTER && code != OUTPUT_REGISTER_FLOAT
                && code != OUTPUT_SIGNED && code != OUTPUT_REGISTER_SIGNED && code != OUTPUT_BYTE
                && code != OUTPUT_CHAR  && code != OUTPUT_REGISTER_BYTE  && code != OUTPUT_REGISTER_CHAR)
            System.out.println(String.format("%1$08X",reg.getInteger(INSTRUCTION_POINTER)) + " | " + this);
        else
            System.out.print(String.format("%1$08X",reg.getInteger(INSTRUCTION_POINTER)) + " | " + this);

    }

    private void enter(Stack stack, Register reg){
        stack.push(reg.getInteger(STACK_FRAME_POINTER));
        reg.setInteger(STACK_FRAME_POINTER, reg.getInteger(STACK_POINTER));
    }

    private void leave(Stack stack, Register reg){
        reg.setInteger(STACK_POINTER, reg.getInteger(STACK_FRAME_POINTER));
        reg.setInteger(STACK_FRAME_POINTER, stack.pop());
    }

    private boolean ret(Register reg, Stack stack){
        if(stack.empty()) return false;
        else {
            reg.setInteger(INSTRUCTION_POINTER, stack.pop());
            return true;
        }
    }

    private void setInstructionPointer(Register reg){
        int instructionLength = (code & 0x10000000) == 0 ? 2 : 4;
        reg.setInteger(INSTRUCTION_POINTER, reg.getInteger(INSTRUCTION_POINTER) + instructionLength);
    }

    private void jump(Register reg, int ramAddress){
        reg.setInteger(INSTRUCTION_POINTER, ramAddress);
    }

    private void exchangeRegReg(Register reg){
        int temp = reg.getInteger(reg1);
        reg.setInteger(reg1, reg.getInteger(reg2));
        reg.setInteger(reg2, temp);
    }

    private void exchangeRegMem(Register reg, RandomAccessMemory ram, int memAddr){
        int temp = reg.getInteger(reg1);
        reg.setInteger(reg1, ram.getInteger(memAddr));
        ram.setInteger(memAddr, temp);
    }

    private int setFlagsAfterLogicalOp(int result, FlagsRegister flags){
        if((result & 0x80000000) != 0)flags.setSignFlag();
        else flags.resetSignFlag();
        if(result == 0)flags.setZeroFlag();
        else flags.resetZeroFlag();
        if((Integer.bitCount(result)&0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
        flags.resetOverflowFlag();
        flags.resetCarryFlag();
        return result;
    }

    private int negateAndSetFlags(int a, FlagsRegister flags){
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
        return result;
    }

    private int decrementAndSetFlags(int a, FlagsRegister flags){
        int result = a-1;
        if(result == 0) flags.setZeroFlag();
        else flags.resetZeroFlag();
        if(a == Integer.MIN_VALUE) flags.setOverflowFlag();
        else flags.resetOverflowFlag();
        if((Integer.bitCount(result)&0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
        return result;
    }

    private int incrementAndSetFlags(int a, FlagsRegister flags){
        int result = a+1;
        if(result == 0) flags.setZeroFlag();
        else flags.resetZeroFlag();
        if(a == Integer.MAX_VALUE) flags.setOverflowFlag();
        else flags.resetOverflowFlag();
        if((Integer.bitCount(result)&0x1)==0)flags.setParityFlag();
        else flags.resetParityFlag();
        return result;
    }

    private void divideAndSetFlags(int b, Register reg, FlagsRegister flags){
        if(b == 0){
            flags.setOverflowFlag();
            flags.setCarryFlag();
            reg.setInteger(reg1, 0);
            reg.setInteger(REMAINDER, 0);
            return;
        }
        flags.resetOverflowFlag();
        flags.resetCarryFlag();
        int a = reg.getInteger(reg1);
        int result = a / b;
        int remainder = a % b;
        reg.setInteger(reg1, result);
        reg.setInteger(REMAINDER, remainder);
    }

    private void divideUnsignedAndSetFlags(int b, Register reg, FlagsRegister flags){
        if(b == 0){
            flags.setOverflowFlag();
            flags.setCarryFlag();
            reg.setInteger(reg1, 0);
            reg.setInteger(REMAINDER, 0);
            return;
        }
        flags.resetOverflowFlag();
        flags.resetCarryFlag();
        int a = reg.getInteger(reg1);
        int result = Integer.divideUnsigned(a, b);
        int remainder = Integer.remainderUnsigned(a, b);
        reg.setInteger(reg1, result);
        reg.setInteger(REMAINDER, remainder);
    }

    private void multiplyAndSetFlags(int b, Register reg, FlagsRegister flags){
        int a = reg.getInteger(reg1);
        int result = a * b;
        if(result != ((long)a * (long)b)){
            flags.setOverflowFlag();
            flags.setCarryFlag();
        }else {
            flags.resetOverflowFlag();
            flags.resetCarryFlag();
        }
        reg.setInteger(reg1, result);
    }

    private void addAndSetFlags(int b, Register reg, FlagsRegister flags){
        compare(reg.getInteger(reg1), b, flags, Long::sum);
        reg.setInteger(reg1, reg.getInteger(reg1) + b);
    }

    private void subAndSetFlags(int b, Register reg, FlagsRegister flags){
        compare(reg.getInteger(reg1), b, flags, (x, y) -> x - y);
        reg.setInteger(reg1, reg.getInteger(reg1) - b);
    }

    private boolean loop(Register reg){
        reg.setInteger(reg1, reg.getInteger(reg1) - 1);
        return reg.getInteger(reg1)!=0;
    }

    private void output(String s){ System.out.println(s); }

    private void compare(int a, int b, FlagsRegister flags, BiFunction<Long, Long, Long> bi){
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

    private void compareFloat(float a, float b, FlagsRegister flags){
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

    private void examineFloat(float f, FlagsRegister flags){
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

    @Override
    public String toString() {
        String instCode;
        switch(code) {
            case LOAD -> instCode = "LOAD";
            case LOAD_REGISTER -> instCode = "LOAD_REGISTER";
            case STORE_BYTE -> instCode = "STORE_BYTE";
            case LOAD_BYTE_UNSIGNED -> instCode = "LOAD_BYTE_UNSIGNED";
            case LOAD_BYTE -> instCode = "LOAD_BYTE";
            case STORE -> instCode = "STORE";
            case LOAD_FLOAT -> instCode = "LOAD_FLOAT";
            case LOAD_REGISTER_FLOAT -> instCode = "LOAD_REGISTER_FLOAT";
            case STORE_FLOAT -> instCode = "STORE_FLOAT";
            case LOAD_ADDRESS -> instCode = "LOAD_ADDRESS";
            case EXCHANGE -> instCode = "EXCHANGE";
            case EXCHANGE_REGISTER -> instCode = "EXCHANGE_REGISTER";
            case EXCHANGE_FLOAT -> instCode = "EXCHANGE_FLOAT";
            case EXCHANGE_REGISTER_FLOAT -> instCode = "EXCHANGE_REGISTER_FLOAT";
            case LOAD_INTEGER_AS_FLOAT -> instCode = "LOAD_INTEGER_AS_FLOAT";
            case STORE_FLOAT_AS_INTEGER -> instCode = "STORE_FLOAT_AS_INTEGER";
            case RANDOM -> instCode = "RANDOM";
            case RANDOM_REGISTER -> instCode = "RANDOM_REGISTER";
            case ENTER -> instCode = "ENTER";
            case LEAVE -> instCode = "LEAVE";
            case PUSH -> instCode = "PUSH";
            case PUSH_REGISTER -> instCode = "PUSH_REGISTER";
            case PUSH_REGISTER_FLOAT -> instCode = "PUSH_REGISTER_FLOAT";
            case PUSH_FLAGS -> instCode = "PUSH_FLAGS";
            case POP -> instCode = "POP";
            case POP_REGISTER -> instCode = "POP_REGISTER";
            case POP_REGISTER_FLOAT -> instCode = "POP_REGISTER_FLOAT";
            case POP_FLAGS -> instCode = "POP_FLAGS";
            case ADD -> instCode = "ADD";
            case ADD_REGISTER -> instCode = "ADD_REGISTER";
            case SUBTRACT -> instCode = "SUBTRACT";
            case SUBTRACT_REGISTER -> instCode = "SUBTRACT_REGISTER";
            case MULTIPLY -> instCode = "MULTIPLY";
            case MULTIPLY_REGISTER -> instCode = "MULTIPLY_REGISTER";
            case DIVIDE -> instCode = "DIVIDE";
            case DIVIDE_REGISTER -> instCode = "DIVIDE_REGISTER";
            case DIVIDE_SIGNED -> instCode = "DIVIDE_SIGNED";
            case DIVIDE_SIGNED_REGISTER -> instCode = "DIVIDE_SIGNED_REGISTER";
            case COMPARE -> instCode = "COMPARE";
            case COMPARE_REGISTER -> instCode = "COMPARE_REGISTER";
            case COMPARE_FLOAT -> instCode = "COMPARE_FLOAT";
            case COMPARE_REGISTER_FLOAT -> instCode = "COMPARE_REGISTER_FLOAT";
            case TEST -> instCode = "TEST";
            case TEST_REGISTER -> instCode = "TEST_REGISTER";
            case ADD_FLOAT -> instCode = "ADD_FLOAT";
            case ADD_REGISTER_FLOAT -> instCode = "ADD_REGISTER_FLOAT";
            case SUBTRACT_FLOAT -> instCode = "SUBTRACT_FLOAT";
            case SUBTRACT_REGISTER_FLOAT ->  instCode = "SUBTRACT_REGISTER_FLOAT";
            case MULTIPLY_FLOAT -> instCode = "MULTIPLY_FLOAT";
            case MULTIPLY_REGISTER_FLOAT -> instCode = "MULTIPLY_REGISTER_FLOAT";
            case DIVIDE_FLOAT -> instCode = "DIVIDE_FLOAT";
            case DIVIDE_REGISTER_FLOAT -> instCode = "DIVIDE_REGISTER_FLOAT";
            case SQUARE_ROOT_FLOAT -> instCode = "SQUARE_ROOT_FLOAT";
            case ABSOLUTE_FLOAT -> instCode = "ABSOLUTE_FLOAT";
            case SINE_FLOAT -> instCode = "SINE_FLOAT";
            case COSINE_FLOAT -> instCode = "COSINE_FLOAT";
            case TANGENT_FLOAT -> instCode = "TANGENT_FLOAT";
            case NEGATE -> instCode = "NEGATE";
            case NEGATE_REGISTER -> instCode = "NEGATE_REGISTER";
            case INCREMENT -> instCode = "INCREMENT";
            case INCREMENT_REGISTER -> instCode = "INCREMENT_REGISTER";
            case DECREMENT -> instCode = "DECREMENT";
            case DECREMENT_REGISTER -> instCode = "DECREMENT_REGISTER";
            case AND -> instCode = "AND";
            case AND_REGISTER -> instCode = "AND_REGISTER";
            case OR -> instCode = "OR";
            case OR_REGISTER -> instCode = "OR_REGISTER";
            case XOR -> instCode = "XOR";
            case XOR_REGISTER -> instCode = "XOR_REGISTER";
            case RIGHT_SHIFT_LOGICAL -> instCode = "RIGHT_SHIFT_LOGICAL";
            case LEFT_SHIFT_LOGICAL -> instCode = "LEFT_SHIFT_LOGICAL";
            case RIGHT_SHIFT_LOGICAL_REGISTER -> instCode = "RIGHT_SHIFT_LOGICAL_REGISTER";
            case LEFT_SHIFT_LOGICAL_REGISTER -> instCode = "LEFT_SHIFT_LOGICAL_REGISTER";
            case RIGHT_SHIFT_ARITHMETIC -> instCode = "RIGHT_SHIFT_ARITHMETIC";
            case RIGHT_SHIFT_ARITHMETIC_REGISTER -> instCode = "RIGHT_SHIFT_ARITHMETIC_REGISTER";
            case RIGHT_ROTATE -> instCode = "RIGHT_ROTATE";
            case LEFT_ROTATE -> instCode = "LEFT_ROTATE";
            case RIGHT_ROTATE_REGISTER -> instCode = "RIGHT_ROTATE_REGISTER";
            case LEFT_ROTATE_REGISTER -> instCode = "LEFT_ROTATE_REGISTER";
            case NOT -> instCode = "NOT";
            case NOT_REGISTER -> instCode = "NOT_REGISTER";
            case JUMP -> instCode = "JUMP";
            case JUMP_EQUAL -> instCode = "JUMP_EQUAL";
            case JUMP_NOT_EQUAL -> instCode = "JUMP_NOT_EQUAL";
            case JUMP_GREATER -> instCode = "JUMP_GREATER";
            case JUMP_GREATER_OR_EQUAL -> instCode = "JUMP_GREATER_OR_EQUAL";
            case JUMP_LESSER -> instCode = "JUMP_LESSER";
            case JUMP_LESS_OR_EQUAL -> instCode = "JUMP_LESS_OR_EQUAL";
            case JUMP_ABOVE -> instCode = "JUMP_ABOVE";
            case JUMP_ABOVE_OR_EQUAL -> instCode = "JUMP_ABOVE_OR_EQUAL";
            case JUMP_BELOW -> instCode = "JUMP_BELOW";
            case JUMP_BELOW_OR_EQUAL -> instCode = "JUMP_BELOW_OR_EQUAL";
            case JUMP_OVERFLOW -> instCode = "JUMP_OVERFLOW";
            case JUMP_NOT_OVERFLOW -> instCode = "JUMP_NOT_OVERFLOW";
            case JUMP_SIGNED -> instCode = "JUMP_SIGNED";
            case JUMP_NOT_SIGNED -> instCode = "JUMP_NOT_SIGNED";
            case JUMP_PARITY -> instCode = "JUMP_PARITY";
            case JUMP_NOT_PARITY -> instCode = "JUMP_NOT_PARITY";
            case LOOP -> instCode = "LOOP";
            case CALL -> instCode = "CALL";
            case OUTPUT -> instCode = "OUTPUT";
            case OUTPUT_SIGNED -> instCode = "OUTPUT_SIGNED";
            case OUTPUT_FLOAT -> instCode = "OUTPUT_FLOAT";
            case OUTPUT_BYTE -> instCode = "OUTPUT_BYTE";
            case OUTPUT_CHAR -> instCode = "OUTPUT_CHAR";
            case OUTPUT_REGISTER -> instCode = "OUTPUT_REGISTER";
            case OUTPUT_REGISTER_SIGNED -> instCode = "OUTPUT_REGISTER_SIGNED";
            case OUTPUT_REGISTER_FLOAT -> instCode = "OUTPUT_REGISTER_FLOAT";
            case OUTPUT_REGISTER_BYTE -> instCode = "OUTPUT_REGISTER_BYTE";
            case OUTPUT_REGISTER_CHAR -> instCode = "OUTPUT_REGISTER_CHAR";
            case RETURN -> instCode = "RETURN";
            case NO_OPERATION -> instCode = "NO_OPERATION";
            default -> instCode = "UNKNOWN";
        }
        String hexCode = String.format("%1$02X",code);
        String r1Hex = String.format("%1$01X",reg1);
        String r2Hex = String.format("%1$01X",reg2);
        String memHex = String.format("%1$04X",ramOffset);
        instCode = String.format("%-32s", instCode);
        return hexCode + " " + r1Hex + " " + r2Hex + " " + memHex + " | " + instCode;
    }
}
