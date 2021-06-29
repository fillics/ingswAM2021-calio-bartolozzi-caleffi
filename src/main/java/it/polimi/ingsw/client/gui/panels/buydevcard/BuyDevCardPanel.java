package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.gui.panels.WarehouseAndDevSpacesPanel;
import it.polimi.ingsw.controller.client_packets.PacketBuyDevCard;
import it.polimi.ingsw.controller.client_packets.PacketChooseDiscount;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyDiscount;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class BuyDevCardPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private Image background;
    private final GridBagConstraints c;
    private final JButton confirmDiscountBtn = new JButton("CONFIRM DISCOUNT");
    private final JButton backBtn = new JButton("BACK");
    private final JButton confirmBuyBtn = new JButton("CONFIRM");

    private ArrayList<Integer> leaderCardsIDs;
    private ArrayList<JButton> jButtons;
    private JButton leaderCard1, leaderCard2;

    private JPanel centralPanel;
    private DevGridPanel devGridPanel;
    private JPanel underGridPanel;
    private JPanel leftPanel, rightPanel, buttonsPanel;
    private WarehouseAndDevSpacesPanel smallBoard;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public BuyDevCardPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException ignored) {}
        c = new GridBagConstraints();

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setOpaque(false);

        createCentralPanel();
        c.gridx=0;
        c.gridy=0;
        mainPanel.add(centralPanel, c);

        createButtonsPanel();
        c.gridx=0;
        c.gridy=1;
        c.insets = new Insets(10, 0,0,0);
        mainPanel.add(buttonsPanel, c);

        this.add(mainPanel);

    }

    public void createCentralPanel(){
        centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());

        createLeftPanel();
        c.gridx=0;
        c.gridy=0;
        centralPanel.add(leftPanel, c);

        createRightPanel();
        c.gridx=1;
        c.gridy=0;
        centralPanel.add(rightPanel, c);

        centralPanel.setOpaque(false);

    }
    public void createRightPanel(){
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());

        JLabel guideRight = new JLabel("Click the resources you want to use and in which development space you want to put the card");
        guideRight.setFont(new Font(guideRight.getFont().getName(), guideRight.getFont().getStyle(), 15));

        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(233, 226, 193));
        textPanel.add(guideRight);
        c.gridx=0;
        c.gridy=0;
        rightPanel.add(textPanel, c);

        createSmallBoard();
        c.gridx=0;
        c.gridy=1;
        rightPanel.add(smallBoard, c);


        rightPanel.setOpaque(false);
        rightPanel.setBackground(new Color(0,0,0,0));
    }

    public void createButtonsPanel(){
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        c.insets = new Insets(0,15,0,15);

        c.gridx=0;
        c.gridy=0;
        c.ipadx=50;
        c.ipady=25;
        buttonsPanel.add(backBtn, c);
        backBtn.addActionListener(this);
        c.gridx=1;
        c.gridy=0;
        buttonsPanel.add(confirmBuyBtn, c);
        confirmBuyBtn.addActionListener(this);
        buttonsPanel.setOpaque(false);
    }

    public void createSmallBoard(){
        smallBoard = new WarehouseAndDevSpacesPanel(this);

    }



    public void createLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());

        JLabel guide = new JLabel("Click on a card to see it bigger");
        guide.setFont(new Font(guide.getFont().getName(), guide.getFont().getStyle(), 15));


        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(233, 226, 193));
        textPanel.add(guide);
        c.gridx=0;
        c.gridy=0;
        leftPanel.add(textPanel, c);
        createDevGrid();
        c.gridx=0;
        c.gridy=1;
        leftPanel.add(devGridPanel, c);

        createUnderGridPanel();
        c.insets = new Insets(20,0,0,0);
        c.gridx=0;
        c.gridy=2;
        leftPanel.add(underGridPanel, c);

        leftPanel.setOpaque(false);

    }

    public void createUnderGridPanel(){

        underGridPanel = new JPanel();
        underGridPanel.setLayout(new GridBagLayout());

        JPanel cards = new JPanel();
        leaderCardsIDs = new ArrayList<>();
        cards.setLayout(new GridBagLayout());
        c.insets = new Insets(0,10,0,10);
        jButtons = new ArrayList<>();

        leaderCard1 = new JButton();
        leaderCard2 = new JButton();

        jButtons.add(leaderCard1);
        jButtons.add(leaderCard2);
        String path;
        // TODO: 22/06/2021 al posto dell'istance of mettiamo equals?
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyDiscount &&
                    gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy().isActive()){
                confirmDiscountBtn.setVisible(true);
                try {
                    switch(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getResourceType()){
                        case SERVANT -> path = "/images/discount/discountServant.png";
                        case STONE -> path = "/images/discount/discountStone.png";
                        case SHIELD -> path = "/images/discount/discountShield.png";
                        case COIN -> path = "/images/discount/discountCoin.png";
                        default -> throw new IllegalStateException("Unexpected value: " + gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getResourceType());
                    }
                    jButtons.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path)).readAllBytes()).getImage().getScaledInstance(200, 80, Image.SCALE_AREA_AVERAGING)));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                jButtons.get(i).addActionListener(this);
                c.gridx=0;
                c.gridy=i;

                int finalI = i;
                jButtons.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        jButtons.get(finalI).setBackground(gui.getGreenColor());
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        jButtons.get(finalI).setBackground(UIManager.getColor("control"));
                    }
                });

                cards.add(jButtons.get(i), c);
            }

        }

        cards.setBackground(new Color(0,0,0,0));
        cards.setOpaque(true);

        c.gridx=0;
        c.gridy=0;
        underGridPanel.add(cards, c);


        JPanel chooseDiscountPanel = new JPanel();
        chooseDiscountPanel.setLayout(new GridBagLayout());
        chooseDiscountPanel.setOpaque(false);
        confirmDiscountBtn.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        confirmDiscountBtn.setVisible(false);
        chooseDiscountPanel.add(confirmDiscountBtn, c);


        c.gridx=1;
        c.gridy=0;
        underGridPanel.add(chooseDiscountPanel, c);
        underGridPanel.setOpaque(false);
    }

    public void createDevGrid(){
        devGridPanel = new DevGridPanel(this);
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == confirmDiscountBtn){
            if(leaderCardsIDs.size()!=0){
                gui.getClient().sendPacketToServer(new PacketChooseDiscount(leaderCardsIDs));
                confirmDiscountBtn.setBackground(gui.getGreenColor());
            }
            else{
                JOptionPane.showMessageDialog(gui.getjFrame(), "Select the leader cards");
            }
        }

        if (e.getSource() == leaderCard1) {
            int id = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(0).getId();
            leaderCardsIDs.add(id);
            leaderCard1.setEnabled(false);
        }
        if (e.getSource() == leaderCard2) {
            int id = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(1).getId();
            leaderCardsIDs.add(id);
            leaderCard2.setEnabled(false);
        }

        if(e.getSource() == confirmBuyBtn){

            if(smallBoard.getWarehousePanel().getChosenWarehouses().size()!=0 && smallBoard.getWarehousePanel().getChosenResources().size()!=0
                    && smallBoard.getDevSpacesPanel().getIdDevSpace()!=0){
                gui.getClient().sendPacketToServer(new PacketBuyDevCard(devGridPanel.getIdCard(), smallBoard.getWarehousePanel().getChosenResources(),
                        smallBoard.getWarehousePanel().getChosenWarehouses(), smallBoard.getDevSpacesPanel().getIdDevSpace()));
                gui.switchPanels(new BoardPanel(gui));
            }
            else{
                JOptionPane.showMessageDialog(gui.getjFrame(), "Fill every field to buy the development card!");
            }

        }

        if(e.getSource() == backBtn){
            gui.switchPanels(new BoardPanel(gui));
        }

    }

    public GUI getGui() {
        return gui;
    }
}
