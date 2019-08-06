package com.hermant.parser;

import com.hermant.program.Declaration;
import com.hermant.program.Instruction;
import com.hermant.program.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

import static com.hermant.machine.Register.DATA_SECTION;
import static com.hermant.machine.Register.PROGRAM_SECTION;
import static com.hermant.program.Declaration.Type.aFloat;
import static com.hermant.program.Declaration.Type.anInteger;

public class Parser {

    //tokens
    private static final String COMMENT = ";";

    //instructions
    private static final String RETURN = "RET";
    private static final String ENTER = "ENTER";
    private static final String LEAVE = "LEAVE";
    private static final String LOAD = "LD";
    private static final String LOAD_FLOAT = "FLD";
    private static final String STORE = "ST";
    private static final String STORE_FLOAT = "FST";
    private static final String LOAD_ADDRESS = "LDA";
    private static final String OUTPUT = "OUT";
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

    private static final int MAX_SECTION_SIZE = 65536;

    public static Program parse(String path){
        Program program = new Program();
        BufferedReader reader;
        try {
            //Linked Hash Map to preserve insertion order
            LinkedHashMap<String, String> labelMemoryTranslation = new LinkedHashMap<>();

            Stack<String> labels = new Stack<>();

            int programSection = 0;
            int dataSection = 0;

            //open file
            reader = new BufferedReader(new FileReader(path));
            int lineNum = 0;
            String line;

            //first round reading labels and translating them to memory addresses and quick syntax check
            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = removeComment(line);

                while(hasLabel(line)){
                    labels.push(label(line));
                    if(!validateLabel(labels.peek()))throw new ParseException("illegal label", lineNum);
                    line = removeLabel(line);
                }
                if(line.isBlank())continue;

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
                switch (words[0]){
                    case RETURN, LOAD, LOAD_FLOAT, STORE, STORE_FLOAT, LOAD_ADDRESS, ADD, ADD_FLOAT, SUBTRACT,
                            SUBTRACT_FLOAT, MULTIPLY, MULTIPLY_FLOAT, DIVIDE, DIVIDE_SIGNED, DIVIDE_FLOAT, COMPARE,
                            COMPARE_FLOAT, AND, OR, XOR, TEST, NOT, RIGHT_SHIFT_LOGICAL, LEFT_SHIFT_LOGICAL,
                            RIGHT_SHIFT_ARITHMETIC, LEFT_SHIFT_ARITHMETIC, RIGHT_ROTATE, LEFT_ROTATE, JUMP, JUMP_EQUAL,
                            JUMP_ZERO, JUMP_NOT_EQUAL, JUMP_NOT_ZERO, JUMP_GREATER, JUMP_NOT_LESS_OR_EQUAL,
                            JUMP_GREATER_OR_EQUAL, JUMP_NOT_LESS, JUMP_LESSER, JUMP_NOT_GREATER_OR_EQUAL,
                            JUMP_LESS_OR_EQUAL, JUMP_NOT_GREATER, JUMP_ABOVE, JUMP_NOT_BELOW_OR_EQUAL,
                            JUMP_ABOVE_OR_EQUAL, JUMP_NOT_BELOW, JUMP_NOT_CARRY, JUMP_BELOW, JUMP_CARRY,
                            JUMP_NOT_ABOVE_OR_EQUAL, JUMP_BELOW_OR_EQUAL, JUMP_NOT_ABOVE, JUMP_OVERFLOW,
                            JUMP_NOT_OVERFLOW, JUMP_SIGNED, JUMP_NOT_SIGNED, JUMP_PARITY, JUMP_NOT_PARITY,
                            LOOP, CALL, OUTPUT, OUTPUT_FLOAT, OUTPUT_SIGNED, NEGATE, INCREMENT, DECREMENT,
                            PUSH, PUSH_FLOAT, POP, POP_FLOAT, PUSH_FLAGS, POP_FLAGS, EXCHANGE, EXCHANGE_FLOAT,
                            LOAD_BYTE, LOAD_BYTE_UNSIGNED, STORE_BYTE, SQUARE_ROOT_FLOAT, ABSOLUTE_FLOAT, SINE_FLOAT,
                            COSINE_FLOAT, TANGENT_FLOAT, LOAD_INTEGER_AS_FLOAT, STORE_FLOAT_AS_INTEGER, RANDOM,
                            ENTER, LEAVE, EXAMINE_FLOAT, TEST_FLOAT -> {
                        while(!labels.empty()){
                            if(labelMemoryTranslation.containsKey(labels.peek()))throw new ParseException("label " + labels.peek() + " duplicate", lineNum);
                            labelMemoryTranslation.put(labels.pop(), PROGRAM_SECTION + "(" + programSection + ")");
                        }
                        switch (words[0]){
                            case RETURN, NO_OPERATION, PUSH_FLAGS, POP_FLAGS, SQUARE_ROOT_FLOAT, ABSOLUTE_FLOAT,
                                    SINE_FLOAT, COSINE_FLOAT, TANGENT_FLOAT, ENTER, LEAVE -> programSection+=2;
                            case STORE, STORE_FLOAT, LOAD_ADDRESS, JUMP, JUMP_EQUAL, JUMP_ZERO, JUMP_NOT_EQUAL,
                                    JUMP_NOT_ZERO, JUMP_GREATER, JUMP_NOT_LESS_OR_EQUAL, JUMP_GREATER_OR_EQUAL,
                                    JUMP_NOT_LESS, JUMP_LESSER, JUMP_NOT_GREATER_OR_EQUAL, JUMP_LESS_OR_EQUAL,
                                    JUMP_NOT_GREATER, JUMP_ABOVE, JUMP_NOT_BELOW_OR_EQUAL, JUMP_ABOVE_OR_EQUAL,
                                    JUMP_NOT_BELOW, JUMP_NOT_CARRY, JUMP_BELOW, JUMP_CARRY, JUMP_NOT_ABOVE_OR_EQUAL,
                                    JUMP_BELOW_OR_EQUAL, JUMP_NOT_ABOVE, JUMP_OVERFLOW, JUMP_NOT_OVERFLOW, JUMP_SIGNED,
                                    JUMP_NOT_SIGNED, JUMP_PARITY, JUMP_NOT_PARITY, LOOP, CALL, LOAD_BYTE,
                                    LOAD_BYTE_UNSIGNED, STORE_BYTE, LOAD_INTEGER_AS_FLOAT, STORE_FLOAT_AS_INTEGER
                                    -> programSection+=4;
                            case OUTPUT, OUTPUT_FLOAT, OUTPUT_SIGNED, NOT, NEGATE, INCREMENT, DECREMENT, PUSH,
                                    PUSH_FLOAT, POP, POP_FLOAT, RANDOM, EXAMINE_FLOAT, TEST_FLOAT
                                    ->{ if(validateRegister(words[1]))programSection +=2; else programSection+=4;}
                            default -> {
                                if(words[2].matches("\\d+"))programSection+=2;
                                else programSection+=4;
                            }
                        }
                        if(programSection > MAX_SECTION_SIZE) throw new IllegalStateException("exceeded maximum instruction section size");
                    }
                    case DECLARE_CONSTANT, DECLARE_SPACE -> {
                        if(programSection>0)throw new ParseException("illegal declaration after instructions" ,lineNum);
                        if(words.length > 2)throw new ParseException("illegal declaration parameters", lineNum);
                        while(!labels.empty()){
                            if(labelMemoryTranslation.containsKey(labels.peek()))throw new ParseException("label " + labels.peek() + " duplicate", lineNum);
                            labelMemoryTranslation.put(labels.pop(), DATA_SECTION + "(" + dataSection + ")");
                        }
                        if(words[1].startsWith(INTEGER) || words[1].startsWith(FLOAT))dataSection+=4;
                        else{
                            String num = words[1].replaceFirst("\\*.*", "");
                            dataSection += parseDecInt(num) * 4;
                        }
                        if(dataSection > MAX_SECTION_SIZE) throw new IllegalStateException("exceeded maximum data section size");
                    }
                    default -> throw new ParseException("unknown token " + words[0], lineNum);
                }
            }

            //write info about labels and memory usage
            System.out.println();
            System.out.println("Labels: ");
            for(var entry : labelMemoryTranslation.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            System.out.println();
            System.out.println("Program section: " + programSection + " bytes");
            System.out.println("Data section: " + dataSection + " bytes");

            //reopen file for second round
            reader.close();
            reader = new BufferedReader(new FileReader(path));
            lineNum = 0;
            //second round precise syntax check and interpreting the instructions
            while ((line = reader.readLine()) != null) {
                lineNum++;
                line = removeLabels(removeComment(line));
                if(line.isBlank())continue;

                line = line.replace(",", " ");

                var words = splitByWhiteSpaces(line);

                words[0] = words[0].toUpperCase(Locale.ROOT);
                switch (words[0]){
                    case RETURN -> loadNoParametersInstruction(Instruction.RETURN, words, program, lineNum);
                    case NO_OPERATION -> loadNoParametersInstruction(Instruction.NO_OPERATION, words, program, lineNum);
                    case PUSH_FLAGS -> loadNoParametersInstruction(Instruction.PUSH_FLAGS, words, program, lineNum);
                    case POP_FLAGS -> loadNoParametersInstruction(Instruction.POP_FLAGS, words, program, lineNum);
                    case ENTER -> loadNoParametersInstruction(Instruction.ENTER, words, program, lineNum);
                    case LEAVE -> loadNoParametersInstruction(Instruction.LEAVE, words, program, lineNum);

                    case LOAD -> loadRegMemOrRegRegInstruction(Instruction.LOAD, Instruction.LOAD_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case LOAD_FLOAT -> loadRegMemOrRegRegInstruction(Instruction.LOAD_FLOAT, Instruction.LOAD_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case STORE -> loadRegMemInstruction(Instruction.STORE, words, labelMemoryTranslation, program, lineNum);
                    case STORE_FLOAT -> loadRegMemInstruction(Instruction.STORE_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case LOAD_ADDRESS -> loadRegMemInstruction(Instruction.LOAD_ADDRESS, words, labelMemoryTranslation, program, lineNum);
                    case EXCHANGE -> loadRegMemOrRegRegInstruction(Instruction.EXCHANGE, Instruction.EXCHANGE_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case EXCHANGE_FLOAT -> loadRegMemOrRegRegInstruction(Instruction.EXCHANGE_FLOAT, Instruction.EXCHANGE_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case PUSH -> loadRegOrMemInstruction(Instruction.PUSH, Instruction.PUSH_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case PUSH_FLOAT -> loadRegOrMemInstruction(Instruction.PUSH, Instruction.PUSH_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case POP -> loadRegOrMemInstruction(Instruction.POP, Instruction.POP_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case POP_FLOAT -> loadRegOrMemInstruction(Instruction.POP, Instruction.POP_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case LOAD_BYTE -> loadRegMemInstruction(Instruction.LOAD_BYTE, words, labelMemoryTranslation, program, lineNum);
                    case LOAD_BYTE_UNSIGNED -> loadRegMemInstruction(Instruction.LOAD_BYTE_UNSIGNED, words, labelMemoryTranslation, program, lineNum);
                    case STORE_BYTE -> loadRegMemInstruction(Instruction.STORE_BYTE, words, labelMemoryTranslation, program, lineNum);
                    case LOAD_INTEGER_AS_FLOAT -> loadRegMemInstruction(Instruction.LOAD_INTEGER_AS_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case STORE_FLOAT_AS_INTEGER -> loadRegMemInstruction(Instruction.STORE_FLOAT_AS_INTEGER, words, labelMemoryTranslation, program, lineNum);
                    case RANDOM -> loadRegOrMemInstruction(Instruction.RANDOM, Instruction.RANDOM_REGISTER, words, labelMemoryTranslation, program, lineNum);

                    case ADD -> loadRegMemOrRegRegInstruction(Instruction.ADD, Instruction.ADD_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case ADD_FLOAT -> loadRegMemOrRegRegInstruction(Instruction.ADD_FLOAT, Instruction.ADD_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case SUBTRACT -> loadRegMemOrRegRegInstruction(Instruction.SUBTRACT, Instruction.SUBTRACT_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case SUBTRACT_FLOAT -> loadRegMemOrRegRegInstruction(Instruction.SUBTRACT_FLOAT, Instruction.SUBTRACT_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case MULTIPLY -> loadRegMemOrRegRegInstruction(Instruction.MULTIPLY, Instruction.MULTIPLY_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case MULTIPLY_FLOAT -> loadRegMemOrRegRegInstruction(Instruction.MULTIPLY_FLOAT, Instruction.MULTIPLY_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case DIVIDE -> loadRegMemOrRegRegInstruction(Instruction.DIVIDE, Instruction.DIVIDE_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case DIVIDE_SIGNED -> loadRegMemOrRegRegInstruction(Instruction.DIVIDE_SIGNED, Instruction.DIVIDE_SIGNED_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case DIVIDE_FLOAT -> loadRegMemOrRegRegInstruction(Instruction.DIVIDE_FLOAT, Instruction.DIVIDE_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case COMPARE -> loadRegMemOrRegRegInstruction(Instruction.COMPARE, Instruction.COMPARE_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case COMPARE_FLOAT -> loadRegMemOrRegRegInstruction(Instruction.COMPARE_FLOAT, Instruction.COMPARE_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);
                    case NEGATE -> loadRegOrMemInstruction(Instruction.NEGATE, Instruction.NEGATE_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case INCREMENT -> loadRegOrMemInstruction(Instruction.INCREMENT, Instruction.INCREMENT_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case DECREMENT -> loadRegOrMemInstruction(Instruction.DECREMENT, Instruction.DECREMENT_REGISTER, words, labelMemoryTranslation, program, lineNum);

                    case ABSOLUTE_FLOAT -> loadRegInstruction(Instruction.ABSOLUTE_FLOAT, words, program, lineNum);
                    case SQUARE_ROOT_FLOAT -> loadRegInstruction(Instruction.SQUARE_ROOT_FLOAT, words, program, lineNum);
                    case SINE_FLOAT -> loadRegInstruction(Instruction.SINE_FLOAT, words, program, lineNum);
                    case COSINE_FLOAT -> loadRegInstruction(Instruction.COSINE_FLOAT, words, program, lineNum);
                    case TANGENT_FLOAT -> loadRegInstruction(Instruction.TANGENT_FLOAT, words, program, lineNum);
                    case EXAMINE_FLOAT -> loadRegOrMemInstruction(Instruction.EXAMINE_FLOAT, Instruction.EXAMINE_FLOAT_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case TEST_FLOAT -> loadRegOrMemInstruction(Instruction.TEST_FLOAT, Instruction.TEST_FLOAT_REGISTER, words, labelMemoryTranslation, program, lineNum);

                    case AND -> loadRegMemOrRegRegInstruction(Instruction.AND, Instruction.AND_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case OR -> loadRegMemOrRegRegInstruction(Instruction.OR, Instruction.OR_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case XOR -> loadRegMemOrRegRegInstruction(Instruction.XOR, Instruction.XOR_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case TEST -> loadRegMemOrRegRegInstruction(Instruction.TEST, Instruction.TEST_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case NOT -> loadRegOrMemInstruction(Instruction.NOT, Instruction.NOT_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case RIGHT_SHIFT_LOGICAL -> loadRegMemOrRegRegInstruction(Instruction.RIGHT_SHIFT_LOGICAL, Instruction.RIGHT_SHIFT_LOGICAL_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case LEFT_SHIFT_LOGICAL -> loadRegMemOrRegRegInstruction(Instruction.LEFT_SHIFT_LOGICAL, Instruction.LEFT_SHIFT_LOGICAL_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case RIGHT_SHIFT_ARITHMETIC -> loadRegMemOrRegRegInstruction(Instruction.RIGHT_SHIFT_ARITHMETIC, Instruction.RIGHT_SHIFT_ARITHMETIC_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case LEFT_SHIFT_ARITHMETIC -> loadRegMemOrRegRegInstruction(Instruction.LEFT_SHIFT_ARITHMETIC, Instruction.LEFT_SHIFT_ARITHMETIC_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case RIGHT_ROTATE -> loadRegMemOrRegRegInstruction(Instruction.RIGHT_ROTATE, Instruction.RIGHT_ROTATE_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case LEFT_ROTATE -> loadRegMemOrRegRegInstruction(Instruction.LEFT_ROTATE, Instruction.LEFT_ROTATE_REGISTER, words, labelMemoryTranslation, program, lineNum);

                    case JUMP -> loadMemInstruction(Instruction.JUMP, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_EQUAL, JUMP_ZERO -> loadMemInstruction(Instruction.JUMP_EQUAL, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_NOT_EQUAL, JUMP_NOT_ZERO -> loadMemInstruction(Instruction.JUMP_NOT_EQUAL, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_GREATER, JUMP_NOT_LESS_OR_EQUAL -> loadMemInstruction(Instruction.JUMP_GREATER, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_GREATER_OR_EQUAL , JUMP_NOT_LESS -> loadMemInstruction(Instruction.JUMP_GREATER_OR_EQUAL, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_LESSER, JUMP_NOT_GREATER_OR_EQUAL -> loadMemInstruction(Instruction.JUMP_LESSER, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_LESS_OR_EQUAL, JUMP_NOT_GREATER -> loadMemInstruction(Instruction.JUMP_LESS_OR_EQUAL, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_ABOVE, JUMP_NOT_BELOW_OR_EQUAL -> loadMemInstruction(Instruction.JUMP_ABOVE, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_ABOVE_OR_EQUAL, JUMP_NOT_BELOW, JUMP_NOT_CARRY -> loadMemInstruction(Instruction.JUMP_ABOVE_OR_EQUAL, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_BELOW, JUMP_NOT_ABOVE_OR_EQUAL, JUMP_CARRY -> loadMemInstruction(Instruction.JUMP_BELOW, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_BELOW_OR_EQUAL, JUMP_NOT_ABOVE -> loadMemInstruction(Instruction.JUMP_BELOW_OR_EQUAL, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_OVERFLOW -> loadMemInstruction(Instruction.JUMP_OVERFLOW, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_NOT_OVERFLOW -> loadMemInstruction(Instruction.JUMP_NOT_OVERFLOW, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_SIGNED -> loadMemInstruction(Instruction.JUMP_SIGNED, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_NOT_SIGNED -> loadMemInstruction(Instruction.JUMP_NOT_SIGNED, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_PARITY -> loadMemInstruction(Instruction.JUMP_PARITY, words, labelMemoryTranslation, program, lineNum);
                    case JUMP_NOT_PARITY -> loadMemInstruction(Instruction.JUMP_NOT_PARITY, words, labelMemoryTranslation, program, lineNum);
                    case LOOP -> loadRegMemInstruction(Instruction.LOOP, words, labelMemoryTranslation, program, lineNum);
                    case CALL -> loadMemInstruction(Instruction.CALL, words, labelMemoryTranslation, program, lineNum);

                    case OUTPUT -> loadRegOrMemInstruction(Instruction.OUTPUT, Instruction.OUTPUT_REGISTER, words, labelMemoryTranslation, program, lineNum);
                    case OUTPUT_SIGNED -> loadRegOrMemInstruction(Instruction.OUTPUT_SIGNED, Instruction.OUTPUT_REGISTER_SIGNED, words, labelMemoryTranslation, program, lineNum);
                    case OUTPUT_FLOAT -> loadRegOrMemInstruction(Instruction.OUTPUT_FLOAT, Instruction.OUTPUT_REGISTER_FLOAT, words, labelMemoryTranslation, program, lineNum);

                    case DECLARE_CONSTANT -> {
                        if (words.length != 2 || !validateDeclaringConstant(words[1]))
                            throw new ParseException("illegal declaration parameters", lineNum);
                        String value = words[1].split("([()])")[1];
                        Declaration.Type type = words[1].contains(INTEGER) ? anInteger : aFloat;
                        if(startsWithPositiveNumber(words[1])){
                            int count = parseDecInt(words[1].split("\\*")[0]);
                            switch (type){
                                case anInteger -> program.addDeclaration(new Declaration(count, parseInt(value)));
                                case aFloat -> program.addDeclaration(new Declaration(count, parseFloat(value)));
                            }
                        } else switch (type) {
                            case anInteger -> program.addDeclaration(new Declaration(1, parseInt(value)));
                            case aFloat -> program.addDeclaration(new Declaration(1, parseFloat(value)));
                        }
                    }
                    case DECLARE_SPACE -> {
                        if (words.length != 2 || !validateDeclaringSpace(words[1]))
                            throw new ParseException("illegal declaration parameters", lineNum);
                        if(startsWithPositiveNumber(words[1])){
                            program.addDeclaration(new Declaration(parseDecInt(words[1].split("\\*")[0])));
                        } else program.addDeclaration(new Declaration(1));
                    }
                    default -> throw new ParseException("unknown token", lineNum);
                }
            }
            reader.close();
            System.out.println();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return program;
    }

    private static void loadNoParametersInstruction(byte code, String[] words, Program program, int lineNum) throws ParseException {
        if(words.length != 1)throw new ParseException("illegal parameters number", lineNum);
        program.addInstruction(new Instruction(code, (byte)0, (byte)0, null));
    }

    private static void loadRegOrMemInstruction(byte codeMem, byte codeReg, String[] words, Map<String, String> labels, Program program, int lineNum) throws ParseException {
        if(words.length != 2)throw new ParseException("illegal parameters number", lineNum);
        if(validateRegister(words[1])){
            int reg = Integer.parseInt(words[1]);
            program.addInstruction(new Instruction(codeReg, (byte)reg, (byte)0, null));
        } else loadMemInstruction(codeMem, words, labels, program, lineNum);
    }

    private static void loadRegInstruction(byte code, String[] words, Program program, int lineNum) throws ParseException {
        if(words.length != 2)throw new ParseException("illegal parameters number", lineNum);
        if(validateRegister(words[1])){
            int reg = Integer.parseInt(words[1]);
            program.addInstruction(new Instruction(code, (byte)reg, (byte)0, null));
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
            mem = parseDecInt(numbers[1]);
            program.addInstruction(new Instruction(code, (byte)reg1, (byte)reg2, mem));
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
            mem = parseDecInt(numbers[1]);
            program.addInstruction(new Instruction(code, (byte)reg1, (byte)reg2, mem));
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
            mem = parseDecInt(numbers[1]);
            program.addInstruction(new Instruction(codeRegMem, (byte)reg1, (byte)reg2, mem));
            return;
        }
        if(validateRegister(words[2])){
            reg2 = Integer.parseInt(words[2]);
            program.addInstruction(new Instruction(codeRegReg, (byte)reg1, (byte)reg2, null));
            return;
        }
        throw new ParseException("illegal arguments", lineNum);
    }

    //some regex one-liners:
    private static String[] splitByWhiteSpaces(String s){ return s.trim().split("\\s+"); }
    private static String removeComment(String s){return s.replaceFirst(COMMENT + ".*", "").trim();}
    private static boolean hasLabel(String s){ return s.contains(":"); }
    private static String label(String s){ if(s.contains(":")) return s.trim().replaceFirst(":.*", "").trim(); else return "";}
    private static String removeLabel(String s){return s.trim().replaceFirst("^[a-zA-Z0-9_]*:", "").trim();}
    private static String removeLabels(String s){return s.trim().replaceFirst(".*:", "").trim();}
    private static boolean validateRegister(String s){ return s.matches("^(1[0-5]|[0-9])$"); }
    private static boolean validateLabel(String s){ return s.matches("^[a-zA-Z_][a-zA-Z0-9_]*$"); }
    private static boolean validateMemoryAddress(String s) { return s.matches("^(1[0-5]|[0-9])\\(([+-]?[1-9]\\d*|[+-]?0)\\)$"); }
    private static boolean validateDeclaringSpace(String s) { return s.matches("^([1-9]\\d*\\*)?(INTEGER|FLOAT)$"); }
    private static boolean validateDeclaringConstant(String s) { return s.matches("^([1-9]\\d*\\*)?(INTEGER|FLOAT)\\((([+-]?[1-9]\\d*|[+-]?0)(\\.\\d*)?|0x[0-9a-fA-F]{1,8}|0b[0-1]{1,32})\\)$"); }
    private static boolean startsWithPositiveNumber(String s){ return s.matches("^[1-9]\\d*.*");}
    private static int parseInt(String s){return s.startsWith("0x") ? (Integer.parseUnsignedInt(s.substring(2), 16)) : s.startsWith("0b") ? (Integer.parseUnsignedInt(s.substring(2), 2)) : parseDecInt(s);}
    private static float parseFloat(String s){return s.startsWith("0x") ? (Float.intBitsToFloat(Integer.parseUnsignedInt(s.substring(2), 16))) : s.startsWith("0b") ? Float.intBitsToFloat((Integer.parseUnsignedInt(s.substring(2), 2))) : Float.parseFloat(s);}
    private static boolean validateCommandWithComma(String s){return s.matches("^\\s*[A-Z]+\\s+[0-9]+\\s*,\\s*[A-Za-z0-9()_]+\\s*$");}
    private static int parseDecInt(String s){return new BigInteger(s).intValue();}
}