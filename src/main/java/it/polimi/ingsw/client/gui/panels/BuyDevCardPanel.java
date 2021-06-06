package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketChooseDiscount;

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
    private ClientModelView clientModelView;
    private final JButton chooseDiscountBtn = new JButton("CHOOSE DISCOUNT");
    private final JButton confirmDiscountBtn = new JButton("CONFIRM");

    private ArrayList<Integer> leaderCardsIDs;
    private ArrayList<JButton> jButtons;
    private JButton leaderCard1, leaderCard2;

    private JPanel cards, buttons;


    private JPanel devGridPanel, underGridPanel, leftPanel, rightPanel, leaderCards, chooseDiscountPanel, smallBoard, buttonsPanel;
    private JButton devCard1, devCard2, devCard3, devCard4, devCard5, devCard6, devCard7, devCard8, devCard9, devCard10, devCard11, devCard12;


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
        createSmallBoard();
        createButtonsPanel();
    }


    public void createButtonsPanel(){

    }

    public void createSmallBoard(){
        smallBoard = new JPanel();
        createDeposits();
        createDevSpace();
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
        devGridPanel = new JPanel();
        devGridPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        devCard1 = new JButton();
        devCard2 = new JButton();
        devCard3 = new JButton();
        devCard4 = new JButton();
        devCard5 = new JButton();
        devCard6 = new JButton();
        devCard7 = new JButton();
        devCard8 = new JButton();
        devCard9 = new JButton();
        devCard10 = new JButton();
        devCard11 = new JButton();
        devCard12 = new JButton();
        try {
            setButtons(devGridPanel, 125, 189, 5,5,5,5);
        } catch (IOException ignored) {}

        devGridPanel.setOpaque(false);

    }

    public void setButtons(JPanel panel, int width, int height, int top, int left, int bottom, int right) throws IOException {
        clientModelView = gui.getClient().getClientModelView();
        c.insets = new Insets(top,left,bottom,right);
        devCard1.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(8).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard1.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(devCard1,c);
        devCard2.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(9).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard2.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(devCard2,c);
        devCard3.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(10).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard3.addActionListener(this);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(devCard3,c);
        devCard4.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(11).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard4.addActionListener(this);
        c.gridx = 3;
        c.gridy = 0;
        panel.add(devCard4,c);
        devCard5.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(4).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard5.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(devCard5,c);
        devCard6.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(5).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard6.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(devCard6,c);
        devCard7.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(6).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard7.addActionListener(this);
        c.gridx = 2;
        c.gridy = 1;
        panel.add(devCard7,c);
        devCard8.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(7).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard8.addActionListener(this);
        c.gridx = 3;
        c.gridy = 1;
        panel.add(devCard8,c);
        devCard9.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(0).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard9.addActionListener(this);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(devCard9,c);
        devCard10.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(1).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard10.addActionListener(this);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(devCard10,c);
        devCard11.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(2).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard11.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        panel.add(devCard11,c);
        devCard12.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(3).getPath()).readAllBytes()).getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)));
        devCard12.addActionListener(this);
        c.gridx = 3;
        c.gridy = 2;
        panel.add(devCard12,c);

    }

    public void createDeposits(){

    }

    public void createDevSpace(){

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chooseDiscountBtn){
            confirmDiscountBtn.setVisible(true);

        }
        if(e.getSource() == confirmDiscountBtn){
            gui.sendPacketToServer(new PacketChooseDiscount(leaderCardsIDs));
        }

        if (e.getSource() == leaderCard1) {
            int id = 0;
            id = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(0).getId();
            leaderCardsIDs.add(id);
        }
        if (e.getSource() == leaderCard2) {
            int id;
            id = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(1).getId();
            leaderCardsIDs.add(id);
        }

    }
}
