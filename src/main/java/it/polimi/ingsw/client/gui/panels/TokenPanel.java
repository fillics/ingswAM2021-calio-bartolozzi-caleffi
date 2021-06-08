package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;

public class TokenPanel extends JPanel {
    private GUI gui;
    private JLabel label;

    public TokenPanel(GUI gui) {
        this.gui = gui;
        label= new JLabel();
    }
}
