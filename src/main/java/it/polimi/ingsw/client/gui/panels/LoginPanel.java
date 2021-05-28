package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class LoginPanel extends JPanel implements ActionListener {


    JLabel login = new JLabel("Insert your username");
    JTextField loginTextField = new JTextField();
    JButton loginButton = new JButton("LOGIN");


    public LoginPanel(){
        this.setBounds(0,0,1000,1000);
        createPanel();
        addComponentsToContainer();
        addActionEvent(loginButton);
        this.setVisible(true);
        this.setLayout(null);
    }


    private void createPanel(){
        login.setBounds(50, 150, 200, 30);
        loginButton.setBounds(50, 300, 100, 30);
        loginTextField.setBounds(300, 150, 150, 30);
    }



    public void addComponentsToContainer() {

        this.add(login);
        this.add(loginButton);
        this.add(loginTextField);

    }


    public void addActionEvent(JButton button) {
        button.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username;
            username = loginTextField.getText();

            JOptionPane.showMessageDialog(this, "Connected");


        }


    }



}