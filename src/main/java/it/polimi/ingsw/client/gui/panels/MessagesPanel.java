package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Class that contains the messages to print on the top of the gui frame
 */
public class MessagesPanel extends JPanel {
    private final String messageToPrint;

    private JPanel mainPanel, messageP;
    private final GridBagConstraints c;


    public MessagesPanel(GUI gui, String messageToPrint) {
        this.messageToPrint = messageToPrint;
        c = new GridBagConstraints();
        this.setPreferredSize(new Dimension(gui.getWidth(), 50));
        this.setBackground(new Color(233, 226, 193));

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        createMessage();
        c.gridy=0;
        c.gridx=0;
        mainPanel.add(messageP, c);


        mainPanel.setOpaque(false);
        this.add(mainPanel);

    }

    public void createMessage(){
        messageP = new JPanel();
        messageP.setLayout(new GridBagLayout());
        c.gridy=0;
        c.gridx=0;

        JLabel message = new JLabel(messageToPrint);
        message.setHorizontalAlignment(JLabel.CENTER);

        messageP.add(message, c);
        messageP.setBackground(new Color(0, 0, 0, 0));
        messageP.setOpaque(true);

    }


}