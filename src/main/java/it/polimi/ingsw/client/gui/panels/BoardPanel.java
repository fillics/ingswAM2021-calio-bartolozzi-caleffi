package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketChooseDiscount;
import it.polimi.ingsw.controller.client_packets.PacketEndTurn;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BoardPanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;
    private final JButton discardLeaderCard = new JButton("DISCARD LEADER CARD");
    private final JButton activateLeaderCard = new JButton("ACTIVATE LEADER CARD");
    private final JButton buyDevCard = new JButton("BUY DEV CARD");
    private final JButton chooseDiscount = new JButton("CHOOSE DISCOUNT");
    private final JButton useProdPower = new JButton("USE PRODUCTION POWER");
    private final JButton moveResource = new JButton("MOVE RESOURCE");
    private final JButton placeResource = new JButton("PLACE RESORCE");
    private final JButton takeResourceFromMarket = new JButton("TAKE RESOURCE FROM MARKET");
    private final JButton endTurn = new JButton("END YOUR TURN");
    private JPanel operations;


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public BoardPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResourceBufferPanel resourceBufferPanel = new ResourceBufferPanel(gui);

        JPanel bigpanel = new JPanel();
        bigpanel.setPreferredSize(new Dimension(1129, 975));
        JPanel operations = new JPanel();
        addAll(operations);
        operations.setOpaque(false);


        operations.setPreferredSize(new Dimension(1129, 70));

        JPanel faithTrackPanel = new JPanel();
        faithTrackPanel.setLayout(new BoxLayout(faithTrackPanel, BoxLayout.X_AXIS));
        FaithTrackPanel faithTrack = new FaithTrackPanel(gui);
        faithTrack.setPreferredSize(new Dimension(970,200));
        faithTrackPanel.add(faithTrack);
        faithTrackPanel.add(Box.createRigidArea(new Dimension(159,200)));
        faithTrackPanel.setOpaque(false);

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
        bigpanel.add(faithTrackPanel);
        bigpanel.add(underboard);
        bigpanel.add(resourceBufferPanel);
        bigpanel.setOpaque(false);
        this.add(bigpanel);

    }

    public void createOperations(){
        operations = new JPanel();
        addAll(operations);

        operations.setPreferredSize(new Dimension(1129, 200));
        operations.setBackground(new Color(0,0,0,0));
        operations.setOpaque(false);
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
