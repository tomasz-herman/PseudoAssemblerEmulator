package com.hermant.machine.ram;

import java.util.function.BiConsumer;
import java.util.function.Function;

public enum Endianness{

    LittleEndian {

        @Override
        void setInteger(BiConsumer<Integer, Byte> setByte, int address, int value) {
            setByte.accept(address++, (byte)((value & 0x000000ff)));
            setByte.accept(address++, (byte)((value & 0x0000ff00)>>8));
            setByte.accept(address++, (byte)((value & 0x00ff0000)>>16));
            setByte.accept(address, (byte)((value & 0xff000000)>>24));
        }

        @Override
        int getInteger(Function<Integer, Byte> getByte, int address) {
            return (getByte.apply(address++)&0xff)|((getByte.apply(address++)<<8)&0xff00)|
                    ((getByte.apply(address++)<<16)&0xff0000)|(getByte.apply(address)<<24);
        }

    },
    MiddleEndian {

        @Override
        void setInteger(BiConsumer<Integer, Byte> setByte, int address, int value) {
            setByte.accept(address++, (byte)((value & 0x00ff0000)>>16));
            setByte.accept(address++, (byte)((value & 0xff000000)>>24));
            setByte.accept(address++, (byte)((value & 0x000000ff)));
            setByte.accept(address, (byte)((value & 0x0000ff00)>>8));
        }

        @Override
        int getInteger(Function<Integer, Byte> getByte, int address) {
            return ((getByte.apply(address++)<<16)&0xff0000)|(getByte.apply(address++)<<24)|
                    (getByte.apply(address++)&0xff)|((getByte.apply(address)<<8)&0xff00);
        }

    },
    BigEndian {

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

    abstract void setInteger(BiConsumer<Integer, Byte> setByte, int address, int value);

    abstract int getInteger(Function<Integer, Byte> getByte, int address);

}
