package com.hermant.machine.ram;

import java.util.function.BiConsumer;
import java.util.function.Function;

public enum Endianness{

    LittleEndian {
        @Override
        void setShort(BiConsumer<Integer, Byte> setByte, int address, short value) {
            setByte.accept(address++, (byte)((value & 0x000000ff)));
            setByte.accept(address, (byte)((value & 0x0000ff00)>>8));
        }

        @Override
        short getShort(Function<Integer, Byte> getByte, int address) {
            return (short) ((getByte.apply(address++) & 0xff) | (getByte.apply(address) & 0xff) << 8);
        }

        @Override
        void setInteger(BiConsumer<Integer, Byte> setByte, int address, int value) {
            setByte.accept(address++, (byte)(value & 0xff));
            setByte.accept(address++, (byte)((value >> 8) & 0xff));
            setByte.accept(address++, (byte)((value >> 16) & 0xff));
            setByte.accept(address, (byte)((value >> 24) & 0xff));
        }

        @Override
        int getInteger(Function<Integer, Byte> getByte, int address) {
            return (getByte.apply(address++)&0xff)|((getByte.apply(address++)&0xff)<<8)|
                    ((getByte.apply(address++)&0xff)<<16)|(getByte.apply(address)<<24);
        }

    },
    BigEndian {
        @Override
        void setShort(BiConsumer<Integer, Byte> setByte, int address, short value) {
            setByte.accept(address++, (byte)((value & 0x0000ff00)>>8));
            setByte.accept(address, (byte)((value & 0x000000ff)));
        }

        @Override
        short getShort(Function<Integer, Byte> getByte, int address) {
            return (short)((getByte.apply(address++) & 0xff) << 8 | (getByte.apply(address) & 0xff));
        }

        @Override
        void setInteger(BiConsumer<Integer, Byte> setByte, int address, int value) {
            setByte.accept(address++, (byte)((value & 0xff000000)>>24));
            setByte.accept(address++, (byte)((value & 0x00ff0000)>>16));
            setByte.accept(address++, (byte)((value & 0x0000ff00)>>8));
            setByte.accept(address, (byte)((value & 0x000000ff)));
        }

        @Override
        int getInteger(Function<Integer, Byte> getByte, int address) {
            return (getByte.apply(address++)<<24)|((getByte.apply(address++)<<16)&0xff0000)|
            ((getByte.apply(address++)<<8)&0xff00)|(getByte.apply(address)&0xff);
        }

    };

    abstract void setShort(BiConsumer<Integer, Byte> setByte, int address, short value);

    abstract short getShort(Function<Integer, Byte> getByte, int address);

    abstract void setInteger(BiConsumer<Integer, Byte> setByte, int address, int value);

    abstract int getInteger(Function<Integer, Byte> getByte, int address);

}
