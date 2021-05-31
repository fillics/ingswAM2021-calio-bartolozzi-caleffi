package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketChooseLeaderCardToRemove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RemoveLeaderCardPanel extends JPanel implements ActionListener {
    private GUI gui;
    private JButton leaderCard1 = new JButton();
    private JButton leaderCard2 = new JButton();
    private JButton leaderCard3 = new JButton();
    private JButton leaderCard4 = new JButton();
    private JButton confirm = new JButton("CONFIRM");

    ArrayList<JButton> jButtons;
    int id1 = 0;
    int id2 = 0;

    public RemoveLeaderCardPanel(GUI gui) {
        jButtons = new ArrayList<>();
        this.gui = gui;
        int x = 0;
        this.setBounds(0,0,1000,1000);
        jButtons.add(leaderCard1);
        jButtons.add(leaderCard2);
        jButtons.add(leaderCard3);
        jButtons.add(leaderCard4);
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
        confirm.setBounds(50, 300, 100, 30);
        confirm.addActionListener(this);
        this.add(confirm);
        this.setVisible(true);
        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(id1 == 0 || id2 == 0){
            if (e.getSource() == leaderCard1) {
                if (id1 == 0) {
                    id1 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(0).getId();
                } else {
                    id2 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(0).getId();
                }
                leaderCard1.setEnabled(false);
            }
            if (e.getSource() == leaderCard2) {
                if (id1 == 0) {
                    id1 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(1).getId();
                } else {
                    id2 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(1).getId();
                }
                leaderCard2.setEnabled(false);
            }
            if (e.getSource() == leaderCard3) {
                if (id1 == 0) {
                    id1 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(2).getId();
                } else {
                    id2 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(2).getId();
                }
                leaderCard3.setEnabled(false);
            }
            if (e.getSource() == leaderCard4) {
                if (id1 == 0) {
                    id1 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(3).getId();
                } else {
                    id2 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(3).getId();
                }
                leaderCard4.setEnabled(false);
            }
        }
        else {
            if (e.getSource() == confirm) {
                PacketChooseLeaderCardToRemove packetChooseLeaderCardToRemove = new PacketChooseLeaderCardToRemove(id1, id2);
                ObjectMapper mapper = new ObjectMapper();
                String jsonResult = null;
                try {
                    jsonResult = mapper.writeValueAsString(packetChooseLeaderCardToRemove);
                } catch (JsonProcessingException jsonProcessingException) {
                    jsonProcessingException.printStackTrace();
                }
                gui.getClient().getSocketClientConnection().sendToServer(jsonResult);
            }
        }
    }
}
