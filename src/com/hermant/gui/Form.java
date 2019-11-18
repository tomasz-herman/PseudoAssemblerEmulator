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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JComboBox<ComboItem> font_name;
    private JComboBox<ComboItem> font_size;
    private JPanel font_color;
    private JPanel background_color;
    private JPanel font_color_chooser;
    private JPanel background_color_chooser;
    private JPanel color_chooser;
    private JFileChooser inputChooser;
    private JFileChooser outputChooser;
    private Terminal terminal;
    private MouseListener font_color_listener;
    private MouseListener background_color_listener;

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
        setupFileChoosers();
        chooseMonospacedFonts();
        setupInputOutputButtons();
        setupComboBox();
        setupSleepSlider();
        terminal = new Terminal(scroll);
        setSignalHandling();
        setupRunButton();
        setupFontBoxes();
        setupColorChoosers();
    }

    private void setupFileChoosers(){
        File workingDirectory = new File(System.getProperty("user.dir"));
        inputChooser = new JFileChooser();
        outputChooser = new JFileChooser();
        inputChooser.setCurrentDirectory(workingDirectory);
        outputChooser.setCurrentDirectory(workingDirectory);
    }

    private void setupColorChoosers(){
        background_color.setBackground(terminal.getBackgroundColor());
        font_color.setBackground(terminal.getTextColor());
        background_color_listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) { }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Color color = JColorChooser.showDialog(null, "Choose background color", background_color.getBackground());
                background_color.setBackground(color);
                terminal.setBackgroundColor(color);
            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) { }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) { }
            @Override
            public void mouseExited(MouseEvent mouseEvent) { }
        };
        background_color_chooser.addMouseListener(background_color_listener);
        font_color_listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) { }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Color color = JColorChooser.showDialog(null, "Choose font color", font_color.getBackground());
                font_color.setBackground(color);
                terminal.setTextColor(color);
            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) { }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) { }
            @Override
            public void mouseExited(MouseEvent mouseEvent) { }
        };
        font_color_chooser.addMouseListener(font_color_listener);
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

    private void chooseMonospacedFonts(){
        var fonts = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        var available = new ArrayList<String>();
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
        font_size.setEnabled(enable);
        font_name.setEnabled(enable);
        color_chooser.setEnabled(enable);
        background_color_chooser.setEnabled(enable);
        font_color_chooser.setEnabled(enable);
        if(enable){
            font_color_chooser.addMouseListener(font_color_listener);
            background_color_chooser.addMouseListener(background_color_listener);
        } else {
            font_color_chooser.removeMouseListener(font_color_listener);
            background_color_chooser.removeMouseListener(background_color_listener);
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
        terminal.clear();
        setControlsEnabled(false);
    }

    private void stop(){
        terminal.flush();
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
