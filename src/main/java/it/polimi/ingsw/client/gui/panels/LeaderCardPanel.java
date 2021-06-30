package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.buydevcard.BuyDevCardPanel;
import it.polimi.ingsw.client.gui.panels.buydevcard.WarehouseForBuyDevCardPanel;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyDeposit;
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

/**
 * Class that creates the panel that contains a leader card
 */
public class LeaderCardPanel extends JPanel implements ActionListener {
    private TakeResourcesFromMarketPanel takeResourcesFromMarketPanel;
    private WarehouseAndDevSpacesPanel warehouseAndDevSpacesPanel;
    private GUI gui;
    private GridBagConstraints c;
    private Image background;
    private final int id;
    private int position;
    private final JButton chooseProdPowerButton = new JButton();
    private final JButton chooseWhiteMarbleButton = new JButton();
    private final JButton depositButton = new JButton("4");
    private final JButton depositForBuyDevCardAndProdPower = new JButton();
    private DepositsPanel depositsPanel;
    private WarehouseForBuyDevCardPanel warehouseForBuyDevCardPanel;
    private DevSpacesPanel devSpacesPanel;
    private JPanel leadercard;
    private JPanel standardPanel;
    private int numOfWhiteChoices=0;
    private BuyDevCardPanel buyDevCardPanel;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0,159, 240, null);
    }


    /**
     * Class' constructor used to create the leader card in tha panel used to take resources from the market
     * @param gui is the GUI object linked to this panel
     * @param id is the id of the leader card to represent
     * @param takeResourcesFromMarketPanel is the take resources from market panel
     */
    public LeaderCardPanel(GUI gui, int id, TakeResourcesFromMarketPanel takeResourcesFromMarketPanel){
        this.takeResourcesFromMarketPanel = takeResourcesFromMarketPanel;
        this.setPreferredSize(new Dimension(159, 240));
        this.id = id;
        this.gui = gui;
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(id == gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId()){
                position = i;
            }
        }
        InputStream is = getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getPath());
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        leadercard = new JPanel();
        leadercard.setPreferredSize(new Dimension(159, 240));
        leadercard.setLayout(new BoxLayout(leadercard, BoxLayout.Y_AXIS));

        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.WHITE_MARBLE)){
            JPanel specialPower = new JPanel();
            specialPower.setPreferredSize(new Dimension(159, 70));
            chooseWhiteMarbleButton.addActionListener(this);
            chooseWhiteMarbleButton.setPreferredSize(new Dimension(159, 70));
            try {
                chooseWhiteMarbleButton.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(159, 70, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            chooseWhiteMarbleButton.setOpaque(false);
            chooseWhiteMarbleButton.setContentAreaFilled(false);
            specialPower.add(chooseWhiteMarbleButton);
            specialPower.setOpaque(false);

            leadercard.add(Box.createRigidArea(new Dimension(159, 170)));
            leadercard.add(specialPower);
        }

        this.add(leadercard);
        leadercard.setOpaque(false);
        this.setOpaque(false);
    }

    /**
     * Method that sets the standard setup of each leader card
     * @param clientModelView is the client model view in which are contained the information
     */
    public void defaultSetup(ClientModelView clientModelView){
        this.setPreferredSize(new Dimension(159, 240));
        for(int i = 0 ; i < clientModelView.getMyPlayer().getLeaderCards().size(); i++){
            if(id == clientModelView.getMyPlayer().getLeaderCards().get(i).getId()){
                position = i;
            }
        }
        InputStream is = getClass().getResourceAsStream(clientModelView.getMyPlayer().getLeaderCards().get(position).getPath());
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }


        leadercard.setPreferredSize(new Dimension(159, 240));
        leadercard.setLayout(new BoxLayout(leadercard, BoxLayout.Y_AXIS));


        JLabel isActive = new JLabel();
        if(clientModelView.getMyPlayer().getLeaderCards().get(position).getStrategy().isActive()){
            try {
                isActive.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/active.png")).readAllBytes()).getImage().getScaledInstance(30,30, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                isActive.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/notactive.png")).readAllBytes()).getImage().getScaledInstance(30,30, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        standardPanel.setLayout(new BoxLayout(standardPanel, BoxLayout.X_AXIS));
        standardPanel.setPreferredSize(new Dimension(159, 40));
        standardPanel.add(Box.createRigidArea(new Dimension(129, 40)));
        standardPanel.add(isActive);
        standardPanel.setOpaque(false);
        leadercard.add(standardPanel);
        leadercard.setOpaque(false);

    }

    /**
     * Method that sets the button to click for white marble leader cards and production power leader cards
     * @param button is the button to set
     */
    public void setMarbleAndProdPower(JButton button){
        JPanel specialPower = new JPanel();
        specialPower.setPreferredSize(new Dimension(159, 70));
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(159, 70));
        try {
            button.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(159, 70, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        specialPower.add(button);
        specialPower.setOpaque(false);

        leadercard.add(Box.createRigidArea(new Dimension(159, 125)));
        leadercard.add(specialPower);
    }

    /**
     * Method that sets the button to click for extra deposit leader cards
     * @param depositButton is the button to set
     * @param clientModelView is the client model view in which are contained the information
     */
    public void setExtraDeposit (JButton depositButton, ClientModelView clientModelView){
        depositButton.setBackground(new Color(233, 226, 193));
        depositButton.addActionListener(this);

        JPanel depositSpace = new JPanel();
        depositSpace.setLayout(new BoxLayout(depositSpace, BoxLayout.X_AXIS));
        depositSpace.setPreferredSize(new Dimension(159, 35));
        JLabel resource1 = new JLabel();
        JLabel resource2 = new JLabel();
        ArrayList<JLabel> resources = new ArrayList<>();
        resources.add(resource1);
        resources.add(resource2);

        for(int i = 0; i < 2; i++){
            if(i < clientModelView.getLiteBoard().getDeposits().get(clientModelView.getMyPlayer().getLeaderCards().get(position).getDepositPosition()).getQuantity()){
                try {
                    resources.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(clientModelView.getLiteBoard().getDeposits().get(clientModelView.getMyPlayer().getLeaderCards().get(position).getDepositPosition()).getResourcetype().path)).readAllBytes()).getImage().getScaledInstance(35,35, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    resources.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(35, 35, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        depositSpace.add(resources.get(0));
        depositSpace.add(Box.createRigidArea(new Dimension(20, 35)));
        depositSpace.add(resources.get(1));
        depositSpace.setOpaque(false);

        leadercard.add(Box.createRigidArea(new Dimension(159, 125)));
        leadercard.add(depositButton);
        leadercard.add(Box.createRigidArea(new Dimension(159, 10)));
        leadercard.add(depositSpace);
    }

    /**
     * Class' constructor used in MoveResourcePanel and PlaceResourcePanel
     * @param gui is the GUI object linked to this panel
     * @param id is the id of the leader card
     * @param depositsPanel is the deposit panel
     */
    public LeaderCardPanel(GUI gui, int id, DepositsPanel depositsPanel) {
        this.depositsPanel = depositsPanel;
        this.id = id;
        this.gui = gui;

        leadercard = new JPanel();
        standardPanel = new JPanel();

        defaultSetup(gui.getClient().getClientModelView());

        setSpecialPowerButtons(gui.getClient().getClientModelView());

        this.add(leadercard);
        this.setOpaque(false);
    }

    /**
     * Class' constructor used in the composition of the other player board panel
     * @param clientModelView is the client model view in which are contained the information
     * @param id is the id of the leader card chosen
     * @param depositsPanel is the deposit panel
     */
    public LeaderCardPanel(ClientModelView clientModelView, int id, DepositsPanel depositsPanel) {
        this.depositsPanel = depositsPanel;
        this.id = id;

        leadercard = new JPanel();
        standardPanel = new JPanel();

        defaultSetup(clientModelView);

        setSpecialPowerButtons(clientModelView);

        this.add(leadercard);
        this.setOpaque(false);
    }

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     * @param id is the id of the leader card chosen
     * @param warehouseAndDevSpacesPanel is the panel that contains the warehouse and the development spaces
     */
    public LeaderCardPanel(GUI gui, int id, WarehouseAndDevSpacesPanel warehouseAndDevSpacesPanel) {
        this.warehouseAndDevSpacesPanel = warehouseAndDevSpacesPanel;
        this.id = id;
        this.gui = gui;

        leadercard = new JPanel();
        standardPanel = new JPanel();

        defaultSetup(gui.getClient().getClientModelView());

        LeaderCardType leaderCardType = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType();

        if(leaderCardType.equals(LeaderCardType.PRODUCTION_POWER)) setMarbleAndProdPower(chooseProdPowerButton);

        if(leaderCardType.equals(LeaderCardType.WHITE_MARBLE)) setMarbleAndProdPower(chooseWhiteMarbleButton);

        if(leaderCardType.equals(LeaderCardType.EXTRA_DEPOSIT) && gui.getClient().getClientModelView().getLiteBoard().getDeposits().size() > 3) setExtraDeposit(depositForBuyDevCardAndProdPower, gui.getClient().getClientModelView());

        this.add(leadercard);
        this.setOpaque(false);
    }

    /**
     * Class' constructor for extra deposit leader cards used in BuyDevCardPanel
     * @param gui is the GUI object linked to this panel
     * @param id is the id of the leader card chosen
     * */
    public LeaderCardPanel(GUI gui, int id, BuyDevCardPanel buyDevCardPanel) {
        this.buyDevCardPanel = buyDevCardPanel;
        this.id = id;
        this.gui = gui;
        c = new GridBagConstraints();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);
        JLabel depositLabel = new JLabel();

        String path = null;
        int i =0;
        for (LeaderCard leaderCard : gui.getClient().getClientModelView().getMyPlayer().getLeaderCards()){
            if(leaderCard.getStrategy() instanceof ConcreteStrategyDeposit && leaderCard.getStrategy().isActive()){
                try {
                    switch(leaderCard.getResourceType()){
                        case SERVANT -> path = "/images/discount/servantExtra.png";
                        case STONE -> path = "/images/discount/stoneExtra.png";
                        case SHIELD -> path = "/images/discount/shieldExtra.png";
                        case COIN -> path = "/images/discount/coinExtra.png";
                        default -> throw new IllegalStateException("Unexpected value: " + leaderCard.getResourceType());
                    }
                    depositLabel.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path)).readAllBytes()).getImage().getScaledInstance(200, 80, Image.SCALE_AREA_AVERAGING)));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                c.gridx=i;
                c.gridy=0;
                i++;
            }
        }
        c.gridx=0;
        c.gridy=0;
        mainPanel.add(depositLabel,c);
        resourcesInDeposit(gui.getClient().getClientModelView());

        setExtraDeposit(depositForBuyDevCardAndProdPower, gui.getClient().getClientModelView());

        this.add(mainPanel);
        this.setOpaque(false);
    }

    public void resourcesInDeposit(ClientModelView clientModelView){
        //resources.get(0).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(0).getResourcetype().path)).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));

    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == depositButton){
            depositsPanel.getIdDepot().add(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getDepositPosition() + 1);
        }

        if(e.getSource() == depositForBuyDevCardAndProdPower){
            warehouseForBuyDevCardPanel.getChosenWarehouses().add(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getDepositPosition() + 1);
            warehouseForBuyDevCardPanel.getChosenResources().add(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getResourceType());
        }
        if(e.getSource() == chooseProdPowerButton){
            devSpacesPanel.getNewProductionPowers().add(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getProductionPowerPosition() + 1);
        }
        if(e.getSource() == chooseWhiteMarbleButton){
            numOfWhiteChoices++;
        }
    }

    /**
     * Method that sets the special power buttons based on the type of the leader card
     * @param clientModelView is the client model view in which are contained the information
     */
    public void setSpecialPowerButtons(ClientModelView clientModelView){
        LeaderCardType leaderCardType = clientModelView.getMyPlayer().getLeaderCards().get(position).getType();
        if(leaderCardType.equals(LeaderCardType.PRODUCTION_POWER)) setMarbleAndProdPower(chooseProdPowerButton);

        if(leaderCardType.equals(LeaderCardType.WHITE_MARBLE)) setMarbleAndProdPower(chooseWhiteMarbleButton);

        if(leaderCardType.equals(LeaderCardType.EXTRA_DEPOSIT) && clientModelView.getLiteBoard().getDeposits().size() > 3) setExtraDeposit(depositButton, clientModelView);
    }

    /**
     * Class' getter
     * @return numOfWhiteChoices
     */
    public int getNumOfWhiteChoices() {
        return numOfWhiteChoices;
    }

    /**
     * Class' getter
     * @return chooseWhiteMarbleButton
     */
    public JButton getChooseWhiteMarbleButton() {
        return chooseWhiteMarbleButton;
    }

    /**
     * Class' getter
     * @return the id of the leader card
     */
    public int getId() {
        return id;
    }

    /**
     * Class' getter
     * @return the deposit button
     */
    public JButton getDepositButton() {
        return depositButton;
    }

    /**
     * Class' getter
     * @return the choose production power button
     */
    public JButton getChooseProdPowerButton() { return chooseProdPowerButton; }

    /**
     * Class' getter
     * @return depositForBuyDevCardAndProdPower button
     */
    public JButton getDepositForBuyDevCardAndProdPower() {
        return depositForBuyDevCardAndProdPower;
    }


}
