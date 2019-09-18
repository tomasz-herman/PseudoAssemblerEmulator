package com.hermant.program.instruction;

import com.hermant.machine.*;
import com.hermant.machine.register.InstructionPointer;

import java.io.Serializable;

public abstract class Instruction {

    //instruction codes
    public static final byte EXIT = (byte)0;
    public static final byte RETURN = (byte)1;

    public static final byte LOAD = (byte)-1;
    public static final byte LOAD_REGISTER = (byte)2;
    public static final byte LOAD_BYTE = (byte)-2;
    public static final byte LOAD_BYTE_UNSIGNED = (byte)-3;
    public static final byte LOAD_FLOAT = (byte)-4;
    public static final byte LOAD_REGISTER_FLOAT = (byte)3;
    public static final byte STORE = (byte)-5;
    public static final byte STORE_BYTE = (byte)-6;
    public static final byte STORE_FLOAT = (byte)-7;
    public static final byte LOAD_ADDRESS = (byte)-8;
    public static final byte OUTPUT = (byte)-9;
    public static final byte OUTPUT_REGISTER = (byte)4;
    public static final byte OUTPUT_SIGNED = (byte)-10;
    public static final byte OUTPUT_REGISTER_SIGNED = (byte)5;
    public static final byte OUTPUT_FLOAT = (byte)-11;
    public static final byte OUTPUT_REGISTER_FLOAT = (byte)6;
    public static final byte OUTPUT_CHAR = (byte)-12;
    public static final byte OUTPUT_REGISTER_CHAR = (byte)7;
    public static final byte OUTPUT_BYTE = (byte)-13;
    public static final byte OUTPUT_REGISTER_BYTE = (byte)8;
    public static final byte PUSH = (byte)-14;
    public static final byte PUSH_REGISTER = (byte)9;
    public static final byte PUSH_REGISTER_FLOAT = (byte)10;
    public static final byte PUSH_FLAGS = (byte)11;
    public static final byte PUSH_ALL = (byte)12;
    public static final byte PUSH_ALL_FLOAT = (byte)13;
    public static final byte POP = (byte)-15;
    public static final byte POP_REGISTER = (byte)14;
    public static final byte POP_REGISTER_FLOAT = (byte)15;
    public static final byte POP_FLAGS = (byte)16;
    public static final byte POP_ALL = (byte)17;
    public static final byte POP_ALL_FLOAT = (byte)18;
    public static final byte EXCHANGE = (byte)-16;
    public static final byte EXCHANGE_REGISTER = (byte)19;
    public static final byte EXCHANGE_FLOAT = (byte)-17;
    public static final byte EXCHANGE_REGISTER_FLOAT = (byte)20;
    public static final byte NO_OPERATION = (byte)21;
    public static final byte STORE_FLOAT_AS_INTEGER = (byte)-18;
    public static final byte LOAD_INTEGER_AS_FLOAT = (byte)-19;
    public static final byte RANDOM = (byte)-20;
    public static final byte RANDOM_REGISTER = (byte)22;
    public static final byte ENTER = (byte)23;
    public static final byte LEAVE = (byte)24;
    public static final byte INPUT = (byte)-21;
    public static final byte INPUT_REGISTER = (byte)25;
    public static final byte HALT = (byte)26;
    public static final byte TIME = (byte)-22;
    public static final byte TIME_REGISTER = (byte)27;
    public static final byte SLEEP = (byte)-23;
    public static final byte SLEEP_REGISTER = (byte)28;

    public static final byte ADD = (byte)-24;
    public static final byte ADD_REGISTER = (byte)29;
    public static final byte SUBTRACT = (byte)-25;
    public static final byte SUBTRACT_REGISTER = (byte)30;
    public static final byte MULTIPLY = (byte)-26;
    public static final byte MULTIPLY_REGISTER = (byte)31;
    public static final byte DIVIDE = (byte)-27;
    public static final byte DIVIDE_REGISTER = (byte)32;
    public static final byte DIVIDE_SIGNED = (byte)-28;
    public static final byte DIVIDE_SIGNED_REGISTER = (byte)33;
    public static final byte COMPARE = (byte)-29;
    public static final byte COMPARE_REGISTER = (byte)34;
    public static final byte INCREMENT = (byte)-30;
    public static final byte INCREMENT_REGISTER = (byte)35;
    public static final byte DECREMENT = (byte)-31;
    public static final byte DECREMENT_REGISTER = (byte)36;
    public static final byte NEGATE = (byte)-32;
    public static final byte NEGATE_REGISTER = (byte)37;

    public static final byte ADD_FLOAT = (byte)-33;
    public static final byte ADD_REGISTER_FLOAT = (byte)38;
    public static final byte SUBTRACT_FLOAT = (byte)-34;
    public static final byte SUBTRACT_REGISTER_FLOAT = (byte)39;
    public static final byte MULTIPLY_FLOAT = (byte)-35;
    public static final byte MULTIPLY_REGISTER_FLOAT = (byte)40;
    public static final byte DIVIDE_FLOAT = (byte)-36;
    public static final byte DIVIDE_REGISTER_FLOAT = (byte)41;
    public static final byte COMPARE_FLOAT = (byte)-37;
    public static final byte COMPARE_REGISTER_FLOAT = (byte)42;
    public static final byte SQUARE_ROOT_FLOAT = (byte)43;
    public static final byte ABSOLUTE_FLOAT = (byte)44;
    public static final byte SINE_FLOAT = (byte)45;
    public static final byte COSINE_FLOAT = (byte)46;
    public static final byte TANGENT_FLOAT = (byte)47;
    public static final byte EXAMINE_FLOAT = (byte)-38;
    public static final byte EXAMINE_FLOAT_REGISTER = (byte)48;
    public static final byte TEST_FLOAT = (byte)-39;
    public static final byte TEST_FLOAT_REGISTER = (byte)49;

    public static final byte AND = (byte)-40;
    public static final byte OR = (byte)-41;
    public static final byte XOR = (byte)-42;
    public static final byte TEST = (byte)-43;
    public static final byte NOT = (byte)-44;
    public static final byte RIGHT_SHIFT_LOGICAL = (byte)-45;
    public static final byte LEFT_SHIFT_LOGICAL = (byte)-46;
    public static final byte RIGHT_SHIFT_ARITHMETIC = (byte)-47;
    public static final byte RIGHT_ROTATE = (byte)-49;
    public static final byte LEFT_ROTATE = (byte)-50;
    public static final byte AND_REGISTER = (byte)50;
    public static final byte OR_REGISTER = (byte)51;
    public static final byte XOR_REGISTER = (byte)52;
    public static final byte TEST_REGISTER = (byte)53;
    public static final byte NOT_REGISTER = (byte)54;
    public static final byte RIGHT_SHIFT_LOGICAL_REGISTER = (byte)55;
    public static final byte LEFT_SHIFT_LOGICAL_REGISTER = (byte)56;
    public static final byte RIGHT_SHIFT_ARITHMETIC_REGISTER = (byte)57;
    public static final byte RIGHT_ROTATE_REGISTER = (byte)59;
    public static final byte LEFT_ROTATE_REGISTER = (byte)60;

    public static final byte JUMP = (byte)-51;
    public static final byte JUMP_EQUAL = (byte)-52;
    public static final byte JUMP_NOT_EQUAL = (byte)-53;
    public static final byte JUMP_GREATER = (byte)-54;
    public static final byte JUMP_GREATER_OR_EQUAL = (byte)-55;
    public static final byte JUMP_LESSER = (byte)-56;
    public static final byte JUMP_LESS_OR_EQUAL = (byte)-57;
    public static final byte JUMP_ABOVE = (byte)-58;
    public static final byte JUMP_ABOVE_OR_EQUAL = (byte)-59;
    public static final byte JUMP_BELOW = (byte)-60;
    public static final byte JUMP_BELOW_OR_EQUAL = (byte)-61;
    public static final byte JUMP_OVERFLOW = (byte)-62;
    public static final byte JUMP_NOT_OVERFLOW = (byte)-63;
    public static final byte JUMP_SIGNED = (byte)-64;
    public static final byte JUMP_NOT_SIGNED = (byte)-65;
    public static final byte JUMP_PARITY = (byte)-66;
    public static final byte JUMP_NOT_PARITY = (byte)-67;
    public static final byte LOOP = (byte)-68;
    public static final byte CALL = (byte)-69;

    byte reg1;
    byte reg2;
    short ramOffset;

    Instruction(){}

    public abstract void run(Machine m);

    /**
     * Prints debug information about this {@link Instruction} instance. It includes memory location of instruction,
     * it's code, registers and memory offset. If the instruction doesn't implement {@link OutputOperation} it will
     * yield new line at the end of debug information.
     * @param m machine
     * @see OutputOperation
     */
    public final void debug(Machine m){
        System.out.print(String.format("%1$08X",m.getInstructionPointer().get()) + " | " + this);
        final boolean output = this instanceof OutputOperation;
        if (!(output)) System.out.println();
        run(m);
        if(output) System.out.println();
        if(this instanceof ModifiesFlagsOperation) System.out.println(m.getFlagsRegister());
    }

    final void setInstructionPointer(InstructionPointer instructionPointer){
        instructionPointer.set(instructionPointer.get() + instLength());
    }

    public abstract byte code();

    public abstract int instLength();

    public abstract String instCode();

    @Override
    public String toString() {
        final String instCode = String.format("%-32s", instCode());
        final String hexCode = String.format("%1$02X",code());
        final String r1Hex = String.format("%1$01X",reg1);
        final String r2Hex = String.format("%1$01X",reg2);
        final String memHex = this instanceof MemoryOperation ? String.format("%1$04X", ramOffset) : "----";
        return hexCode + " " + r1Hex + " " + r2Hex + " " + memHex + " | " + instCode;
    }
}
