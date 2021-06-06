package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketUseAndChooseProdPower;
import it.polimi.ingsw.model.board.resources.ResourceType;

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
    private WarehousePanel warehousePanel;
    private DevSpacesPanel devSpacesPanel;
    private ArrayList<LeaderCardPanel> leaderCardPanels;
    private JPanel leaderCards, underBoard, faithTrackPanel, mainPanel, buttons;
    private JLabel quantityCoins, quantityStones, quantityServants, quantityShields;
    private int coins, stones, servants, shields;
    private String coinsText, stonesText, servantsText, shieldsText;
    private JPanel resourcesPanel;
    private final JButton coin = new JButton();
    private final JButton stone = new JButton();
    private final JButton servant = new JButton();
    private final JButton shield = new JButton();
    private final JButton confirm = new JButton("CONFIRM");
    private final JButton back = new JButton("BACK");
    ArrayList<ResourceType> resources = new ArrayList<>();



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public UseProductionPowerPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException ignored) {}

        c = new GridBagConstraints();

        this.setLayout(new GridBagLayout());

        createLeaderCardsPanel();
        c.gridx=0;
        c.gridy=0;
        this.add(leaderCards, c);

        createMainPanel();
        c.gridx=1;
        c.gridy=0;
        this.add(mainPanel, c);

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

        createFaithTrackPanel();
        mainPanel.add(faithTrackPanel);

        createUnderBoard();
        mainPanel.add(underBoard);

        createResourcesPanel();
        mainPanel.add(resourcesPanel);

        createButtons();
        mainPanel.add(buttons);

        mainPanel.setOpaque(false);


    }

    public void createUnderBoard(){

        underBoard = new JPanel();
        underBoard.setLayout(new BoxLayout(underBoard, BoxLayout.X_AXIS));


        warehousePanel = new WarehousePanel(gui);
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
            LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(gui, gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId(),159,240);
            leaderCardPanels.add(leaderCardPanel1);
            leaderCardPanel1.setOpaque(false);
            c.gridx=0;
            c.gridy=i;
            leaderCards.add(leaderCardPanels.get(i), c);
        }
        leaderCards.setOpaque(false);

    }

    public void createResourcesPanel(){
        coins=0;
        shields=0;
        stones=0;
        servants=0;
        coinsText = "x " + coins;
        stonesText = "x " + stones;
        servantsText = "x " + servants;
        shieldsText = "x " + shields;

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
            quantityCoins.setForeground(Color.WHITE);
            c.gridx=0;
            c.gridy=0;
            coinPanel.add(quantityCoins, c);
            coin.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/coin.png").readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            c.gridx=0;
            c.gridy=1;
            coinPanel.add(coin, c);

            quantityStones= new JLabel(stonesText);
            quantityStones.setForeground(Color.WHITE);
            c.gridx=1;
            c.gridy=0;
            stonePanel.add(quantityStones, c);
            stone.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/stone.png").readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            c.gridx=1;
            c.gridy=1;
            stonePanel.add(stone, c);

            quantityServants = new JLabel(servantsText);
            quantityServants.setForeground(Color.WHITE);
            c.gridx=2;
            c.gridy=0;
            servantPanel.add(quantityServants, c);
            servant.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/servant.png").readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            c.gridx=2;
            c.gridy=1;
            servantPanel.add(servant, c);

            quantityShields = new JLabel(shieldsText);
            quantityShields.setForeground(Color.WHITE);
            c.gridx=3;
            c.gridy=0;
            shieldPanel.add(quantityShields, c);
            shield.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/shield.png").readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
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
        coinPanel.setOpaque(false);
        coin.setContentAreaFilled(false);
        coin.setBorder(gui.getBorders().get(0));
        stonePanel.setOpaque(false);
        stone.setContentAreaFilled(false);
        stone.setBorder(gui.getBorders().get(0));

        servantPanel.setOpaque(false);
        servant.setContentAreaFilled(false);
        servant.setBorder(gui.getBorders().get(0));

        shieldPanel.setOpaque(false);
        shield.setContentAreaFilled(false);
        shield.setBorder(gui.getBorders().get(0));

        resourcesPanel.setOpaque(false);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            BoardPanel boardPanel = new BoardPanel(gui);
            gui.switchPanels(boardPanel);
        }
        if (e.getSource() == coin) {
            resources.add(ResourceType.COIN);

            coins++;
            coinsText = "x " + coins;
            quantityCoins.setText(coinsText);
        }

        if (e.getSource() == stone) {
            resources.add(ResourceType.STONE);

            stones++;
            stonesText = "x " + stones;
            quantityStones.setText(stonesText);

        }
        if (e.getSource() == servant) {
            resources.add(ResourceType.SERVANT);

            servants++;
            servantsText = "x " + servants;
            quantityServants.setText(servantsText);

        }
        if (e.getSource() == shield) {
            resources.add(ResourceType.SHIELD);

            shields++;
            shieldsText = "x " + shields;
            quantityShields.setText(shieldsText);
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
                gui.switchPanels(new AdditionalResourcePanel(gui, devSpacesPanel.getProductionPowers(), devSpacesPanel.getNewProductionPowers(), resources, warehousePanel.getDepositsPanel().getIdDepot()));
            }
            else {
                PacketUseAndChooseProdPower packetUseAndChooseProdPower = new PacketUseAndChooseProdPower(devSpacesPanel.getProductionPowers(), devSpacesPanel.getNewProductionPowers(), resources, warehousePanel.getDepositsPanel().getIdDepot(), newResources);
                gui.sendPacketToServer(packetUseAndChooseProdPower);
                gui.switchPanels(new BoardPanel(gui));
            }
        }
    }
}
