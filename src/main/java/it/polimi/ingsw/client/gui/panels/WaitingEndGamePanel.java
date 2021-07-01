package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.io.InputStream;

import java.util.Objects;

public class WaitingEndGamePanel extends JPanel {
    private final GUI gui;
    private final GridBagConstraints c;

    private Image background;
    private JPanel waiting;
    private  JPanel loadingPanel;
    private final JLabel waitingLabel = new JLabel("PLEASE WAIT OTHER PLAYERS...");


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getWidth(), gui.getHeight()-50, null);
    }

    public WaitingEndGamePanel(GUI gui) {
        this.gui = gui;

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

        createWaiting();
        c.gridx=0;
        c.gridy=1;
        c.insets = new Insets(175,0,0,0);
        mainPanel.add(waiting, c);

        loading();
        c.gridx=0;
        c.gridy=2;
        mainPanel.add(loadingPanel, c);

        mainPanel.setOpaque(false);

        this.add(mainPanel);

    }

    public void createWaiting(){
        waiting = new JPanel();
        waiting.setLayout(new GridBagLayout());
        waitingLabel.setFont(new Font(waitingLabel.getFont().getName(), waitingLabel.getFont().getStyle(), 30));

        c.gridx=0;
        c.gridy=0;
        waiting.add(waitingLabel, c);

        waiting.setOpaque(false);
    }


    public void loading(){
        loadingPanel = new JPanel();

        Icon imgIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/gif/ajax-loader.gif")));

        JLabel label = new JLabel(imgIcon);
        loadingPanel.setLayout(new GridBagLayout());

        c.gridx=0;
        c.gridy=0;
        loadingPanel.add(label, c);

        loadingPanel.setBackground(new Color(0,0,0,0));

    }


}