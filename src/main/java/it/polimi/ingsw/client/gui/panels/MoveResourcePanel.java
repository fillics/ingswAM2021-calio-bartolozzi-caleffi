package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketMoveResource;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.model.cards.leadercards.LeaderCardType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Class that creates the panel used to do the move resource operation
 */
public class MoveResourcePanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;
    private JButton confirmBtn, backBtn;
    private ResourceBufferPanel resourceBufferPanel;
    private WarehousePanel warehousePanel;
    private DevSpacesPanel devSpacesPanel;
    private int position;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     */
    public MoveResourcePanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resourceBufferPanel = new ResourceBufferPanel(gui);
        JPanel bigPanel = new JPanel();
        bigPanel.setPreferredSize(new Dimension(970, 1025));

        JPanel faithTrackPanel = new JPanel();
        faithTrackPanel.setLayout(new BoxLayout(faithTrackPanel, BoxLayout.X_AXIS));
        FaithTrackPanel faithTrack = new FaithTrackPanel(gui);
        faithTrack.setPreferredSize(new Dimension(970,200));
        faithTrackPanel.add(faithTrack);
        faithTrackPanel.setOpaque(false);

        JPanel underBoard = new JPanel();
        underBoard.setPreferredSize(new Dimension(970, 480));
        underBoard.setLayout(new BoxLayout(underBoard, BoxLayout.X_AXIS));
        warehousePanel = new WarehousePanel(gui);
        devSpacesPanel = new DevSpacesPanel(gui);

        JPanel leaderCards = new JPanel();
        leaderCards.setLayout(new BoxLayout(leaderCards, BoxLayout.Y_AXIS));
        leaderCards.setPreferredSize(new Dimension(159, 250));
        for(LeaderCard leaderCard : gui.getClient().getClientModelView().getMyPlayer().getLeaderCards()){
            if(leaderCard.getType().equals(LeaderCardType.EXTRA_DEPOSIT) && leaderCard.getStrategy().isActive()){
                leaderCards.add(new LeaderCardPanel(gui, leaderCard.getId(), warehousePanel.getDepositsPanel()));
            }
        }
        leaderCards.setOpaque(false);
        underBoard.add(leaderCards);
        underBoard.add(warehousePanel);
        underBoard.add(devSpacesPanel);
        underBoard.setOpaque(false);

        underBoard.add(warehousePanel);
        underBoard.add(devSpacesPanel);

        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(970,50));
        confirmBtn = new JButton("CONFIRM");
        confirmBtn.setPreferredSize(new Dimension(200, 50));

        confirmBtn.addActionListener(this);
        backBtn = new JButton("BACK TO THE BOARD");
        backBtn.addActionListener(this);
        backBtn.setPreferredSize(new Dimension(200, 50));

        buttons.add(backBtn);
        buttons.add(Box.createRigidArea(new Dimension(250, 50)));
        buttons.add(confirmBtn);
        buttons.setOpaque(false);

        bigPanel.add(faithTrackPanel);
        bigPanel.add(underBoard);
        bigPanel.add(resourceBufferPanel);
        bigPanel.add(buttons);
        bigPanel.setOpaque(false);
        this.add(bigPanel);

        disableButtons(devSpacesPanel, warehousePanel, resourceBufferPanel);
    }

    /**
     * Method that disables all the buttons not requested
     * @param devSpacesPanel is the development spaces panel
     * @param warehousePanel is the warehouse panel
     * @param resourceBufferPanel is the resource buffer panel
     */
    public void disableButtons(DevSpacesPanel devSpacesPanel, WarehousePanel warehousePanel, ResourceBufferPanel resourceBufferPanel){
        devSpacesPanel.getDevSpace1().setVisible(false);
        devSpacesPanel.getDevSpace2().setVisible(false);
        devSpacesPanel.getDevSpace3().setVisible(false);
        devSpacesPanel.getBaseProdPower().setVisible(false);
        warehousePanel.getStrongboxPanel().getStrongboxButton().setVisible(false);
        for(int i = 0; i < resourceBufferPanel.getResources().size(); i++){
            resourceBufferPanel.getResources().get(i).setEnabled(false);
        }
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirmBtn){
            if(warehousePanel.getDepositsPanel().getIdDepot().size() != 0){
                position = warehousePanel.getDepositsPanel().getIdDepot().get(warehousePanel.getDepositsPanel().getIdDepot().size() - 1);
                gui.getClient().sendPacketToServer(new PacketMoveResource(position - 1));
            }
            else {
                JOptionPane.showMessageDialog(gui.getjFrame(), "Choose the deposit in which you want to take the resource");
            }
            gui.switchPanels(new MoveResourcePanel(gui));
        }
        else if(e.getSource() == backBtn){
            gui.switchPanels(new BoardPanel(gui));
        }
    }
}
