package com.hermant.parser;

import com.hermant.program.Program;
import com.hermant.program.declaration.Declaration;
import com.hermant.program.declaration.IntegerDeclaration;
import com.hermant.program.instruction.Instruction;
import com.hermant.program.instruction.LoadableInstruction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.hermant.machine.register.GeneralPurposeRegister.DATA_SECTION;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    static Stream<Arguments> programsTestSet() {
        return Stream.of(
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of(""), Program.of()),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC INTEGER(1)"), Program.of(new IntegerDeclaration(1, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC INTEGER(2)"), Program.of(new IntegerDeclaration(1, 2))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC INTEGER(-1)"), Program.of(new IntegerDeclaration(1, -1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC INTEGER(0b11)"), Program.of(new IntegerDeclaration(1, 3))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC INTEGER(0xffFFffFF)"), Program.of(new IntegerDeclaration(1, -1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 10*INTEGER(1)"), Program.of(new IntegerDeclaration(10, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 10*INTEGER(2)"), Program.of(new IntegerDeclaration(10, 2))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 10*INTEGER(-1)"), Program.of(new IntegerDeclaration(10, -1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 10*INTEGER(0b11)"), Program.of(new IntegerDeclaration(10, 3))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 10*INTEGER(0xffFFffFF)"), Program.of(new IntegerDeclaration(10, -1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("LABEL: DC INTEGER(1)"), Program.of(new IntegerDeclaration(1, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("LABEL:         DC INTEGER(1)"), Program.of(new IntegerDeclaration(1, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("LABEL:", "DC INTEGER(1)"), Program.of(new IntegerDeclaration(1, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("LABEL1:", "LABEL2:", "DC INTEGER(1)"), Program.of(new IntegerDeclaration(1, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("LABEL1:", "LABEL2:", "LABEL3: DC INTEGER(1)"), Program.of(new IntegerDeclaration(1, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("LABEL1: LABEL2: LABEL3: DC INTEGER(1)"), Program.of(new IntegerDeclaration(1, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("LABEL1: LABEL2:", "\n", "\t", "DC INTEGER(1)"), Program.of(new IntegerDeclaration(1, 1))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("ADD 3, 7"), Program.of(new LoadableInstruction(Instruction.ADD_REGISTER, (byte) 3, (byte) 7, (short) 0))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("SUB 3, 7"), Program.of(new LoadableInstruction(Instruction.SUBTRACT_REGISTER, (byte) 3, (byte) 7, (short) 0))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("MUL 3, 7"), Program.of(new LoadableInstruction(Instruction.MULTIPLY_REGISTER, (byte) 3, (byte) 7, (short) 0))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DIV 3, 7"), Program.of(new LoadableInstruction(Instruction.DIVIDE_REGISTER, (byte) 3, (byte) 7, (short) 0))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("IDIV 3, 7"), Program.of(new LoadableInstruction(Instruction.DIVIDE_SIGNED_REGISTER, (byte) 3, (byte) 7, (short) 0))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 13*INTEGER(1)", "LABEL: DC INTEGER(1)", "ADD 3, LABEL"), Program.of(new IntegerDeclaration(13, 1), new IntegerDeclaration(1, 1), new LoadableInstruction(Instruction.ADD, (byte) 3, (byte) DATA_SECTION, (short) (13 * 4)))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 13*INTEGER(1)", "LABEL: DC INTEGER(1)", "SUB 3, LABEL"), Program.of(new IntegerDeclaration(13, 1), new IntegerDeclaration(1, 1), new LoadableInstruction(Instruction.SUBTRACT, (byte) 3, (byte) DATA_SECTION, (short) (13 * 4)))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 13*INTEGER(1)", "LABEL: DC INTEGER(1)", "MUL 3, LABEL"), Program.of(new IntegerDeclaration(13, 1), new IntegerDeclaration(1, 1), new LoadableInstruction(Instruction.MULTIPLY, (byte) 3, (byte) DATA_SECTION, (short) (13 * 4)))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 13*INTEGER(1)", "LABEL: DC INTEGER(1)", "DIV 3, LABEL"), Program.of(new IntegerDeclaration(13, 1), new IntegerDeclaration(1, 1), new LoadableInstruction(Instruction.DIVIDE, (byte) 3, (byte) DATA_SECTION, (short) (13 * 4)))),
                Arguments.of((Supplier<Stream<String>>) () -> Stream.of("DC 13*INTEGER(1)", "LABEL: DC INTEGER(1)", "IDIV 3, LABEL"), Program.of(new IntegerDeclaration(13, 1), new IntegerDeclaration(1, 1), new LoadableInstruction(Instruction.DIVIDE_SIGNED, (byte) 3, (byte) DATA_SECTION, (short) (13 * 4))))
        );
    }

    @ParameterizedTest
    @MethodSource("programsTestSet")
    public void parseProgram(Supplier<Stream<String>> supplier, Program expectedProgram) throws ParseException {
        Program program = Parser.parse(supplier, false);
        byte[] expectedBytes = expectedProgram.serialize();
        byte[] actualBytes = program.serialize();
        assertEquals(expectedBytes.length, actualBytes.length);
        for (int j = 0; j < expectedBytes.length; j++) assertEquals(expectedBytes[j], actualBytes[j]);
    }

}