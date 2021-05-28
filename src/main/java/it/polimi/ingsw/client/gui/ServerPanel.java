package it.polimi.ingsw.client.gui;


import it.polimi.ingsw.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class ServerPanel extends JPanel implements ActionListener {

    JLabel ipAddress = new JLabel("Insert the server IP address");
    JTextField ipAddressTextField = new JTextField();
    JLabel serverPort = new JLabel("Insert the server port");
    JTextField serverPortTextField = new JTextField();
    JButton connectButton = new JButton("CONNECT");
    JButton resetButton = new JButton("RESET");



    public ServerPanel(){

        this.setBounds(0,0,1000,1000);
        this.setLayout(null);
        createPanel();
        addComponentsToContainer();
        addActionEvent(connectButton);
        addActionEvent(resetButton);
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


    }



    public void addComponentsToContainer() {
        this.add(ipAddress);
        this.add(ipAddressTextField);
        this.add(serverPort);
        this.add(serverPortTextField);
        this.add(connectButton);
        this.add(resetButton);


    }


    public void addActionEvent(JButton button) {
        button.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectButton) {
            /*String ipAddress, port;
            ipAddress = ipAddressTextField.getText();
            port = serverPortTextField.getText();
            Constants.setAddressServer(ipAddress);
            Constants.setPort(Integer.parseInt(port));*/

            //JOptionPane.showMessageDialog(this, "Connected");
            GUI.switchPanels(GUI.getPanels().get(0));

        }
        if (e.getSource() == resetButton) {
            ipAddressTextField.setText("");
            serverPortTextField.setText("");
        }

    }



}