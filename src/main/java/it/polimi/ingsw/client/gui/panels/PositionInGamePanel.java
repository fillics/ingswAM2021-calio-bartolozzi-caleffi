package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;

public class PositionInGamePanel extends JPanel {
    private String positionInGame;
    private JPanel mainPanel, messageP, positionPanel;
    private final GridBagConstraints c;


    public PositionInGamePanel(GUI gui, String positionInGame) {
        this.positionInGame = positionInGame;
        c = new GridBagConstraints();
        this.setPreferredSize(new Dimension(gui.getWidth(), 50));
        this.setBackground(new Color(233, 226, 193));

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        createTurnPosition();
        c.gridy=0;
        c.gridx=0;
        c.insets = new Insets(0,50,0,0);
        mainPanel.add(positionPanel, c);

        mainPanel.setOpaque(false);
        this.add(mainPanel);
        this.setOpaque(false);

    }

    public void createTurnPosition(){
        positionPanel = new JPanel();
        positionPanel.setLayout(new GridBagLayout());
        c.gridy=0;
        c.gridx=0;

        JLabel position = new JLabel(positionInGame);
        position.setHorizontalAlignment(JLabel.CENTER);

        positionPanel.add(position, c);
        positionPanel.setBackground(new Color(0, 0, 0, 0));
        positionPanel.setOpaque(true);
    }


    public void setPositionInGame(String positionInGame) {
        this.positionInGame = positionInGame;
    }
}