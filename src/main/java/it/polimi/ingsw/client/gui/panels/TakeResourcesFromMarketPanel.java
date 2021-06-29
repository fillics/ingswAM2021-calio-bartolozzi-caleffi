package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketTakeResourceFromMarket;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyMarble;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Class that creates the panel used to do the take resources from market operation
 */
public class TakeResourcesFromMarketPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private Image background;
    private GridBagConstraints c;
    private JPanel leaderCards, buttonPanel, mainPanel;
    private MarketPanel marketTrayPanel;
    private ArrayList<LeaderCardPanel> leaderCardPanels;
    private JButton confirm, back, resetBtn;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getDimension().width, gui.getDimension().height, null);
    }

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     */
    public TakeResourcesFromMarketPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {
        }
        c = new GridBagConstraints();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        createLeaderCardsPanel();
        c.gridx=0;
        c.gridy=0;
        mainPanel.add(leaderCards, c);

        createMarketTrayPanel();
        c.gridx=1;
        c.gridy=0;
        mainPanel.add(marketTrayPanel, c);

        createButtonPanel();
        c.gridx=2;
        c.gridy=0;
        mainPanel.add(buttonPanel, c);

        mainPanel.setOpaque(false);
        this.add(mainPanel);
    }

    /**
     * Method that creates the leader cards panel
     */
    private void createLeaderCardsPanel(){
        leaderCards = new JPanel();
        leaderCards.setLayout(new GridBagLayout());
        c.insets = new Insets(10, 10, 10, 10);

        leaderCardPanels = new ArrayList<>();
        int j=-1;
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyMarble &&
                    gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy().isActive()){
                LeaderCardPanel leaderCardPanel1 = new LeaderCardPanel(gui, gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId(),this);
                leaderCardPanels.add(leaderCardPanel1);
                j++;
                leaderCardPanel1.setOpaque(false);
                c.gridx=0;
                c.gridy=i;
                leaderCards.add(leaderCardPanels.get(j), c);
            }
        }
        leaderCards.setOpaque(false);
    }

    /**
     * Method that creates the market tray panel
     */
    public void createMarketTrayPanel(){
        JPanel marketPanel = new JPanel();
        marketPanel.setLayout(new BoxLayout(marketPanel, BoxLayout.X_AXIS));
        marketPanel.setPreferredSize(new Dimension(550,700));

        marketTrayPanel = new MarketPanel(gui,false);
        marketTrayPanel.setPreferredSize(new Dimension(550,700));

        marketPanel.add(marketTrayPanel);
    }

    /**
     * Method that creates the buttons used to go back or confirm the operation.
     */
    public void createButtonPanel(){
        buttonPanel= new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);

        confirm = new JButton("CONFIRM");
        back = new JButton("BACK TO THE BOARD");
        resetBtn = new JButton("RESET");
        setButton(confirm, gui.getGreenColor());
        setButton(back, gui.getGreenColor());
        setButton(resetBtn, Color.RED);
        c.gridx=0;
        c.gridy=0;
        buttonPanel.add(confirm,c);
        c.gridx=0;
        c.gridy=1;
        buttonPanel.add(back,c);
        c.gridx=0;
        c.gridy=2;
        buttonPanel.add(resetBtn,c);
    }

    /**
     * Method that colors the background of the button when the user hovers over it.
     * @param button is the button chosen
     * @param color is the color chosen
     */
    public void setButton(JButton button, Color color){
        button.setPreferredSize(new Dimension(250,50));
        button.addActionListener(this);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
               button.setBackground(color);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
               button.setBackground(UIManager.getColor("control"));
            }
        });
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList <Integer> leaderCards = new ArrayList<>();
        if(leaderCardPanels!=null){
            for (LeaderCardPanel leaderCardPanel : leaderCardPanels) {
                if (leaderCardPanel.getNumOfWhiteChoices()!=0) {
                    for(int i=0; i<leaderCardPanel.getNumOfWhiteChoices(); i++)
                        leaderCards.add(leaderCardPanel.getId());
                }
            }
        }
        if(e.getSource() == confirm){
            if(!marketTrayPanel.getLine().equals("") && marketTrayPanel.getNumline()!=0){
                gui.getClient().sendPacketToServer(new PacketTakeResourceFromMarket(marketTrayPanel.getLine(), marketTrayPanel.getNumline(), leaderCards));
                gui.switchPanels(new BoardPanel(gui));
            }
        }
        if(e.getSource() == back){
            gui.switchPanels(new BoardPanel(gui));
        }

        if(e.getSource() == resetBtn){
            marketTrayPanel.activateAllButtons();
        }
    }
}
