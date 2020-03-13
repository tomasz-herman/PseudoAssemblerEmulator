package com.hermant.gui;

import com.hermant.cli.Version;

import javax.swing.*;
import java.awt.*;

public class Window {
    public Window(){
        JFrame frame = new JFrame(Version.getFull());
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
