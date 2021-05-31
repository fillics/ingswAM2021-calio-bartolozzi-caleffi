package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: 31/05/2021 fare che puoi scegliere il numero di player da una griglia con quattro pulsanti con i quattro numeri 
public class NumPlayersPanel extends JPanel implements ActionListener {
    private GUI gui;
    JLabel numPlayers = new JLabel(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS.getMessage());
    private JButton confirmButton = new JButton("CONFIRM");
    JTextField numPlayersTextField = new JTextField();

    public NumPlayersPanel(GUI gui) {
        this.gui = gui;
        this.setBounds(0,0,1000,1000);
        
        createPanel();
        
        this.add(confirmButton);
        this.add(numPlayersTextField);
        this.add(numPlayers);

        confirmButton.addActionListener(this);
        this.setVisible(true);
        this.setLayout(null);
    }


    private void createPanel(){
        numPlayers.setBounds(50, 150, 200, 30);
        numPlayersTextField.setBounds(300, 150, 150, 30);
        confirmButton.setBounds(50, 300, 100, 30);
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        String number_of_players;
        number_of_players = numPlayersTextField.getText();
        gui.getClient().sendNumPlayers(Integer.parseInt(number_of_players));
    }
}
