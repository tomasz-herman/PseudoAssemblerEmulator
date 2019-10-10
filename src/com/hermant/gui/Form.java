package com.hermant.gui;

import com.hermant.machine.Machine;
import com.hermant.parser.ParseException;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.SerializationException;
import com.hermant.serializer.Serializer;
import sun.misc.Signal;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Form {
    private JButton select_input_button;
    private JButton run_button;
    private JCheckBox unsafeCheckBox;
    private JCheckBox abandonCheckBox;
    private JCheckBox binaryCheckBox;
    private JSlider sleep_slider;
    private JButton select_output_button;
    private JPanel main;
    private JComboBox<ComboItem> routine;
    private JLabel input;
    private JLabel output;
    private JLabel sleep;
    private JScrollPane scroll;
    private JComboBox<ComboItem>  font_name;
    private JComboBox<ComboItem>  font_size;
    private JFileChooser inputChooser = new JFileChooser();
    private JFileChooser outputChooser = new JFileChooser();
    private JTextArea terminal;

    private CustomInputStream inputStream;
    private CustomOutputStream outputStream;

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
            "SOFTWARE.";

    private String[] monospaced = new String[]{
            "DejaVu Sans Mono", "Bitstream Vera Sans Mono", "Noto Sans Mono", "Droid Sans Mono",
            "Fira Code", "FreeMono", "Hack", "Liberation Mono", "Source Code Pro", "Lucida Console",
            "Courier New", "Consolas", "Noto Sans Mono Condensed"
    };

    private String[] sizes = new String[]{
            "8", "9", "10", "11", "12", "13", "14", "15", "16", "18", "20",
            "22", "24", "26", "28", "32", "36", "40", "48", "56", "64", "72"
    };

    private volatile boolean running = false;

    Form() {
        chooseMonospacedFonts();
        setupInputOutputButtons();
        setupComboBox();
        setupSleepSlider();
        terminal = createTerminal();
        inputStream = (CustomInputStream)createInputStream(terminal);
        outputStream = (CustomOutputStream)createOutputStream(terminal);
        setSignalHandling();
        setupRunButton();
        setupFontBoxes();
    }

    private Options getOptions(){
        Options options = new Options();
        options.unsafe = unsafeCheckBox.isSelected();
        options.abandon = abandonCheckBox.isSelected();
        options.binary = binaryCheckBox.isSelected();
        options.debug = Objects.requireNonNull(routine.getSelectedItem()).toString().equals("Debug");
        options.sleep = getSleep();
        options.input = input.getText();
        options.output = output.getText();
        return options;
    }

    private int getSleep(){
        return sleep_slider.getValue() * sleep_slider.getValue();
    }

    private void setupSleepSlider(){
        sleep_slider.addChangeListener(
                e -> sleep.setText("Sleep:" + getSleep())
        );
    }

    private void setupRunButton(){
        run_button.addActionListener(e -> {
            Thread t = new Thread(this::run);
            if(!running) t.start();
            else JOptionPane.showMessageDialog(null, "Some program is already running!");
        });
    }

    private void setupInputOutputButtons(){
        select_input_button.addActionListener(e -> {
            int returnVal = inputChooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = inputChooser.getSelectedFile();
                input.setText(file.getPath());
                input.setToolTipText(file.getPath());
            }
        });
        select_output_button.addActionListener(e -> {
            int returnVal = outputChooser.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = outputChooser.getSelectedFile();
                output.setText(file.getPath());
                output.setToolTipText(file.getPath());
            }
        });
    }

    private void setupComboBox(){
        routine.addItem(new ComboItem(0, "Run"));
        routine.addItem(new ComboItem(1, "Debug"));
        routine.addActionListener(e -> run_button.setText(Objects.requireNonNull(routine.getSelectedItem()).toString()));
    }

    private void setupFontBoxes(){
        for (int i = 0; i < monospaced.length; i++) {
            font_name.addItem(new ComboItem(i, monospaced[i]));
        }
        for (int i = 0; i < sizes.length; i++) {
            font_size.addItem(new ComboItem(i, sizes[i]));
        }
        font_name.addActionListener(e ->
                terminal.setFont(
                        new Font(
                                Objects.requireNonNull(font_name.getSelectedItem()).toString(),
                                Font.PLAIN,
                                Integer.parseInt(Objects.requireNonNull(font_size.getSelectedItem()).toString())
                        )
                )
        );
        font_size.addActionListener(e ->
                terminal.setFont(
                        new Font(
                                Objects.requireNonNull(font_name.getSelectedItem()).toString(),
                                Font.PLAIN,
                                Integer.parseInt(Objects.requireNonNull(font_size.getSelectedItem()).toString())
                        )
                )
        );
        font_size.setSelectedIndex(10);
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

    private JTextArea createTerminal(){
        JTextArea terminal = new JTextArea();
        disableArrowKeys(terminal.getInputMap());
        terminal.setEditable(false);
        scroll.setViewportView(terminal);
        terminal.setText(HELP + "\n\n" + LICENSE);
        return terminal;
    }

    private void chooseMonospacedFonts(){
        var fonts = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        var available = new ArrayList<String>();
        fonts.forEach(System.out::println);
        for (String mono : monospaced) {
            if(fonts.contains(mono))
               available.add(mono);
        }
        monospaced = available.toArray(new String[0]);
    }

    private void setSignalHandling(){
        Signal.handle(new Signal("INT"), sig -> {});
    }

    private void setControlsEnabled(boolean enable){
        run_button.setEnabled(enable);
        sleep_slider.setEnabled(enable);
        select_input_button.setEnabled(enable);
        select_output_button.setEnabled(enable);
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

    private Program getProgram(Options options){
        try {
            return options.binary ?
                    Serializer.deserializeBinary(options.input) : Parser.parse(options.input);
        } catch (IOException | SerializationException | ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void serializeProgram(Program program, String output){
        try {
            Serializer.serializeProgram(program, output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void prepare(){
        terminal.setText("");
        outputStream.reset();
        inputStream.reset();
        setControlsEnabled(false);
    }

    private void stop(){
        outputStream.flush();
        setControlsEnabled(true);
        running = false;
    }

    private void run() {
        running = true;
        prepare();
        Options options = getOptions();
        if (options.input.isEmpty()) JOptionPane.showMessageDialog(null, "You must provide input file!");
        else {
            Program program = getProgram(options);
            if(program == null){
                stop();
                return;
            }
            if (!options.output.isEmpty()) serializeProgram(program, options.output);
            Machine m = new Machine(options.debug, options.unsafe);
            m.loadProgram(program);
            if (!options.abandon)
                try {
                    m.runProgram(options.sleep);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            m.free();
        }
        stop();
    }
}
