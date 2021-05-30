package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionalResourcePanel extends JPanel implements ActionListener {
    private GUI gui;
    private JButton coin = new JButton("COIN");
    private JButton stone = new JButton("STONE");
    private JButton servant = new JButton("SERVANT");
    private JButton shield = new JButton("SHIELD");

    public AdditionalResourcePanel(GUI gui) {
        this.gui = gui;
        this.setBounds(0,0,1500,1000);
        addActionEvent();
        addAll();
        setAllBounds();
        this.setVisible(true);
        this.setLayout(null);

    }

    public void addActionEvent(){
        coin.addActionListener(this);
        stone.addActionListener(this);
        servant.addActionListener(this);
        shield.addActionListener(this);
    }

    public void addAll(){
        this.add(coin);
        this.add(stone);
        this.add(servant);
        this.add(shield);
    }

    public void setAllBounds(){
        coin.setBounds(0,0, 150, 30);
        stone.setBounds(160, 0, 150, 30);
        servant.setBounds(320, 0, 150, 30);
        shield.setBounds(480, 0, 150, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
