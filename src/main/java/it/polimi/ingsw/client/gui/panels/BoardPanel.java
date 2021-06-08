package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.buydevcard.BuyDevCardPanel;
import it.polimi.ingsw.controller.client_packets.PacketEndTurn;
import it.polimi.ingsw.controller.client_packets.cheatpackets.FaithMarkerCheatPacket;
import it.polimi.ingsw.controller.client_packets.cheatpackets.ResourcesInStrongboxCheatPacket;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class BoardPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private Image background;
    private GridBagConstraints c;
    private final JButton showDevGrid= new JButton("SHOW DEVELOPMENT GRID");
    private final JButton showMarketTray= new JButton("SHOW THE MARKET TRAY");
    private final JButton showBoardOtherPlayer= new JButton("SHOW BOARD OF OTHER PLAYERS"); // TODO: 06/06/2021 guarda jmenu bar
    private final JButton discardLeaderCard = new JButton("DISCARD LEADER CARD");
    private final JButton activateLeaderCard = new JButton("ACTIVATE LEADER CARD");
    private final JButton buyDevCard = new JButton("BUY DEV CARD");
    private final JButton useProdPower = new JButton("USE PRODUCTION POWER");
    private final JButton moveResource = new JButton("MOVE RESOURCE");
    private final JButton placeResource = new JButton("PLACE RESOURCE");
    private final JButton takeResourceFromMarket = new JButton("TAKE RESOURCE FROM MARKET");
    private final JButton endTurn = new JButton("END YOUR TURN");
    private final JButton resourceCheatButton = new JButton("+20 resources");
    private final JButton faithMarkerCheatButton = new JButton("+1 faith marker");
    private JPanel operations, leaderCards, underBoard, faithTrackPanel, mainPanel, showButtons;
    private ArrayList<LeaderCardPanel> leaderCardPanels;
    private ResourceBufferPanel resourceBufferPanel;
    private WarehousePanel warehousePanel;
    private DevSpacesPanel devSpacesPanel;


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public BoardPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/backgroundGame2.png");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException ignored) {
        }
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


        createOperations();
        c.gridx=2;
        c.gridy=0;
        this.add(operations, c);


        this.setOpaque(false);


        disableButtons(resourceBufferPanel, devSpacesPanel, warehousePanel);


    }


    public void createShowButtons(){

        showButtons = new JPanel();
        showButtons.setLayout(new GridBagLayout());


        c.gridx=0;
        c.gridy=0;
        setupButtons(showDevGrid);
        showButtons.add(showDevGrid, c);

        c.gridx=1;
        c.gridy=0;
        setupButtons(showMarketTray);
        showButtons.add(showMarketTray, c);

        c.gridx=2;
        c.gridy=0;
        setupButtons(showBoardOtherPlayer);
        showButtons.add(showBoardOtherPlayer, c);

        showButtons.setOpaque(false);

    }


    public void createMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        createShowButtons();
        mainPanel.add(showButtons);

        createFaithTrackPanel();
        mainPanel.add(faithTrackPanel);

        createUnderBoard();
        mainPanel.add(underBoard);

        createResourceBuffer();
        mainPanel.add(resourceBufferPanel);

        mainPanel.setOpaque(false);
    }

    public void createResourceBuffer(){
         resourceBufferPanel = new ResourceBufferPanel(gui);
    }

    public void createOperations(){
        operations = new JPanel();
        operations.setLayout(new GridBagLayout());

        c.insets = new Insets(10, 10, 10, 10);

        c.gridx=0;
        c.gridy=0;
        setupButtons(discardLeaderCard);
        operations.add(discardLeaderCard, c);

        c.gridx=0;
        c.gridy=1;
        setupButtons(activateLeaderCard);
        operations.add(activateLeaderCard, c);

        c.gridx=0;
        c.gridy=2;
        setupButtons(buyDevCard);
        operations.add(buyDevCard, c);


        c.gridx=0;
        c.gridy=3;
        setupButtons(useProdPower);
        operations.add(useProdPower, c);

        c.gridx=0;
        c.gridy=4;
        setupButtons(moveResource);
        operations.add(moveResource, c);

        c.gridx=0;
        c.gridy=5;
        setupButtons(placeResource);
        operations.add(placeResource, c);

        c.gridx=0;
        c.gridy=6;
        setupButtons(takeResourceFromMarket);
        operations.add(takeResourceFromMarket, c);

        c.gridx=0;
        c.gridy=7;
        setupButtons(endTurn);
        operations.add(endTurn, c);

        c.gridx=0;
        c.gridy=8;
        setupButtons(resourceCheatButton);
        operations.add(resourceCheatButton, c);

        c.gridx=0;
        c.gridy=9;
        setupButtons(faithMarkerCheatButton);
        operations.add(faithMarkerCheatButton, c);

        operations.setBackground(new Color(0,0,0,0));
        operations.setOpaque(false);
    }

    public void setupButtons(JButton button){
        button.setPreferredSize(new Dimension(250,50));
        changeBackground(button);
        button.addActionListener(this);
    }
    public void createUnderBoard(){

        underBoard = new JPanel();
        underBoard.setLayout(new BoxLayout(underBoard, BoxLayout.X_AXIS));


        warehousePanel = new WarehousePanel(gui);
        devSpacesPanel = new DevSpacesPanel(gui);
        underBoard.add(warehousePanel);
        underBoard.add(devSpacesPanel);
    }


    public void createFaithTrackPanel(){

        faithTrackPanel = new JPanel();
        faithTrackPanel.setLayout(new BoxLayout(faithTrackPanel, BoxLayout.X_AXIS));
        FaithTrackPanel faithTrack = new FaithTrackPanel(gui);
        faithTrack.setPreferredSize(new Dimension(970,200));
        faithTrackPanel.add(faithTrack);
        faithTrackPanel.setOpaque(false);
    }

    public void createLeaderCardsPanel(){
        leaderCards = new JPanel();
        leaderCards.setLayout(new GridBagLayout());
        c.insets = new Insets(10, 10, 10, 10);

        leaderCardPanels = new ArrayList<>();
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(gui, gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId(), 159, 240, warehousePanel.getDepositsPanel());
            leaderCardPanels.add(leaderCardPanel1);
            leaderCardPanel1.setOpaque(false);
            c.gridx=0;
            c.gridy=i;
            leaderCards.add(leaderCardPanels.get(i), c);
        }
        leaderCards.setOpaque(false);

    }

    public void changeBackground(JButton button){
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(233, 226, 193));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });
    }

    public void disableButtons(ResourceBufferPanel resourceBufferPanel, DevSpacesPanel devSpacesPanel, WarehousePanel warehousePanel){
        for(int i = 0; i < resourceBufferPanel.getResources().size(); i++){
            resourceBufferPanel.getResources().get(i).setEnabled(false);
        }
        devSpacesPanel.getDevSpace1().setVisible(false);
        devSpacesPanel.getDevSpace2().setVisible(false);
        devSpacesPanel.getDevSpace3().setVisible(false);
        devSpacesPanel.getBaseProdPower().setVisible(false);
        warehousePanel.getDepositsPanel().getDeposit1Button().setVisible(false);
        warehousePanel.getDepositsPanel().getDeposit2Button().setVisible(false);
        warehousePanel.getDepositsPanel().getDeposit3Button().setVisible(false);
        warehousePanel.getStrongboxPanel().getStrongboxButton().setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO: 06/06/2021 cambiamo il messaggio visualizzato sopra quando premiamo un bottone, tipo "hai scelto di guardare il dev grid" 
        if(e.getSource() == showDevGrid){
            gui.switchPanels(new ShowDevGridPanel(gui));
        }
        if(e.getSource() == showMarketTray){
            gui.switchPanels(new ShowMarketTrayPanel(gui));
        }
        if(e.getSource() == activateLeaderCard){
            gui.switchPanels(new ActivateLeaderCardPanel(gui));
        }
        if(e.getSource() == discardLeaderCard){
            gui.switchPanels(new DiscardLeaderCardPanel(gui));
        }
        if(e.getSource() == buyDevCard){
            gui.switchPanels(new BuyDevCardPanel(gui));
        }
        if(e.getSource()== takeResourceFromMarket){
            gui.switchPanels(new TakeResourceFromMarketPanel(gui));
        }

        if(e.getSource() == placeResource){
            if(resourceBufferPanel.getResources().size() != 0){
                gui.switchPanels(new PlaceResourcePanel(gui));
            }
            else{
                JOptionPane.showMessageDialog(gui.getjFrame(), "You don't have any resources to place");
            }
        }
        if(e.getSource() == moveResource){
            gui.switchPanels(new MoveResourcePanel(gui));
        }
        if(e.getSource() == endTurn){
            gui.sendPacketToServer(new PacketEndTurn());
        }
        if(e.getSource() == resourceCheatButton){
            gui.sendPacketToServer(new ResourcesInStrongboxCheatPacket());
            gui.switchPanels(new BoardPanel(gui));
        }
        if(e.getSource() == faithMarkerCheatButton){
            gui.sendPacketToServer(new FaithMarkerCheatPacket());
        }
        if(e.getSource() == useProdPower){
            gui.switchPanels(new UseProductionPowerPanel(gui));
        }

    }
}
