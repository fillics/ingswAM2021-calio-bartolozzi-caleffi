package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketEndConnection;
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
import java.util.Locale;
import java.util.Objects;

public class WinnerPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private GridBagConstraints c;

    private Image background;
    private JPanel playersPanel;
    private JPanel buttonPanel;
    private JPanel usernamePanel;
    private JButton closeButton;
    private final ArrayList<PlayerInfoEndMatch> players;
    private final String username;


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getWidth(), gui.getHeight()-50, null);
    }

    public WinnerPanel(GUI gui, ArrayList<PlayerInfoEndMatch> players, String username) {
        this.gui = gui;
        this.players = players;
        this.username = username;
        c = new GridBagConstraints();
        InputStream is = getClass().getResourceAsStream("/images/background/end.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {}


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        c.gridy=0;
        c.gridx=0;
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0,0,0,0));
        mainPanel.add(emptyPanel, c);

        createWinnerUsername();
        c.gridx=0;
        c.gridy=1;
        c.insets = new Insets(175,0,0,0);
        mainPanel.add(usernamePanel, c);

        c.insets = new Insets(10,10,0,10);

        if(players!=null){
            createPlayersPanel();
            c.gridx=0;
            c.gridy=2;
            mainPanel.add(playersPanel, c);
        }

        c.insets = new Insets(20,0,0,0);
        createButton();
        c.gridx=0;
        if(players!=null) c.gridy=3;
        else c.gridy=2;
        mainPanel.add(buttonPanel, c);
        mainPanel.setOpaque(false);

        this.add(mainPanel);

    }

    public void createWinnerUsername(){
        usernamePanel = new JPanel();
        usernamePanel.setPreferredSize(new Dimension(450, 50));

        JLabel winner = new JLabel("The winner is "+username.toUpperCase(Locale.ROOT));
        winner.setFont(new Font(winner.getFont().getName(), winner.getFont().getStyle(), 15));

        usernamePanel.setLayout(new GridBagLayout());
        winner.setHorizontalAlignment(JLabel.CENTER);

        c.gridy=0;
        c.gridx=0;
        c.insets = new Insets(0,0,0,0);
        usernamePanel.add(winner, c);
        usernamePanel.setBackground(gui.getGreenColor());
    }

    public void createPlayersPanel(){
        playersPanel = new JPanel();
        playersPanel.setLayout(new GridBagLayout());

        int i=0;
        for(PlayerInfoEndMatch player: players){
            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

            JLabel username = new JLabel(player.getUsername().toUpperCase(Locale.ROOT));
            username.setFont(new Font(username.getFont().getName(), username.getFont().getStyle(), 30));
            JLabel faithMarker = new JLabel("Position of Faith Marker: "+player.getFaithMarker());
            faithMarker.setFont(new Font(faithMarker.getFont().getName(), faithMarker.getFont().getStyle(), 20));
            JLabel numDevCards = new JLabel("Number of Dev Cards: "+player.getNumDevCards());
            numDevCards.setFont(new Font(numDevCards.getFont().getName(), numDevCards.getFont().getStyle(), 20));
            JLabel numCoins = new JLabel("Number of Coins: "+player.getNumCoins());
            numCoins.setFont(new Font(numCoins.getFont().getName(), numCoins.getFont().getStyle(), 20));
            JLabel numStones = new JLabel("Number of Stones: "+player.getNumStones());
            numStones.setFont(new Font(numStones.getFont().getName(), numStones.getFont().getStyle(), 20));
            JLabel numShields = new JLabel("Number of Shields: "+player.getNumShields());
            numShields.setFont(new Font(numShields.getFont().getName(), numShields.getFont().getStyle(), 20));
            JLabel numServants = new JLabel("Number of Servants: "+player.getNumServants());
            numServants.setFont(new Font(numServants.getFont().getName(), numServants.getFont().getStyle(), 20));
            JLabel totVictory = new JLabel("Total Victory Points: "+player.getTotVictory());
            totVictory.setFont(new Font(totVictory.getFont().getName(), totVictory.getFont().getStyle(), 20));


            playerPanel.add(username);
            playerPanel.add(faithMarker);
            playerPanel.add(numDevCards);
            playerPanel.add(numCoins);
            playerPanel.add(numStones);
            playerPanel.add(numShields);
            playerPanel.add(numServants);
            playerPanel.add(totVictory);

            c.gridx=i;
            c.gridy=0;
            i++;
            c.insets = new Insets(25,25,10,25);
            playersPanel.add(playerPanel, c);
            playerPanel.setOpaque(false);
            playerPanel.setBorder(gui.getBlackline());

        }

        playersPanel.setOpaque(false);
    }

    public void createButton(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        closeButton = new JButton("CLOSE CONNECTION");
        closeButton.addActionListener(this);
        closeButton.setPreferredSize(new Dimension(200, 50));
        c.gridx=0;
        c.gridy=0;
        buttonPanel.add(closeButton, c);
        buttonPanel.setOpaque(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeButton){
            gui.getClient().sendPacketToServer(new PacketEndConnection());
            // gui.getClient().getSocketClientConnection().getSocket().close();
            System.exit(0);
        }
    }
}
