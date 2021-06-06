package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketMoveResource;
import it.polimi.ingsw.controller.client_packets.PacketPlaceResource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class MoveResourcePanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;
    private JButton confirm;
    private JButton back;
    private ResourceBufferPanel resourceBufferPanel;
    private WarehousePanel warehousePanel;
    private DevSpacesPanel devSpacesPanel;
    private int position;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public MoveResourcePanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resourceBufferPanel = new ResourceBufferPanel(gui);
        JPanel bigpanel = new JPanel();
        bigpanel.setPreferredSize(new Dimension(970, 1025));
        JPanel faithTrackPanel = new JPanel();
        faithTrackPanel.setLayout(new BoxLayout(faithTrackPanel, BoxLayout.X_AXIS));
        FaithTrackPanel faithTrack = new FaithTrackPanel(gui);
        faithTrack.setPreferredSize(new Dimension(970,200));
        faithTrackPanel.add(faithTrack);
        faithTrackPanel.setOpaque(false);

        JPanel underboard = new JPanel();
        underboard.setPreferredSize(new Dimension(970, 480));
        underboard.setLayout(new BoxLayout(underboard, BoxLayout.X_AXIS));
        warehousePanel = new WarehousePanel(gui);
        devSpacesPanel = new DevSpacesPanel(gui);
        underboard.add(warehousePanel);
        underboard.add(devSpacesPanel);

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

        disableButtons(devSpacesPanel, warehousePanel, resourceBufferPanel);
    }

    public void disableButtons(DevSpacesPanel devSpacesPanel, WarehousePanel warehousePanel, ResourceBufferPanel resourceBufferPanel){
        devSpacesPanel.getDevSpace1().setVisible(false);
        devSpacesPanel.getDevSpace2().setVisible(false);
        devSpacesPanel.getDevSpace3().setVisible(false);
        devSpacesPanel.getProdPower().setVisible(false);
        warehousePanel.getStrongboxPanel().getStrongboxButton().setVisible(false);
        for(int i = 0; i < resourceBufferPanel.getResources().size(); i++){
            resourceBufferPanel.getResources().get(i).setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm){
            if(warehousePanel.getDepositsPanel().getIdDepot().size() != 0){
                position = warehousePanel.getDepositsPanel().getIdDepot().get(warehousePanel.getDepositsPanel().getIdDepot().size() - 1);
                PacketMoveResource packetMoveResource = new PacketMoveResource(position - 1);
                gui.sendPacketToServer(packetMoveResource);
            }
            else {
                JOptionPane.showMessageDialog(gui.getjFrame(), "Choose the deposit in which you want to take the resource");
            }

            gui.switchPanels(new MoveResourcePanel(gui));
        }
        else if(e.getSource() == back){
            gui.switchPanels(new BoardPanel(gui));
        }
    }
}
