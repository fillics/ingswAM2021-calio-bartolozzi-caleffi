package it.polimi.ingsw.client.gui.panels;


import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.constants.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerPanel extends JPanel implements ActionListener {

    private GUI gui;
    private JLabel ipAddress = new JLabel("Insert the server IP address");
    private JTextField ipAddressTextField = new JTextField();
    private JLabel serverPort = new JLabel("Insert the server port");
    private JTextField serverPortTextField = new JTextField();
    private JButton connectButton = new JButton("CONNECT");
    private JButton resetButton = new JButton("RESET");



    public ServerPanel(GUI gui){
        this.gui = gui;
        this.setBounds(0,0,1000,1000);
        this.setLayout(null);
        createPanel();

 }


    private void createPanel(){

        ipAddress.setBounds(50, 150, 200, 30);
        ipAddressTextField.setBounds(300, 150, 150, 30);
        serverPort.setBounds(50, 250, 200, 30);
        serverPortTextField.setBounds(300, 250, 150, 30);
        connectButton.setBounds(50, 300, 100, 30);
        connectButton.addActionListener(this);
        resetButton.setBounds(200, 300, 100, 30);
        resetButton.addActionListener(this);

        ipAddressTextField.setText("127.0.0.1");
        serverPortTextField.setText("1234");
        this.add(ipAddress);
        this.add(ipAddressTextField);
        this.add(serverPort);
        this.add(serverPortTextField);
        this.add(connectButton);
        this.add(resetButton);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectButton) {
            String ipAddress, port;
            ipAddress = ipAddressTextField.getText();
            port = serverPortTextField.getText();
            Constants.setAddressServer(ipAddress);
            Constants.setPort(Integer.parseInt(port));

            // TODO: 28/05/2021 togliere finestra di dialogo, mettere solo panel di messaggi risposta da server
            gui.switchPanels(gui.getPanels().get(1));
            gui.getClient().serverConnection(2);

        }
        if (e.getSource() == resetButton) {
            ipAddressTextField.setText("");
            serverPortTextField.setText("");
        }

    }



}