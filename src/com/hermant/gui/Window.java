package com.hermant.gui;

import javax.swing.*;
import java.awt.*;

public class Window {
    public Window(){
        JFrame frame = new JFrame("gui");
        Form form = new Form();
        frame.setContentPane(form.getMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(1280, 720));
        frame.setMinimumSize(new Dimension(640, 480));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
