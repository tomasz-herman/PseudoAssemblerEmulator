package com.hermant.serializer;

import com.hermant.program.Program;
import com.hermant.program.declaration.*;
import com.hermant.program.instruction.InstructionUtils;
import com.hermant.program.instruction.LoadableInstruction;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serializer {

    public enum DeclarationType{
        SINGLE_BYTE{
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                return new ByteDeclaration(1, buffer.get());
            }
        },
        SINGLE_INT {
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                return new IntegerDeclaration(1, buffer.getInt());
            }
        },
        MANY_BYTES {
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                return new ByteDeclaration(Short.toUnsignedInt(buffer.getShort()), buffer.get());
            }
        },
        MANY_INTS {
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                return new IntegerDeclaration(Short.toUnsignedInt(buffer.getShort()), buffer.getInt());
            }
        },
        STRING {
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                StringBuilder string = new StringBuilder();
                int chars = Short.toUnsignedInt(buffer.getShort());
                for (int i = 0; i < chars; i++) {
                    string.append((char)buffer.get());
                }
                return new StringDeclaration(string.toString());
            }
        },
        EMPTY_BYTE {
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                return new ByteDeclaration(1, null);
            }
        },
        EMPTY_INT {
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                return new IntegerDeclaration(1, null);
            }
        },
        MANY_EMPTY_BYTES {
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                return new ByteDeclaration(Short.toUnsignedInt(buffer.getShort()), null);
            }
        },
        MANY_EMPTY_INTS {
            @Override
            Declaration deserialize(ByteBuffer buffer) {
                return new IntegerDeclaration(Short.toUnsignedInt(buffer.getShort()), null);
            }
        };

        abstract Declaration deserialize(ByteBuffer buffer);
    }

    public static Program deserializeBinary(String path) throws IOException, SerializationException, BufferUnderflowException {
        Program program = new Program();
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        if(buffer.remaining() < 2)throw new SerializationException("File is too small.");
        int declarations = Short.toUnsignedInt(buffer.getShort());
        int maxDeclarationType = DeclarationType.values().length - 1;
        while(declarations > 0 && buffer.hasRemaining()){
            int typeIndex = buffer.get();
            if(typeIndex > maxDeclarationType || typeIndex < 0)
                throw new SerializationException("invalid internal structure");
            DeclarationType type = DeclarationType.values()[typeIndex];
            program.addDeclaration(type.deserialize(buffer));
            declarations--;
        }
        if(declarations > 0) throw new SerializationException("invalid internal structure");
        while(buffer.hasRemaining()){
            program.addInstruction(deserializeInstruction(buffer));
        }
        return program;
    }

    public static void serializeProgram(Program program, String path) throws IOException {
        Files.write(Paths.get(path), program.serialize());
    }

    private static LoadableInstruction deserializeInstruction(ByteBuffer buffer){
        byte code = buffer.get();
        int instructionLength = InstructionUtils.getInstructionLength(code);
        byte reg = buffer.get();
        byte reg1 = (byte)(reg>>4&0xf);
        byte reg2 = (byte)(reg&0xf);
        short offset = instructionLength == 4 ? buffer.getShort() : 0;
        return new LoadableInstruction(code, reg1, reg2, offset);
    }
}
