package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketActivateLeaderCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ActivateLeaderCardPanel extends JPanel implements ActionListener {
    private Image background;
    private GUI gui;
    private JButton leaderCard1 = new JButton();
    private JButton leaderCard2 = new JButton();
    private JButton confirm = new JButton("CONFIRM");
    private JButton back = new JButton("BACK");
    ArrayList<JButton> jButtons;
    int id1 = 0;
    private JPanel cards;
    private JPanel buttons;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, 1000, 500, null);
    }

    public ActivateLeaderCardPanel(GUI gui) {
        InputStream is = getClass().getResourceAsStream("/images/board/devSpaces.jpg");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cards = new JPanel();
        buttons = new JPanel();
        this.setPreferredSize(new Dimension(1000, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        cards.setLayout(new BoxLayout(cards, BoxLayout.X_AXIS));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

        jButtons = new ArrayList<>();
        this.gui = gui;
        jButtons.add(leaderCard1);
        jButtons.add(leaderCard2);
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            try {
                jButtons.get(i).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            jButtons.get(i).addActionListener(this);
            cards.add(jButtons.get(i));
        }
        confirm.addActionListener(this);
        confirm.setPreferredSize(new Dimension(100, 30));
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(100, 30));

        buttons.setPreferredSize(new Dimension(1000, 250));
        cards.setPreferredSize(new Dimension(1000, 250));

        buttons.add(back);
        buttons.add(Box.createRigidArea(new Dimension(100, 200)));
        buttons.add(confirm);

        cards.setOpaque(false);
        buttons.setOpaque(false);

        this.add(cards);
        this.add(buttons);

        this.setVisible(true);
    }

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
