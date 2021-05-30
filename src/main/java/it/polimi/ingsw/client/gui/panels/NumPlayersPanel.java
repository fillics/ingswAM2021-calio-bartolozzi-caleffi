package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumPlayersPanel extends JPanel implements ActionListener {
    private GUI gui;
    private JButton confirm = new JButton("CONFIRM");
    JTextField loginTextField = new JTextField();

    public NumPlayersPanel(GUI gui) {
        this.gui = gui;
        this.setBounds(0,0,1000,1000);
        loginTextField.setBounds(300, 150, 150, 30);
        confirm.setBounds(50, 300, 100, 30);
        this.add(confirm);
        this.add(loginTextField);

        confirm.addActionListener(this);
        this.setVisible(true);
        this.setLayout(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String number_of_players;
        number_of_players = loginTextField.getText();
        gui.getClient().sendNumPlayers(Integer.parseInt(number_of_players));
    }
}
