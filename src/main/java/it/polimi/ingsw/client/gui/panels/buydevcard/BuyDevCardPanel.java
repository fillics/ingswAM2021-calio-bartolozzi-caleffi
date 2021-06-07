package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.gui.panels.DevSpacesPanel;
import it.polimi.ingsw.client.gui.panels.WarehousePanel;
import it.polimi.ingsw.controller.client_packets.PacketBuyDevCard;
import it.polimi.ingsw.controller.client_packets.PacketChooseDiscount;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyDiscount;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BuyDevCardPanel extends JPanel implements ActionListener {
    private GUI gui;
    private Image background;
    private GridBagConstraints c;
    private final JButton chooseDiscountBtn = new JButton("CHOOSE DISCOUNT");
    private final JButton confirmDiscountBtn = new JButton("CONFIRM DISCOUNT");
    private final JButton backBtn = new JButton("BACK");
    private final JButton confirmBuyBtn = new JButton("CONFIRM");

    private ArrayList<Integer> leaderCardsIDs;
    private ArrayList<JButton> jButtons;
    private JButton leaderCard1, leaderCard2;

    private JPanel cards, buttons;




    private DevGridBuyCardPanel devGridPanel;
    private WarehouseForBuyDevCardPanel warehousePanel;
    private DevSpaceBuyDevCardPanel devSpacesPanel;
    private JPanel underGridPanel, leftPanel, rightPanel, leaderCards, chooseDiscountPanel, smallBoard,
            buttonsPanel;


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public BuyDevCardPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/backgroundGame2.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException ignored) {
        }
        c = new GridBagConstraints();


        this.setLayout(new GridBagLayout());
        this.setOpaque(false);

        createLeftPanel();
        c.gridx=0;
        c.gridy=0;
        this.add(leftPanel, c);

        createRightPanel();
        c.gridx=1;
        c.gridy=0;
        this.add(rightPanel, c);

    }

    public void createRightPanel(){
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());

        createSmallBoard();
        c.gridx=0;
        c.gridy=0;
        rightPanel.add(smallBoard, c);

        createButtonsPanel();
        c.gridx=0;
        c.gridy=1;
        c.insets = new Insets(10, 0,0,0);
        rightPanel.add(buttonsPanel, c);

        rightPanel.setOpaque(false);
    }


    public void createButtonsPanel(){
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());

        c.gridx=0;
        c.gridy=0;
        buttonsPanel.add(backBtn, c);
        backBtn.addActionListener(this);
        c.gridx=1;
        c.gridy=0;
        buttonsPanel.add(confirmBuyBtn, c);
        confirmBuyBtn.addActionListener(this);
    }

    public void createSmallBoard(){
        smallBoard = new JPanel();
        smallBoard.setLayout(new GridBagLayout());

        createDeposits();
        c.gridx=0;
        c.gridy=0;
        smallBoard.add(warehousePanel, c);

        createDevSpace();
        c.gridx=1;
        c.gridy=0;
        smallBoard.add(devSpacesPanel, c);
    }



    public void createLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());

        createDevGrid();
        c.gridx=0;
        c.gridy=0;
        leftPanel.add(devGridPanel, c);

        createUnderGridPanel();
        c.gridx=0;
        c.gridy=1;
        leftPanel.add(underGridPanel, c);

        leftPanel.setOpaque(false);

    }

    public void createUnderGridPanel(){

        underGridPanel = new JPanel();
        underGridPanel.setLayout(new GridBagLayout());

        cards = new JPanel();
        leaderCardsIDs = new ArrayList<>();
        cards.setLayout(new GridBagLayout());
        c.insets = new Insets(0,10,0,10);
        jButtons = new ArrayList<>();

        leaderCard1 = new JButton();
        leaderCard2 = new JButton();

        jButtons.add(leaderCard1);
        jButtons.add(leaderCard2);
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyDiscount &&
                    gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy().isActive()){
                try {
                    jButtons.get(i).setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getPath()).readAllBytes()).getImage().getScaledInstance(125, 189, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jButtons.get(i).addActionListener(this);
                c.gridx=i;
                c.gridy=0;

                int finalI = i;
                jButtons.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        jButtons.get(finalI).setBackground(new Color(51, 180, 76));
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


        chooseDiscountPanel = new JPanel();
        chooseDiscountPanel.setLayout(new GridBagLayout());
        chooseDiscountBtn.addActionListener(this);
        confirmDiscountBtn.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        chooseDiscountPanel.add(chooseDiscountBtn, c);
        c.gridx=0;
        c.gridy=1;
        confirmDiscountBtn.setVisible(false);
        chooseDiscountPanel.add(confirmDiscountBtn, c);


        c.gridx=1;
        c.gridy=0;
        underGridPanel.add(chooseDiscountPanel, c);
    }

    public void createDevGrid(){
        devGridPanel = new DevGridBuyCardPanel(gui);

    }



    public void createDeposits(){
        warehousePanel = new WarehouseForBuyDevCardPanel(gui);

    }

    public void createDevSpace(){
        devSpacesPanel = new DevSpaceBuyDevCardPanel(gui);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chooseDiscountBtn){
            confirmDiscountBtn.setVisible(true);

        }
        if(e.getSource() == confirmDiscountBtn && leaderCardsIDs.size()!=0){
            gui.sendPacketToServer(new PacketChooseDiscount(leaderCardsIDs));
        }

        if (e.getSource() == leaderCard1) {
            int id = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(0).getId();
            leaderCardsIDs.add(id);
        }
        if (e.getSource() == leaderCard2) {
            int id = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(1).getId();
            leaderCardsIDs.add(id);
        }

        if(e.getSource() == confirmBuyBtn){

            if(warehousePanel.getChosenWarehouses().size()!=0 && warehousePanel.getChosenResources().size()!=0 && devSpacesPanel.getIdDevSpace()!=0){
                gui.sendPacketToServer(new PacketBuyDevCard(devGridPanel.getIdCard(), warehousePanel.getChosenResources(),
                        warehousePanel.getChosenWarehouses(), devSpacesPanel.getIdDevSpace()));
            }
            else{
                JOptionPane.showMessageDialog(gui.getjFrame(), "Fill every field to buy the development card!");
            }

        }

        if(e.getSource() == backBtn){
            gui.switchPanels(new BoardPanel(gui));
        }

    }
}
