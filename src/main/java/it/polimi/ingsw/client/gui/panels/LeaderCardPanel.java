package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.model.cards.leadercards.LeaderCardType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class LeaderCardPanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;
    private int id;
    private int position;



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0,159, 240, null);
    }

    public LeaderCardPanel(GUI gui, int id) {
        this.setPreferredSize(new Dimension(159, 240));
        this.id = id;
        this.gui = gui;
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(id == gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId()){
                position = i;
            }
        }
        InputStream is = getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getPath());
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel leadercard = new JPanel();
        leadercard.setPreferredSize(new Dimension(159, 240));
        leadercard.setLayout(new BoxLayout(leadercard, BoxLayout.Y_AXIS));
        JLabel isactive = new JLabel();
        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getStrategy().isActive()){
            try {
                isactive.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/active.png").readAllBytes()).getImage().getScaledInstance(30,30, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                isactive.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/notactive.png").readAllBytes()).getImage().getScaledInstance(30,30, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.DISCOUNT) ||
                gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.PRODUCTION_POWER) ||
                        gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.WHITE_MARBLE)){
            JPanel panel1 = new JPanel();
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
            panel1.setPreferredSize(new Dimension(159, 30));
            panel1.add(Box.createRigidArea(new Dimension(129, 30)));
            panel1.add(isactive);
            leadercard.add(panel1);
            panel1.setOpaque(false);
        }
        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.EXTRA_DEPOSIT)){

        }
        this.add(leadercard);
        leadercard.setOpaque(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
