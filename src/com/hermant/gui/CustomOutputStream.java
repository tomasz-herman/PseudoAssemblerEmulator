package com.hermant.gui;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.OutputStream;

class CustomOutputStream extends OutputStream {

    private StringBuilder line = new StringBuilder();
    private final StringBuilder buffer = new StringBuilder();

    private int lines = 0;
    private int bufferedLines = 0;

    private static final int MAX_LINES = 16384;
    private static final int TRUNK = 4096;

    private final int FRAMES_PER_SECOND = 25;
    private final long SKIP_TICKS = 1000000000 / FRAMES_PER_SECOND;

    CustomOutputStream(JTextArea area) {
        Thread updater = getUpdaterThread(area);
        updater.start();
    }

    private Thread getUpdaterThread(JTextArea area){
        return new Thread(() -> {
            long lastUpdate = System.nanoTime();
            while(true){
                if(lines > MAX_LINES) {
                    try {
                        area.getDocument().remove(0, area.getLineEndOffset(TRUNK - 1));
                        lines -= TRUNK;
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
                long now = System.nanoTime();
                long elapsed = now - lastUpdate;
                if(elapsed > SKIP_TICKS || bufferedLines > MAX_LINES / 2){
                    lastUpdate = now;
                    synchronized (buffer){
                        if(buffer.capacity() != 0){
                            try {
                                area.append(buffer.toString());
                                buffer.delete(0, buffer.length());
                                lines += bufferedLines;
                                bufferedLines = 0;
                            } catch (Error ignored) {}
                        }
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignored) { }
            }
        });
    }

    void reset(){
        synchronized (buffer){
            buffer.delete(0, buffer.length());
            bufferedLines = 0;
            line.setLength(0);
            lines = 0;
        }
    }

    @Override
    public void write(int b) {
        line.append((char)b);
        if(b=='\n'){
            synchronized (buffer){
                bufferedLines++;
                buffer.append(line.toString());
                line.setLength(0);
            }
        }
    }

    @Override
    public void flush() {
        synchronized (buffer){
            String s = line.toString();
            buffer.append(s);
            if(s.contains("\n"))bufferedLines++;
            line.setLength(0);
        }
    }
}
