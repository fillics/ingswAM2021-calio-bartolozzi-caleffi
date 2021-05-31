package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseResourcesPanel extends JPanel implements ActionListener {

    private GUI gui;
    private final JButton coin = new JButton();
    private final JButton stone = new JButton();
    private final JButton servant = new JButton();
    private final JButton shield = new JButton();
    private GridBagConstraints c;


    // TODO: 31/05/2021 mettere nel costruttore gui
    public ChooseResourcesPanel() {
        //this.gui = gui;
        c = new GridBagConstraints();
        c.insets = new Insets(0, 2, 0, 2);

        try {
            coin.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/coin.png").readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=0;
            c.gridy=0;
            this.add(coin, c);

            stone.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/stone.png").readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=1;
            c.gridy=0;
            this.add(stone, c);

            servant.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/servant.png").readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=2;
            c.gridy=0;
            c.insets = new Insets(50,50,50,50);
            this.add(servant, c);

            shield.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/shield.png").readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=3;
            c.gridy=0;
            this.add(shield, c);

        } catch (IOException e) {
            e.printStackTrace();
        }

        coin.addActionListener(this);
        shield.addActionListener(this);
        servant.addActionListener(this);
        stone.addActionListener(this);
        this.setLayout(new GridBagLayout());

        this.setVisible(true);
        this.setSize(new Dimension(500,500));

    }

    public void setDisabled(){
        coin.setEnabled(false);
        stone.setEnabled(false);
        shield.setEnabled(false);
        servant.setEnabled(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        ResourceType resourceType;

        if (e.getSource() == coin) {
            resourceType = ResourceType.COIN;
            setDisabled();
        }
        if (stone.equals(e.getSource())) {
            resourceType = ResourceType.STONE;
            setDisabled();
        }
        if (servant.equals(e.getSource())) {
            resourceType = ResourceType.SERVANT;
            setDisabled();
        }
        if (shield.equals(e.getSource())) {
            resourceType = ResourceType.SHIELD;
            setDisabled();
        }
    }


    public static void main(String[] args) {

        ChooseResourcesPanel chooseResourcesPanel = new ChooseResourcesPanel();
        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale
        frame.add(chooseResourcesPanel);

        frame.setTitle("Master of Renaissance");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(1920, 1080);


    }
}
