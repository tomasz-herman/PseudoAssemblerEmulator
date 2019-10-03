package com.hermant.gui;

import com.hermant.machine.Machine;
import com.hermant.parser.ParseException;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.SerializationException;
import com.hermant.serializer.Serializer;
import sun.misc.Signal;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Objects;

public class Form {
    private JButton select_input;
    private JButton run_button;
    private JCheckBox unsafeCheckBox;
    private JCheckBox abandonCheckBox;
    private JCheckBox binaryCheckBox;
    private JSlider sleep_slider;
    private JButton selectOutputFileButton;
    private JPanel main;
    private JComboBox<Combo> routine;
    private JLabel input;
    private JLabel output;
    private JLabel sleep;
    private JFileChooser inputChooser;
    private JFileChooser outputChooser;
    private JScrollPane scroll;

    private static final String HELP =
            "This is an emulation environment for Pseudo-Assembler programs. Select input file\n" +
            "containing your program, choose if it should be run/debugged and click run/debug\n" +
            "to start your program. Selecting output file is optional. It will save assembled\n" +
            "program to specified file in machine code, allowing for later use. It may be used\n" +
            "as an input file later, but \"binary\" box must be checked, so instead of assembling\n" +
            "your program will be directly loaded into memory. The option \"unsafe\" allows the\n" +
            "program to read/write memory freely, but it may also read/write others program's\n" +
            "memory witch may result in unexpected crash for the price of 10-20% of improved\n" +
            "performance. It should only be used when you are sure the program only reads/writes\n" +
            "to it's own memory. The option \"abandon\" causes the program to assemble, but\n" +
            "run/debug part is skipped. Sleep slider allows to slow down the program, by sleeping\n" +
            "specified amount of milliseconds in between executing instructions.\n" +
            "\n" +
            "The project is open sourced, available under MIT Licence provided below. The source\n" +
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
            "SOFTWARE.";

    private volatile boolean running = false;

    Form() {
        inputChooser = new JFileChooser();
        outputChooser = new JFileChooser();
        select_input.addActionListener(e -> {
            int returnVal = inputChooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = inputChooser.getSelectedFile();
                input.setText(file.getPath());
                input.setToolTipText(file.getPath());
            }
        });
        selectOutputFileButton.addActionListener(e -> {
            int returnVal = outputChooser.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = outputChooser.getSelectedFile();
                output.setText(file.getPath());
                output.setToolTipText(file.getPath());
            }
        });
        sleep_slider.addChangeListener(
            e -> sleep.setText("Sleep:" + sleep_slider.getValue() * sleep_slider.getValue())
        );
        JTextArea terminal = new JTextArea();
        disableArrowKeys(terminal.getInputMap());
        terminal.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
        terminal.setEditable(false);
        scroll.setViewportView(terminal);
        routine.addItem(new Combo(0, "Run"));
        routine.addItem(new Combo(1, "Debug"));
        routine.addActionListener(e -> run_button.setText(Objects.requireNonNull(routine.getSelectedItem()).toString()));

        CustomInputStream inputStream = new CustomInputStream();
        terminal.addKeyListener(inputStream);
        System.setIn(inputStream);
        CustomOutputStream outputStream = new CustomOutputStream(terminal);
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        System.setErr(printStream);

        setSignalHandling();

        terminal.setText(HELP + "\n\n" + LICENSE);

        run_button.addActionListener(e -> {
            Thread t = new Thread(() -> {
                running = true;
                terminal.setText("");
                outputStream.reset();
                inputStream.reset();
                setControlsEnabled(false);
                boolean unsafe = unsafeCheckBox.isSelected();
                boolean abandon = abandonCheckBox.isSelected();
                boolean binary = binaryCheckBox.isSelected();
                boolean debug = Objects.requireNonNull(routine.getSelectedItem()).toString().equals("Debug");
                int sleep = sleep_slider.getValue() * sleep_slider.getValue();
                String inputFile = input.getText();
                String outputFile = output.getText();
                if(inputFile.isEmpty()) JOptionPane.showMessageDialog(null, "You must provide input file!");
                else {
                    Program program;
                    try {
                        program = binary ?
                                Serializer.deserializeBinary(inputFile) : Parser.parse(inputFile);
                    } catch (IOException | SerializationException | ParseException ex) {
                        ex.printStackTrace();
                        setControlsEnabled(true);
                        running = false;
                        return;
                    }
                    if(!outputFile.isEmpty()) {
                        try {
                            Serializer.serializeProgram(program, outputFile);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    Machine m = new Machine(debug, unsafe);
                    m.loadProgram(program);
                    if(!abandon)
                        try { m.runProgram(sleep); }
                        catch (Exception ex){
                            ex.printStackTrace();
                        }
                    m.free();
                }
                outputStream.flush();
                setControlsEnabled(true);
                running = false;
            });
            if(!running)
                t.start();
            else JOptionPane.showMessageDialog(null, "Some program is already running!");
        });
    }

    private void setSignalHandling(){
        Signal.handle(new Signal("INT"), sig -> {});
    }

    private void setControlsEnabled(boolean enable){
        run_button.setEnabled(enable);
        sleep_slider.setEnabled(enable);
        select_input.setEnabled(enable);
        selectOutputFileButton.setEnabled(enable);
        input.setEnabled(enable);
        output.setEnabled(enable);
        routine.setEnabled(enable);
        sleep.setEnabled(enable);
        abandonCheckBox.setEnabled(enable);
        binaryCheckBox.setEnabled(enable);
        unsafeCheckBox.setEnabled(enable);
    }

    private void disableArrowKeys(InputMap inputMap) {
        String[] keys = {"UP", "DOWN", "LEFT", "RIGHT", "HOME", "ENTER"};
        for (String key : keys) {
            inputMap.put(KeyStroke.getKeyStroke(key), "none");
        }
    }

    public JPanel getMain(){
        return main;
    }

    private static class Combo{
        private final int value;
        private final String label;

        Combo(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    static class CustomOutputStream extends OutputStream {

        private JTextArea area;
        private StringBuilder line = new StringBuilder();
        private int lines = 0;
        private static final int MAX_LINES = 32000;
        private static final int TRUNK = 4000;

        CustomOutputStream(JTextArea area) {
            this.area = area;
        }

        void reset(){
            lines = 0;
        }

        @Override
        public void write(int b) {
            line.append((char)b);
            if(b==10){
                try {
                    if(lines > MAX_LINES) {
                        area.replaceRange("", 0, area.getLineEndOffset(TRUNK - 1));
                        lines -= TRUNK;
                        Thread.sleep(1);
                    } else if((lines & 0xff) == 0)Thread.sleep(0, 1);
                    area.append(line.toString());
                    area.setCaretPosition(area.getDocument().getLength());
                    line = new StringBuilder();
                    lines++;
                } catch (BadLocationException e){
                    e.printStackTrace();
                } catch (InterruptedException | Error ignored) {}
            }
        }

        @Override
        public void flush() {
            try {
                if(lines > MAX_LINES) {
                    area.replaceRange("", 0, area.getLineEndOffset(TRUNK - 1));
                    lines -= TRUNK;
                    Thread.sleep(1);
                } else if((lines & 0xff) == 0)Thread.sleep(0, 1);
                String s = line.toString();
                area.append(line.toString());
                area.setCaretPosition(area.getDocument().getLength());
                line = new StringBuilder();
                if(s.contains("\n"))
                    lines++;
            } catch (BadLocationException e){
                e.printStackTrace();
            } catch (InterruptedException | Error ignored){}
        }
    }

    static class CustomInputStream extends InputStream implements KeyListener {

        private String str = "";
        private StringBuilder buffer = new StringBuilder();
        private int pos = 0;

        @Override
        public void reset(){
            str = "";
            pos = 0;
            buffer = new StringBuilder();
        }

        @Override
        public int available() {
            return str.length() - pos;
        }

        @Override
        public int read() {
            //test if the available input has reached its end
            //and the EOS should be returned
            if(!str.isEmpty() && pos == str.length()){
                str = "";
                //this is supposed to return -1 on "end of stream"
                //but I'm having a hard time locating the constant
                return java.io.StreamTokenizer.TT_EOF;
            }
            //no input available, block until more is available because that's
            //the behavior specified in the Javadocs
            while (str.isEmpty() || pos >= str.length()) {
                try {
                    //according to the docs read() should block until new input is available
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            //read an additional character, return it and increment the index
            return str.charAt(pos++);
        }

        @Override
        public void keyTyped(KeyEvent e) { }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                str = str.substring(pos);
                pos = 0;
                System.out.print('\n');
                buffer.append('\n');
                str += buffer.toString();
                buffer = new StringBuilder();
                synchronized (this) {
                    this.notifyAll();
                }
            } else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if(buffer.length() > 0)
                    buffer.deleteCharAt(buffer.length() - 1);
            } else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C){
                Signal.raise(new Signal("INT"));
            } else {
                char c = e.getKeyChar();
                if(c >= 32 && c < 127) buffer.append(c);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) { }
    }
}
