package it.polimi.ingsw.client.gui.panels.pregamepanels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketUsername;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Class that creates the panel used for the login
 */
public class LoginPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private final GridBagConstraints c;
    private Image background;
    private JLabel error;
    private JTextField loginTextField;
    private final JButton loginButton = new JButton("LOGIN");
    private JPanel login;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getWidth(), gui.getHeight()-50, null);
    }


    /**
     * Class' constructor
     * @param gui gui is the GUI object linked to this panel
     */
    public LoginPanel(GUI gui){
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/pregame.png");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {}


        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        int top = gui.getHeight()/3;
        c.insets = new Insets(top,0,50,0);

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
        c.weighty=50;

        this.add(loginButton, c);
        loginButton.addActionListener(this);
        loginButton.setFont(new Font(loginButton.getFont().getName(), loginButton.getFont().getStyle(), 20));
        loginButton.setPreferredSize(new Dimension(150,50));
    }

    /**
     * Method that creates the login label
     */
    private void createLoginLabel(){
        login = new JPanel();

        login.setLayout(new GridBagLayout());
        c.insets = new Insets(0,50,0,50);

        JLabel loginLabel = new JLabel(ConnectionMessages.INSERT_USERNAME.getMessage());
        loginLabel.setFont(new Font(loginLabel.getFont().getName(), loginLabel.getFont().getStyle(), 15));
        loginLabel.setPreferredSize(new Dimension(200,50));
        loginLabel.setHorizontalAlignment(JLabel.CENTER);

        loginTextField = new JTextField();
        loginTextField.setFont(new Font(loginTextField.getFont().getName(), loginTextField.getFont().getStyle(), 25));
        loginTextField.setPreferredSize(new Dimension(200, 25));
        loginTextField.setHorizontalAlignment(JTextField.CENTER);
        loginTextField.addActionListener(this);
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

        login.setBackground(gui.getGiallinoBackgroundColor());

    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton || e.getSource()==loginTextField) {
            String username;
            username = loginTextField.getText();
            if(username.length()!=0){
                gui.getClient().sendPacketToServer(new PacketUsername(username.toLowerCase(Locale.ROOT)));

            }
            else error.setVisible(true);
        }
    }
}