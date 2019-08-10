package com.hermant.program.instruction;

import com.hermant.machine.*;

import java.io.Serializable;
import java.util.function.BiFunction;

import static com.hermant.machine.Register.*;

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

    private Byte code;
    Byte reg1;
    Byte reg2;
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

    //Factory method
    public static Instruction fetchNextInstruction(RandomAccessMemory ram, Register reg){
        int address = reg.getInteger(INSTRUCTION_POINTER);
        byte code = (byte)ram.getByte(address);
        byte reg1 = (byte)((ram.getByte(address+1)&0xf0)>>4);
        byte reg2 = (byte)(ram.getByte(address+1)&0xf);
        Integer ramOffset = null;
        if ((code & 0x10000000)!=0)
        switch(ram.endianness){
            case LittleEndian, MiddleEndian ->
                    ramOffset = (ram.getByte(address+3)<<8) + ram.getByte(address + 2);
            case BigEndian ->
                    ramOffset = (ram.getByte(address+2)<<8) + ram.getByte(address + 3);
        }
        switch(code) {
            case RETURN -> {return new ReturnInstruction(reg1, reg2, ramOffset);}
            case LOAD -> {return new LoadInstruction(reg1, reg2, ramOffset);}
            case LOAD_REGISTER -> {return new LoadRegisterInstruction(reg1, reg2, ramOffset);}
            case STORE -> {return new StoreInstruction(reg1, reg2, ramOffset);}
            case LOAD_FLOAT -> {return new LoadFloatInstruction(reg1, reg2, ramOffset);}
            case LOAD_REGISTER_FLOAT -> {return new LoadRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case STORE_FLOAT -> {return new StoreFloatInstruction(reg1, reg2, ramOffset);}
            case LOAD_ADDRESS -> {return new LoadAddressInstruction(reg1, reg2, ramOffset);}
            case EXCHANGE -> {return new ExchangeInstruction(reg1, reg2, ramOffset);}
            case EXCHANGE_REGISTER -> {return new ExchangeRegisterInstruction(reg1, reg2, ramOffset);}
            case EXCHANGE_FLOAT -> {return new ExchangeFloatInstruction(reg1, reg2, ramOffset);}
            case EXCHANGE_REGISTER_FLOAT ->{return new ExchangeRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case STORE_BYTE -> {return new StoreByteInstruction(reg1, reg2, ramOffset);}
            case LOAD_BYTE_UNSIGNED -> {return new LoadByteUnsignedInstruction(reg1, reg2, ramOffset);}
            case LOAD_BYTE -> {return new LoadByteInstruction(reg1, reg2, ramOffset);}
            case LOAD_INTEGER_AS_FLOAT -> {return new LoadIntegerAsFloatInstruction(reg1, reg2, ramOffset);}
            case STORE_FLOAT_AS_INTEGER -> {return new StoreFloatAsIntegerInstruction(reg1, reg2, ramOffset);}
            case RANDOM -> {return new RandomInstruction(reg1, reg2, ramOffset);}
            case RANDOM_REGISTER -> {return new RandomRegisterInstruction(reg1, reg2, ramOffset);}
            case ENTER -> {return new EnterInstruction(reg1, reg2, ramOffset);}
            case LEAVE -> {return new LeaveInstruction(reg1, reg2, ramOffset);}
            case OUTPUT -> {return new OutputInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_SIGNED -> {return new OutputSignedInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_FLOAT -> {return new OutputFloatInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_BYTE -> {return new OutputByteInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_CHAR -> {return new OutputCharInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER -> {return new OutputRegisterInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER_SIGNED -> {return new OutputRegisterSignedInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER_FLOAT -> {return new OutputRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER_BYTE -> {return new OutputRegisterByteInstruction(reg1, reg2, ramOffset);}
            case OUTPUT_REGISTER_CHAR -> {return new OutputRegisterCharInstruction(reg1, reg2, ramOffset);}
            case NO_OPERATION -> {return new NoOperationInstruction(reg1, reg2, ramOffset);}

            case PUSH -> {return new PushInstruction(reg1, reg2, ramOffset);}
            case PUSH_REGISTER -> {return new PushRegisterInstruction(reg1, reg2, ramOffset);}
            case PUSH_REGISTER_FLOAT -> {return new PushRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case PUSH_FLAGS -> {return new PushFlagsInstruction(reg1, reg2, ramOffset);}
            case POP -> {return new PopInstruction(reg1, reg2, ramOffset);}
            case POP_REGISTER -> {return new PopRegisterInstruction(reg1, reg2, ramOffset);}
            case POP_REGISTER_FLOAT -> {return new PopRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case POP_FLAGS -> {return new PopFlagsInstruction(reg1, reg2, ramOffset);}

            case ADD -> {return new AddInstruction(reg1, reg2, ramOffset);}
            case ADD_REGISTER -> {return new AddRegisterInstruction(reg1, reg2, ramOffset);}
            case SUBTRACT -> {return new SubtractInstruction(reg1, reg2, ramOffset);}
            case SUBTRACT_REGISTER -> {return new SubtractRegisterInstruction(reg1, reg2, ramOffset);}
            case MULTIPLY -> {return new MultiplyInstruction(reg1, reg2, ramOffset);}
            case MULTIPLY_REGISTER -> {return new MultiplyRegisterInstruction(reg1, reg2, ramOffset);}
            case DIVIDE -> {return new DivideInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_REGISTER -> {return new DivideRegisterInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_SIGNED -> {return new DivideSignedInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_SIGNED_REGISTER -> {return new DivideSignedRegisterInstruction(reg1, reg2, ramOffset);}
            case COMPARE -> {return new CompareInstruction(reg1, reg2, ramOffset);}
            case COMPARE_REGISTER -> {return new CompareRegisterInstruction(reg1, reg2, ramOffset);}

            case COMPARE_FLOAT -> {return new CompareFloatInstruction(reg1, reg2, ramOffset);}
            case COMPARE_REGISTER_FLOAT -> {return new CompareRegisterFloatInstruction(reg1, reg2, ramOffset);}

            case ADD_FLOAT -> {return new AddFloatInstruction(reg1, reg2, ramOffset);}
            case ADD_REGISTER_FLOAT -> {return new AddRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case SUBTRACT_FLOAT -> {return new SubtractFloatInstruction(reg1, reg2, ramOffset);}
            case SUBTRACT_REGISTER_FLOAT -> {return new SubtractRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case MULTIPLY_FLOAT -> {return new MultiplyFloatInstruction(reg1, reg2, ramOffset);}
            case MULTIPLY_REGISTER_FLOAT -> {return new MultiplyRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_FLOAT -> {return new DivideFloatInstruction(reg1, reg2, ramOffset);}
            case DIVIDE_REGISTER_FLOAT -> {return new DivideRegisterFloatInstruction(reg1, reg2, ramOffset);}
            case SQUARE_ROOT_FLOAT -> {return new SquareRootFloatInstruction(reg1, reg2, ramOffset);}
            case ABSOLUTE_FLOAT -> {return new AbsoluteFloatInstruction(reg1, reg2, ramOffset);}
            case SINE_FLOAT -> {return new SineFloatInstruction(reg1, reg2, ramOffset);}
            case COSINE_FLOAT -> {return new CosineFloatInstruction(reg1, reg2, ramOffset);}
            case TANGENT_FLOAT -> {return new TangentFloatInstruction(reg1, reg2, ramOffset);}
            case EXAMINE_FLOAT -> {return new ExamineFloatInstruction(reg1, reg2, ramOffset);}
            case EXAMINE_FLOAT_REGISTER -> {return new ExamineFloatRegisterInstruction(reg1, reg2, ramOffset);}
            case TEST_FLOAT -> {return new TestFloatInstruction(reg1, reg2, ramOffset);}
            case TEST_FLOAT_REGISTER -> {return new TestFloatRegisterInstruction(reg1, reg2, ramOffset);}

            case NEGATE -> {return new NegateInstruction(reg1, reg2, ramOffset);}
            case NEGATE_REGISTER -> {return new NegateRegisterInstruction(reg1, reg2, ramOffset);}
            case INCREMENT -> {return new IncrementInstruction(reg1, reg2, ramOffset);}
            case INCREMENT_REGISTER -> {return new IncrementRegisterInstruction(reg1, reg2, ramOffset);}
            case DECREMENT -> {return new DecrementInstruction(reg1, reg2, ramOffset);}
            case DECREMENT_REGISTER -> {return new DecrementRegisterInstruction(reg1, reg2, ramOffset);}

            case TEST -> {return new TestInstruction(reg1, reg2, ramOffset);}
            case TEST_REGISTER -> {return new TestRegisterInstruction(reg1, reg2, ramOffset);}
            case AND -> {return new AndInstruction(reg1, reg2, ramOffset);}
            case AND_REGISTER -> {return new AndRegisterInstruction(reg1, reg2, ramOffset);}
            case OR -> {return new OrInstruction(reg1, reg2, ramOffset);}
            case OR_REGISTER -> {return new OrRegisterInstruction(reg1, reg2, ramOffset);}
            case XOR -> {return new XorInstruction(reg1, reg2, ramOffset);}
            case XOR_REGISTER -> {return new XorRegisterInstruction(reg1, reg2, ramOffset);}
            case RIGHT_SHIFT_LOGICAL -> {return new RightShiftLogicalInstruction(reg1, reg2, ramOffset);}
            case LEFT_SHIFT_LOGICAL -> {return new LeftShiftLogicalInstruction(reg1, reg2, ramOffset);}
            case RIGHT_SHIFT_LOGICAL_REGISTER -> {return new RightShiftLogicalRegisterInstruction(reg1, reg2, ramOffset);}
            case LEFT_SHIFT_LOGICAL_REGISTER -> {return new LeftShiftLogicalRegisterInstruction(reg1, reg2, ramOffset);}
            case RIGHT_SHIFT_ARITHMETIC -> {return new RightShiftArithmeticInstruction(reg1, reg2, ramOffset);}
            case RIGHT_SHIFT_ARITHMETIC_REGISTER -> {return new RightShiftArithmeticRegisterInstruction(reg1, reg2, ramOffset);}
            case RIGHT_ROTATE -> {return new RightRotateInstruction(reg1, reg2, ramOffset);}
            case LEFT_ROTATE -> {return new LeftRotateInstruction(reg1, reg2, ramOffset);}
            case RIGHT_ROTATE_REGISTER -> {return new RightRotateRegisterInstruction(reg1, reg2, ramOffset);}
            case LEFT_ROTATE_REGISTER -> {return new LeftRotateRegisterInstruction(reg1, reg2, ramOffset);}

            case NOT_REGISTER -> {return new NotRegisterInstruction(reg1, reg2, ramOffset);}
            case NOT -> {return new NotInstruction(reg1, reg2, ramOffset);}

            case JUMP -> {return new JumpInstruction(reg1, reg2, ramOffset);}
            case JUMP_EQUAL -> {return new JumpEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_NOT_EQUAL -> {return new JumpNotEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_GREATER -> {return new JumpGreaterInstruction(reg1, reg2, ramOffset);}
            case JUMP_GREATER_OR_EQUAL -> {return new JumpGreaterOrEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_LESSER -> {return new JumpLesserInstruction(reg1, reg2, ramOffset);}
            case JUMP_LESS_OR_EQUAL -> {return new JumpLessOrEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_ABOVE -> {return new JumpAboveInstruction(reg1, reg2, ramOffset);}
            case JUMP_ABOVE_OR_EQUAL -> {return new JumpAboveOrEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_BELOW -> {return new JumpBelowInstruction(reg1, reg2, ramOffset);}
            case JUMP_BELOW_OR_EQUAL -> {return new JumpBelowOrEqualInstruction(reg1, reg2, ramOffset);}
            case JUMP_OVERFLOW ->{return new JumpOverflowInstruction(reg1, reg2, ramOffset);}
            case JUMP_NOT_OVERFLOW -> {return new JumpNotOverflowInstruction(reg1, reg2, ramOffset);}
            case JUMP_SIGNED -> {return new JumpSignedInstruction(reg1, reg2, ramOffset);}
            case JUMP_NOT_SIGNED -> {return new JumpNotSignedInstruction(reg1, reg2, ramOffset);}
            case JUMP_PARITY -> {return new JumpParityInstruction(reg1, reg2, ramOffset);}
            case JUMP_NOT_PARITY -> {return new JumpNotParityInstruction(reg1, reg2, ramOffset);}
            case LOOP -> {return new LoopInstruction(reg1, reg2, ramOffset);}
            case CALL -> {return new CallInstruction(reg1, reg2, ramOffset);}

            default -> throw new IllegalStateException("Unrecognizable instruction code " + String.format("%1$02X",code));
        }
    }

    int getMemoryAddress(Register reg){ return (code & 0x10000000) != 0 ? reg.getInteger(reg2) + ramOffset : 0; }

    public boolean execute(Machine m, boolean debug){
        if(debug) debug(m.getRegister());
        setInstructionPointer(m.getRegister());
        return true;
    }

    public void debug(Register reg){
        System.out.println(String.format("%1$08X",reg.getInteger(INSTRUCTION_POINTER)) + " | " + this);
    }

    private void setInstructionPointer(Register reg){
        int instructionLength = (code & 0x10000000) == 0 ? 2 : 4;
        reg.setInteger(INSTRUCTION_POINTER, reg.getInteger(INSTRUCTION_POINTER) + instructionLength);
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
