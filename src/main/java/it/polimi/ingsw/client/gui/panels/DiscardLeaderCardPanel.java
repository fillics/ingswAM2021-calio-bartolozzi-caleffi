package it.polimi.ingsw.client.gui.panels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketDiscardLeaderCard;

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
 * Class used to create the panel to show the leader card to discard during the game.
 */
public class DiscardLeaderCardPanel extends JPanel implements ActionListener {
    private Image background;
    private final GUI gui;
    private final GridBagConstraints c;
    private JButton leaderCard1, leaderCard2;
    private JButton confirm, back;
    private ArrayList<JButton> jButtons;
    private int id1 = 0;
    private JPanel cards, buttons;

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
    public DiscardLeaderCardPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        c.gridy=0;
        c.gridx=0;
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0,0,0,0));
        this.add(emptyPanel, c);

        createCards();
        c.gridx=0;
        c.gridy=1;
        this.add(cards, c);

        createButtons();
        c.gridx=0;
        c.gridy=2;
        this.add(buttons, c);
    }

    /**
     * Method that creates a panel that contains all the leader cards of the player
     */
    public void createCards(){
        cards = new JPanel();
        cards.setLayout(new GridBagLayout());
        c.insets = new Insets(0,10,0,10);
        jButtons = new ArrayList<>();

        leaderCard1 = new JButton();
        leaderCard2 = new JButton();

        jButtons.add(leaderCard1);
        jButtons.add(leaderCard2);
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(!gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getStrategy().isActive()){
                try {
                    jButtons.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getPath())).readAllBytes()).getImage().getScaledInstance(200, 302, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jButtons.get(i).addActionListener(this);
                c.gridx = i;
                c.gridy = 0;

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

    }

    /**
     * Method that creates the buttons used to go back or confirm the operation.
     */
    public void createButtons(){
        buttons = new JPanel();

        c.insets = new Insets(25,0,0,0);
        back = new JButton("BACK TO THE BOARD");
        confirm = new JButton("CONFIRM");
        buttons.setLayout(new GridBagLayout());
        c.gridy=0;
        c.gridx=0;

        setButton(back, gui.getGreenColor());
        buttons.add(back, c);

        c.gridy=0;
        c.gridx=1;

        setButton(confirm, gui.getGreenColor());
        buttons.add(confirm, c);

        buttons.setOpaque(false);


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
        if(e.getSource() == back){
            gui.switchPanels(new BoardPanel(gui));
            gui.createMessageFromServer("Back to your board");

        }
        if(id1 == 0){
            if (e.getSource() == leaderCard1) {
                id1 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(0).getId();
                leaderCard1.setEnabled(false);
            }
            if (e.getSource() == leaderCard2) {
                id1 = gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(1).getId();
                leaderCard2.setEnabled(false);
            }
        }
        else{
            if (e.getSource() == confirm) {
                gui.getClient().sendPacketToServer(new PacketDiscardLeaderCard(id1));
                gui.switchPanels(new BoardPanel(gui));
                gui.createMessageFromServer("Back to your board");
            }
        }
    }

}
