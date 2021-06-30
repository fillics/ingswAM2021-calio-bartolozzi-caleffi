package it.polimi.ingsw.client.gui.panels.pregamepanels;

import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.pregamepanels.LoginPanel;
import it.polimi.ingsw.constants.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class that creates the panel used to connect to the server
 */
public class ServerPanel extends JPanel implements ActionListener {

    private Image background;
    private final GridBagConstraints c;
    private final GUI gui;
    private JTextField ipAddressTextField, serverPortTextField;
    private  JButton connectButton, resetButton;
    private JPanel biggestPanel, ipPanel, portPanel, buttonsPanel;
    private final boolean defaultConnection;

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
    public ServerPanel(GUI gui){
        this.gui = gui;
        this.defaultConnection = defaultConnection;
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

        createBiggestPanel();
        c.gridx=0;
        c.gridy=1;
        this.add(biggestPanel, c);


        createButtonsPanel();
        c.gridx=0;
        c.gridy=2;
        c.ipadx=50;
        c.ipady=50;
        this.add(buttonsPanel, c);


    }

    /**
     * Method that creates the biggest panel to contain all the other panels
     */
    public void createBiggestPanel(){
        biggestPanel = new JPanel();
        biggestPanel.setLayout(new GridBagLayout());

        createIpPanel();
        c.gridx=0;
        c.gridy=1;
        c.ipadx=50;
        c.ipady=50;
        biggestPanel.add(ipPanel, c);

        createPortPanel();
        c.gridx=0;
        c.gridy=2;
        c.ipadx=100;
        c.ipady=25;
        c.weighty=100;
        biggestPanel.add(portPanel, c);
        biggestPanel.setBackground(new Color(233, 226, 193));
        biggestPanel.setBorder(gui.getBlackline());



    }

    /**
     * Method that creates the panel for the insert of the IP
     */
    private void createIpPanel(){
        ipPanel = new JPanel();
        ipPanel.setLayout(new GridBagLayout());
        c.insets = new Insets(0,50,0,50);
        JLabel ipAddress = new JLabel("Insert the server IP address");
        ipAddress.setFont(new Font("Times New Roman", ipAddress.getFont().getStyle(), 20));
        ipAddress.setPreferredSize(new Dimension(250,50));
        ipAddress.setBackground(new Color(233, 226, 193));
        ipAddress.setHorizontalAlignment(JLabel.CENTER);

        ipAddressTextField = new JTextField();
        c.gridx=0;
        c.gridy=0;
        ipPanel.add(ipAddress, c);
        c.gridx=1;
        c.gridy=0;
        ipPanel.add(ipAddressTextField, c);

        ipAddressTextField.setText("127.0.0.1");
        if(defaultConnection) ipAddressTextField.setText(Constants.getAddressServer());
        ipAddressTextField.setPreferredSize(new Dimension(200, 50));
        ipAddressTextField.setHorizontalAlignment(JTextField.CENTER);
        ipAddressTextField.setFont(new Font("Times New Roman", ipAddressTextField.getFont().getStyle(), 20));

        ipPanel.setBackground(new Color(233, 226, 193));
        ipAddress.setOpaque(true);
    }

    /**
     * Method that creates the panel for the insert of the port
     */
    private void createPortPanel(){
        portPanel = new JPanel();
        portPanel.setLayout(new GridBagLayout());
        c.insets = new Insets(0,50,0,50);

        JLabel serverPort = new JLabel("Insert the IP port");
        serverPort.setFont(new Font("Times New Roman", serverPort.getFont().getStyle(), 20));

        serverPort.setPreferredSize(new Dimension(200,10));
        serverPort.setHorizontalAlignment(JLabel.CENTER);
        serverPort.setBackground(new Color(233, 226, 193));
        serverPortTextField = new JTextField();
        c.gridx=0;
        c.gridy=0;
        portPanel.add(serverPort, c);

        c.gridx=1;
        c.gridy=0;
        portPanel.add(serverPortTextField, c);

        // TODO: 26/06/2021 toglier riga commentata
        serverPortTextField.setText(String.valueOf(1234));
        if(defaultConnection) serverPortTextField.setText(String.valueOf(Constants.getPort()));

        serverPortTextField.setPreferredSize(new Dimension(200, 10));
        serverPortTextField.setFont(new Font("Times New Roman", serverPortTextField.getFont().getStyle(), 20));
        serverPortTextField.setHorizontalAlignment(JTextField.CENTER);

        portPanel.setBackground(new Color(233, 226, 193));
        serverPort.setOpaque(true);

    }

    /**
     * Method that creates the "connect" and "reset" buttons
     */
    public void createButtonsPanel(){
        buttonsPanel = new JPanel();
        connectButton = new JButton("CONNECT");
        resetButton = new JButton("RESET");
        c.insets = new Insets(0,50,0,50);

        buttonsPanel.setLayout(new GridBagLayout());
        c.gridx=0;
        c.gridy=0;
        buttonsPanel.add(connectButton, c);

        c.gridx=1;
        c.gridy=0;
        buttonsPanel.add(resetButton, c);
        buttonsPanel.setBackground(new Color(0,0,0,0));
        setButtons(connectButton);
        setButtons(resetButton);

    }

    /**
     * Method that sets font and size of a button
     * @param button is the button chosen
     */
    public void setButtons(JButton button){
        button.addActionListener(this);
        button.setFont(new Font("Times New Roman", button.getFont().getStyle(), 20));
        button.setPreferredSize(new Dimension(150,50));
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == connectButton || e.getSource()==serverPortTextField) {
            Constants.setAddressServer(ipAddressTextField.getText());
            Constants.setPort(Integer.parseInt(serverPortTextField.getText()));
            handleServerConnection();
        }
        if (e.getSource() == resetButton) {
            ipAddressTextField.setText("");
            serverPortTextField.setText("");
        }
    }

    /**
     * Method that handles the connection to the server. It shows an error if the connection failed or it changes panel
     * to ask the username
     */
    public void handleServerConnection(){
        gui.getClient().serverConnection(ViewChoice.GUI);
        if(gui.getClient().getSocketClientConnection().getConnectionToServer().get()){
            gui.switchPanels(new LoginPanel(gui));
        }
        else{
            JOptionPane.showMessageDialog(gui.getjFrame(), "Error in connecting to the server. Retry!");

        }
    }
}