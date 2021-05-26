package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class SetupFrame extends JFrame implements ActionListener {


    private Socket socket;

    Container container = getContentPane();
    JLabel ipAddress = new JLabel("Insert the server IP address");
    JTextField ipAddressTextField = new JTextField();
    JLabel serverPort = new JLabel("Insert the server port");
    JTextField serverPortTextField = new JTextField();
    JButton connectButton = new JButton("CONNECT");
    JButton resetButton = new JButton("RESET");

    JLabel login = new JLabel("Insert your username");
    JTextField loginTextField = new JTextField();
    JButton loginButton = new JButton("LOGIN");


    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();


    public SetupFrame(){

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent(connectButton);
        addActionEvent(loginButton);
        addActionEvent(resetButton);

        panel2.setBackground(Color.RED);

        ImageIcon image = new ImageIcon("src/main/resources/images/punchboard/calamaio.png"); //create an ImageIcon
        this.setIconImage(image.getImage()); //change icon of the this
        this.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale
    }


    public void setLayoutManager() {
        container.setLayout(null);
    }


    public void setLocationAndSize() {
        ipAddress.setBounds(50, 150, 200, 30);
        ipAddressTextField.setBounds(300, 150, 150, 30);
        serverPort.setBounds(50, 250, 200, 30);
        serverPortTextField.setBounds(300, 250, 150, 30);
        connectButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(ipAddress);
        container.add(ipAddressTextField);
        container.add(serverPort);
        container.add(serverPortTextField);
        container.add(connectButton);
        container.add(resetButton);
    }


    public void addActionEvent(JButton button) {
        button.addActionListener(this);
    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());

        loginPhase();
    }

    public void loginPhase(){
        panel2.add(login);
        panel2.add(loginTextField);
        panel2.add(loginButton);

        login.setBounds(250,250,250,250);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectButton) {
            String ipAddress, port;
            ipAddress = ipAddressTextField.getText();
            port = serverPortTextField.getText();

            Constants.setAddressServer(ipAddress);
            Constants.setPort(Integer.parseInt(port));

            try {
                socket = new Socket(Constants.getAddressServer(), Constants.getPort());
                JOptionPane.showMessageDialog(this, "Connected");
                changePanel(panel1);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this, "Invalid IP address or server port");
            }

        }
        if (e.getSource() == resetButton) {
            ipAddressTextField.setText("");
            serverPortTextField.setText("");
        }

    }



}
