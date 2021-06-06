package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * quando clicco la carta nel development grid entro in questo pannello che me la fa vedere più grossa
 */
public class DevelopmentCardPanel extends JPanel {

    private Image background;
    private final GUI gui;
    private int id;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height, null);

    }

    public DevelopmentCardPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/backgroundGame2.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException ignored) {
        }




    }
}
