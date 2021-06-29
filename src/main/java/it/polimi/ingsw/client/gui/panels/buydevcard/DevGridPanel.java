package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class DevGridPanel extends JPanel implements ActionListener {

    private BuyDevCardPanel buyDevCardPanel;
    private GridBagConstraints c;
    private final JButton devCard1;
    private final JButton devCard2;
    private final JButton devCard3;
    private final JButton devCard4;
    private final JButton devCard5;
    private final JButton devCard6;
    private final JButton devCard7;
    private final JButton devCard8;
    private final JButton devCard9;
    private final JButton devCard10;
    private final JButton devCard11;
    private final JButton devCard12;
    private String path1,path2,path3,path4,path5,path6,path7,path8,path9,path10,path11,path12;
    private final int width;
    private final int height;

    private ClientModelView clientModelView;

    private int idCard;

    public void setBuyDevCardPanel(BuyDevCardPanel buyDevCardPanel) {
        this.buyDevCardPanel = buyDevCardPanel;
    }

    public DevGridPanel(BuyDevCardPanel buyDevCardPanel) {
        this.buyDevCardPanel = buyDevCardPanel;
        c = new GridBagConstraints();
        width = 129*3;
        height= 180*3;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
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
            setButtons(mainPanel, 125, 189, 0,0,0,0);
        } catch (IOException ignored) {}

        mainPanel.setOpaque(false);
        this.setOpaque(false);
        this.add(mainPanel);
    }

    public void setButtons(JPanel panel, int width, int height, int top, int left, int bottom, int right) throws IOException {
        clientModelView = buyDevCardPanel.getGui().getClient().getClientModelView();
        c.insets = new Insets(top,left,bottom,right);

        try{
            path1 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath();
        }catch (NullPointerException e){
            path1 = "/images/back/DevCardBackY3.png";
            disableButton(devCard1, path1, width, height);
        }
        c.gridx = 0;
        c.gridy = 0;
        setButton(devCard1, c, panel, width, height, path1);


        try{
            path2 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath();
        }catch (NullPointerException e){
            path2 = "/images/back/DevCardBackG3.png";
            disableButton(devCard2, path2, width, height);
        }
        c.gridx = 1;
        c.gridy = 0;
        setButton(devCard2, c, panel, width, height, path2);


        try{
            path3 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath();
        }catch (NullPointerException e){
            path3 = "/images/back/DevCardBackP3.png";
            disableButton(devCard3, path3, width, height);
        }
        c.gridx = 2;
        c.gridy = 0;
        setButton(devCard3, c, panel, width, height, path3);


        try{
            path4 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath();
        }catch (NullPointerException e){
            path4 = "/images/back/DevCardBackB3.png";
            disableButton(devCard4, path4, width, height);
        }
        c.gridx = 3;
        c.gridy = 0;
        setButton(devCard4, c, panel, width, height, path4);


        try{
            path5 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath();
        }catch (NullPointerException e){
            path5 = "/images/back/DevCardBackY2.png";
            disableButton(devCard5, path5, width, height);
        }
        c.gridx = 0;
        c.gridy = 1;
        setButton(devCard5, c, panel, width, height, path5);



        try{
            path6 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath();
        }catch (NullPointerException e){
            path6 = "/images/back/DevCardBackG2.png";
            disableButton(devCard6, path6, width, height);
        }
        c.gridx = 1;
        c.gridy = 1;
        setButton(devCard6, c, panel, width, height, path6);


        try{
            path7 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath();
        }catch (NullPointerException e){
            path7 = "/images/back/DevCardBackP2.png";
            disableButton(devCard7, path7, width, height);
        }
        c.gridx = 2;
        c.gridy = 1;
        setButton(devCard7, c, panel, width, height, path7);

        try{
            path8 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath();
        }catch(NullPointerException e){
            path8 = "/images/back/DevCardBackB2.png";
            disableButton(devCard8, path8, width, height);
        }
        c.gridx = 3;
        c.gridy = 1;
        setButton(devCard8, c, panel, width, height, path8);


        try{
            path9 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath();
        }catch (NullPointerException e){
            path9 = "/images/back/DevCardBackY1.png";
            disableButton(devCard9, path9, width, height);
        }
        c.gridx = 0;
        c.gridy = 2;
        setButton(devCard9, c, panel, width, height, path9);


        try{
            path10 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath();
        }catch(NullPointerException e){
            path10 = "/images/back/DevCardBackG1.png";
            disableButton(devCard10, path10, width, height);
        }
        c.gridx = 1;
        c.gridy = 2;
        setButton(devCard10, c, panel, width, height, path10);


        try{
            path11 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath();
        }catch (NullPointerException e){
            path11 = "/images/back/DevCardBackP1.png";
            disableButton(devCard11, path11, width, height);
        }
        c.gridx = 2;
        c.gridy = 2;
        setButton(devCard11, c, panel, width, height, path11);

        try{
            path12 = clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath();
        }catch (NullPointerException e){
            path12 = "/images/back/DevCardBackB1.png";
            disableButton(devCard12, path12, width, height);
        }
        c.gridx = 3;
        c.gridy = 2;
        setButton(devCard12, c, panel, width, height, path12);

    }

    public void setButton(JButton button, GridBagConstraints c, JPanel panel, int width, int height, String path){
        try {
            button.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException ignored) { }
        button.addActionListener(this);
        panel.add(button,c);
        button.setOpaque(false);
        button.setBackground(new Color(0,0,0,0));
    }

    public void disableButton(JButton button, String path, int width, int height){
        button.setEnabled(false);
        try {
            button.setDisabledIcon(new ImageIcon(new ImageIcon((GUI.class.getResourceAsStream(path)).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException ignored) {}


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


    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
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
}
