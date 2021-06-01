package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginPanel extends JPanel implements ActionListener {
    private GUI gui;
    private JLabel loginLabel;
    private JTextField loginTextField;
    private JButton loginButton = new JButton("LOGIN");
    private JButton confirmButton = new JButton("CONFIRM");
    private JPanel login;
    private JPanel numPlayersPanel;
    private JPanel buttonPanel;
    public LoginPanel(GUI gui){
        this.gui = gui;
        this.setOpaque(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(300 ,500)));
        login = new JPanel();
        createLoginLabel();
        this.add(login);
        this.add(Box.createRigidArea(new Dimension(300 ,50)));
        this.add(loginButton);
        loginButton.addActionListener(this);
    }
    private void createLoginLabel(){
        login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
        loginLabel = new JLabel("Insert your username");
        loginTextField = new JTextField();
        login.add(loginLabel);
        login.add(loginTextField);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username;
            username = loginTextField.getText();
            gui.getClient().sendUsername(username);
            //JOptionPane.showMessageDialog(this, "Connected");
        }
    }
}