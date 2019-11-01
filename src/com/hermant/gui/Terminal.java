package com.hermant.gui;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

class Terminal {

    private static final String HELP =
            "This is an emulation environment for Pseudo-Assembler programs. Select input file\n" +
            "containing your program, choose if it should be run/debugged and click run/debug\n" +
            "to start your program. Selecting output file is optional. It will save assembled\n" +
            "program to specified file in machine code, allowing for later use. It may be used\n" +
            "as an input file later, but \"binary\" box must be checked, so instead of assembling\n" +
            "your program will be directly loaded into memory. The option \"unsafe\" allows the\n" +
            "program to read/write memory freely, but it may also read/write other program's\n" +
            "memory witch may result in an unexpected crash for the price of 10-20% of improved\n" +
            "performance. It should only be used when you are sure the program only reads/writes\n" +
            "to it's own memory. The option \"abandon\" causes the program to assemble, but\n" +
            "run/debug part is skipped. Sleep slider allows to slow down the program, by sleeping\n" +
            "specified amount of milliseconds in between executing instructions. In run mode Ctrl + C\n" +
            "stops the program immediately, in debug mode Ctrl + C pauses the program that might be\n" +
            "resumed with Enter key, second Ctrl + C stops the program.\n" +
            "\n" +
            "The project is open source, available under MIT Licence provided below. The source\n" +
            "code is available at: \"https://github.com/tomasz-herman/PseudoAssemblerEmulator\".\n" +
            "Documentation of the language should be bundled with this program.\n" +
            "Feel free to report noticed issues. Good luck and have fun learning.";

    private static final String LICENSE =
            "MIT License\n" +
            "\n" +
            "Copyright (c) 2019 Tomasz Herman\n" +
            "\n" +
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
            "of this software and associated documentation files (the \"Software\"), to deal\n" +
            "in the Software without restriction, including without limitation the rights\n" +
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
            "copies of the Software, and to permit persons to whom the Software is\n" +
            "furnished to do so, subject to the following conditions:\n" +
            "\n" +
            "The above copyright notice and this permission notice shall be included in all\n" +
            "copies or substantial portions of the Software.\n" +
            "\n" +
            "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
            "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
            "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
            "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
            "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
            "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
            "SOFTWARE.\n";

    private int verticalScrollBarMaximumValue;
    private JTextArea textArea;
    private CustomInputStream inputStream;
    private CustomOutputStream outputStream;

    Terminal(JScrollPane scrollPane){
        verticalScrollBarMaximumValue = scrollPane.getVerticalScrollBar().getMaximum();
        scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            if ((verticalScrollBarMaximumValue - e.getAdjustable().getMaximum()) == 0) return;
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            verticalScrollBarMaximumValue = scrollPane.getVerticalScrollBar().getMaximum();
        });
        textArea = new JTextArea();
        disableArrowKeys(textArea.getInputMap());
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        textArea.setText(HELP + "\n\n" + LICENSE);
        inputStream = (CustomInputStream)createInputStream(textArea);
        outputStream = (CustomOutputStream)createOutputStream(textArea);
    }

    Terminal(){
        textArea = new JTextArea();
        disableArrowKeys(textArea.getInputMap());
        textArea.setEditable(false);
        textArea.setText(HELP + "\n\n" + LICENSE);
        inputStream = (CustomInputStream)createInputStream(textArea);
        outputStream = (CustomOutputStream)createOutputStream(textArea);
    }

    private void disableArrowKeys(InputMap inputMap) {
        String[] keys = {"UP", "DOWN", "LEFT", "RIGHT", "HOME", "ENTER"};
        for (String key : keys) {
            inputMap.put(KeyStroke.getKeyStroke(key), "none");
        }
    }

    private InputStream createInputStream(JTextArea terminal){
        CustomInputStream inputStream = new CustomInputStream();
        terminal.addKeyListener(inputStream);
        System.setIn(inputStream);
        return inputStream;
    }

    private OutputStream createOutputStream(JTextArea terminal){
        CustomOutputStream outputStream = new CustomOutputStream(terminal);
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        System.setErr(printStream);
        return outputStream;
    }

    void clear() {
        textArea.setText("");
        outputStream.reset();
        inputStream.reset();
    }

    void flush() {
        outputStream.flush();
    }

    void setFont(Font font){
        textArea.setFont(font);
    }

    void setTextColor(Color color){
        textArea.setForeground(color);
    }

    void setBackgroundColor(Color color){
        textArea.setBackground(color);
    }

    Color getTextColor(){
        return textArea.getForeground();
    }

    Color getBackgroundColor(){
        return textArea.getBackground();
    }

    JTextArea getTextArea(){
        return textArea;
    }
}
