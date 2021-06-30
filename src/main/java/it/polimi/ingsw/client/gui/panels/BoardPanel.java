package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.buydevcard.BuyDevCardPanel;
import it.polimi.ingsw.client.gui.panels.showpanels.ShowDevGridPanel;
import it.polimi.ingsw.client.gui.panels.showpanels.ShowMarketTrayPanel;
import it.polimi.ingsw.controller.client_packets.PacketEndTurn;
import it.polimi.ingsw.controller.client_packets.PacketUsernameOfAnotherPlayer;
import it.polimi.ingsw.controller.client_packets.cheatpackets.FaithMarkerCheatPacket;
import it.polimi.ingsw.controller.client_packets.cheatpackets.ResourcesInStrongboxCheatPacket;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * Class that creates the panel of the principal board
 */
public class BoardPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private Image background;
    private final GridBagConstraints c;
    private JPanel usernamePanel;
    private final JButton showDevGrid= new JButton("SHOW DEVELOPMENT GRID");
    private final JButton showMarketTray= new JButton("SHOW THE MARKET TRAY");
    private final JButton showBoardOtherPlayer= new JButton("SHOW OTHER PLAYERS");
    private final JButton discardLeaderCard = new JButton("DISCARD LEADER CARD");
    private final JButton activateLeaderCard = new JButton("ACTIVATE LEADER CARD");
    private final JButton buyDevCard = new JButton("BUY DEVELOPMENT CARD");
    private final JButton useProdPower = new JButton("USE PRODUCTION POWER");
    private final JButton moveResource = new JButton("MOVE RESOURCE");
    private final JButton placeResource = new JButton("PLACE RESOURCE");
    private final JButton takeResourceFromMarket = new JButton("RESOURCES FROM MARKET");
    private final JButton endTurn = new JButton("END YOUR TURN");
    private final JButton resourceCheatButton = new JButton("+20 resources");
    private final JButton faithMarkerCheatButton = new JButton("+1 faith marker");
    private JPanel operations;
    private JPanel leaderCards;
    private JPanel underBoard;
    private JPanel faithTrackPanel;
    private JPanel boardPanel;
    private JPanel showButtons;
    private final JPanel mainPanel;
    private ArrayList<LeaderCardPanel> leaderCardPanels;
    private ResourceBufferPanel resourceBufferPanel;
    private WarehousePanel warehousePanel;
    private DevSpacesPanel devSpacesPanel;
    private final boolean isSingleGame;

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
    public BoardPanel(GUI gui) {
        this.gui = gui;
        isSingleGame= gui.getClient().getClientModelView().isSingleGame();
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException ignored) {
        }
        c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        createBoardPanel();
        c.gridx=1;
        c.gridy=0;
        mainPanel.add(boardPanel, c);

        createLeaderCardsPanel();
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(25, 0,0,0);
        mainPanel.add(leaderCards, c);

        createOperations();
        c.gridx=2;
        c.gridy=0;
        c.insets = new Insets(0, 0,0,0);
        mainPanel.add(operations, c);

        mainPanel.setOpaque(false);


        disableButtons(resourceBufferPanel, devSpacesPanel, warehousePanel);

        c.gridx=0;
        c.gridy=0;
        this.add(mainPanel, c);

    }

    /**
     * Method that sets the buttons used to show the board of other players, the development grid and the market tray.
     */
    public void createShowButtons(){

        showButtons = new JPanel();
        showButtons.setLayout(new GridBagLayout());

        c.insets = new Insets(0,10,10,10);

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

    /**
     * Method that creates all the sub-panel of the board panel
     */
    public void createBoardPanel(){
        boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));

        createShowButtons();
        boardPanel.add(showButtons);

        createFaithTrackPanel();
        boardPanel.add(faithTrackPanel);

        createUnderBoard();
        boardPanel.add(underBoard);

        createResourceBuffer();
        boardPanel.add(resourceBufferPanel);

        boardPanel.setOpaque(false);
        boardPanel.setBackground(new Color(0,0,0,0));

        mainPanel.setOpaque(false);
    }

    /**
     * Method that creates the resource buffer panel
     */
    public void createResourceBuffer(){
         resourceBufferPanel = new ResourceBufferPanel(gui);
    }

    /**
     * Method that sets all the operations buttons
     */
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

    /**
     * Method that sets size, background and font of a button
     * @param button is the button chosen
     */
    public void setupButtons(JButton button){
        button.setPreferredSize(new Dimension(250,50));
        changeBackground(button);
        button.addActionListener(this);
        button.setFont(new Font("Times New Roman", button.getFont().getStyle(), 15));

    }

    /**
     * Method that creates the panel that includes warehouse and development spaces panels.
     */
    public void createUnderBoard(){

        underBoard = new JPanel();
        underBoard.setLayout(new BoxLayout(underBoard, BoxLayout.X_AXIS));

        warehousePanel = new WarehousePanel(gui);
        devSpacesPanel = new DevSpacesPanel(gui);
        underBoard.add(warehousePanel);
        underBoard.add(devSpacesPanel);
    }

    /**
     * Method that creates the faith track panel
     */
    public void createFaithTrackPanel(){

        faithTrackPanel = new JPanel();
        faithTrackPanel.setLayout(new BoxLayout(faithTrackPanel, BoxLayout.X_AXIS));
        FaithTrackPanel faithTrack = new FaithTrackPanel(gui);
        faithTrack.setPreferredSize(new Dimension(970,200));
        faithTrackPanel.add(faithTrack);
        faithTrackPanel.setOpaque(false);
    }

    /**
     * Method that creates the token panel in single player game
     */
    public void createToken(){
        if(isSingleGame) {
            JPanel tokenPanel = new JPanel();
            tokenPanel.setLayout(new GridBagLayout());
            tokenPanel.setOpaque(false);
            TokenPanel token = new TokenPanel(gui);

            tokenPanel.add(token);
            c.gridx=0;
            c.gridy=0;
            leaderCards.add(tokenPanel, c);
        }
        else if (gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==0){
            JPanel inkwellPanel = new JPanel();
            inkwellPanel.setLayout(new GridBagLayout());
            inkwellPanel.setOpaque(false);
            JLabel inkwell = new JLabel();
            try {
                inkwell.setIcon(new ImageIcon(new ImageIcon((Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/calamaio.png"))).readAllBytes()).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException ignored) { }

            c.gridx=0;
            c.gridy=0;
            inkwellPanel.add(inkwell, c);
            leaderCards.add(inkwellPanel, c);
        }
    }

    /**
     * Method that creates the leader cards panel
     */
    public void createLeaderCardsPanel(){
        leaderCards = new JPanel();
        leaderCards.setLayout(new GridBagLayout());
        c.insets = new Insets(10, 10, 10, 10);

        createToken();

        leaderCardPanels = new ArrayList<>();
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(gui, gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId(), warehousePanel.getDepositsPanel());
            leaderCardPanels.add(leaderCardPanel1);
            leaderCardPanel1.setOpaque(false);
            c.gridx=0;
            if(isSingleGame || gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==0) c.gridy=i+1;
            else c.gridy=i;
            leaderCards.add(leaderCardPanels.get(i), c);
        }

        createUsername();
        c.gridx=0;
        c.gridy+=1;
        leaderCards.add(usernamePanel, c);

        leaderCards.setOpaque(false);

    }

    /**
     * Method that creates the username panel
     */
    public void createUsername(){
        usernamePanel = new JPanel();
        usernamePanel.setLayout(new GridBagLayout());
        JLabel username = new JLabel();

        usernamePanel.setBackground(gui.getGreenColor()); // TODO: 11/06/2021 mettere colore casuale
        usernamePanel.setPreferredSize(new Dimension(159,110));
        usernamePanel.setBorder(gui.getBorders().get(0));

        username.setText(gui.getClient().getClientModelView().getMyPlayer().getUsername().toUpperCase(Locale.ROOT));
        username.setFont(new Font("Serif", Font.BOLD, 16));
        username.setHorizontalTextPosition(JLabel.CENTER);
        username.setVerticalTextPosition(JLabel.CENTER);


        usernamePanel.add(username);


    }

    /**
     * Method used to change the background of a button
     * @param button is the button chosen
     */
    public void changeBackground(JButton button){
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(gui.getGiallinoBackgroundColor());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });
    }

    /**
     * Method that disables the buttons not needed in the board panel
     * @param resourceBufferPanel is the resource buffer panel
     * @param devSpacesPanel is the development spaces panel
     * @param warehousePanel is the warehouse panel
     */
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

        for (LeaderCardPanel leaderCardPanel : leaderCardPanels) {
            leaderCardPanel.getDepositButton().setVisible(false);
            leaderCardPanel.getDepositForBuyDevCardAndProdPower().setVisible(false);
            leaderCardPanel.getChooseWhiteMarbleButton().setVisible(false);
            leaderCardPanel.getChooseProdPowerButton().setVisible(false);
        }
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO: 06/06/2021 cambiamo il messaggio visualizzato sopra quando premiamo un bottone, tipo "hai scelto di guardare il dev grid" 
        if(e.getSource() == showDevGrid){
            gui.switchPanels(new ShowDevGridPanel(gui));
        }
        if(e.getSource() == showMarketTray){
            gui.switchPanels(new ShowMarketTrayPanel(gui));
        }
        if(e.getSource() == showBoardOtherPlayer){
            if(!isSingleGame){

                JComboBox comboBox = new JComboBox(gui.getClient().getClientModelView().getPlayers().toArray());

                JOptionPane.showMessageDialog(gui.getjFrame(), comboBox, "Select a player", JOptionPane.QUESTION_MESSAGE);

                String playerSelected = (String) comboBox.getSelectedItem();

                gui.getClient().sendPacketToServer(new PacketUsernameOfAnotherPlayer(playerSelected));
            }
            else JOptionPane.showMessageDialog(gui.getjFrame(), "You are in single player mode, there are no other players, please choose another action");


        }
        if(e.getSource() == activateLeaderCard){
            for(LeaderCard leaderCard : gui.getClient().getClientModelView().getMyPlayer().getLeaderCards()){
                if(!leaderCard.getStrategy().isActive()){
                    gui.switchPanels(new ActivateLeaderCardPanel(gui));
                    break;
                }
            }
        }
        if(e.getSource() == discardLeaderCard){
            if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size() != 0){
                for(LeaderCard leaderCard : gui.getClient().getClientModelView().getMyPlayer().getLeaderCards()){
                    if(!leaderCard.getStrategy().isActive()){
                        gui.switchPanels(new DiscardLeaderCardPanel(gui));
                        break;
                    }
                }
            }
        }
        if(e.getSource() == buyDevCard){
            gui.switchPanels(new BuyDevCardPanel(gui));
        }
        if(e.getSource()== takeResourceFromMarket){
            gui.switchPanels(new TakeResourcesFromMarketPanel(gui));
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
            gui.getClient().sendPacketToServer(new PacketEndTurn());
        }
        if(e.getSource() == resourceCheatButton){
            gui.createMessageFromServer("20 resources in your strongbox. Don't tell anyone ;)");
            gui.getClient().sendPacketToServer(new ResourcesInStrongboxCheatPacket());
            gui.switchPanels(new BoardPanel(gui));
        }
        if(e.getSource() == faithMarkerCheatButton){
            gui.createMessageFromServer("+1 faith marker for you. Don't tell anyone ;)");
            gui.getClient().sendPacketToServer(new FaithMarkerCheatPacket());
        }
        if(e.getSource() == useProdPower){
            gui.switchPanels(new UseProductionPowerPanel(gui));
        }

    }
}
