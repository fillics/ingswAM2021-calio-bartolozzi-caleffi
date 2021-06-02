package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;

public class MessagesPanel extends JPanel {
    private GUI gui;
    private JLabel message;

    public MessagesPanel(GUI gui) {
        this.gui = gui;
        createMessage();
        this.add(message);

    }

    public void createMessage(){
        message = new JLabel("messaggio dal server");

    }

}
