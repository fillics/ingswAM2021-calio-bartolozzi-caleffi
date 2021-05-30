package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumPlayersPanel extends JPanel implements ActionListener {
    private GUI gui;
    private JButton button = new JButton("choose number of players");

    public NumPlayersPanel(GUI gui) {
        this.gui = gui;
        this.setBounds(0,0,1000,1000);
        button.setBounds(0, 0, 100, 30);
        this.add(button);

        button.addActionListener(this);
        this.setVisible(true);
        this.setLayout(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getClient().sendNumPlayers(1);
    }
}
