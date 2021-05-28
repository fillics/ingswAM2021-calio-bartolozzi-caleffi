package it.polimi.ingsw.client.ClientGUI;

import it.polimi.ingsw.client.ClientModelView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DevGridPanel {
    JPanel gridpanel;
    GridBagConstraints c;
    ArrayList<JButton> develpmentCards;
    ClientModelView clientModelView;

    public DevGridPanel(ClientModelView clientModelView) {
        gridpanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        for(int i = 0; i< 12; i++){
            develpmentCards.add(new JButton());
        }
        try {
            setButtons();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.clientModelView = clientModelView;
    }

    public void setButtons() throws IOException {
        develpmentCards.get(0).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 0;
        gridpanel.add(develpmentCards.get(0),c);
        develpmentCards.get(1).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 0;
        gridpanel.add(develpmentCards.get(1),c);
        develpmentCards.get(2).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 0;
        gridpanel.add(develpmentCards.get(2),c);
        develpmentCards.get(3).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 0;
        gridpanel.add(develpmentCards.get(3),c);
        develpmentCards.get(4).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 1;
        gridpanel.add(develpmentCards.get(4),c);
        develpmentCards.get(5).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 1;
        gridpanel.add(develpmentCards.get(5),c);
        develpmentCards.get(6).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 1;
        gridpanel.add(develpmentCards.get(6),c);
        develpmentCards.get(7).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 1;
        gridpanel.add(develpmentCards.get(7),c);
        develpmentCards.get(8).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 0;
        c.gridy = 2;
        gridpanel.add(develpmentCards.get(8),c);
        develpmentCards.get(9).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 1;
        c.gridy = 2;
        gridpanel.add(develpmentCards.get(9),c);
        develpmentCards.get(10).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 2;
        c.gridy = 2;
        gridpanel.add(develpmentCards.get(10),c);
        develpmentCards.get(11).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath()).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
        c.gridx = 3;
        c.gridy = 2;
        gridpanel.add(develpmentCards.get(11),c);
    }





}
