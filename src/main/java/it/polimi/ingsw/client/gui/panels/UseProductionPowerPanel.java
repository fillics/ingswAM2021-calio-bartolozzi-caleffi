package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.pregamepanels.AdditionalResourcePanel;
import it.polimi.ingsw.controller.client_packets.PacketUseAndChooseProdPower;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.leadercards.LeaderCardType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Class that creates the panel used to do the use production power operation
 */
public class UseProductionPowerPanel extends JPanel implements ActionListener {
    private Image background;
    private final GUI gui;
    private final GridBagConstraints c;
    private WarehouseAndDevSpacesPanel smallBoard;
    private JPanel leaderCards, mainPanel, buttons;
    private final JButton confirm = new JButton("CONFIRM");
    private final JButton back = new JButton("BACK TO THE BOARD");

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
    public UseProductionPowerPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {}

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



        this.setOpaque(false);
    }

    /**
     * Method that creates all the buttons of the panel
     */
    public void createButtons(){
        buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(970, 50));

        confirm.setFont(new Font("Times New Roman", confirm.getFont().getStyle(), 15));
        confirm.setPreferredSize(new Dimension(250, 50));

        back.setFont(new Font("Times New Roman", back.getFont().getStyle(), 15));
        back.setPreferredSize(new Dimension(250, 50));

        confirm.addActionListener(this);
        back.addActionListener(this);

        buttons.add(back);
        buttons.add(Box.createRigidArea(new Dimension(180, 50)));
        buttons.add(confirm);

        buttons.setOpaque(false);

    }

    /**
     * Method that creates the main panel
     */
    public void createMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        createUnderBoard();
        mainPanel.add(smallBoard);

        createButtons();
        mainPanel.add(buttons);

        mainPanel.setOpaque(false);


    }

    /**
     * Method that creates the under board panel
     */
    public void createUnderBoard(){

        smallBoard = new WarehouseAndDevSpacesPanel(this);

        if (gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(0).getTopCard() == null) {
            smallBoard.getDevSpacesPanel().getDevSpace1().setVisible(false);
        }
        if (gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(1).getTopCard() == null) {
            smallBoard.getDevSpacesPanel().getDevSpace2().setVisible(false);
        }
        if (gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(2).getTopCard() == null) {
            smallBoard.getDevSpacesPanel().getDevSpace3().setVisible(false);
        }

    }

    /**
     * Method that creates the leader cards panel
     */
    public void createLeaderCardsPanel(){
        leaderCards = new JPanel();
        leaderCards.setLayout(new GridBagLayout());
        c.insets = new Insets(10, 10, 10, 10);

        ArrayList<LeaderCardPanel> leaderCardPanels = new ArrayList<>();
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if ((gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getType().equals(LeaderCardType.PRODUCTION_POWER)||
                    gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getType().equals(LeaderCardType.EXTRA_DEPOSIT)) &&
                    gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy().isActive()){
                LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(gui, gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId(), smallBoard.getWarehousePanel(), smallBoard.getDevSpacesPanel());
                leaderCardPanels.add(leaderCardPanel1);
                leaderCardPanel1.setOpaque(false);
                c.gridx=0;
                c.gridy=i;
                leaderCards.add(leaderCardPanel1, c);
            }
        }
        leaderCards.setOpaque(false);

    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            gui.switchPanels(new BoardPanel(gui));
        }

        if(e.getSource() == confirm){

            boolean checkProd = false;
            for(int i : smallBoard.getDevSpacesPanel().getProductionPowers()){
                if(gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(i - 1).getTopCard() != null){
                    if (gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(i - 1).getTopCardProductionPower().getResourceObtained().containsKey(ResourceType.JOLLY)) {
                        checkProd = true;
                        break;
                    }
                }
            }
            for(int i : smallBoard.getDevSpacesPanel().getNewProductionPowers()){
                if (gui.getClient().getClientModelView().getLiteBoard().getSpecialProductionPower().get(i - 1).getResourceObtained().containsKey(ResourceType.JOLLY)) {
                    checkProd = true;
                    break;
                }
            }
            ArrayList<ResourceType> newResources = new ArrayList<>();
            if(checkProd){
                gui.switchPanels(new AdditionalResourcePanel(gui, smallBoard.getDevSpacesPanel().getProductionPowers(), smallBoard.getDevSpacesPanel().getNewProductionPowers(), smallBoard.getWarehousePanel().getChosenResources(), smallBoard.getWarehousePanel().getChosenWarehouses()));
            }
            else {
                gui.getClient().sendPacketToServer(new PacketUseAndChooseProdPower(smallBoard.getDevSpacesPanel().getProductionPowers(), smallBoard.getDevSpacesPanel().getNewProductionPowers(), smallBoard.getWarehousePanel().getChosenResources(), smallBoard.getWarehousePanel().getChosenWarehouses(), newResources));
                gui.switchPanels(new BoardPanel(gui));
            }
        }
    }

    /**
     * Class' getter
     * @return the gui of the panel
     */
    public GUI getGui() {
        return gui;
    }
}
