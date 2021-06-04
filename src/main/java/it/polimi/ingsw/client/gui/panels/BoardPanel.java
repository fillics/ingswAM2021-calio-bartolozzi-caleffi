package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketEndTurn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        ResourceBufferPanel resourceBufferPanel = new ResourceBufferPanel(gui);

        JPanel bigpanel = new JPanel();
        bigpanel.setPreferredSize(new Dimension(1129, 775));
        JPanel operations = new JPanel();
        addAll(operations);

        operations.setPreferredSize(new Dimension(1129, 200));

        JPanel underboard = new JPanel();
        underboard.setPreferredSize(new Dimension(1129, 480));
        underboard.setLayout(new BoxLayout(underboard, BoxLayout.X_AXIS));
        JPanel leadercards = new JPanel();
        leadercards.setLayout(new BoxLayout(leadercards, BoxLayout.Y_AXIS));
        leadercards.setPreferredSize(new Dimension(159, 480));
        ArrayList<LeaderCardPanel> leaderCardPanels = new ArrayList<>();
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(gui, gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId());
            leaderCardPanels.add(leaderCardPanel1);
            leadercards.add(leaderCardPanels.get(i));
        }
        WarehousePanel warehousePanel = new WarehousePanel(gui);
        DevSpacesPanel devSpacesPanel = new DevSpacesPanel(gui);
        underboard.add(warehousePanel);
        underboard.add(devSpacesPanel);
        underboard.add(leadercards);

        addActionEvent();
        bigpanel.add(operations);
        bigpanel.add(underboard);
        bigpanel.add(resourceBufferPanel);

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
        if(e.getSource() == chooseDiscount){
            ChooseDiscountPanel chooseDiscountPanel = new ChooseDiscountPanel(gui);
            gui.switchPanels(chooseDiscountPanel);
        }
        if(e.getSource() == endTurn){
            PacketEndTurn packetEndTurn = new PacketEndTurn();
            ObjectMapper mapper = new ObjectMapper();
            String jsonResult = null;
            try {
                jsonResult = mapper.writeValueAsString(packetEndTurn);
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
            gui.getClient().getSocketClientConnection().sendToServer(jsonResult);
        }
    }
}
