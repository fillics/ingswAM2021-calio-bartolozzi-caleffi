package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;

public class MessagesPanel extends JPanel {
    private GUI gui;
    private String messageToPrint;
    private JLabel message;
    private JPanel messageP;
    private final GridBagConstraints c;


    public MessagesPanel(GUI gui, String messageToPrint) {
        this.gui = gui;
        this.messageToPrint = messageToPrint;
        c = new GridBagConstraints();
        this.setPreferredSize(new Dimension(gui.getWidth(), 50));
        this.setBackground(new Color(233, 226, 193));

        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        createMessage();
        this.add(messageP);


    }

    public void createMessage(){
        messageP = new JPanel();
        messageP.setLayout(new GridBagLayout());
        c.gridy=0;
        c.gridx=0;

        message = new JLabel(messageToPrint);
        message.setHorizontalAlignment(JLabel.CENTER);

        messageP.add(message, c);
        messageP.setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(true);

    }

}