package com.hermant.cli;

import com.hermant.program.Program;

import java.io.*;

public class Serializer {

    public static Program deserializeBinary(String path){
        Program program;
        try
        {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            program = (Program) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
            program = new Program();
        }
        return program;
    }

    public static void serializeProgram(Program program, String path){
        try
        {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(program);
            oos.close();
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
