package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketChooseLeaderCardToRemove;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RemoveLeaderCardPanel extends JPanel implements ActionListener {
    private Image background;
    private GUI gui;
    private final GridBagConstraints c;

    private JButton leaderCard1, leaderCard2, leaderCard3, leaderCard4;
    private JButton confirm;
    private JPanel cards, buttons;

    private ArrayList<JButton> jButtons;
    private int id1 = 0;
    private int id2 = 0;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getWidth(), gui.getHeight()-50, null);
    }

    public RemoveLeaderCardPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        c.gridy=0;
        c.gridx=0;
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0,0,0,0));
        this.add(emptyPanel, c);

        createCards();
        c.gridx=0;
        c.gridy=1;
        this.add(cards, c);

        createButtons();
        c.gridx=0;
        c.gridy=2;
        this.add(buttons, c);
        this.setVisible(true);
    }

    public void createCards(){
        cards = new JPanel();
        cards.setLayout(new GridBagLayout());
        c.insets = new Insets(0,10,0,10);
        jButtons = new ArrayList<>();

        leaderCard1 = new JButton();
        leaderCard2 = new JButton();
        leaderCard3 = new JButton();
        leaderCard4 = new JButton();

        jButtons.add(leaderCard1);
        jButtons.add(leaderCard2);
        jButtons.add(leaderCard3);
        jButtons.add(leaderCard4);
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            try {
                jButtons.get(i).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getPath()).readAllBytes()).getImage().getScaledInstance(200, 302, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            jButtons.get(i).addActionListener(this);
            c.gridx=i;
            c.gridy=0;

            int finalI = i;
            jButtons.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jButtons.get(finalI).setBackground(new Color(51, 180, 76));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jButtons.get(finalI).setBackground(UIManager.getColor("control"));
                }
            });

            cards.add(jButtons.get(i), c);
        }

        cards.setBackground(new Color(0,0,0,0));
        cards.setOpaque(true);

    }

    public void createButtons(){
        buttons = new JPanel();

        confirm = new JButton("CONFIRM");
        buttons.setLayout(new GridBagLayout());
        c.gridy=0;
        c.gridx=0;
        c.ipady=50;

        confirm.addActionListener(this);
        confirm.setPreferredSize(new Dimension(100, 30));
        buttons.add(confirm, c);

        buttons.setOpaque(false);


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
