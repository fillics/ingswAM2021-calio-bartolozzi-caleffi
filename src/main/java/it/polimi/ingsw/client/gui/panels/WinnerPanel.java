package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerInfoEndMatch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class WinnerPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private GridBagConstraints c;

    private Image background;
    private JPanel mainPanel, playersPanel, buttonPanel;
    private JButton closeButton;
    private ArrayList<PlayerInfoEndMatch> players;


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, gui.getDimension().width, gui.getDimension().height - 50, null);

    }

    public WinnerPanel(GUI gui, ArrayList<PlayerInfoEndMatch> players) {
        this.gui = gui;
        this.players = players;
        c = new GridBagConstraints();
        InputStream is = getClass().getResourceAsStream("/images/background/backgroundGame2.png");
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException ignored) {}


        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        createPlayersPanel();
        c.gridx=0;
        c.gridy=0;
        mainPanel.add(playersPanel, c);

        createButton();
        c.gridx=0;
        c.gridy=1;
        mainPanel.add(buttonPanel, c);

        this.add(mainPanel);


    }

    public void createPlayersPanel(){
        playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.X_AXIS));

        for(PlayerInfoEndMatch player: players){
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
            JLabel username = new JLabel(player.getUsername());
            JLabel faithMarker = new JLabel("Position of Faith Marker: "+player.getFaithMarker());
            JLabel numDevCards = new JLabel("Number of Dev Cards: "+player.getNumDevCards());
            JLabel numCoins = new JLabel("Number of Coins: "+player.getNumCoins());
            JLabel numStones = new JLabel("Number of Stones: "+player.getNumStones());
            JLabel numShields = new JLabel("Number of Shields: "+player.getNumShields());
            JLabel numServants = new JLabel("Number of Servants: "+player.getNumServants());
            JLabel totVictory = new JLabel("Total Victory Points: "+player.getTotVictory());

            playerPanel.add(username);
            playerPanel.add(faithMarker);
            playerPanel.add(numDevCards);
            playerPanel.add(numCoins);
            playerPanel.add(numStones);
            playerPanel.add(numShields);
            playerPanel.add(numServants);
            playerPanel.add(totVictory);

            playersPanel.add(playerPanel);

        }

        playersPanel.setOpaque(false);
    }

    public void createButton(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());


        closeButton = new JButton("CLOSE CONNECTION");
        closeButton.addActionListener(this);
        c.gridx=0;
        c.gridy=0;
        buttonPanel.add(closeButton, c);
        buttonPanel.setOpaque(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeButton){

        }
    }
}
