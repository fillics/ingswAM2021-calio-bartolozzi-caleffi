package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class OtherPlayersBoardPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private Image background;
    private final GridBagConstraints c;
    private final JButton backButton = new JButton("BACK TO YOUR PERSONAL BOARD");
    private JPanel operations;
    private JPanel leaderCards;
    private JPanel underBoard;
    private JPanel faithTrackPanel;
    private JPanel boardPanel;
    private final JPanel mainPanel;
    private ArrayList<LeaderCardPanel> leaderCardPanels;
    private WarehousePanel warehousePanel;
    private DevSpacesPanel devSpacesPanel;
    private ClientModelView clientModelView;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height-50, null);

    }

    public OtherPlayersBoardPanel(GUI gui, ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/backgroundGame2.png");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException ignored) {
        }
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

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


        disableButtons( devSpacesPanel, warehousePanel);

        c.gridx=0;
        c.gridy=0;
        this.add(mainPanel, c);


    }



    public void createBoardPanel(){
        boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));


        createFaithTrackPanel();
        boardPanel.add(faithTrackPanel);

        createUnderBoard();
        boardPanel.add(underBoard);


        boardPanel.setOpaque(false);
        boardPanel.setBackground(new Color(0,0,0,0));

        mainPanel.setOpaque(false);
    }


    public void createOperations(){
        operations = new JPanel();
        operations.setLayout(new GridBagLayout());

        c.insets = new Insets(10, 10, 10, 10);

        c.gridx=0;
        c.gridy=0;
        setupButtons(backButton);
        operations.add(backButton, c);


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

        warehousePanel = new WarehousePanel(clientModelView);
        devSpacesPanel = new DevSpacesPanel(clientModelView);
        underBoard.add(warehousePanel);
        underBoard.add(devSpacesPanel);
    }


    public void createFaithTrackPanel(){

        faithTrackPanel = new JPanel();
        faithTrackPanel.setLayout(new BoxLayout(faithTrackPanel, BoxLayout.X_AXIS));
        FaithTrackPanel faithTrack = new FaithTrackPanel(clientModelView);
        faithTrack.setPreferredSize(new Dimension(970,200));
        faithTrackPanel.add(faithTrack);
        faithTrackPanel.setOpaque(false);
    }

    public void createLeaderCardsPanel(){
        leaderCards = new JPanel();
        leaderCards.setLayout(new GridBagLayout());
        c.insets = new Insets(10, 10, 10, 10);


        leaderCardPanels = new ArrayList<>();
        System.out.println(clientModelView.getMyPlayer().getLeaderCards().size());
        for(int i = 0; i < clientModelView.getMyPlayer().getLeaderCards().size(); i++){
            LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(clientModelView, clientModelView.getMyPlayer().getLeaderCards().get(i).getId(), 159, 240, warehousePanel.getDepositsPanel());
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

    public void disableButtons(DevSpacesPanel devSpacesPanel, WarehousePanel warehousePanel){
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
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == backButton){
            gui.switchPanels(new BoardPanel(gui));
        }

    }
}

