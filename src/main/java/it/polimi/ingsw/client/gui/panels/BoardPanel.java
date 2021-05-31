package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardPanel extends JPanel implements ActionListener {
    private GUI gui;
    private JButton discardLeaderCard = new JButton("DISCARD LEADER CARD");
    private JButton activateLeaderCard = new JButton("ACTIVATE LEADER CARD");
    private JButton buyDevCard = new JButton("BUY DEV CARD");
    private JButton chooseDiscount = new JButton("CHOOSE DISCOUNT");
    private JButton useProdPower = new JButton("USE PRODUCTION POWER");
    private JButton moveResource = new JButton("MOVE RESOURCE");
    private JButton placeResource = new JButton("PLACE RESORCE");
    private JButton takeResourceFromMarket = new JButton("TAKE RESOURCE FROM MARKET");
    private JButton endTurn = new JButton("END YOUR TURN");

    public BoardPanel(GUI gui) {
        this.gui = gui;
        this.setBounds(0,0,1500,1000);

        setAllBounds();
        addAll();
        addActionEvent();

        this.setVisible(true);
        this.setLayout(null);
    }

    public void addActionEvent(){
        discardLeaderCard.addActionListener(this);
        activateLeaderCard.addActionListener(this);
        buyDevCard.addActionListener(this);
        chooseDiscount.addActionListener(this);
        useProdPower.addActionListener(this);
        moveResource.addActionListener(this);
        placeResource.addActionListener(this);
        takeResourceFromMarket.addActionListener(this);
        endTurn.addActionListener(this);
    }

    public void addAll(){
        this.add(discardLeaderCard);
        this.add(activateLeaderCard);
        this.add(buyDevCard);
        this.add(chooseDiscount);
        this.add(useProdPower);
        this.add(moveResource);
        this.add(placeResource);
        this.add(takeResourceFromMarket);
        this.add(endTurn);
    }

    public void setAllBounds(){
        discardLeaderCard.setBounds(0,0, 150, 30);
        activateLeaderCard.setBounds(160, 0, 150, 30);
        buyDevCard.setBounds(320, 0, 150, 30);
        chooseDiscount.setBounds(480, 0, 150, 30);
        useProdPower.setBounds(640, 0, 150, 30);
        moveResource.setBounds(800, 0, 150, 30);
        placeResource.setBounds(960, 0, 150, 30);
        takeResourceFromMarket.setBounds(1120, 0, 150, 30);
        endTurn.setBounds(1280, 0, 150, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == activateLeaderCard){
            ActivateLeaderCardPanel activateLeaderCardPanel = new ActivateLeaderCardPanel(gui);
            gui.switchPanels(activateLeaderCardPanel);
        }
        if(e.getSource() == discardLeaderCard){
            DiscardLeaderCardPanel discardLeaderCardPanel = new DiscardLeaderCardPanel(gui);
            gui.switchPanels(discardLeaderCardPanel);
        }
        if(e.getSource() == buyDevCard){
            DevGridPanel devGridPanel = new DevGridPanel(gui.getClientModelView());
            gui.switchPanels(devGridPanel);
        }
    }
}
