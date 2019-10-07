package com.hermant.gui;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.OutputStream;

class CustomOutputStream extends OutputStream {

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
