package com.hermant.serializer;

import com.hermant.program.Program;
import com.hermant.program.declaration.*;
import com.hermant.program.instruction.InstructionFactory;
import com.hermant.program.instruction.LoadableInstruction;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serializer {

    public enum DeclarationType{
        SINGLE_BYTE,
        SINGLE_INT,
        MANY_BYTES,
        MANY_INTS,
        STRING,
        EMPTY_BYTE,
        EMPTY_INT,
        MANY_EMPTY_BYTES,
        MANY_EMPTY_INTS
    }

    public static Program deserializeBinary(String path){
        Program program = new Program();
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
            if(buffer.remaining() < 2)throw new SerializationException("File is too small.");
            int declarations = Short.toUnsignedInt(buffer.getShort());
            int maxDeclarationType = DeclarationType.values().length - 1;
            while(declarations > 0 && buffer.hasRemaining()){
                int typeIndex = buffer.get();
                if(typeIndex > maxDeclarationType || typeIndex < 0) throw new SerializationException("invalid internal structure");
                DeclarationType type = DeclarationType.values()[typeIndex];
                switch(type){
                    case SINGLE_BYTE -> program.addDeclaration(new ByteDeclaration(1, buffer.get()));
                    case SINGLE_INT -> program.addDeclaration(new IntegerDeclaration(1, buffer.getInt()));
                    case EMPTY_BYTE -> program.addDeclaration(new ByteDeclaration(1, null));
                    case EMPTY_INT -> program.addDeclaration(new IntegerDeclaration(1, null));
                    case MANY_BYTES -> program.addDeclaration(new ByteDeclaration(Short.toUnsignedInt(buffer.getShort()), buffer.get()));
                    case MANY_INTS -> program.addDeclaration(new IntegerDeclaration(Short.toUnsignedInt(buffer.getShort()), buffer.getInt()));
                    case MANY_EMPTY_BYTES -> program.addDeclaration(new ByteDeclaration(Short.toUnsignedInt(buffer.getShort()), null));
                    case MANY_EMPTY_INTS -> program.addDeclaration(new IntegerDeclaration(Short.toUnsignedInt(buffer.getShort()), null));
                    case STRING -> {
                        StringBuilder string = new StringBuilder();
                        int chars = Short.toUnsignedInt(buffer.getShort());
                        for (int i = 0; i < chars; i++) {
                            string.append((char)buffer.get());
                        }
                        program.addDeclaration(new StringDeclaration(string.toString()));
                    }
                }
                declarations--;
            }
            if(declarations > 0) throw new SerializationException("invalid internal structure");
            while(buffer.hasRemaining()){
                byte code = buffer.get();
                int instructionLength = InstructionFactory.getInstructionLength(code);
                byte reg = buffer.get();
                byte reg1 = (byte)(reg>>4&0xf);
                byte reg2 = (byte)(reg&0xf);
                short offset = instructionLength == 4 ? buffer.getShort() : 0;
                program.addInstruction(new LoadableInstruction(code, reg1, reg2, offset));
            }
        } catch (IOException | SerializationException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return program;
    }

    public static void serializeProgram(Program program, String path){
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int declarations = program.declarations.size();
        buffer.write((byte)declarations);
        buffer.write(declarations >> 8);
        for (Declaration d : program.declarations) buffer.writeBytes(d.toByteArray());
        for (LoadableInstruction inst : program.instructions) buffer.writeBytes(inst.toByteArray());
        try {
            Files.write(Paths.get(path), buffer.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
