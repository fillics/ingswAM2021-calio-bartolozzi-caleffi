package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketPlaceResource;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.model.cards.leadercards.LeaderCardType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class PlaceResourcePanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;
    private JButton confirm;
    private JButton back;
    private ResourceBufferPanel resourceBufferPanel;
    private WarehousePanel warehousePanel;
    private DevSpacesPanel devSpacesPanel;
    private int position;
    private int resource;
    private ArrayList<LeaderCardPanel> leaderCardPanels;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public PlaceResourcePanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resourceBufferPanel = new ResourceBufferPanel(gui);
        JPanel bigpanel = new JPanel();
        bigpanel.setPreferredSize(new Dimension(1129, 1025));


        JPanel faithTrackPanel = new JPanel();
        faithTrackPanel.setLayout(new BoxLayout(faithTrackPanel, BoxLayout.X_AXIS));
        FaithTrackPanel faithTrack = new FaithTrackPanel(gui);
        faithTrack.setPreferredSize(new Dimension(970,200));
        faithTrackPanel.add(faithTrack);
        faithTrackPanel.setOpaque(false);

        JPanel underboard = new JPanel();
        underboard.setPreferredSize(new Dimension(1129, 480));
        underboard.setLayout(new BoxLayout(underboard, BoxLayout.X_AXIS));
        warehousePanel = new WarehousePanel(gui);
        devSpacesPanel = new DevSpacesPanel(gui);
        JPanel leadercards = new JPanel();
        leadercards.setLayout(new BoxLayout(leadercards, BoxLayout.Y_AXIS));
        leadercards.setPreferredSize(new Dimension(159, 250));
        for(LeaderCard leaderCard : gui.getClient().getClientModelView().getMyPlayer().getLeaderCards()){
            if(leaderCard.getType().equals(LeaderCardType.EXTRA_DEPOSIT) && leaderCard.getStrategy().isActive()){
                leadercards.add(new LeaderCardPanel(gui, leaderCard.getId(),159, 240, warehousePanel.getDepositsPanel()));
            }
        }
        leadercards.setOpaque(false);
        underboard.add(leadercards);
        underboard.add(warehousePanel);
        underboard.add(devSpacesPanel);
        underboard.setOpaque(false);

        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(970,50));
        confirm = new JButton("CONFIRM");
        confirm.addActionListener(this);
        back = new JButton("BACK");
        back.addActionListener(this);

        buttons.add(back);
        buttons.add(Box.createRigidArea(new Dimension(250, 50)));
        buttons.add(confirm);
        buttons.setOpaque(false);

        bigpanel.add(faithTrackPanel);
        bigpanel.add(underboard);
        bigpanel.add(resourceBufferPanel);
        bigpanel.add(buttons);
        bigpanel.setOpaque(false);
        this.add(bigpanel);

        disableButtons(devSpacesPanel, warehousePanel);
    }

    public void disableButtons(DevSpacesPanel devSpacesPanel, WarehousePanel warehousePanel){
        devSpacesPanel.getDevSpace1().setVisible(false);
        devSpacesPanel.getDevSpace2().setVisible(false);
        devSpacesPanel.getDevSpace3().setVisible(false);
        devSpacesPanel.getProdPower().setVisible(false);
        warehousePanel.getStrongboxPanel().getStrongboxButton().setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm){
            if(warehousePanel.getDepositsPanel().getIdDepot().size() != 0 && resourceBufferPanel.getPositions().size() != 0){
                position = warehousePanel.getDepositsPanel().getIdDepot().get(warehousePanel.getDepositsPanel().getIdDepot().size() - 1);
                resource = resourceBufferPanel.getPositions().get(resourceBufferPanel.getPositions().size() - 1);
                PacketPlaceResource packetPlaceResource = new PacketPlaceResource(position - 1, resource);
                gui.sendPacketToServer(packetPlaceResource);
            }
            else {
                JOptionPane.showMessageDialog(gui.getjFrame(), "Choose one resource and the deposit in which you want to place it");
            }
            PlaceResourcePanel placeResourcePanel = new PlaceResourcePanel(gui);
            gui.switchPanels(placeResourcePanel);
        }
        if(e.getSource() == back){
            BoardPanel boardPanel = new BoardPanel(gui);
            gui.switchPanels(boardPanel);
        }
    }
}
