package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DevGridBuyCardPanel extends JPanel implements ActionListener {

    private GUI gui;
    private GridBagConstraints c;
    private JButton devCard1, devCard2, devCard3, devCard4, devCard5, devCard6, devCard7, devCard8, devCard9, devCard10, devCard11, devCard12;

    private ClientModelView clientModelView;

    private int idCard;


    public DevGridBuyCardPanel(GUI gui) {
        this.gui = gui;
        c = new GridBagConstraints();

        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        devCard1 = new JButton();
        devCard2 = new JButton();
        devCard3 = new JButton();
        devCard4 = new JButton();
        devCard5 = new JButton();
        devCard6 = new JButton();
        devCard7 = new JButton();
        devCard8 = new JButton();
        devCard9 = new JButton();
        devCard10 = new JButton();
        devCard11 = new JButton();
        devCard12 = new JButton();
        try {
            setButtons(this, 125, 189, 5,5,5,5);
        } catch (IOException ignored) {}

        this.setOpaque(false);
    }

    public void setButtons(JPanel panel, int width, int height, int top, int left, int bottom, int right) throws IOException {
        clientModelView = gui.getClient().getClientModelView();
        c.insets = new Insets(top,left,bottom,right);
        devCard1.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard1.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        devCard1.setOpaque(false);
        panel.add(devCard1,c);

        devCard2.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard2.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        devCard2.setOpaque(false);
        panel.add(devCard2,c);

        devCard3.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard3.addActionListener(this);
        c.gridx = 2;
        c.gridy = 0;
        devCard3.setOpaque(false);
        panel.add(devCard3,c);

        devCard4.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard4.addActionListener(this);
        c.gridx = 3;
        c.gridy = 0;
        devCard4.setOpaque(false);
        panel.add(devCard4,c);

        devCard5.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard5.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        devCard5.setOpaque(false);
        panel.add(devCard5,c);

        devCard6.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard6.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        devCard6.setOpaque(false);
        panel.add(devCard6,c);

        devCard7.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard7.addActionListener(this);
        c.gridx = 2;
        c.gridy = 1;
        devCard7.setOpaque(false);
        panel.add(devCard7,c);

        devCard8.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard8.addActionListener(this);
        c.gridx = 3;
        c.gridy = 1;
        devCard8.setOpaque(false);
        panel.add(devCard8,c);

        devCard9.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard9.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        devCard9.setOpaque(false);
        panel.add(devCard9,c);

        devCard10.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard10.addActionListener(this);
        c.gridx = 1;
        c.gridy = 2;
        devCard10.setOpaque(false);
        panel.add(devCard10,c);

        devCard11.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard11.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        devCard11.setOpaque(false);
        panel.add(devCard11,c);

        devCard12.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard12.addActionListener(this);
        c.gridx = 3;
        c.gridy = 2;
        devCard12.setOpaque(false);
        panel.add(devCard12,c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == devCard1){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getId();
        }
        if(e.getSource() == devCard2){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getId();
        }
        if(e.getSource() == devCard3){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getId();
        }
        if(e.getSource() == devCard4){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getId();
        }
        if(e.getSource() == devCard5){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getId();
        }
        if(e.getSource() == devCard6){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getId();
        }
        if(e.getSource() == devCard7){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getId();
        }
        if(e.getSource() == devCard8){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getId();
        }
        if(e.getSource() == devCard9){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getId();
        }
        if(e.getSource() == devCard10){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getId();
        }
        if(e.getSource() == devCard11){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getId();
        }
        if(e.getSource() == devCard12){
            idCard = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getId();
        }
    }

    public int getIdCard() {
        return idCard;
    }
}
