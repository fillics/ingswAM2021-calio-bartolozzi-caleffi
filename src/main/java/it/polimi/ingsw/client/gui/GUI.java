package it.polimi.ingsw.client.gui;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ViewInterface;
import it.polimi.ingsw.client.gui.panels.LoginPanel;
import it.polimi.ingsw.client.gui.panels.NumPlayersPanel;
import it.polimi.ingsw.client.gui.panels.ServerPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GUI implements Runnable, ViewInterface {

    private static JPanel bigPanel;
    private LoginPanel loginPanel;
    private ServerPanel serverPanel;
    private NumPlayersPanel numPlayersPanel;
    private Client client;
    private static ArrayList<JPanel> panels;
    private Dimension dimension;
    private int width, height;
    private ClientModelView clientModelView;

    public GUI(Client client, ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        width = dimension.width;
        height = dimension.height-50;
        this.client = client;

        panels = new ArrayList<>();
        loginPanel = new LoginPanel(this);
        serverPanel = new ServerPanel(this);
        numPlayersPanel = new NumPlayersPanel(this);

        panels.add(serverPanel);
        panels.add(loginPanel);
        panels.add(numPlayersPanel);
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }

    public void switchPanels(JPanel panel){
        bigPanel.removeAll();
        bigPanel.add(panel);
        bigPanel.repaint();
        bigPanel.revalidate();
    }

    public Client getClient() {
        return client;
    }

    @Override
    public ClientModelView getClientModelView() {
        return null;
    }

    @Override
    public void printLeaderCards() {

    }

    @Override
    public void printActivatedLeaderCards() {

    }

    @Override
    public void printDeposits() {

    }

    @Override
    public void printStrongbox() {

    }

    @Override
    public void printDevGrid() {

    }

    @Override
    public void printResourceBuffer() {

    }

    @Override
    public void printMarketTray() {

    }

    @Override
    public void printFaithTrack() {

    }

    @Override
    public void printDevSpaces() {

    }

    @Override
    public void printBaseProdPower() {

    }

    @Override
    public void printResourcesLegend() {

    }

    @Override
    public void run() {

        JFrame frame = new JFrame();
        bigPanel = new JPanel();

        bigPanel.setVisible(true);
        bigPanel.setLayout(null);

        bigPanel.setBounds(0,0,width,height);

        bigPanel.add(serverPanel);
        frame.add(bigPanel);

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale

        frame.setTitle("Master of Renaissance");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(width, height);

        ImageIcon image = null; //create an ImageIcon
        try {
            image = new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/calamaio.png")).readAllBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        frame.setIconImage(image != null ? image.getImage() : null); //change icon of the this
    }
}

