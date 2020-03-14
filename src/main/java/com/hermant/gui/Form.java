package com.hermant.gui;

import com.hermant.machine.Machine;
import com.hermant.parser.ParseException;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.SerializationException;
import com.hermant.serializer.Serializer;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
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
    private JComboBox<String> routine;
    private JLabel input;
    private JLabel output;
    private JLabel sleep;
    private JScrollPane scroll;
    private JComboBox<String> font_name;
    private JComboBox<String> font_size;
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

    private void setupFileChoosers() {
        File workingDirectory = new File(System.getProperty("user.dir"));
        inputChooser = new JFileChooser();
        outputChooser = new JFileChooser();
        inputChooser.setCurrentDirectory(workingDirectory);
        outputChooser.setCurrentDirectory(workingDirectory);
    }

    private void setupColorChoosers() {
        background_color.setBackground(terminal.getBackgroundColor());
        font_color.setBackground(terminal.getTextColor());
        background_color_listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Color color = JColorChooser.showDialog(null, "Choose background color", background_color.getBackground());
                background_color.setBackground(color);
                terminal.setBackgroundColor(color);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        };
        background_color.addMouseListener(background_color_listener);
        font_color_listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Color color = JColorChooser.showDialog(null, "Choose font color", font_color.getBackground());
                font_color.setBackground(color);
                terminal.setTextColor(color);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        };
        font_color.addMouseListener(font_color_listener);
    }

    private Options getOptions() {
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

    private int getSleep() {
        return sleep_slider.getValue() * sleep_slider.getValue();
    }

    private void setupSleepSlider() {
        sleep_slider.addChangeListener(
                e -> sleep.setText("Sleep:" + getSleep())
        );
    }

    private void setupRunButton() {
        run_button.addActionListener(e -> {
            Thread t = new Thread(this::run);
            if (!running) t.start();
            else JOptionPane.showMessageDialog(null, "Some program is already running!");
        });
    }

    private void setupInputOutputButtons() {
        select_input_button.addActionListener(e -> {
            int returnVal = inputChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = inputChooser.getSelectedFile();
                input.setText(file.getPath());
                input.setToolTipText(file.getPath());
            }
        });
        select_output_button.addActionListener(e -> {
            int returnVal = outputChooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = outputChooser.getSelectedFile();
                output.setText(file.getPath());
                output.setToolTipText(file.getPath());
            }
        });
    }

    private void setupComboBox() {
        routine.addItem("Run");
        routine.addItem("Debug");
        routine.addActionListener(e -> run_button.setText(Objects.requireNonNull(routine.getSelectedItem()).toString()));
    }

    private void setupFontBoxes() {
        font_name.setPrototypeDisplayValue("XXXXXXXXXXXXX");
        for (String s : monospaced) font_name.addItem(s);
        for (String size : sizes) font_size.addItem(size);
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

    private void chooseMonospacedFonts() {
        var fonts = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        var available = new ArrayList<String>();
        for (String mono : monospaced) {
            if (fonts.contains(mono))
                available.add(mono);
        }
        monospaced = available.toArray(new String[0]);
    }

    private void setSignalHandling() {
        Signal.handle(new Signal("INT"), sig -> {
        });
    }

    private void setControlsEnabled(boolean enable) {
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
        background_color.setEnabled(enable);
        font_color.setEnabled(enable);
        if (enable) {
            font_color.addMouseListener(font_color_listener);
            background_color.addMouseListener(background_color_listener);
        } else {
            font_color.removeMouseListener(font_color_listener);
            background_color.removeMouseListener(background_color_listener);
        }
    }

    public JPanel getMain() {
        return main;
    }

    private Program getProgram(Options options) {
        try {
            return options.binary ?
                    Serializer.deserializeBinary(options.input) : Parser.parse(options.input, true);
        } catch (IOException | SerializationException | ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void serializeProgram(Program program, String output) {
        try {
            Serializer.serializeProgram(program, output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void prepare() {
        terminal.clear();
        setControlsEnabled(false);
    }

    private void stop() {
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
            if (program == null) {
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        main = new JPanel();
        main.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(15, 2, new Insets(8, 0, 2, 8), -1, -1));
        main.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), null, new Dimension(240, -1), 0, false));
        select_input_button = new JButton();
        select_input_button.setText("Select Input File");
        select_input_button.setToolTipText("specifing input file is mandatory.");
        panel1.add(select_input_button, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        run_button = new JButton();
        run_button.setText("Run");
        run_button.setToolTipText("start your program.");
        panel1.add(run_button, new GridConstraints(10, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        unsafeCheckBox = new JCheckBox();
        unsafeCheckBox.setText("Unsafe");
        unsafeCheckBox.setToolTipText("If machine can access memory, that doesn't belong to it.");
        panel1.add(unsafeCheckBox, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        abandonCheckBox = new JCheckBox();
        abandonCheckBox.setText("Abandon");
        abandonCheckBox.setToolTipText("If the program should not run after being loaded.");
        panel1.add(abandonCheckBox, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        binaryCheckBox = new JCheckBox();
        binaryCheckBox.setText("Binary");
        binaryCheckBox.setToolTipText("If input is a binary file.");
        panel1.add(binaryCheckBox, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sleep_slider = new JSlider();
        sleep_slider.setInverted(false);
        sleep_slider.setMajorTickSpacing(10);
        sleep_slider.setMaximum(100);
        sleep_slider.setMinorTickSpacing(5);
        sleep_slider.setPaintLabels(false);
        sleep_slider.setPaintTicks(true);
        sleep_slider.setPaintTrack(true);
        sleep_slider.setToolTipText("slide to setup the number of milliseconds to sleep in between commands.");
        sleep_slider.setValue(0);
        sleep_slider.setValueIsAdjusting(false);
        panel1.add(sleep_slider, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        select_output_button = new JButton();
        select_output_button.setText("Select Output File");
        select_output_button.setToolTipText("specifing output file is optional.");
        panel1.add(select_output_button, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        routine = new JComboBox();
        routine.setToolTipText("setup routine for your program.");
        panel1.add(routine, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        input = new JLabel();
        input.setHorizontalAlignment(10);
        input.setText("");
        input.setToolTipText("specified input file");
        panel1.add(input, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(180, -1), new Dimension(180, -1), new Dimension(180, -1), 0, false));
        output = new JLabel();
        output.setText("");
        output.setToolTipText("specified output file");
        panel1.add(output, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(180, -1), new Dimension(180, -1), new Dimension(180, -1), 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(11, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        sleep = new JLabel();
        sleep.setText("Sleep:0");
        sleep.setToolTipText("amount in milliseconds to sleep.");
        panel1.add(sleep, new GridConstraints(9, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        font_name = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        font_name.setModel(defaultComboBoxModel1);
        panel1.add(font_name, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        font_size = new JComboBox();
        panel1.add(font_size, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(60, -1), new Dimension(60, -1), new Dimension(60, -1), 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("font color");
        panel1.add(label1, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("background color");
        panel1.add(label2, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        font_color = new JPanel();
        font_color.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(font_color, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        font_color.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        background_color = new JPanel();
        background_color.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(background_color, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        background_color.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));
        scroll = new JScrollPane();
        main.add(scroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return main;
    }
}
