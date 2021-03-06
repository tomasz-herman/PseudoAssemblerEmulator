package com.hermant.parser;

import com.hermant.program.*;
import com.hermant.program.declaration.*;
import com.hermant.program.instruction.LoadableInstruction;
import com.hermant.program.instruction.Instruction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.hermant.machine.Machine.SECTION_SIZE;
import static com.hermant.machine.register.GeneralPurposeRegister.DATA_SECTION;
import static com.hermant.machine.register.GeneralPurposeRegister.PROGRAM_SECTION;

public class Parser {

    //tokens

    //instructions
    private static final String EXIT = "EXIT";
    private static final String RETURN = "RET";
    private static final String PUSH_ALL = "PUSHA";
    private static final String PUSH_ALL_FLOAT = "FPUSHA";
    private static final String POP_ALL = "POPA";
    private static final String POP_ALL_FLOAT = "FPOPA";
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
    private static final String RANDOM = "RAND";
    private static final String HALT = "HALT";
    private static final String TIME = "TIME";
    private static final String INPUT = "IN";
    private static final String SLEEP = "SLEEP";

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
    private static final String STRING = "STRING";

    //regular expressions
    private static final String DECIMAL_INTEGER = "([+-]?(0|[1-9][0-9]{0,8}|1[0-9]{9}|20[0-9]{8}|21[0-3][0-9]{7}|" +
            "214[0-6][0-9]{6}|2147[0-3][0-9]{5}|21474[0-7][0-9]{4}|214748[0-2][0-9]{3}|2147483[0-5][0-9]{2}|" +
            "21474836[0-3][0-9]|214748364[0-7]))|-2147483648";
    private static final String HEXADECIMAL_INTEGER = "0x[0-9a-fA-F]{1,8}";
    private static final String BINARY_INTEGER = "0b[0-1]{1,32}";
    private static final String FLOATING_POINT_NUMBER = "([+-]?(([1-9]\\d*|0)(\\.\\d*)?|\\.\\d+)";
    private static final String DECIMAL_UNSIGNED_BYTE = "[0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]";
    private static final String HEXADECIMAL_BYTE = "0x[0-9a-fA-F]{1,2}";
    private static final String BINARY_BYTE = "0b[0-1]{1,8}";
    private static final String ASCII_CHAR = "[\\x00-\\x7F]";
    private static final String ESCAPED_CHAR = "\\\\n|\\\\t|\\\\'|\\\\\"|\\\\(" + DECIMAL_UNSIGNED_BYTE + ")";
    private static final String DECIMAL_UNSIGNED_SHORT = "([0-9]|[1-9][0-9]|[1-9][0-9]{2}|[1-9][0-9]{3}|[1-5][0-9]{4}|" +
            "6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])";

    @FunctionalInterface
    public interface ParseMethod {
        void parse(String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException;
    }

    private static final Map<String, ParseMethod> TOKENS;

    static {
        final Trie<ParseMethod> tokens = new Trie<>();
        TOKENS = Collections.unmodifiableMap(tokens);
        tokens.put(EXIT, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.EXIT, words, program, lineNum));
        tokens.put(RETURN, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.RETURN, words, program, lineNum));
        tokens.put(HALT, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.HALT, words, program, lineNum));
        tokens.put(PUSH_ALL, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.PUSH_ALL, words, program, lineNum));
        tokens.put(PUSH_ALL_FLOAT, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.PUSH_ALL_FLOAT, words, program, lineNum));
        tokens.put(POP_ALL, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.POP_ALL, words, program, lineNum));
        tokens.put(POP_ALL_FLOAT, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.POP_ALL_FLOAT, words, program, lineNum));
        tokens.put(PUSH_FLAGS, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.PUSH_FLAGS, words, program, lineNum));
        tokens.put(POP_FLAGS, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.POP_FLAGS, words, program, lineNum));
        tokens.put(ENTER, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.ENTER, words, program, lineNum));
        tokens.put(LEAVE, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.LEAVE, words, program, lineNum));
        tokens.put(NO_OPERATION, (words, labels, program, lineNum) -> loadNoParametersInstruction(Instruction.NO_OPERATION, words, program, lineNum));
        tokens.put(LOAD, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.LOAD, Instruction.LOAD_REGISTER, words, labels, program, lineNum));
        tokens.put(LOAD_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.LOAD_FLOAT, Instruction.LOAD_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(STORE, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.STORE, words, labels, program, lineNum));
        tokens.put(STORE_FLOAT, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.STORE_FLOAT, words, labels, program, lineNum));
        tokens.put(LOAD_ADDRESS, (words, labels, program, lineNum) -> loadRegMemInstruction(Instruction.LOAD_ADDRESS, words, labels, program, lineNum));
        tokens.put(EXCHANGE, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.EXCHANGE, Instruction.EXCHANGE_REGISTER, words, labels, program, lineNum));
        tokens.put(EXCHANGE_FLOAT, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.EXCHANGE_FLOAT, Instruction.EXCHANGE_REGISTER_FLOAT, words, labels, program, lineNum));
        tokens.put(INPUT, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.INPUT, Instruction.INPUT_REGISTER, words, labels, program, lineNum));
        tokens.put(TIME, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.TIME, Instruction.TIME_REGISTER, words, labels, program, lineNum));
        tokens.put(SLEEP, (words, labels, program, lineNum) -> loadRegOrMemInstruction(Instruction.SLEEP, Instruction.SLEEP_REGISTER, words, labels, program, lineNum));
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
        tokens.put(LEFT_SHIFT_ARITHMETIC, (words, labels, program, lineNum) -> loadRegMemOrRegRegInstruction(Instruction.LEFT_SHIFT_LOGICAL, Instruction.LEFT_SHIFT_LOGICAL_REGISTER, words, labels, program, lineNum));
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

    private static final Map<String, InstructionType> INSTRUCTION_TYPES;

    static { 
        final Trie<InstructionType> instructionTypes = new Trie<>();
        INSTRUCTION_TYPES = Collections.unmodifiableMap(instructionTypes);
        instructionTypes.put(EXIT, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(RETURN, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(HALT, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(PUSH_ALL, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(PUSH_ALL_FLOAT, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(POP_ALL, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(POP_ALL_FLOAT, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(PUSH_FLAGS, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(POP_FLAGS, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(ENTER, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(LEAVE, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(NO_OPERATION, InstructionType.instruction2Bytes1Word);
        instructionTypes.put(LOAD, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(LOAD_FLOAT, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(STORE, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(STORE_FLOAT, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(LOAD_ADDRESS, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(EXCHANGE, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(EXCHANGE_FLOAT, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(INPUT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(TIME, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(SLEEP, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(PUSH, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(PUSH_FLOAT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(POP, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(POP_FLOAT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(LOAD_BYTE, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(LOAD_BYTE_UNSIGNED, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(STORE_BYTE, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(LOAD_INTEGER_AS_FLOAT, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(STORE_FLOAT_AS_INTEGER, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(RANDOM, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(ADD, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(ADD_FLOAT, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(SUBTRACT, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(SUBTRACT_FLOAT, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(MULTIPLY, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(MULTIPLY_FLOAT, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(DIVIDE, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(DIVIDE_SIGNED, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(DIVIDE_FLOAT, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(COMPARE, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(COMPARE_FLOAT, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(NEGATE, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(INCREMENT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(DECREMENT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(ABSOLUTE_FLOAT, InstructionType.instruction2Bytes2Words);
        instructionTypes.put(SQUARE_ROOT_FLOAT, InstructionType.instruction2Bytes2Words);
        instructionTypes.put(SINE_FLOAT, InstructionType.instruction2Bytes2Words);
        instructionTypes.put(COSINE_FLOAT, InstructionType.instruction2Bytes2Words);
        instructionTypes.put(TANGENT_FLOAT, InstructionType.instruction2Bytes2Words);
        instructionTypes.put(EXAMINE_FLOAT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(TEST_FLOAT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(AND, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(OR, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(XOR, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(TEST, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(NOT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(RIGHT_SHIFT_LOGICAL, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(LEFT_SHIFT_LOGICAL, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(RIGHT_SHIFT_ARITHMETIC, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(LEFT_SHIFT_ARITHMETIC, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(RIGHT_ROTATE, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(LEFT_ROTATE, InstructionType.instruction2or4Bytes3Words);
        instructionTypes.put(JUMP, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_ZERO, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_ZERO, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_LESS_OR_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_GREATER, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_LESS, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_GREATER_OR_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_GREATER_OR_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_LESSER, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_GREATER, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_LESS_OR_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_BELOW_OR_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_ABOVE, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_BELOW, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_ABOVE_OR_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_CARRY, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_BELOW, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_ABOVE_OR_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_CARRY, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_ABOVE, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_BELOW_OR_EQUAL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_OVERFLOW, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_OVERFLOW, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_SIGNED, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_SIGNED, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_PARITY, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(JUMP_NOT_PARITY, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(LOOP, InstructionType.instruction4Bytes3Words);
        instructionTypes.put(CALL, InstructionType.instruction4Bytes2Words);
        instructionTypes.put(OUTPUT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(OUTPUT_SIGNED, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(OUTPUT_FLOAT, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(OUTPUT_BYTE, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(OUTPUT_CHAR, InstructionType.instruction2or4Bytes2Words);
        instructionTypes.put(DECLARE_CONSTANT, InstructionType.declaration);
        instructionTypes.put(DECLARE_SPACE, InstructionType.declaration);
    }

    enum InstructionType {
        declaration{
            @Override
            int analyze(String[] words, Stack<String> labels, Map<String, String> labelMemoryTranslation, int programSection, int dataSection, int lineNum) throws ParseException {
                if(programSection>0)throw new ParseException("illegal declaration after instructions" ,lineNum);
                if(words.length != 2)throw new ParseException("illegal words number(expected 3, got " + words.length +")", lineNum);
                storeLabels(labels, labelMemoryTranslation, DATA_SECTION, dataSection, lineNum);
                return getDeclarationSize(words[1], lineNum);
            }
        },
        instruction2or4Bytes2Words {
            @Override
            int analyze(String[] words, Stack<String> labels, Map<String, String> labelMemoryTranslation, int programSection, int dataSectionint, int lineNum) throws ParseException {
                if(words.length != 2)throw new ParseException("illegal words number(expected 2, got "+ words.length +")",lineNum);
                storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                return validateRegister(words[1]) ? 2 : 4;
            }
        },
        instruction2or4Bytes3Words {
            @Override
            int analyze(String[] words, Stack<String> labels, Map<String, String> labelMemoryTranslation, int programSection, int dataSection, int lineNum) throws ParseException {
                if(words.length != 3)throw new ParseException("illegal words number(expected 3, got "+ words.length +")",lineNum);
                storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                return validateRegister(words[2]) ? 2 : 4;
            }
        },
        instruction2Bytes1Word {
            @Override
            int analyze(String[] words, Stack<String> labels, Map<String, String> labelMemoryTranslation, int programSection, int dataSection, int lineNum) throws ParseException {
                if(words.length != 1)throw new ParseException("illegal words number(expected 1, got "+ words.length +")", lineNum);
                storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                return 2;
            }
        },
        instruction2Bytes2Words {
            @Override
            int analyze(String[] words, Stack<String> labels, Map<String, String> labelMemoryTranslation, int programSection, int dataSection, int lineNum) throws ParseException {
                if(words.length != 2)throw new ParseException("illegal words number(expected 2, got "+ words.length +")", lineNum);
                storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                return 2;
            }
        },
        instruction4Bytes2Words {
            @Override
            int analyze(String[] words, Stack<String> labels, Map<String, String> labelMemoryTranslation, int programSection, int dataSection, int lineNum) throws ParseException {
                if(words.length != 2)throw new ParseException("illegal words number(expected 2, got "+ words.length +")", lineNum);
                storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                return 4;
            }
        },
        instruction4Bytes3Words {
            @Override
            int analyze(String[] words, Stack<String> labels, Map<String, String> labelMemoryTranslation, int programSection, int dataSection, int lineNum) throws ParseException {
                if(words.length != 3)throw new ParseException("illegal words number(expected 3, got "+ words.length +")", lineNum);
                storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
                return 4;
            }
        };

        abstract int analyze(String[] words,
                             Stack<String> labels,
                             Map<String, String> labelMemoryTranslation,
                             int programSection,
                             int dataSection,
                             int lineNum) throws ParseException;
    }

    enum DeclarationType {
        anInteger{
            @Override
            public Declaration<Integer> create(int count, String value) {
                return (value == null) ?
                        new IntegerDeclaration(count, null) :
                        new IntegerDeclaration(count, parseInt(value));
            }
        },
        aFloat {
            @Override
            public Declaration<Float> create(int count, String value) {
                return (value == null) ?
                        new FloatDeclaration(count, null) :
                        new FloatDeclaration(count, parseFloat(value));
            }
        },
        aByte {
            @Override
            public Declaration<Byte> create(int count, String value) {
                return (value == null) ?
                        new ByteDeclaration(count, null) :
                        new ByteDeclaration(count, (byte)parseInt(value));
            }
        },
        aChar {
            @Override
            public Declaration<Character> create(int count, String value) {
                return (value == null) ?
                        new CharDeclaration(count, null) :
                        new CharDeclaration(count, processCharValue(value));
            }
        },
        aString {
            @Override
            public Declaration<String> create(int count, String value) {
                return new StringDeclaration(value);
            }
        };
        public abstract Declaration<?> create(int count, String value);
    }

    public static Program parse(String path, boolean verbose) throws IOException, ParseException {
        return parse(Files.lines(Paths.get(path)), analyzeLabels(Files.lines(Paths.get(path)), verbose));
    }

    public static Program parse(Supplier<Stream<String>> supplier, boolean verbose) throws ParseException {
        return parse(supplier.get(), analyzeLabels(supplier.get(), verbose));
    }

    private static Program parse(Stream<String> lines, Map<String, String> labelMemoryTranslation) throws ParseException {
        Program program = new Program();
        int lineNum = 0;
        var iterator = lines.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            lineNum++;
            line = removeLabels(removeComment(line));
            if(line.isBlank())continue;
            var words = extractWords(line, lineNum);
            ParseMethod method = TOKENS.get(words[0]);
            if(method==null) throw new ParseException("unknown token: " + words[0], lineNum);
            method.parse(words, labelMemoryTranslation, program, lineNum);
        }
        return program;
    }

    private static LinkedHashMap<String, String> analyzeLabels(Stream<String> lines, boolean verbose) throws ParseException {
        //Linked Hash Map to preserve insertion order
        LinkedHashMap<String, String> labelMemoryTranslation = new LinkedHashMap<>();
        Stack<String> labels = new Stack<>();
        int programSection = 0;
        int dataSection = 0;
        int lineNum = 0;
        var iterator = lines.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            lineNum++;
            line = removeComment(line);
            line = processLabels(line, labels, lineNum);
            if(line.isBlank())continue;
            String[] words = extractWords(line, lineNum);
            InstructionType type = INSTRUCTION_TYPES.get(words[0]);
            if(type == null) throw new ParseException("unknown token: " + words[0], lineNum);
            if(type == InstructionType.declaration)
                dataSection += type.analyze(words, labels, labelMemoryTranslation, programSection, dataSection, lineNum);
            else
                programSection += type.analyze(words, labels, labelMemoryTranslation, programSection, dataSection, lineNum);
            if(dataSection > SECTION_SIZE) throw new ParseException("exceeded maximum data section size", lineNum);
            if(programSection > SECTION_SIZE) throw new ParseException("exceeded maximum instruction section size", lineNum);
        }
        storeLabels(labels, labelMemoryTranslation, PROGRAM_SECTION, programSection, lineNum);
        if(verbose) printLabelsInfo(labelMemoryTranslation, programSection, dataSection);
        return labelMemoryTranslation;
    }

    private static void printLabelsInfo(Map<String, String> labelMemoryTranslation, int programSectionSize, int dataSectionSize){
        System.out.println("Labels: ");
        for(var entry : labelMemoryTranslation.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println();
        System.out.println("Program section: " + programSectionSize + " bytes");
        System.out.println("Data section: " + dataSectionSize + " bytes");
    }

    private static int getDeclarationSize(String declaration, int lineNum) throws ParseException {
        if(!(validateDeclaringSpace(declaration) || validateDeclaringConstant(declaration)))
            throw new ParseException("Illegal declaration: " + declaration, lineNum);
        if(declaration.startsWith(INTEGER) || declaration.startsWith(FLOAT))return 4;
        else if(declaration.startsWith(BYTE) || declaration.startsWith(CHAR))return 1;
        else if(declaration.startsWith(STRING)){
            String value = retrieveDeclaredValue(declaration);
            value = removeFirstAndLastChar(value);
            if(validateStringValue(value)){
                value = processEscapedCharacters(value, '"');
                return value.length() + 1;
            }
            throw new ParseException("Illegal string value " + value, lineNum);
        } else {
            if(!startsWithPositiveShortNumber(declaration))throw new ParseException("Illegal declaration: " + declaration, lineNum);
            String num = declaration.replaceFirst("\\*.*", "");
            if(declaration.contains(INTEGER) || declaration.contains(FLOAT))return Integer.parseInt(num) * 4;
            else if(declaration.contains(BYTE) || declaration.contains(CHAR))return Integer.parseInt(num);
        }
        throw new ParseException("Illegal declaration: " + declaration, lineNum);
    }

    private static void storeLabels(Stack<String> labels, Map<String, String> labelMemoryTranslation, int reg, int address, int lineNum) throws ParseException {
        while(!labels.empty()){
            if(labelMemoryTranslation.containsKey(labels.peek()))throw new ParseException("label " + labels.peek() + " duplicate", lineNum);
            labelMemoryTranslation.put(labels.pop(), reg + "(" + address + ")");
        }
    }

    private static String[] extractWords(String line, int lineNum) throws ParseException {
        String[] words;
        if(line.contains(",") && !line.matches(".*'.*,.*'.*|.*\".*,.*\".*"))
            if (validateCommandWithComma(line)) {
                line = line.replace(",", " ");
                words = splitByWhiteSpaces(line);
            }
            else throw new ParseException("illegal syntax: " + line, lineNum);
        else{
            words = splitByWhiteSpaces(line);
            if(words.length > 2 && TOKENS.get(words[0]) == null) throw new ParseException("unknown token: " + words[0], lineNum);
            if(words.length > 2) throw new ParseException("illegal syntax: " + line + "\n(missing comma?)", lineNum);
        }
        words[0] = words[0].toUpperCase(Locale.ROOT);
        return words;
    }

    private static String processLabels(String line, Stack<String> labels, int lineNum) throws ParseException {
        while(hasLabel(line)){
            labels.push(label(line));
            if(!validateLabel(labels.peek()))throw new ParseException("illegal label " + labels.peek(), lineNum);
            line = removeLabel(line);
        }
        return line;
    }

    private static void declareConstant(String[] words, Program program, int lineNum) throws ParseException {
        if (words.length != 2) {
            throw new ParseException("illegal words number(expected 2, got "+ words.length +")", lineNum);
        } else if (!validateDeclaringConstant(words[1])) {
            throw new ParseException("illegal declaration " + words[1], lineNum);
        }
        String value = retrieveDeclaredValue(words[1]);
        DeclarationType type = words[1].contains(INTEGER) ?
                DeclarationType.anInteger : words[1].contains(FLOAT) ?
                DeclarationType.aFloat : words[1].contains(BYTE) ?
                DeclarationType.aByte : words[1].contains(CHAR) ?
                DeclarationType.aChar : DeclarationType.aString;
        if(type== DeclarationType.aString){
            value = removeFirstAndLastChar(value);
            if(!validateStringValue(value)) throw new ParseException("illegal string value: " + value, lineNum);
            value = processEscapedCharacters(value, '"');
        }
        if(startsWithPositiveShortNumber(words[1])){
            int count = Integer.parseInt(words[1].split("\\*")[0]);
            program.addDeclaration(type.create(count, value));
        } else
            program.addDeclaration(type.create(1, value));
    }

    private static void declareSpace(String[] words, Program program, int lineNum) throws ParseException {
        if (words.length != 2) {
            throw new ParseException("illegal words number(expected 2, got "+ words.length +")", lineNum);
        } else if (!validateDeclaringSpace(words[1])) {
            throw new ParseException("illegal declaration " + words[1], lineNum);
        }
        DeclarationType type = words[1].contains(INTEGER) ?
                DeclarationType.anInteger : words[1].contains(FLOAT) ?
                DeclarationType.aFloat : words[1].contains(BYTE) ?
                DeclarationType.aByte : DeclarationType.aChar;
        if(startsWithPositiveShortNumber(words[1])){
            int count = Integer.parseInt(words[1].split("\\*")[0]);
            program.addDeclaration(type.create(count, null));
        } else
            program.addDeclaration(type.create(1, null));
    }

    private static void loadNoParametersInstruction(byte code, String[] words, Program program, int lineNum) throws ParseException {
        if(words.length != 1)throw new ParseException("illegal words number(expected 1, got "+ words.length +")", lineNum);
        program.addInstruction(new LoadableInstruction(code, (byte)0, (byte)0, (short)0));
    }

    private static void loadRegOrMemInstruction(byte codeMem, byte codeReg, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 2)throw new ParseException("illegal words number(expected 2, got "+ words.length +")", lineNum);
        if(validateRegister(words[1])){
            int reg = Integer.parseInt(words[1]);
            program.addInstruction(new LoadableInstruction(codeReg, (byte)reg, (byte)0, (short)0));
        } else loadMemInstruction(codeMem, words, labels, program, lineNum);
    }

    private static void loadRegInstruction(byte code, String[] words, Program program, int lineNum) throws ParseException {
        if(words.length != 2)throw new ParseException("illegal words number(expected 2, got "+ words.length +")", lineNum);
        if(validateRegister(words[1])){
            int reg = Integer.parseInt(words[1]);
            program.addInstruction(new LoadableInstruction(code, (byte)reg, (byte)0, (short)0));
            return;
        }
        throw new ParseException("illegal argument: " + words[1], lineNum);
    }

    private static void loadMemInstruction(byte code, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 2)throw new ParseException("illegal words number(expected 2, got "+ words.length +")", lineNum);
        int reg1 = 0;
        int reg2, mem;
        if(validateLabel(words[1])){
            String labelTranslation = labels.get(words[1]);
            if(labelTranslation==null) throw new ParseException("unknown label: " + words[1], lineNum);
            words[1] = labelTranslation;
        }
        if(validateMemoryAddress(words[1])) {
            var numbers = words[1].split("([()])");
            reg2 = Integer.parseInt(numbers[0]);
            mem = Integer.parseInt(numbers[1]);
            program.addInstruction(new LoadableInstruction(code, (byte)reg1, (byte)reg2, (short)mem));
            return;
        }
        throw new ParseException("illegal arguments:" + words[1], lineNum);
    }

    private static void loadRegMemInstruction(byte code, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 3)throw new ParseException("illegal words number(expected 3, got "+ words.length +")",lineNum);
        if(!validateRegister(words[1])) throw new ParseException("illegal arguments", lineNum);
        int reg1 = Integer.parseInt(words[1]);
        int reg2, mem;
        if(validateLabel(words[2])){
            String labelTranslation = labels.get(words[2]);
            if(labelTranslation==null) throw new ParseException("unknown label: " + words[2], lineNum);
            words[2] = labelTranslation;
        }
        if(validateMemoryAddress(words[2])) {
            var numbers = words[2].split("([()])");
            reg2 = Integer.parseInt(numbers[0]);
            mem = Integer.parseInt(numbers[1]);
            program.addInstruction(new LoadableInstruction(code, (byte)reg1, (byte)reg2, (short)mem));
            return;
        }
        throw new ParseException("illegal arguments: " + words[2], lineNum);
    }

    private static void loadRegMemOrRegRegInstruction(byte codeRegMem, byte codeRegReg, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 3)throw new ParseException("illegal words number(expected 3, got "+ words.length +")", lineNum);
        if(!validateRegister(words[1])) throw new ParseException("illegal arguments: " + words[1] + " is not a valid register", lineNum);
        int reg1 = Integer.parseInt(words[1]);
        int reg2, mem;
        if(validateLabel(words[2])){
            String labelTranslation = labels.get(words[2]);
            if(labelTranslation==null) throw new ParseException("unknown label: " + words[2], lineNum);
            words[2] = labelTranslation;
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
            program.addInstruction(new LoadableInstruction(codeRegReg, (byte)reg1, (byte)reg2, (short)0));
            return;
        }
        throw new ParseException("illegal arguments: " + words[2], lineNum);
    }

    /**
     * @param s input string line.
     * @return input s splitted by whitespaces. Quoted sequences are ignored, these are left as a whole.
     */
    private static String[] splitByWhiteSpaces(String s){
        Pattern pat = Pattern.compile("(((?<![\\\\])['\"])((?:.(?!(?<![\\\\])\\2))*.?)\\2|[\\S])+");
        Matcher mat = pat.matcher(s.trim());
        ArrayList<String> list = new ArrayList<>();
        while (mat.find()) list.add(mat.group());
        return list.toArray(new String[0]);
    }

    /**
     * @param s input string line.
     * @return output string line with comments removed.
     */
    private static String removeComment(String s){
        int comment = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ';') { comment = i; break; }
            else if(c == '\'' || c == '\"') for (i++; i < s.length(); i++) {
                char ignored = s.charAt(i);
                if (ignored == '\\') i++;
                else if (ignored == c) break;
            }
        }
        if(comment != -1) s = s.substring(0, comment);
        return s;
    }

    /**
     * @param s input string line.
     * @return true if input string has label. However this method doesn't check if label is valid.
     */
    private static boolean hasLabel(String s){
        return s.replaceAll("((?<![\\\\])['\"])((?:.(?!(?<![\\\\])\\1))*.?)\\1", "").contains(":");
    }

    /**
     * @param s input string line.
     * @return first label of the line if line has one, otherwise empty string.
     */
    private static String label(String s){
        if(hasLabel(s))
            return s.trim().replaceFirst(":.*", "").trim();
        else
            return "";
    }

    /**
     * @param s input string line.
     * @return removes first label of the line. Resulting line is trimmed.
     */
    private static String removeLabel(String s){
        return s.trim().replaceFirst("^[a-zA-Z0-9_]*:", "").trim();
    }

    /**
     * @param s s input string line.
     * @return removes all labels of the line. Resulting line is trimmed.
     */
    private static String removeLabels(String s){
        while(hasLabel(s))s = removeLabel(s);
        return s.trim();
    }

    /**
     * @param s input string.
     * @return true if input string is a valid register, i.e. number from 0 to 15.
     */
    private static boolean validateRegister(String s){
        return s.matches("^(1[0-5]|[0-9])$");
    }

    /**
     * @param s input string.
     * @return true if input string is a valid label, i.e. starts with a
     * letter or '_' and contains only letters numbers or '_' chars.
     */
    private static boolean validateLabel(String s){
        return s.matches("^[a-zA-Z_][a-zA-Z0-9_]*$");
    }

    /**
     * @param s input string.
     * @return true if input string is a valid memory address, e.g. 15(-3245),
     * first number indicates register, second memory offset and must be 6 digits maximum.
     */
    private static boolean validateMemoryAddress(String s) {
        return s.matches("^(1[0-5]|[0-9])\\(("+DECIMAL_INTEGER+")\\)$");
    }

    /**
     * @param s input string.
     * @return true if input string is a valid declaration of space.
     */
    private static boolean validateDeclaringSpace(String s) {
        return s.matches("^("+DECIMAL_UNSIGNED_SHORT+"\\*)?(INTEGER|FLOAT|BYTE|CHAR)$");
    }

    /**
     * @param s input string.
     * @return true if input string is a valid declaration of a constant.
     */
    private static boolean validateDeclaringConstant(String s) {
        return s.matches("^(("+DECIMAL_UNSIGNED_SHORT+"\\*)?" +
                "(INTEGER\\(("+DECIMAL_INTEGER+"|"+HEXADECIMAL_INTEGER+"|"+BINARY_INTEGER+")\\)|" +
                "FLOAT\\("+FLOATING_POINT_NUMBER+"|"+HEXADECIMAL_INTEGER+"|"+BINARY_INTEGER+")\\)|" +
                "BYTE\\(("+DECIMAL_UNSIGNED_BYTE+"|"+HEXADECIMAL_BYTE+"|"+BINARY_BYTE+")\\)|" +
                "CHAR\\(("+DECIMAL_UNSIGNED_BYTE+"|"+HEXADECIMAL_BYTE+"|"+BINARY_BYTE+"|'("+ASCII_CHAR+"|"+ESCAPED_CHAR+")')\\))|" +
                "STRING\\(\""+ASCII_CHAR+"*\"\\))$");
    }

    /**
     * @param s input string.
     * @return true if input string starts with a positive number, but is no larger than 6 digits.
     */
    private static boolean startsWithPositiveShortNumber(String s){
        return s.matches("^[1-9]\\d{0,5}.*");
    }

    /**
     * @param s string containing int number or byte/hex representation of int.
     * @return parsed int value.
     */
    private static int parseInt(String s){
        return s.startsWith("0x") ?
                (Integer.parseUnsignedInt(s.substring(2), 16)) :
                s.startsWith("0b") ? (Integer.parseUnsignedInt(s.substring(2), 2)) :
                        Integer.parseInt(s);
    }

    /**
     * @param s string containing float number or byte/hex representation of float.
     * @return parsed float value.
     */
    private static float parseFloat(String s){
        return s.startsWith("0x") ?
                (Float.intBitsToFloat(Integer.parseUnsignedInt(s.substring(2), 16))) :
                s.startsWith("0b") ?
                        Float.intBitsToFloat((Integer.parseUnsignedInt(s.substring(2), 2))) :
                        Float.parseFloat(s);
    }

    /**
     * @param s input command string.
     * @return true if input string is a valid command with comma in the right place.
     */
    private static boolean validateCommandWithComma(String s){
        return s.matches("^\\s*[A-Z]+\\s+[0-9]+\\s*,\\s*[-A-Za-z0-9()_]+\\s*$");
    }

    /**
     * @param s input string containing char value. Might be either byte value or single quoted char or escaped char.
     * @return if s was a byte value returns char from byte value, otherwise returns char or escaped char.
     */
    private static char processCharValue(String s){
        if(s.startsWith("'"))
            return processEscapedCharacters(removeFirstAndLastChar(s), '\'').charAt(0);
        else
            return (char)parseInt(s);
    }

    /**
     * @param s input string.
     * @return substring between first '(' char and last ')' char.
     * @exception IndexOutOfBoundsException might be thrown if the input string isn't valid declaration.
     */
    private static String retrieveDeclaredValue(String s){
        return s.substring(s.indexOf('(') + 1, s.lastIndexOf(')'));
    }

    /**
     * @param s input string.
     * @return output string without first and last character.
     * @exception IndexOutOfBoundsException if the input string has length lesser than 2.
     */
    private static String removeFirstAndLastChar(String s){
        return s.substring(1, s.length() - 1);
    }

    /**
     * @param s input string
     * @return true if input string is valid String sequence i.e. all chars '\' and '"' are properly escaped.
     */
    private static boolean validateStringValue(String s){
        return s.matches("^([^\\\\\"]|\\\\\\\\|\\\\("+DECIMAL_UNSIGNED_BYTE+")|\\\\n|\\\\t|\\\\\")*$");
    }

    /**
     * @param s input string
     * @param quote char that needs escaping depending if char or string value is processed.
     * @return String with escaped chars replaced e.g. sequence '\n' will be replaced with new line char.
     */
    private static String processEscapedCharacters(String s, char quote){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c=='\\'){
                char escaped = s.charAt(i+1);
                if (escaped == 'n') {
                    sb.append('\n');
                } else if (escaped == 't') {
                    sb.append('\t');
                } else if (escaped == '\\') {
                    sb.append('\\');
                } else if(escaped==quote) sb.append(quote);
                else if(Character.isDigit(escaped)){
                    StringBuilder number = new StringBuilder(4);
                    int value = escaped - '0';
                    number.append(escaped);
                    while(i + 2 < s.length()){
                        char digit = s.charAt(++i+1);
                        if(Character.isDigit(digit)){
                            value = value * 10 + digit - '0';
                            if(value > 255)break;
                            else number.append(digit);
                        }
                        else break;
                    }
                    i--;
                    sb.append((char)Integer.parseInt(number, 0, number.length(), 10));
                }
                i++;
            } else sb.append(c);
        }
        return sb.toString();
    }
}