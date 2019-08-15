package com.hermant.parser;

import com.hermant.program.*;
import com.hermant.program.declaration.*;
import com.hermant.program.instruction.LoadableInstruction;
import com.hermant.program.instruction.Instruction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hermant.machine.Machine.SECTION_SIZE;
import static com.hermant.machine.register.Register.DATA_SECTION;
import static com.hermant.machine.register.Register.PROGRAM_SECTION;
import static com.hermant.program.declaration.Declaration.Type.*;

public class Parser {

    //tokens
    private static final String COMMENT = ";";

    //instructions
    private static final String EXIT = "EXIT";
    private static final String RETURN = "RET";
    private static final String ENTER = "ENTER";
    private static final String LEAVE = "LEAVE";
    private static final String LOAD = "LD";
    private static final String LOAD_FLOAT = "FLD";
    private static final String STORE = "ST";
    private static final String STORE_FLOAT = "FST";
    private static final String LOAD_ADDRESS = "LDA";
    private static final String OUTPUT = "OUT";
    private static final String OUTPUT_BYTE = "BOUT";
    private static final String OUTPUT_CHAR = "COUT";
    private static final String OUTPUT_SIGNED = "IOUT";
    private static final String OUTPUT_FLOAT = "FOUT";
    private static final String NO_OPERATION = "NOP";
    private static final String EXCHANGE = "XCHG";
    private static final String EXCHANGE_FLOAT = "FXCH";
    private static final String LOAD_BYTE = "LDB";
    private static final String LOAD_BYTE_UNSIGNED = "LDBU";
    private static final String STORE_BYTE = "STB";
    private static final String LOAD_INTEGER_AS_FLOAT = "FILD";
    private static final String STORE_FLOAT_AS_INTEGER = "FIST";
    private static final String RANDOM = "RND";

    private static final String PUSH = "PUSH";
    private static final String PUSH_FLOAT = "FPUSH";
    private static final String POP = "POP";
    private static final String POP_FLOAT = "FPOP";
    private static final String PUSH_FLAGS = "PUSHF";
    private static final String POP_FLAGS = "POPF";

    private static final String ADD = "ADD";
    private static final String SUBTRACT = "SUB";
    private static final String MULTIPLY = "MUL";
    private static final String DIVIDE = "DIV";
    private static final String DIVIDE_SIGNED = "IDIV";
    private static final String COMPARE = "CMP";
    private static final String ADD_FLOAT = "FADD";
    private static final String SUBTRACT_FLOAT = "FSUB";
    private static final String MULTIPLY_FLOAT = "FMUL";
    private static final String DIVIDE_FLOAT = "FDIV";
    private static final String COMPARE_FLOAT = "FCMP";
    private static final String NEGATE = "NEG";
    private static final String INCREMENT = "INC";
    private static final String DECREMENT = "DEC";
    private static final String SQUARE_ROOT_FLOAT = "FSQRT";
    private static final String ABSOLUTE_FLOAT = "FABS";
    private static final String SINE_FLOAT = "FSIN";
    private static final String COSINE_FLOAT = "FCOS";
    private static final String TANGENT_FLOAT = "FTAN";
    private static final String EXAMINE_FLOAT = "FXAM";
    private static final String TEST_FLOAT = "FTST";

    private static final String AND = "AND";
    private static final String OR = "OR";
    private static final String XOR = "XOR";
    private static final String TEST = "TEST";
    private static final String NOT = "NOT";
    private static final String RIGHT_SHIFT_LOGICAL = "SHR";
    private static final String LEFT_SHIFT_LOGICAL = "SHL";
    private static final String RIGHT_SHIFT_ARITHMETIC = "SAR";
    private static final String LEFT_SHIFT_ARITHMETIC = "SAL";
    private static final String RIGHT_ROTATE = "ROR";
    private static final String LEFT_ROTATE = "ROL";

    private static final String JUMP = "JMP";
    private static final String JUMP_EQUAL = "JE";
    private static final String JUMP_ZERO = "JZ";
    private static final String JUMP_NOT_EQUAL = "JNE";
    private static final String JUMP_NOT_ZERO = "JNZ";
    private static final String JUMP_GREATER = "JG";
    private static final String JUMP_NOT_LESS_OR_EQUAL = "JNLE";
    private static final String JUMP_GREATER_OR_EQUAL = "JGE";
    private static final String JUMP_NOT_LESS = "JNL";
    private static final String JUMP_LESSER = "JL";
    private static final String JUMP_NOT_GREATER_OR_EQUAL = "JNGE";
    private static final String JUMP_LESS_OR_EQUAL = "JLE";
    private static final String JUMP_NOT_GREATER = "JNG";
    private static final String JUMP_ABOVE = "JA";
    private static final String JUMP_NOT_BELOW_OR_EQUAL = "JNBE";
    private static final String JUMP_ABOVE_OR_EQUAL = "JAE";
    private static final String JUMP_NOT_BELOW = "JNB";
    private static final String JUMP_NOT_CARRY = "JNC";
    private static final String JUMP_BELOW = "JB";
    private static final String JUMP_NOT_ABOVE_OR_EQUAL = "JNAE";
    private static final String JUMP_CARRY = "JC";
    private static final String JUMP_BELOW_OR_EQUAL = "JBE";
    private static final String JUMP_NOT_ABOVE = "JNA";
    private static final String JUMP_OVERFLOW = "JO";
    private static final String JUMP_NOT_OVERFLOW = "JNO";
    private static final String JUMP_SIGNED = "JS";
    private static final String JUMP_NOT_SIGNED = "JNS";
    private static final String JUMP_PARITY = "JP";
    private static final String JUMP_NOT_PARITY = "JNP";
    private static final String LOOP = "LOOP";
    private static final String CALL = "CALL";

    //declarations
    private static final String DECLARE_CONSTANT = "DC";
    private static final String DECLARE_SPACE = "DS";
    private static final String INTEGER = "INTEGER";
    private static final String FLOAT = "FLOAT";
    private static final String BYTE = "BYTE";
    private static final String CHAR = "CHAR";

    public interface ParseMethod { void parse(String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException; }

    private static final Map<String, ParseMethod> TOKENS;

    static {
        final Map<String, ParseMethod> tokens = new HashMap<>();
        TOKENS = Collections.unmodifiableMap(tokens);
        tokens.put(EXIT, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.EXIT, words, program, lineNum));
        tokens.put(RETURN, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.RETURN, words, program, lineNum));
        tokens.put(NO_OPERATION, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.NO_OPERATION, words, program, lineNum));
        tokens.put(PUSH_FLAGS, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.PUSH_FLAGS, words, program, lineNum));
        tokens.put(POP_FLAGS, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.POP_FLAGS, words, program, lineNum));
        tokens.put(ENTER, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.ENTER, words, program, lineNum));
        tokens.put(LEAVE, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.LEAVE, words, program, lineNum));
        tokens.put(LOAD, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.LOAD, Instruction.LOAD_REGISTER, words, labels, program, lineNum));
        tokens.put(LOAD_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.LOAD_FLOAT, Instruction.LOAD_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(STORE, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.STORE, words, labels, program, lineNum));
        tokens.put(STORE_FLOAT, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.STORE_FLOAT, words, labels, program, lineNum));
        tokens.put(LOAD_ADDRESS, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.LOAD_ADDRESS, words, labels, program, lineNum));
        tokens.put(EXCHANGE, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.EXCHANGE, Instruction.EXCHANGE_REGISTER, words, labels, program, lineNum));
        tokens.put(EXCHANGE_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.EXCHANGE_FLOAT, Instruction.EXCHANGE_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(PUSH, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.PUSH, Instruction.PUSH_REGISTER, words, labels, program, lineNum));
        tokens.put(PUSH_FLOAT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.PUSH, Instruction.PUSH_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(POP, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.POP, Instruction.POP_REGISTER, words, labels, program, lineNum));
        tokens.put(POP_FLOAT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.POP, Instruction.POP_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(LOAD_BYTE, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.LOAD_BYTE, words, labels, program, lineNum));
        tokens.put(LOAD_BYTE_UNSIGNED, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.LOAD_BYTE_UNSIGNED, words, labels, program, lineNum));
        tokens.put(STORE_BYTE, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.STORE_BYTE, words, labels, program, lineNum));
        tokens.put(LOAD_INTEGER_AS_FLOAT, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.LOAD_INTEGER_AS_FLOAT, words, labels, program, lineNum));
        tokens.put(STORE_FLOAT_AS_INTEGER, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.STORE_FLOAT_AS_INTEGER, words, labels, program, lineNum));
        tokens.put(RANDOM, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.RANDOM, Instruction.RANDOM_REGISTER, words, labels, program, lineNum));
        tokens.put(ADD, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.ADD, Instruction.ADD_REGISTER, words, labels, program, lineNum));
        tokens.put(ADD_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.ADD_FLOAT, Instruction.ADD_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(SUBTRACT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.SUBTRACT, Instruction.SUBTRACT_REGISTER, words, labels, program, lineNum));
        tokens.put(SUBTRACT_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.SUBTRACT_FLOAT, Instruction.SUBTRACT_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(MULTIPLY, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.MULTIPLY, Instruction.MULTIPLY_REGISTER, words, labels, program, lineNum));
        tokens.put(MULTIPLY_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.MULTIPLY_FLOAT, Instruction.MULTIPLY_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(DIVIDE, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.DIVIDE, Instruction.DIVIDE_REGISTER, words, labels, program, lineNum));
        tokens.put(DIVIDE_SIGNED, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.DIVIDE_SIGNED, Instruction.DIVIDE_SIGNED_REGISTER, words, labels, program, lineNum));
        tokens.put(DIVIDE_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.DIVIDE_FLOAT, Instruction.DIVIDE_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(COMPARE, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.COMPARE, Instruction.COMPARE_REGISTER, words, labels, program, lineNum));
        tokens.put(COMPARE_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.COMPARE_FLOAT, Instruction.COMPARE_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(NEGATE, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.NEGATE, Instruction.NEGATE_REGISTER, words, labels, program, lineNum));
        tokens.put(INCREMENT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.INCREMENT, Instruction.INCREMENT_REGISTER, words, labels, program, lineNum));
        tokens.put(DECREMENT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.DECREMENT, Instruction.DECREMENT_REGISTER, words, labels, program, lineNum));
        tokens.put(ABSOLUTE_FLOAT, (words, labels, program, lineNum) -> loadRegInstruction(Instruction.ABSOLUTE_FLOAT, words, program, lineNum));
        tokens.put(SQUARE_ROOT_FLOAT, (words, labels, program, lineNum) -> loadRegInstruction(Instruction.SQUARE_ROOT_FLOAT, words, program, lineNum));
        tokens.put(SINE_FLOAT, (words, labels, program, lineNum) -> loadRegInstruction(Instruction.SINE_FLOAT, words, program, lineNum));
        tokens.put(COSINE_FLOAT, (words, labels, program, lineNum) -> loadRegInstruction(Instruction.COSINE_FLOAT, words, program, lineNum));
        tokens.put(TANGENT_FLOAT, (words, labels, program, lineNum) -> loadRegInstruction(Instruction.TANGENT_FLOAT, words, program, lineNum));
        tokens.put(EXAMINE_FLOAT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.EXAMINE_FLOAT, Instruction.EXAMINE_FLOAT_REGISTER, words, labels, program, lineNum));
        tokens.put(TEST_FLOAT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.TEST_FLOAT, Instruction.TEST_FLOAT_REGISTER, words, labels, program, lineNum));
        tokens.put(AND, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.AND, Instruction.AND_REGISTER, words, labels, program, lineNum));
        tokens.put(OR, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.OR, Instruction.OR_REGISTER, words, labels, program, lineNum));
        tokens.put(XOR, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.XOR, Instruction.XOR_REGISTER, words, labels, program, lineNum));
        tokens.put(TEST, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.TEST, Instruction.TEST_REGISTER, words, labels, program, lineNum));
        tokens.put(NOT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.NOT, Instruction.NOT_REGISTER, words, labels, program, lineNum));
        tokens.put(RIGHT_SHIFT_LOGICAL, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.RIGHT_SHIFT_LOGICAL, Instruction.RIGHT_SHIFT_LOGICAL_REGISTER, words, labels, program, lineNum));
        tokens.put(LEFT_SHIFT_LOGICAL, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.LEFT_SHIFT_LOGICAL, Instruction.LEFT_SHIFT_LOGICAL_REGISTER, words, labels, program, lineNum));
        tokens.put(RIGHT_SHIFT_ARITHMETIC, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.RIGHT_SHIFT_ARITHMETIC, Instruction.RIGHT_SHIFT_ARITHMETIC_REGISTER, words, labels, program, lineNum));
        tokens.put(LEFT_SHIFT_ARITHMETIC, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.LEFT_SHIFT_ARITHMETIC, Instruction.LEFT_SHIFT_ARITHMETIC_REGISTER, words, labels, program, lineNum));
        tokens.put(RIGHT_ROTATE, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.RIGHT_ROTATE, Instruction.RIGHT_ROTATE_REGISTER, words, labels, program, lineNum));
        tokens.put(LEFT_ROTATE, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.LEFT_ROTATE, Instruction.LEFT_ROTATE_REGISTER, words, labels, program, lineNum));
        tokens.put(JUMP, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP, words, labels, program, lineNum));
        tokens.put(JUMP_ZERO, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_ZERO, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_NOT_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_NOT_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_LESS_OR_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_GREATER, words, labels, program, lineNum));
        tokens.put(JUMP_GREATER, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_GREATER, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_LESS, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_GREATER_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_GREATER_OR_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_GREATER_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_GREATER_OR_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_LESSER, words, labels, program, lineNum));
        tokens.put(JUMP_LESSER, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_LESSER, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_GREATER, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_LESS_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_LESS_OR_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_LESS_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_BELOW_OR_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_ABOVE, words, labels, program, lineNum));
        tokens.put(JUMP_ABOVE, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_ABOVE, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_BELOW, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_ABOVE_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_ABOVE_OR_EQUAL,(words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_ABOVE_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_CARRY, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_ABOVE_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_BELOW, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_BELOW, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_ABOVE_OR_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_BELOW, words, labels, program, lineNum));
        tokens.put(JUMP_CARRY, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_BELOW, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_ABOVE, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_BELOW_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_BELOW_OR_EQUAL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_BELOW_OR_EQUAL, words, labels, program, lineNum));
        tokens.put(JUMP_OVERFLOW, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_OVERFLOW, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_OVERFLOW, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_NOT_OVERFLOW, words, labels, program, lineNum));
        tokens.put(JUMP_SIGNED, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_SIGNED, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_SIGNED, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_NOT_SIGNED, words, labels, program, lineNum));
        tokens.put(JUMP_PARITY, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_PARITY, words, labels, program, lineNum));
        tokens.put(JUMP_NOT_PARITY, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.JUMP_NOT_PARITY, words, labels, program, lineNum));
        tokens.put(LOOP, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.LOOP, words, labels, program, lineNum));
        tokens.put(CALL, (words, labels, program, lineNum) -> loadMemInstruction(Instruction.CALL, words, labels, program, lineNum));
        tokens.put(OUTPUT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.OUTPUT, Instruction.OUTPUT_REGISTER, words, labels, program, lineNum));
        tokens.put(OUTPUT_SIGNED, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.OUTPUT_SIGNED, Instruction.OUTPUT_REGISTER_SIGNED, words, labels, program, lineNum));
        tokens.put(OUTPUT_FLOAT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.OUTPUT_FLOAT, Instruction.OUTPUT_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(OUTPUT_BYTE, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.OUTPUT_BYTE, Instruction.OUTPUT_REGISTER_BYTE, words, labels, program, lineNum));
        tokens.put(OUTPUT_CHAR, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.OUTPUT_CHAR, Instruction.OUTPUT_REGISTER_CHAR, words, labels, program, lineNum));
        tokens.put(DECLARE_CONSTANT, (words, labels, program, lineNum) -> declareConstant(words, program, lineNum));
        tokens.put(DECLARE_SPACE, (words, labels, program, lineNum) -> declareSpace(words, program, lineNum));
    }

    private static final Map<String, instructionType> INSTRUCTION_TYPES;

    static {
        final Map<String, instructionType> instruction_types = new HashMap<>();
        INSTRUCTION_TYPES = Collections.unmodifiableMap(instruction_types);
        instruction_types.put(EXIT, instructionType.instruction2Bytes1Word);
        instruction_types.put(RETURN, instructionType.instruction2Bytes1Word);
        instruction_types.put(NO_OPERATION, instructionType.instruction2Bytes1Word);
        instruction_types.put(PUSH_FLAGS, instructionType.instruction2Bytes1Word);
        instruction_types.put(POP_FLAGS, instructionType.instruction2Bytes1Word);
        instruction_types.put(ENTER, instructionType.instruction2Bytes1Word);
        instruction_types.put(LEAVE, instructionType.instruction2Bytes1Word);
        instruction_types.put(LOAD, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(LOAD_FLOAT, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(STORE, instructionType.instruction4Bytes3Words);
        instruction_types.put(STORE_FLOAT, instructionType.instruction4Bytes3Words);
        instruction_types.put(LOAD_ADDRESS, instructionType.instruction4Bytes3Words);
        instruction_types.put(EXCHANGE, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(EXCHANGE_FLOAT, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(PUSH, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(PUSH_FLOAT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(POP, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(POP_FLOAT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(LOAD_BYTE, instructionType.instruction4Bytes3Words);
        instruction_types.put(LOAD_BYTE_UNSIGNED, instructionType.instruction4Bytes3Words);
        instruction_types.put(STORE_BYTE, instructionType.instruction4Bytes3Words);
        instruction_types.put(LOAD_INTEGER_AS_FLOAT, instructionType.instruction4Bytes3Words);
        instruction_types.put(STORE_FLOAT_AS_INTEGER, instructionType.instruction4Bytes3Words);
        instruction_types.put(RANDOM, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(ADD, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(ADD_FLOAT, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(SUBTRACT, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(SUBTRACT_FLOAT, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(MULTIPLY, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(MULTIPLY_FLOAT, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(DIVIDE, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(DIVIDE_SIGNED, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(DIVIDE_FLOAT, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(COMPARE, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(COMPARE_FLOAT, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(NEGATE, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(INCREMENT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(DECREMENT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(ABSOLUTE_FLOAT, instructionType.instruction2Bytes2Words);
        instruction_types.put(SQUARE_ROOT_FLOAT, instructionType.instruction2Bytes2Words);
        instruction_types.put(SINE_FLOAT, instructionType.instruction2Bytes2Words);
        instruction_types.put(COSINE_FLOAT, instructionType.instruction2Bytes2Words);
        instruction_types.put(TANGENT_FLOAT, instructionType.instruction2Bytes2Words);
        instruction_types.put(EXAMINE_FLOAT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(TEST_FLOAT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(AND, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(OR, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(XOR, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(TEST, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(NOT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(RIGHT_SHIFT_LOGICAL, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(LEFT_SHIFT_LOGICAL, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(RIGHT_SHIFT_ARITHMETIC, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(LEFT_SHIFT_ARITHMETIC, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(RIGHT_ROTATE, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(LEFT_ROTATE, instructionType.instruction2or4Bytes3Words);
        instruction_types.put(JUMP, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_ZERO, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_ZERO, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_LESS_OR_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_GREATER, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_LESS, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_GREATER_OR_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_GREATER_OR_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_LESSER, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_GREATER, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_LESS_OR_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_BELOW_OR_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_ABOVE, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_BELOW, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_ABOVE_OR_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_CARRY, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_BELOW, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_ABOVE_OR_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_CARRY, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_ABOVE, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_BELOW_OR_EQUAL, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_OVERFLOW, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_OVERFLOW, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_SIGNED, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_SIGNED, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_PARITY, instructionType.instruction4Bytes2Words);
        instruction_types.put(JUMP_NOT_PARITY, instructionType.instruction4Bytes2Words);
        instruction_types.put(LOOP, instructionType.instruction4Bytes3Words);
        instruction_types.put(CALL, instructionType.instruction4Bytes2Words);
        instruction_types.put(OUTPUT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(OUTPUT_SIGNED, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(OUTPUT_FLOAT, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(OUTPUT_BYTE, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(OUTPUT_CHAR, instructionType.instruction2or4Bytes2Words);
        instruction_types.put(DECLARE_CONSTANT, instructionType.declaration);
        instruction_types.put(DECLARE_SPACE, instructionType.declaration);
    }

    public enum instructionType {
        declaration,
        instruction2or4Bytes2Words, instruction2or4Bytes3Words,
        instruction2Bytes1Word, instruction2Bytes2Words,
        instruction4Bytes2Words, instruction4Bytes3Words
    }

    public static Program parse(String path){
        Program program = new Program();
        try {
            program = parse(path, analyzeLabels(path));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return program;
    }

    private static Program parse(String path, Map<String, String> labelMemoryTranslation) throws ParseException, IOException {
        Program program = new Program();
        int lineNum = 0;
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            lineNum++;
            line = removeLabels(removeComment(line));
            if(line.isBlank())continue;
            var words = extractWords(line, lineNum);
            ParseMethod method = TOKENS.get(words[0]);
            if(method==null) throw new ParseException("unknown token", lineNum);
            method.parse(words, labelMemoryTranslation, program, lineNum);
        }
        reader.close();
        return program;
    }

    private static LinkedHashMap<String, String> analyzeLabels(String path) throws ParseException, IOException{
        //Linked Hash Map to preserve insertion order
        LinkedHashMap<String, String> labelMemoryTranslation = new LinkedHashMap<>();
        Stack<String> labels = new Stack<>();
        int programSection = 0;
        int dataSection = 0;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int lineNum = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            lineNum++;
            line = removeComment(line);
            line = processLabels(line, labels, lineNum);
            if(line.isBlank())continue;
            String[] words = extractWords(line, lineNum);
            switch (INSTRUCTION_TYPES.get(words[0])){
                case declaration -> {
                    if(programSection>0)throw new ParseException("illegal declaration after instructions" ,lineNum);
                    if(words.length != 2)throw new ParseException("illegal declaration parameters", lineNum);
                    storeLabels(labels, labelMemoryTranslation, DATA_SECTION, dataSection, lineNum);
                    dataSection += getDeclarationSize(words[1], lineNum);
                }
                case instruction2Bytes1Word -> {
                    if(words.length != 1)throw new ParseException("illegal instruction parameters", lineNum);
                    storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                    programSection+=2;
                }
                case instruction2Bytes2Words -> {
                    if(words.length != 2)throw new ParseException("illegal instruction parameters", lineNum);
                    storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                    programSection+=2;
                }
                case instruction4Bytes2Words -> {
                    if(words.length != 2)throw new ParseException("illegal instruction parameters", lineNum);
                    storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                    programSection+=4;
                }
                case instruction4Bytes3Words -> {
                    if(words.length != 3)throw new ParseException("illegal instruction parameters", lineNum);
                    storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                    programSection+=4;
                }
                case instruction2or4Bytes2Words -> {
                    if(words.length != 2)throw new ParseException("illegal instruction parameters",lineNum);
                    storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                    if(validateRegister(words[1]))programSection +=2;
                    else programSection+=4;
                }
                case instruction2or4Bytes3Words -> {
                    if(words.length != 3)throw new ParseException("illegal instruction parameters",lineNum);
                    storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                    if(validateRegister(words[2]))programSection+=2;
                    else programSection+=4;
                }
                default -> throw new ParseException("Unrecognizable OP code", lineNum);
            }
            if(dataSection > SECTION_SIZE) throw new IllegalStateException("exceeded maximum data section size");
            if(programSection > SECTION_SIZE) throw new IllegalStateException("exceeded maximum instruction section size");
        }
        reader.close();
        printLabelsInfo(labelMemoryTranslation, programSection, dataSection);
        return labelMemoryTranslation;
    }

    private static void printLabelsInfo(Map<String, String> labelMemoryTranslation, int programSectionSize, int dataSectionSize){
        System.out.println();
        System.out.println("Labels: ");
        for(var entry : labelMemoryTranslation.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println();
        System.out.println("Program section: " + programSectionSize + " bytes");
        System.out.println("Data section: " + dataSectionSize + " bytes");
        System.out.println();
    }

    private static int getDeclarationSize(String declaration, int lineNum) throws ParseException {
        if(declaration.startsWith(INTEGER) || declaration.startsWith(FLOAT))return 4;
        else if(declaration.startsWith(BYTE) || declaration.startsWith(CHAR))return 1;
        else{
            if(!startsWithPositiveShortNumber(declaration))throw new ParseException("Illegal declaration", lineNum);
            String num = declaration.replaceFirst("\\*.*", "");
            if(declaration.contains(INTEGER) || declaration.contains(FLOAT))return Integer.parseInt(num) * 4;
            else if(declaration.contains(BYTE) || declaration.contains(CHAR))return Integer.parseInt(num);
        }
        throw new ParseException("Illegal declaration", lineNum);
    }

    private static void storeLabels(Stack<String> labels, Map<String, String> labelMemoryTranslation, int reg, int address, int lineNum) throws ParseException {
        while(!labels.empty()){
            if(labelMemoryTranslation.containsKey(labels.peek()))throw new ParseException("label " + labels.peek() + " duplicate", lineNum);
            labelMemoryTranslation.put(labels.pop(), reg + "(" + address + ")");
        }
    }

    private static String[] extractWords(String line, int lineNum) throws ParseException {
        String[] words;
        if(line.contains(","))
            if (validateCommandWithComma(line)) {
                line = line.replace(",", " ");
                words = splitByWhiteSpaces(line);
            }
            else throw new ParseException("illegal syntax", lineNum);
        else{
            words = splitByWhiteSpaces(line);
            if(words.length > 2) throw new ParseException("illegal syntax", lineNum);
        }
        words[0] = words[0].toUpperCase(Locale.ROOT);
        return words;
    }

    private static String processLabels(String line, Stack<String> labels, int lineNum) throws ParseException {
        while(hasLabel(line)){
            labels.push(label(line));
            if(!validateLabel(labels.peek()))throw new ParseException("illegal label", lineNum);
            line = removeLabel(line);
        }
        return line;
    }

    private static void declareConstant(String[] words, Program program, int lineNum) throws ParseException {
        if (words.length != 2 || !validateDeclaringConstant(words[1]))
            throw new ParseException("illegal declaration parameters", lineNum);
        String value = words[1].split("([()])")[1];
        Declaration.Type type = words[1].contains(INTEGER) ? anInteger : words[1].contains(FLOAT) ?
                aFloat : words[1].contains(BYTE) ? aByte : words[1].contains(CHAR) ? aChar : aString;
        if(startsWithPositiveShortNumber(words[1])){
            int count = Integer.parseInt(words[1].split("\\*")[0]);
            switch (type){
                case anInteger -> program.addDeclaration(new IntegerDeclaration(count, parseInt(value)));
                case aFloat -> program.addDeclaration(new FloatDeclaration(count, parseFloat(value)));
                case aByte -> program.addDeclaration(new ByteDeclaration(count, (byte)parseInt(value)));
                case aChar -> program.addDeclaration(new CharDeclaration(count, processCharValue(value)));
            }
        } else switch (type) {
            case anInteger -> program.addDeclaration(new IntegerDeclaration(1, parseInt(value)));
            case aFloat -> program.addDeclaration(new FloatDeclaration(1, parseFloat(value)));
            case aByte -> program.addDeclaration(new ByteDeclaration(1, (byte)parseInt(value)));
            case aChar -> program.addDeclaration(new CharDeclaration(1, processCharValue(value)));
        }
    }

    private static void declareSpace(String[] words, Program program, int lineNum) throws ParseException {
        if (words.length != 2 || !validateDeclaringSpace(words[1]))
            throw new ParseException("illegal declaration parameters", lineNum);
        Declaration.Type type = words[1].contains(INTEGER) ? anInteger : words[1].contains(FLOAT) ?
                aFloat : words[1].contains(BYTE) ? aByte : words[1].contains(CHAR) ? aChar : aString;
        if(startsWithPositiveShortNumber(words[1])){
            int count = Integer.parseInt(words[1].split("\\*")[0]);
            switch (type){
                case anInteger-> program.addDeclaration(new IntegerDeclaration(count, null));
                case aFloat -> program.addDeclaration(new FloatDeclaration(count, null));
                case aByte -> program.addDeclaration(new ByteDeclaration(count, null));
                case aChar -> program.addDeclaration(new CharDeclaration(count, null));
            }
        } else switch (type){
            case anInteger -> program.addDeclaration(new IntegerDeclaration(1, null));
            case aFloat -> program.addDeclaration(new FloatDeclaration(1, null));
            case aByte -> program.addDeclaration(new ByteDeclaration(1, null));
            case aChar -> program.addDeclaration(new CharDeclaration(1, null));
        }
    }

    private static void loadNoParametersInstruction(byte code, String[] words, Program program, int lineNum) throws ParseException {
        if(words.length != 1)throw new ParseException("illegal parameters number", lineNum);
        program.addInstruction(new LoadableInstruction(code, (byte)0, (byte)0, null));
    }

    private static void loadRegOrMemInstruction(byte codeMem, byte codeReg, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 2)throw new ParseException("illegal parameters number", lineNum);
        if(validateRegister(words[1])){
            int reg = Integer.parseInt(words[1]);
            program.addInstruction(new LoadableInstruction(codeReg, (byte)reg, (byte)0, null));
        } else loadMemInstruction(codeMem, words, labels, program, lineNum);
    }

    private static void loadRegInstruction(byte code, String[] words, Program program, int lineNum) throws ParseException {
        if(words.length != 2)throw new ParseException("illegal parameters number", lineNum);
        if(validateRegister(words[1])){
            int reg = Integer.parseInt(words[1]);
            program.addInstruction(new LoadableInstruction(code, (byte)reg, (byte)0, null));
            return;
        }
        throw new ParseException("illegal arguments", lineNum);
    }

    private static void loadMemInstruction(byte code, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 2)throw new ParseException("illegal parameters number", lineNum);
        int reg1 = 0;
        int reg2, mem;
        if(validateLabel(words[1])){
            words[1] = labels.get(words[1]);
            if(words[1]==null) throw new ParseException("unknown label", lineNum);
        }
        if(validateMemoryAddress(words[1])) {
            var numbers = words[1].split("([()])");
            reg2 = Integer.parseInt(numbers[0]);
            mem = Integer.parseInt(numbers[1]);
            program.addInstruction(new LoadableInstruction(code, (byte)reg1, (byte)reg2, (short)mem));
            return;
        }
        throw new ParseException("illegal arguments", lineNum);
    }

    private static void loadRegMemInstruction(byte code, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 3)throw new ParseException("illegal parameters number",lineNum);
        if(!validateRegister(words[1])) throw new ParseException("illegal arguments", lineNum);
        int reg1 = Integer.parseInt(words[1]);
        int reg2, mem;
        if(validateLabel(words[2])){
            words[2] = labels.get(words[2]);
            if(words[2]==null) throw new ParseException("unknown label", lineNum);
        }
        if(validateMemoryAddress(words[2])) {
            var numbers = words[2].split("([()])");
            reg2 = Integer.parseInt(numbers[0]);
            mem = Integer.parseInt(numbers[1]);
            program.addInstruction(new LoadableInstruction(code, (byte)reg1, (byte)reg2, (short)mem));
            return;
        }
        throw new ParseException("illegal arguments", lineNum);
    }

    private static void loadRegMemOrRegRegInstruction(byte codeRegMem, byte codeRegReg, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 3)throw new ParseException("illegal parameters number", lineNum);
        if(!validateRegister(words[1])) throw new ParseException("illegal arguments", lineNum);
        int reg1 = Integer.parseInt(words[1]);
        int reg2, mem;
        if(validateLabel(words[2])){
            words[2] = labels.get(words[2]);
            if(words[2]==null) throw new ParseException("unknown label", lineNum);
        }
        if(validateMemoryAddress(words[2])) {
            var numbers = words[2].split("([()])");
            reg2 = Integer.parseInt(numbers[0]);
            mem = Integer.parseInt(numbers[1]);
            program.addInstruction(new LoadableInstruction(codeRegMem, (byte)reg1, (byte)reg2, (short)mem));
            return;
        }
        if(validateRegister(words[2])){
            reg2 = Integer.parseInt(words[2]);
            program.addInstruction(new LoadableInstruction(codeRegReg, (byte)reg1, (byte)reg2, null));
            return;
        }
        throw new ParseException("illegal arguments", lineNum);
    }

    //some regex and string processing:
    private static String[] splitByWhiteSpaces(String s){
        Pattern pat = Pattern.compile("(\"[^\"]*\"|'[^']*'|[\\S])+");
        Matcher mat = pat.matcher(s.trim());
        ArrayList<String> list = new ArrayList<>();
        while (mat.find()) list.add(mat.group());
        return list.toArray(new String[0]);
    }
    private static String removeComment(String s){return s.replaceFirst(COMMENT + ".*", "").trim();}
    private static boolean hasLabel(String s){ return s.contains(":"); }
    private static String label(String s){ if(s.contains(":")) return s.trim().replaceFirst(":.*", "").trim(); else return "";}
    private static String removeLabel(String s){return s.trim().replaceFirst("^[a-zA-Z0-9_]*:", "").trim();}
    private static String removeLabels(String s){return s.trim().replaceFirst(".*:", "").trim();}
    private static boolean validateRegister(String s){ return s.matches("^(1[0-5]|[0-9])$"); }
    private static boolean validateLabel(String s){ return s.matches("^[a-zA-Z_][a-zA-Z0-9_]*$"); }
    private static boolean validateMemoryAddress(String s) { return s.matches("^(1[0-5]|[0-9])\\(([+-]?[1-9]\\d{0,5}|[+-]?0)\\)$"); }
    private static boolean validateDeclaringSpace(String s) { return s.matches("^([1-9]\\d{0,5}\\*)?(INTEGER|FLOAT|BYTE|CHAR)$"); }
    private static boolean validateDeclaringConstant(String s) { return s.matches("^(([1-9]\\d{0,5}\\*)?(INTEGER\\((([+-]?0|[1-9][0-9]{0,8}|1[0-9]{9}|20[0-9]{8}|21[0-3][0-9]{7}|214[0-6][0-9]{6}|2147[0-3][0-9]{5}|21474[0-7][0-9]{4}|214748[0-2][0-9]{3}|2147483[0-5][0-9]{2}|21474836[0-3][0-9]|214748364[0-7])|-2147483648|0x[0-9a-fA-F]{1,8}|0b[0-1]{1,32})\\)|FLOAT\\(([+-]?(([1-9]\\d*|0)(\\.\\d*)?|\\.\\d+)|0x[0-9a-fA-F]{1,8}|0b[0-1]{1,32})\\)|BYTE\\(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]|0x[0-9a-fA-F]{1,2}|0b[0-1]{1,8})\\)|CHAR\\(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]|0x[0-9a-fA-F]{1,2}|0b[0-1]{1,8}|'([\\x00-\\x7F]|\\\\n|\\\\t|\\\\'|\\\\\"|\\\\([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))')\\))|STRING\\(\"[\\x00-\\x7F]*\"\\))"); }
    private static boolean startsWithPositiveShortNumber(String s){ return s.matches("^[1-9]\\d{0,5}.*");}
    private static int parseInt(String s){return s.startsWith("0x") ? (Integer.parseUnsignedInt(s.substring(2), 16)) : s.startsWith("0b") ? (Integer.parseUnsignedInt(s.substring(2), 2)) : Integer.parseInt(s);}
    private static float parseFloat(String s){return s.startsWith("0x") ? (Float.intBitsToFloat(Integer.parseUnsignedInt(s.substring(2), 16))) : s.startsWith("0b") ? Float.intBitsToFloat((Integer.parseUnsignedInt(s.substring(2), 2))) : Float.parseFloat(s);}
    private static boolean validateCommandWithComma(String s){return s.matches("^\\s*[A-Z]+\\s+[0-9]+\\s*,\\s*[-A-Za-z0-9()_]+\\s*$");}
    private static char processCharValue(String s){if(s.startsWith("\'"))return processStringValue(s).charAt(1); else return (char)parseInt(s);}
    private static String processStringValue(String s){return s.replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t").replaceAll("\\\\'", "'");}
}