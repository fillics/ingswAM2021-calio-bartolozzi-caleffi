package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class LoginPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private final GridBagConstraints c;

    private Image background;

    private JLabel loginLabel, error;
    private JTextField loginTextField;
    private JButton loginButton = new JButton("LOGIN");
    private JButton confirmButton = new JButton("CONFIRM");
    private JPanel login;
    private JPanel numPlayersPanel;
    private JPanel buttonPanel;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getWidth(), gui.getHeight(), null);
    }


    public LoginPanel(GUI gui){
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/home.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException ignored) {}


        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = new Insets(500,0,50,0);

        c.gridy=0;
        c.gridx=0;
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0,0,0,0));
        this.add(emptyPanel, c);

        createLoginLabel();
        c.gridx=0;
        c.gridy=1;
        this.add(login, c);

        c.gridx=0;
        c.gridy=2;
        c.ipadx=100;
        c.ipady=25;
        c.weighty=50;

        this.add(loginButton, c);
        loginButton.addActionListener(this);
    }

    private void createLoginLabel(){
        login = new JPanel();

        login.setLayout(new GridBagLayout());
        c.insets = new Insets(0,50,0,50);

        loginLabel = new JLabel(ConnectionMessages.INSERT_USERNAME.getMessage());
        loginLabel.setPreferredSize(new Dimension(200,50));
        loginLabel.setHorizontalAlignment(JLabel.CENTER);

        loginTextField = new JTextField();
        loginTextField.setPreferredSize(new Dimension(200, 25));
        loginTextField.setHorizontalAlignment(JTextField.CENTER);
        c.gridx=0;
        c.gridy=0;
        login.add(loginLabel, c);
        c.gridx=0;
        c.gridy=1;
        c.ipadx=50;
        c.ipady=50;
        login.add(loginTextField, c);

        error = new JLabel("Do not insert empty string!");
        error.setHorizontalAlignment(JLabel.CENTER);
        c.gridx=0;
        c.gridy=2;
        error.setForeground(Color.RED);
        error.setVisible(false);
        login.add(error, c);

        login.setBackground(new Color(233, 226, 193));

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username;
            username = loginTextField.getText();

            if(username.length()!=0) gui.getClient().sendUsername(username);

            else error.setVisible(true);

            //JOptionPane.showMessageDialog(this, "Connected");
        }
    }
}