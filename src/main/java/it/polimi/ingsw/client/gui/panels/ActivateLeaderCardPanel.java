package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketActivateLeaderCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ActivateLeaderCardPanel extends JPanel implements ActionListener {
    private GUI gui;
    private JButton leaderCard1 = new JButton();
    private JButton leaderCard2 = new JButton();
    private JButton confirm = new JButton("CONFIRM");
    private JButton back = new JButton("BACK");
    ArrayList<JButton> jButtons;
    int id1 = 0;

    public ActivateLeaderCardPanel(GUI gui) {
        jButtons = new ArrayList<>();
        this.gui = gui;
        int x = 0;
        this.setBounds(0,0,1000,1000);
        jButtons.add(leaderCard1);
        jButtons.add(leaderCard2);
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            int y = 0;
            try {
                jButtons.get(i).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            jButtons.get(i).setBounds(x, y, 150, 200);
            jButtons.get(i).addActionListener(this);
            this.add(jButtons.get(i));
            x += 160;

        }
        back.setBounds(50, 300, 100, 30);
        confirm.setBounds(300, 300, 100, 30);
        back.addActionListener(this);
        confirm.addActionListener(this);
        this.add(confirm);
        this.add(back);
        this.setVisible(true);
        this.setLayout(null);    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            BoardPanel boardPanel = new BoardPanel(gui);
            gui.switchPanels(boardPanel);
        }
        if(id1 == 0){
            if (e.getSource() == leaderCard1) {
                id1 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(0).getId();
                leaderCard1.setEnabled(false);
            }
            if (e.getSource() == leaderCard2) {
                id1 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(1).getId();
                leaderCard2.setEnabled(false);
            }
        }
        else{
            if (e.getSource() == confirm) {
                PacketActivateLeaderCard packetActivateLeaderCard = new PacketActivateLeaderCard(id1);
                ObjectMapper mapper = new ObjectMapper();
                String jsonResult = null;
                try {
                    jsonResult = mapper.writeValueAsString(packetActivateLeaderCard);
                } catch (JsonProcessingException jsonProcessingException) {
                    jsonProcessingException.printStackTrace();
                }
                gui.getClient().getSocketClientConnection().sendToServer(jsonResult);
            }
        }
    }
}
