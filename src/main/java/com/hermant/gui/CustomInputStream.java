package com.hermant.gui;

import sun.misc.Signal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;


/**
 * Class that may be added as key listener to some {@link javax.swing.JComponent JComponent}
 * and will convert input taken from it into stream that might be read later by calling
 * {@link CustomInputStream#read()}. It's main purpose is to replace {@link System#in} with
 * this class, so gui application will mimic behaviour of cli interface.
 */
class CustomInputStream extends InputStream implements KeyListener {

    private String str = "";
    private StringBuilder buffer = new StringBuilder();
    private int pos = 0;

    /**
     * The stream is reset to initial state. After this call no bytes are available
     * to read.
     */
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
            buffer.append('\n');
            str += buffer.toString();
            System.out.print(buffer.toString());
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