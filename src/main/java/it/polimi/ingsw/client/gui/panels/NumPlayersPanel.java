package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class NumPlayersPanel extends JPanel implements ActionListener {
    private GUI gui;
    private JLabel numPlayers;
    private JPanel numbersPanel;
    private JButton btn1, btn2, btn3, btn4;
    private  int number_of_players;
    public NumPlayersPanel(GUI gui) {
        this.gui = gui;
        this.setOpaque(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(300 ,400)));
        createNumbersGrid();
    }
    public void createNumbersGrid(){
        numPlayers = new JLabel(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS.getMessage());
        this.add(numPlayers);
        numbersPanel = new JPanel();
        numbersPanel.setLayout(new GridLayout(2,2));
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        numbersPanel.add(btn1);
        numbersPanel.add(btn2);
        numbersPanel.add(btn3);
        numbersPanel.add(btn4);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        this.add(numbersPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) number_of_players = 1;
        if (e.getSource() == btn2) number_of_players = 2;
        if (e.getSource() == btn3) number_of_players = 3;
        if (e.getSource() == btn4) number_of_players = 4;
        gui.getClient().sendNumPlayers(number_of_players);
    }
}