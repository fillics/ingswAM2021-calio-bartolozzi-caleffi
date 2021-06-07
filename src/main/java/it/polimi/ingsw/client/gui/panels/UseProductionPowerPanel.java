package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.buydevcard.WarehouseForBuyDevCardPanel;
import it.polimi.ingsw.controller.client_packets.PacketUseAndChooseProdPower;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyProductionPower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UseProductionPowerPanel extends JPanel implements ActionListener {
    private Image background;
    private GUI gui;
    private GridBagConstraints c;

    private DevSpacesPanel devSpacesPanel;
    private ArrayList<LeaderCardPanel> leaderCardPanels;
    private JPanel leaderCards, underBoard, mainPanel, buttons;

    private WarehouseForBuyDevCardPanel warehousePanel;

    private final JButton confirm = new JButton("CONFIRM");
    private final JButton back = new JButton("BACK");
    ArrayList<ResourceType> resources = new ArrayList<>();



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public UseProductionPowerPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/backgroundGame2.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException ignored) {}

        c = new GridBagConstraints();

        this.setLayout(new GridBagLayout());

        createMainPanel();
        c.gridx=1;
        c.gridy=0;
        this.add(mainPanel, c);

        createLeaderCardsPanel();
        c.gridx=0;
        c.gridy=0;
        this.add(leaderCards, c);



        this.setOpaque(false);
    }

    public void createButtons(){
        buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(970, 50));

        confirm.addActionListener(this);
        back.addActionListener(this);

        buttons.add(back);
        buttons.add(Box.createRigidArea(new Dimension(180, 50)));
        buttons.add(confirm);

        buttons.setOpaque(false);

    }

    public void createMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        createUnderBoard();
        mainPanel.add(underBoard);

        createButtons();
        mainPanel.add(buttons);

        mainPanel.setOpaque(false);


    }


    public void createUnderBoard(){

        underBoard = new JPanel();
        underBoard.setLayout(new BoxLayout(underBoard, BoxLayout.X_AXIS));


        warehousePanel = new WarehouseForBuyDevCardPanel(gui);
        devSpacesPanel = new DevSpacesPanel(gui);

        if (gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(0).getTopCard() == null) {
            devSpacesPanel.getDevSpace1().setVisible(false);
        }
        if (gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(1).getTopCard() == null) {
            devSpacesPanel.getDevSpace2().setVisible(false);
        }
        if (gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(2).getTopCard() == null) {
            devSpacesPanel.getDevSpace3().setVisible(false);
        }
        underBoard.add(warehousePanel);
        underBoard.add(devSpacesPanel);
    }

    public void createLeaderCardsPanel(){
        leaderCards = new JPanel();
        leaderCards.setLayout(new GridBagLayout());
        c.insets = new Insets(10, 10, 10, 10);

        leaderCardPanels = new ArrayList<>();
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if (gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyProductionPower &&
                    gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy().isActive()){
                LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(gui, gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId(),159,240, warehousePanel.getDepositsPanel());
                leaderCardPanels.add(leaderCardPanel1);
                leaderCardPanel1.setOpaque(false);
                c.gridx=0;
                c.gridy=i;
                leaderCards.add(leaderCardPanels.get(i), c);
            }
        }
        leaderCards.setOpaque(false);

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            gui.switchPanels(new BoardPanel(gui));
        }

        if(e.getSource() == confirm){


            boolean checkProd = false;
            for(int i : devSpacesPanel.getProductionPowers()){
                if(gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(i).getTopCard() != null){
                    if (gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(i - 1).getTopCardProductionPower().getResourceObtained().get(ResourceType.JOLLY) > 0) {
                        checkProd = true;
                        break;
                    }
                }
            }
            for(int i : devSpacesPanel.getNewProductionPowers()){
                if (gui.getClient().getClientModelView().getLiteBoard().getSpecialProductionPower().get(i - 1).getResourceObtained().get(ResourceType.JOLLY) > 0) {
                    checkProd = true;
                    break;
                }
            }
            ArrayList<ResourceType> newResources = new ArrayList<>();
            if(checkProd){
                gui.switchPanels(new AdditionalResourcePanel(gui, devSpacesPanel.getProductionPowers(), devSpacesPanel.getNewProductionPowers(), warehousePanel.getChosenResources(), warehousePanel.getChosenWarehouses()));
            }
            else {

                gui.sendPacketToServer(new PacketUseAndChooseProdPower(devSpacesPanel.getProductionPowers(), devSpacesPanel.getNewProductionPowers(), warehousePanel.getChosenResources(), warehousePanel.getChosenWarehouses(), newResources));
                gui.switchPanels(new BoardPanel(gui));
            }
        }
    }
}
