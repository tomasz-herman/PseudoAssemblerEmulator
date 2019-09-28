package com.hermant.gui;

import com.hermant.machine.Machine;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.Serializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class Form {
    private JTextArea terminal;
    private JButton select_input;
    private JButton run_button;
    private JCheckBox unsafeCheckBox;
    private JCheckBox abandonCheckBox;
    private JCheckBox binaryCheckBox;
    private JSlider sleep_slider;
    private JButton selectOutputFileButton;
    private JPanel main;
    private JPanel secondary;
    private JComboBox<Combo> routine;
    private JLabel input;
    private JLabel output;
    private JLabel sleep;

    private JFileChooser inputChooser;
    private JFileChooser outputChooser;
    private JScrollPane scroll;

    public Form() {
        inputChooser = new JFileChooser();
        outputChooser = new JFileChooser();
        select_input.addActionListener(e -> {
            int returnVal = inputChooser.showOpenDialog(secondary);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = inputChooser.getSelectedFile();
                input.setText(file.getPath());
            }
        });
        selectOutputFileButton.addActionListener(e -> {
            int returnVal = outputChooser.showSaveDialog(secondary);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = outputChooser.getSelectedFile();
                output.setText(file.getPath());
            }
        });
        sleep_slider.addChangeListener(
            e -> sleep.setText("Sleep:" + sleep_slider.getValue() * 10)
        );
        terminal = new JTextArea();
        disableArrowKeys(terminal.getInputMap());
        terminal.setFont(new Font("Monospace", Font.BOLD, 18));
        scroll.setViewportView(terminal);
        routine.addItem(new Combo(0, "Run"));
        routine.addItem(new Combo(1, "Debug"));
        routine.addActionListener(e -> run_button.setText(Objects.requireNonNull(routine.getSelectedItem()).toString()));

        run_button.addActionListener(e -> {
            boolean unsafe = unsafeCheckBox.isSelected();
            boolean abandon = abandonCheckBox.isSelected();
            boolean binary = binaryCheckBox.isSelected();
            boolean debug = Objects.requireNonNull(routine.getSelectedItem()).toString().equals("Debug");
            int sleep = sleep_slider.getValue() * 10;
            String inputFile = input.getText();
            String outputFile = output.getText();
            if(inputFile.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You must provide input file!");
            } else {
                Program program = binary ?
                        Serializer.deserializeBinary(inputFile) : Parser.parse(inputFile);
                if(!outputFile.isEmpty()) Serializer.serializeProgram(program, outputFile);
                if(abandon)System.exit(0);
                Machine m = new Machine(debug, unsafe);
                m.loadProgram(program);
                m.runProgram(sleep);
                m.free();
            }
        });
    }

    private void disableArrowKeys(InputMap inputMap) {
        String[] keys = {"UP", "DOWN", "LEFT", "RIGHT", "HOME"};
        for (String key : keys) {
            inputMap.put(KeyStroke.getKeyStroke(key), "none");
        }
    }

    public JPanel getMain(){
        return main;
    }

    public JTextArea getTerminal(){
        return terminal;
    }

    private static class Combo{
        private final int value;
        private final String label;

        public Combo(int value, String label) {
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
}
