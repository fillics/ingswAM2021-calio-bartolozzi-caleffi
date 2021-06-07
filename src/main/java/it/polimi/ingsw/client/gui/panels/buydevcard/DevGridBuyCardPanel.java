package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.DevCardPanel;
import it.polimi.ingsw.client.gui.panels.LeaderCardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class DevGridBuyCardPanel extends JPanel implements ActionListener {

    private BuyDevCardPanel buyDevCardPanel;
    private GridBagConstraints c;
    private JButton devCard1, devCard2, devCard3, devCard4, devCard5, devCard6, devCard7, devCard8, devCard9, devCard10, devCard11, devCard12;
    private String path1,path2,path3,path4,path5,path6,path7,path8,path9,path10,path11,path12;
    private int width, height;

    private ClientModelView clientModelView;

    private int idCard;


    public DevGridBuyCardPanel(BuyDevCardPanel buyDevCardPanel) {
        this.buyDevCardPanel = buyDevCardPanel;
        c = new GridBagConstraints();
        width = 129*3;
        height= 180*3;

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
            setButtons(this, 125, 189, 0,0,0,0);
        } catch (IOException ignored) {}

        this.setOpaque(false);
    }

    public void setButtons(JPanel panel, int width, int height, int top, int left, int bottom, int right) throws IOException {
        clientModelView = buyDevCardPanel.getGui().getClient().getClientModelView();
        c.insets = new Insets(top,left,bottom,right);

        path1 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath();
        devCard1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path1)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard1.addActionListener(this);
        changeBackground(devCard1);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(devCard1,c);

        path2 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath();
        devCard2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path2)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard2.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        changeBackground(devCard2);
        panel.add(devCard2,c);

        path3 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath();
        devCard3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path3)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard3.addActionListener(this);
        c.gridx = 2;
        c.gridy = 0;
        changeBackground(devCard3);
        panel.add(devCard3,c);

        path4 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath();
        devCard4.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path4)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard4.addActionListener(this);
        c.gridx = 3;
        c.gridy = 0;
        changeBackground(devCard4);
        panel.add(devCard4,c);

        path5 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath();
        devCard5.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path5)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard5.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        changeBackground(devCard5);
        panel.add(devCard5,c);

        path6 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath();
        devCard6.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path6)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard6.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        changeBackground(devCard6);
        panel.add(devCard6,c);

        path7 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath();
        devCard7.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path7)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard7.addActionListener(this);
        c.gridx = 2;
        c.gridy = 1;
        changeBackground(devCard7);
        panel.add(devCard7,c);

        path8 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath();
        devCard8.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path8)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard8.addActionListener(this);
        c.gridx = 3;
        c.gridy = 1;
        changeBackground(devCard8);
        panel.add(devCard8,c);

        path9 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath();
        devCard9.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path9)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard9.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        changeBackground(devCard9);
        panel.add(devCard9,c);

        path10 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath();
        devCard10.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path10)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard10.addActionListener(this);
        c.gridx = 1;
        c.gridy = 2;
        changeBackground(devCard10);
        panel.add(devCard10,c);

        path11 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath();
        devCard11.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path11)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard11.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        changeBackground(devCard11);
        panel.add(devCard11,c);

        path12 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath();
        devCard12.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path12)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard12.addActionListener(this);
        c.gridx = 3;
        c.gridy = 2;
        changeBackground(devCard12);
        panel.add(devCard12,c);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == devCard1){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path1,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getId()));
        }
        if(e.getSource() == devCard2){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path2,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getId()));
        }
        if(e.getSource() == devCard3){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path3,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getId()));
        }
        if(e.getSource() == devCard4){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path4,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getId()));
        }
        if(e.getSource() == devCard5){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path5,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getId()));
        }
        if(e.getSource() == devCard6){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path6,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getId()));
        }
        if(e.getSource() == devCard7){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path7,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getId()));
        }
        if(e.getSource() == devCard8){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path8,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getId()));
        }
        if(e.getSource() == devCard9){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path9,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getId()));
        }
        if(e.getSource() == devCard10){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path10,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getId()));
        }
        if(e.getSource() == devCard11){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path11,  width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getId()));
        }
        if(e.getSource() == devCard12){
            buyDevCardPanel.getGui().switchPanels(new DevCardPanel(this, path12, width, height, clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getId()));

        }
    }


    public void changeBackground(JButton button){
        button.setOpaque(false);
        button.setBackground(new Color(0,0,0,0));
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public BuyDevCardPanel getBuyDevCardPanel() {
        return buyDevCardPanel;
    }
}
