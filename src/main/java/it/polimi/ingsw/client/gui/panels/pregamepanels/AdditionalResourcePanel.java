package it.polimi.ingsw.client.gui.panels.pregamepanels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.gui.panels.DepositsPanel;
import it.polimi.ingsw.controller.client_packets.PacketChooseInitialResources;
import it.polimi.ingsw.controller.client_packets.PacketUseAndChooseProdPower;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that creates the panel that contains the additional resources to choose at the beginning of the game
 */
public class AdditionalResourcePanel extends JPanel implements ActionListener {

    private final GUI gui;
    private ArrayList<Integer> productionPowers;
    private ArrayList<Integer> newProductionPowers;
    private ArrayList<ResourceType> resourcesForProduction;
    private ArrayList<Integer> warehouse;
    private final JButton confirmForProduction = new JButton("CONFIRM");
    private final JButton backForProduction = new JButton("BACK");
    private JPanel buttonsForProduction;
    private JButton coinForProduction, stoneForProduction, servantForProduction, shieldForProduction;

    private Image background;
    private ArrayList<Integer> deposits;
    private final ArrayList<ResourceType> resources;
    private JPanel resourcesPanel, buttonsPanel;
    private DepositsPanel depositsPanel;
    private final JButton coin = new JButton();
    private final JButton stone = new JButton();
    private final JButton servant = new JButton();
    private final JButton shield = new JButton();
    private JButton confirmButton;
    private final GridBagConstraints c;
    private JLabel quantityCoins, quantityStones, quantityServants, quantityShields;
    private int coins, stones, servants, shields;
    private String coinsText, stonesText, servantsText, shieldsText;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getWidth(), gui.getHeight()-50, null);
    }

    /**
     * Class' constructor used for a production
     * @param gui gui is the GUI object linked to this panel
     * @param productionPowers is the array that contains the production power chosen
     * @param newProductionPowers is the array that contains the new production powers chosen
     * @param resourcesForProduction are the resources used for the production
     * @param warehouse is the position of the warehouses chosen
     */
    public AdditionalResourcePanel(GUI gui, ArrayList<Integer> productionPowers, ArrayList<Integer> newProductionPowers, ArrayList<ResourceType> resourcesForProduction, ArrayList<Integer> warehouse){
        this.gui = gui;
        this.productionPowers = productionPowers;
        this.newProductionPowers = newProductionPowers;
        this.resourcesForProduction = resourcesForProduction;
        this.warehouse = warehouse;

        coinForProduction = new JButton();
        stoneForProduction = new JButton();
        servantForProduction = new JButton();
        shieldForProduction = new JButton();

        coins=0;
        shields=0;
        stones=0;
        servants=0;
        coinsText = "x " + coins;
        stonesText = "x " + stones;
        servantsText = "x " + servants;
        shieldsText = "x " + shields;

        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {}


        gui.createMessageFromServer("Choose the resources instead of the jolly resources");



        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        deposits = new ArrayList<>();
        resources = new ArrayList<>();
        createResourcesPanelForProduction();
        c.gridx=0;
        c.gridy=0;
        this.add(resourcesPanel, c);

        createButtons();
        c.gridx = 0;
        c.gridy = 1;
        this.add(buttonsForProduction, c);


    }

    /**
     * Method that creates the resources panel for the production
     */
    public void createResourcesPanelForProduction(){
        resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new GridBagLayout());
        c.insets = new Insets(0, 2, 0, 2);

        JPanel coinPanel = new JPanel();
        coinPanel.setLayout(new GridBagLayout());
        JPanel stonePanel = new JPanel();
        stonePanel.setLayout(new GridBagLayout());
        JPanel servantPanel = new JPanel();
        servantPanel.setLayout(new GridBagLayout());
        JPanel shieldPanel = new JPanel();
        shieldPanel.setLayout(new GridBagLayout());


        try {
            quantityCoins = new JLabel(coinsText);
            c.gridx=0;
            c.gridy=0;
            coinPanel.add(quantityCoins, c);
            coinForProduction.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/coin.png")).readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=0;
            c.gridy=1;
            coinPanel.add(coinForProduction, c);

            quantityStones= new JLabel(stonesText);
            c.gridx=1;
            c.gridy=0;
            stonePanel.add(quantityStones, c);
            stoneForProduction.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/stone.png")).readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=1;
            c.gridy=1;
            stonePanel.add(stoneForProduction, c);

            quantityServants = new JLabel(servantsText);
            c.gridx=2;
            c.gridy=0;
            servantPanel.add(quantityServants, c);
            servantForProduction.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/servant.png")).readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=2;
            c.gridy=1;
            servantPanel.add(servantForProduction, c);

            quantityShields = new JLabel(shieldsText);
            c.gridx=3;
            c.gridy=0;
            shieldPanel.add(quantityShields, c);
            shieldForProduction.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/shield.png")).readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=3;
            c.gridy=1;
            shieldPanel.add(shieldForProduction, c);

        } catch (IOException e) {
            e.printStackTrace();
        }

        coinForProduction.addActionListener(this);
        shieldForProduction.addActionListener(this);
        servantForProduction.addActionListener(this);
        stoneForProduction.addActionListener(this);

        c.gridx=0;
        c.gridy=0;
        resourcesPanel.add(coinPanel, c);
        c.gridx=1;
        c.gridy=0;
        resourcesPanel.add(stonePanel, c);
        c.gridx=2;
        c.gridy=0;
        resourcesPanel.add(servantPanel, c);
        c.gridx=3;
        c.gridy=0;
        resourcesPanel.add(shieldPanel, c);

        resourcesPanel.setBackground(new Color(0, 0, 0, 0));
        resourcesPanel.setOpaque(true);
    }

    /**
     * Method that creates the "confirm" and "back" buttons
     */
    public void createButtons(){
        buttonsForProduction = new JPanel();

        setButtons(confirmButton);
        setButtons(backForProduction);
        buttonsForProduction.add(backForProduction);
        buttonsForProduction.add(Box.createRigidArea(new Dimension(180, 20)));
        buttonsForProduction.add(confirmForProduction);
    }

    /**
     * Method that sets a button font and size
     * @param button is the button chosen
     */
    public void setButtons(JButton button){
        button.addActionListener(this);
        button.setFont(new Font("Times New Roman", button.getFont().getStyle(), 15));
        button.setPreferredSize(new Dimension(150,50));
    }

    /**
     * Class' constructor
     * @param gui gui is the GUI object linked to this panel
     */
    public AdditionalResourcePanel(GUI gui) {
        this.gui = gui;
        coins=0;
        shields=0;
        stones=0;
        servants=0;
        coinsText = "x " + coins;
        stonesText = "x " + stones;
        servantsText = "x " + servants;
        shieldsText = "x " + shields;

        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {}

        if(gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==1 ||
                gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==2)
            gui.createMessageFromServer(ConnectionMessages.CHOOSE_ONE_RESOURCE_GUI.getMessage());

        else gui.createMessageFromServer(ConnectionMessages.CHOOSE_TWO_RESOURCES_GUI.getMessage());


        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        deposits = new ArrayList<>();
        resources = new ArrayList<>();
        createResourcesPanel();

        c.gridx=0;
        c.gridy=0;
        this.add(resourcesPanel, c);


        createDepositsPanel();
        c.gridx=0;
        c.gridy=1;
        this.add(depositsPanel, c);


        createButton();
        c.gridx=0;
        c.gridy=2;
        this.add(buttonsPanel, c);

    }

    /**
     * Method that creates the resources panel
     */
    public void createResourcesPanel(){
        resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new GridBagLayout());
        c.insets = new Insets(0, 2, 0, 2);

        JPanel coinPanel = new JPanel();
        coinPanel.setLayout(new GridBagLayout());
        JPanel stonePanel = new JPanel();
        stonePanel.setLayout(new GridBagLayout());
        JPanel servantPanel = new JPanel();
        servantPanel.setLayout(new GridBagLayout());
        JPanel shieldPanel = new JPanel();
        shieldPanel.setLayout(new GridBagLayout());


        try {
            quantityCoins = new JLabel(coinsText);
            c.gridx=0;
            c.gridy=0;
            coinPanel.add(quantityCoins, c);
            coin.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/coin.png")).readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=0;
            c.gridy=1;
            coinPanel.add(coin, c);

            quantityStones= new JLabel(stonesText);
            c.gridx=1;
            c.gridy=0;
            stonePanel.add(quantityStones, c);
            stone.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/stone.png")).readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=1;
            c.gridy=1;
            stonePanel.add(stone, c);

            quantityServants = new JLabel(servantsText);
            c.gridx=2;
            c.gridy=0;
            servantPanel.add(quantityServants, c);
            servant.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/servant.png")).readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=2;
            c.gridy=1;
            servantPanel.add(servant, c);

            quantityShields = new JLabel(shieldsText);
            c.gridx=3;
            c.gridy=0;
            shieldPanel.add(quantityShields, c);
            shield.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/shield.png")).readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=3;
            c.gridy=1;
            shieldPanel.add(shield, c);

        } catch (IOException e) {
            e.printStackTrace();
        }

        coin.addActionListener(this);
        shield.addActionListener(this);
        servant.addActionListener(this);
        stone.addActionListener(this);

        c.gridx=0;
        c.gridy=0;
        resourcesPanel.add(coinPanel, c);
        c.gridx=1;
        c.gridy=0;
        resourcesPanel.add(stonePanel, c);
        c.gridx=2;
        c.gridy=0;
        resourcesPanel.add(servantPanel, c);
        c.gridx=3;
        c.gridy=0;
        resourcesPanel.add(shieldPanel, c);

        resourcesPanel.setBackground(new Color(0, 0, 0, 0));
        resourcesPanel.setOpaque(true);

    }

    /**
     * Method that creates the deposits panel
     */
    public void createDepositsPanel(){
        depositsPanel = new DepositsPanel(gui);
        depositsPanel.setVisible(false);
    }

    /**
     * Method that creates the "confirm" button
     */
    public void createButton(){
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());

        confirmButton = new JButton("CONFIRM");
        confirmButton.setFont(new Font("Times New Roman", confirmButton.getFont().getStyle(), 15));
        c.gridx=0;
        c.gridy=0;
        c.ipadx=50;
        c.ipady=25;
        buttonsPanel.add(confirmButton, c);
        confirmButton.addActionListener(this);
        buttonsPanel.setBackground(new Color(0,0,0,0));

    }


    // TODO: 06/06/2021 mettere che disabilito i bottoni dei depositi quando ci cliccco sopra, con i vari get
    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == coin) {
            onlyOneResource();
            resources.add(ResourceType.COIN);
            if(resources.size()==2) setDisabled();

            coins++;
            coinsText = "x " + coins;
            quantityCoins.setText(coinsText);
            depositsPanel.setVisible(true);
        }

        if (e.getSource() == stone) {
            onlyOneResource();

            resources.add(ResourceType.STONE);
            if(resources.size()==2) setDisabled();

            stones++;
            stonesText = "x " + stones;
            quantityStones.setText(stonesText);
            depositsPanel.setVisible(true);

        }
        if (e.getSource() == servant) {
            onlyOneResource();
            resources.add(ResourceType.SERVANT);
            if(resources.size()==2) setDisabled();

            servants++;
            servantsText = "x " + servants;
            quantityServants.setText(servantsText);
            depositsPanel.setVisible(true);

        }
        if (e.getSource() == shield) {
            onlyOneResource();

            resources.add(ResourceType.SHIELD);
            if(resources.size()==2) setDisabled();

            shields++;
            shieldsText = "x " + shields;
            quantityShields.setText(shieldsText);
            depositsPanel.setVisible(true);

        }


        if(e.getSource() == confirmButton){

            deposits = depositsPanel.getIdDepot();

            if(deposits.size() == resources.size()){
                gui.getClient().sendPacketToServer(new PacketChooseInitialResources(deposits, resources));
                gui.createMessageFromServer("Good game!");
            }
            else{
                gui.createMessageFromServer("Arrays of different dimension. Please choose again the resources and the deposits");
                resources.clear();
                deposits.clear();
                depositsPanel.getIdDepot().clear();
                setEnabled();
                depositsPanel.setEnabled();

                coins=0;
                coinsText = "x " + coins;
                quantityCoins.setText(coinsText);

                stones=0;
                stonesText = "x " + stones;
                quantityStones.setText(stonesText);

                shields=0;
                shieldsText = "x " + shields;
                quantityShields.setText(shieldsText);

                servants=0;
                servantsText = "x " + servants;
                quantityServants.setText(servantsText);

                depositsPanel.changeBackgroundColor(new Color(151, 74, 74));


            }
        }

        if (e.getSource() == coinForProduction) {
            resources.add(ResourceType.COIN);

            coins++;
            coinsText = "x " + coins;
            quantityCoins.setText(coinsText);
        }

        if (e.getSource() == stoneForProduction) {
            resources.add(ResourceType.STONE);

            stones++;
            stonesText = "x " + stones;
            quantityStones.setText(stonesText);

        }
        if (e.getSource() == servantForProduction) {
            resources.add(ResourceType.SERVANT);

            servants++;
            servantsText = "x " + servants;
            quantityServants.setText(servantsText);

        }
        if (e.getSource() == shieldForProduction) {
            resources.add(ResourceType.SHIELD);

            shields++;
            shieldsText = "x " + shields;
            quantityShields.setText(shieldsText);
        }
        if( e.getSource() == confirmForProduction){
            PacketUseAndChooseProdPower packetUseAndChooseProdPower = new PacketUseAndChooseProdPower(productionPowers, newProductionPowers, resourcesForProduction, warehouse, resources);
            gui.getClient().sendPacketToServer(packetUseAndChooseProdPower);
            gui.switchPanels(new BoardPanel(gui));
        }
        if(e.getSource() == backForProduction){
            gui.switchPanels(new BoardPanel(gui));
        }

    }

    /**
     * Method that, based on the position in game of the player, disables the resources buttons
     */
    public void onlyOneResource(){
        if(gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==1 ||
                gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==2){
            setDisabled();
        }
    }

    /**
     * Method that disables the resources buttons
     */
    public void setDisabled(){
        coin.setEnabled(false);
        stone.setEnabled(false);
        shield.setEnabled(false);
        servant.setEnabled(false);
    }

    /**
     * Method that enables the resources buttons
     */
    public void setEnabled(){
        coin.setEnabled(true);
        stone.setEnabled(true);
        shield.setEnabled(true);
        servant.setEnabled(true);
    }

}