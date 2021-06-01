package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class WarehousePanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;

    public WarehousePanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/board/deposits.jpg");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, 720, 480, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
