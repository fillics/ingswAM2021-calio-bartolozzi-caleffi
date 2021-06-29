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

/**
 * Class that creates the panel to show the board of other players
 */
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
    private final ClientModelView clientModelView;

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
     * @param clientModelView is the client model view in which are contained the information
     */
    public OtherPlayersBoardPanel(GUI gui, ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
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


    /**
     * Method that creates the board panel that is composed by the faith track panel and the under board panel
     */
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


    /**
     * Method that creates the "back to your personal board" button
     */
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

    /**
     * Method that sets up the button
     * @param button is the button to set up
     */
    public void setupButtons(JButton button){
        button.setPreferredSize(new Dimension(250,50));
        changeBackground(button);
        button.addActionListener(this);
    }

    /**
     * Method that creates the under board panel
     */
    public void createUnderBoard(){

        underBoard = new JPanel();
        underBoard.setLayout(new BoxLayout(underBoard, BoxLayout.X_AXIS));

        warehousePanel = new WarehousePanel(clientModelView);
        devSpacesPanel = new DevSpacesPanel(clientModelView);
        underBoard.add(warehousePanel);
        underBoard.add(devSpacesPanel);
    }

    /**
     * Method that creates the faith track panel
     */
    public void createFaithTrackPanel(){

        faithTrackPanel = new JPanel();
        faithTrackPanel.setLayout(new BoxLayout(faithTrackPanel, BoxLayout.X_AXIS));

        FaithTrackPanel faithTrack = new FaithTrackPanel(clientModelView);
        faithTrack.setPreferredSize(new Dimension(970,200));
        faithTrackPanel.add(faithTrack);
        faithTrackPanel.setOpaque(false);
    }

    /**
     * Method that creates the leader cards panel
     */
    public void createLeaderCardsPanel(){
        leaderCards = new JPanel();
        leaderCards.setLayout(new GridBagLayout());
        c.insets = new Insets(10, 10, 10, 10);


        leaderCardPanels = new ArrayList<>();
        for(int i = 0; i < clientModelView.getMyPlayer().getLeaderCards().size(); i++){
            LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(clientModelView, clientModelView.getMyPlayer().getLeaderCards().get(i).getId(), warehousePanel.getDepositsPanel());
            leaderCardPanels.add(leaderCardPanel1);
            leaderCardPanel1.setOpaque(false);
            c.gridx=0;
            c.gridy=i;
            leaderCards.add(leaderCardPanels.get(i), c);
        }
        leaderCards.setOpaque(false);

    }

    /**
     * Method that changes the background of a button
     * @param button is the button chosen
     */
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

    /**
     * Method that disables all the buttons not needed
     * @param devSpacesPanel is the development spaces panel
     * @param warehousePanel is the warehouse panel
     */
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

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == backButton){
            gui.switchPanels(new BoardPanel(gui));
        }

    }
}

