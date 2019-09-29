package com.hermant.gui;

import com.hermant.machine.Machine;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.Serializer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
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
    private JPanel secondary;
    private JComboBox<Combo> routine;
    private JLabel input;
    private JLabel output;
    private JLabel sleep;

    private JFileChooser inputChooser;
    private JFileChooser outputChooser;
    private JScrollPane scroll;

    private volatile boolean running = false;

    Form() {
        inputChooser = new JFileChooser();
        outputChooser = new JFileChooser();
        select_input.addActionListener(e -> {
            int returnVal = inputChooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = inputChooser.getSelectedFile();
                input.setText(file.getPath());
            }
        });
        selectOutputFileButton.addActionListener(e -> {
            int returnVal = outputChooser.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = outputChooser.getSelectedFile();
                output.setText(file.getPath());
            }
        });
        sleep_slider.addChangeListener(
            e -> sleep.setText("Sleep:" + sleep_slider.getValue() * 10)
        );
        JTextArea terminal = new JTextArea();
        disableArrowKeys(terminal.getInputMap());
        terminal.setFont(new Font("Monospace", Font.BOLD, 18));
        scroll.setViewportView(terminal);
        routine.addItem(new Combo(0, "Run"));
        routine.addItem(new Combo(1, "Debug"));
        routine.addActionListener(e -> run_button.setText(Objects.requireNonNull(routine.getSelectedItem()).toString()));

        run_button.addActionListener(e -> {
            Thread t = new Thread(() -> {
                running = true;
                terminal.setText("");
                run_button.setEnabled(false);
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
                run_button.setEnabled(true);
                running = false;
            });
            if(!running)
                t.start();
            else JOptionPane.showMessageDialog(null, "Some program is already running!");
        });
        TextFieldStreamer streamer = new TextFieldStreamer();
        terminal.addKeyListener(streamer);
        System.setIn(streamer);
        PrintStream printStream = new PrintStream(new CustomOutputStream(terminal));
        System.setOut(printStream);
        terminal.getDocument().addDocumentListener(new LimitLinesDocumentListener(256));
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

        CustomOutputStream(JTextArea area) {
            this.area = area;
        }

        @Override
        public void write(int b) {
            line.append((char)b);
            if(b==10){
                area.append(line.toString());
                area.setCaretPosition(area.getDocument().getLength());
                line = new StringBuilder();
            }
        }
    }

    static class TextFieldStreamer extends InputStream implements KeyListener {

        private String str = "";
        private StringBuilder buffer = new StringBuilder();
        private int pos = 0;

        TextFieldStreamer() {
        }

        @Override
        public int available() {
            return str.length() - pos;
        }

        @Override
        public int read() {
            //test if the available input has reached its end
            //and the EOS should be returned
            if(str != null && pos == str.length()){
                str =null;
                //this is supposed to return -1 on "end of stream"
                //but I'm having a hard time locating the constant
                return java.io.StreamTokenizer.TT_EOF;
            }
            //no input available, block until more is available because that's
            //the behavior specified in the Javadocs
            while (str == null || pos >= str.length()) {
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
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                System.err.println(buffer);
                pos = 0;
                buffer.append('\n');
                str = buffer.toString();
                buffer = new StringBuilder();
                synchronized (this) {
                    this.notifyAll();
                }
            } else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if(buffer.length() > 0)
                    buffer.deleteCharAt(buffer.length() - 1);
            } else {
                char c = e.getKeyChar();
                if(c >= 32 && c < 127)
                    buffer.append(c);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public static class LimitLinesDocumentListener implements DocumentListener
    {
        private int maximumLines;
        private boolean isRemoveFromStart;

        /*
         *  Specify the number of lines to be stored in the Document.
         *  Extra lines will be removed from the start of the Document.
         */
        LimitLinesDocumentListener(int maximumLines)
        {
            this(maximumLines, true);
        }

        /*
         *  Specify the number of lines to be stored in the Document.
         *  Extra lines will be removed from the start or end of the Document,
         *  depending on the boolean value specified.
         */
        LimitLinesDocumentListener(int maximumLines, boolean isRemoveFromStart)
        {
            setLimitLines(maximumLines);
            this.isRemoveFromStart = isRemoveFromStart;
        }

        /*
         *  Set the maximum number of lines to be stored in the Document
         */
        void setLimitLines(int maximumLines)
        {
            if (maximumLines < 1)
            {
                String message = "Maximum lines must be greater than 0";
                throw new IllegalArgumentException(message);
            }

            this.maximumLines = maximumLines;
        }

        //  Handle insertion of new text into the Document

        public void insertUpdate(final DocumentEvent e)
        {
            //  Changes to the Document can not be done within the listener
            //  so we need to add the processing to the end of the EDT

            SwingUtilities.invokeLater(() -> removeLines(e));
        }

        public void removeUpdate(DocumentEvent e) {}
        public void changedUpdate(DocumentEvent e) {}

        /*
         *  Remove lines from the Document when necessary
         */
        private void removeLines(DocumentEvent e)
        {
            //  The root Element of the Document will tell us the total number
            //  of line in the Document.

            Document document = e.getDocument();
            Element root = document.getDefaultRootElement();

            while (root.getElementCount() > maximumLines)
            {
                if (isRemoveFromStart)
                {
                    removeFromStart(document, root);
                }
                else
                {
                    removeFromEnd(document, root);
                }
            }
        }

        /*
         *  Remove lines from the start of the Document
         */
        private void removeFromStart(Document document, Element root)
        {
            Element line = root.getElement(0);
            int end = line.getEndOffset();

            try
            {
                document.remove(0, end);
            }
            catch(BadLocationException e)
            {
                e.printStackTrace();
            }
        }

        /*
         *  Remove lines from the end of the Document
         */
        private void removeFromEnd(Document document, Element root)
        {
            //  We use start minus 1 to make sure we remove the newline
            //  character of the previous line

            Element line = root.getElement(root.getElementCount() - 1);
            int start = line.getStartOffset();
            int end = line.getEndOffset();

            try
            {
                document.remove(start - 1, end - start);
            }
            catch(BadLocationException e)
            {
                e.printStackTrace();
            }
        }
    }
}
