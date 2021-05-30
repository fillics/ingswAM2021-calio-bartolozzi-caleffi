package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderCardPanel extends JPanel implements ActionListener {
    private GUI gui;

    public LeaderCardPanel(GUI gui) {
        this.gui = gui;
        int x = 0;
        this.setBounds(0,0,1000,1000);
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            int y = 0;
            JButton leaderCard = new JButton("carta" + (i+1));
            leaderCard.setBounds(x, y, 100, 30);
            leaderCard.addActionListener(this);
            this.add(leaderCard);
            x += 110;

        }
        this.setVisible(true);
        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
