package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
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
        JPanel bigpanel = new JPanel();
        bigpanel.setPreferredSize(new Dimension(970, 700));
        JPanel operations = new JPanel();
        addAll(operations);

        operations.setPreferredSize(new Dimension(970, 200));
        JPanel underboard = new JPanel();
        underboard.setPreferredSize(new Dimension(970, 480));
        underboard.setLayout(new BoxLayout(underboard, BoxLayout.X_AXIS));
        WarehousePanel warehousePanel = new WarehousePanel(gui);
        DevSpacesPanel devSpacesPanel = new DevSpacesPanel(gui);
        underboard.add(warehousePanel);
        underboard.add(devSpacesPanel);
        addActionEvent();
        bigpanel.add(operations);
        bigpanel.add(underboard);

        this.add(bigpanel);
        this.setVisible(true);
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

    public void addAll(JPanel operations){
        operations.add(discardLeaderCard);
        operations.add(activateLeaderCard);
        operations.add(buyDevCard);
        operations.add(chooseDiscount);
        operations.add(useProdPower);
        operations.add(moveResource);
        operations.add(placeResource);
        operations.add(takeResourceFromMarket);
        operations.add(endTurn);
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
            DevGridPanel devGridPanel = new DevGridPanel(gui);
            gui.switchPanels(devGridPanel);
        }
        if(e.getSource()== takeResourceFromMarket){
            MarketPanel marketPanel = new MarketPanel(gui);
            gui.switchPanels(marketPanel);
        }
    }
}
