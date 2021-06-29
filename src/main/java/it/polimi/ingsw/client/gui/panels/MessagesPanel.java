package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Class that contains the messages to print on the top of the gui frame
 */
public class MessagesPanel extends JPanel {
    private final String messageToPrint;

    private JPanel messageP;
    private final GridBagConstraints c;

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     * @param messageToPrint is the message to print on the panel
     */
    public MessagesPanel(GUI gui, String messageToPrint) {
        this.messageToPrint = messageToPrint;
        c = new GridBagConstraints();
        this.setPreferredSize(new Dimension(gui.getWidth(), 50));
        this.setBackground(new Color(233, 226, 193));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        createMessage();
        c.gridy=0;
        c.gridx=0;
        mainPanel.add(messageP, c);


        mainPanel.setOpaque(false);
        this.add(mainPanel);

    }

    /**
     * Method that inserts the message in the panel
     */
    public void createMessage(){
        messageP = new JPanel();
        messageP.setLayout(new GridBagLayout());
        c.gridy=0;
        c.gridx=0;

        JLabel message = new JLabel(messageToPrint);
        message.setFont(new Font(message.getFont().getName(), message.getFont().getStyle(), 15));

        message.setHorizontalAlignment(JLabel.CENTER);

        messageP.add(message, c);
        messageP.setBackground(new Color(0, 0, 0, 0));
        messageP.setOpaque(true);

    }


}