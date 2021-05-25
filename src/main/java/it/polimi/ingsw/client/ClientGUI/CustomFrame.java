package it.polimi.ingsw.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class CustomFrame extends JFrame {
    public void paint(Graphics g) {
        g.drawString("Hello", 200, 50);
        int x = 30;
        int y = 100;
        int rectwidth = 50;
        int rectheight = 100;
        g.drawRect(x, y, rectwidth, rectheight);
    }
}
