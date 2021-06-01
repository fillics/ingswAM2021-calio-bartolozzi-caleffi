package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.constants.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerPanel extends JPanel implements ActionListener {
    private GUI gui;
    private JLabel ipAddress;
    private JTextField ipAddressTextField;
    private JLabel serverPort;
    private JTextField serverPortTextField;
    private JButton connectButton = new JButton("CONNECT");
    private JButton resetButton = new JButton("RESET");
    private JPanel ipPanel;
    private JPanel portPanel;
    private JPanel buttonsPanel;
    public ServerPanel(GUI gui){
        this.gui = gui;
        this.setOpaque(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(300 ,400)));
        ipPanel = new JPanel();
        createIpPanel();
        this.add(Box.createRigidArea(new Dimension(300,100)));
        portPanel = new JPanel();
        createPortPanel();
        this.add(Box.createRigidArea(new Dimension(300,100)));
        buttonsPanel = new JPanel();
        createButtonsPanel();
    }
    private void createIpPanel(){
        ipPanel.setLayout(new BoxLayout(ipPanel, BoxLayout.X_AXIS));
        ipAddress = new JLabel("Insert the server IP address");
        ipAddressTextField = new JTextField();
        ipPanel.add(ipAddress);
        ipPanel.add(Box.createRigidArea(new Dimension(20,10)));
        ipPanel.add(ipAddressTextField);
        ipAddressTextField.setText("127.0.0.1");
        ipPanel.setOpaque(true);
        this.add(ipPanel);
    }
    private void createPortPanel(){
        portPanel.setLayout(new BoxLayout(portPanel, BoxLayout.X_AXIS));
        serverPort = new JLabel("Insert the IP port");
        serverPortTextField = new JTextField();
        portPanel.add(serverPort);
        portPanel.add(Box.createRigidArea(new Dimension(50,10)));
        portPanel.add(serverPortTextField);
        serverPortTextField.setText("1234");
        portPanel.setOpaque(true);
        this.add(portPanel);
    }
    public void createButtonsPanel(){
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(connectButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(50,10)));
        buttonsPanel.add(resetButton);
        buttonsPanel.setOpaque(true);
        connectButton.addActionListener(this);
        resetButton.addActionListener(this);
        this.add(buttonsPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectButton) {
            Constants.setAddressServer(ipAddressTextField.getText());
            Constants.setPort(Integer.parseInt(serverPortTextField.getText()));
            gui.getClient().serverConnection(ViewChoice.GUI);
            handleMessageFromServer();
            //JOptionPane.showMessageDialog(this, "Connected!");
        }
        if (e.getSource() == resetButton) {
            ipAddressTextField.setText("");
            serverPortTextField.setText("");
        }
    }
    public void handleMessageFromServer(){
        if(gui.getClient().getSocketClientConnection().getConnectionToServer().get()){
            gui.switchPanels(new LoginPanel(gui));
        }
        else{ // TODO: 01/06/2021 stampare messaggio di errore
        }
    }
}