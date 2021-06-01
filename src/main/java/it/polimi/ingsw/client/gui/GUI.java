package it.polimi.ingsw.client.gui;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ViewInterface;
import it.polimi.ingsw.client.gui.panels.*;
import it.polimi.ingsw.constants.Constants;

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
    private BoardPanel boardPanel;
    private BuyDevCardPanel buyDevCardPanel;
    private DevGridPanel devGridPanel;
    private MarketPanel marketPanel;
    private RemoveLeaderCardPanel removeLeaderCardPanel;
    private AdditionalResourcePanel additionalResourcePanel;
    private Client client;
    private static ArrayList<JPanel> panels;
    private Dimension dimension;
    private final int width;
    private final int height;
    private ClientModelView clientModelView;
    private JFrame jFrame;

    public GUI(Client client, ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
        jFrame = new JFrame();
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        width = dimension.width;
        height = dimension.height-50;
        this.client = client;

        panels = new ArrayList<>();
        loginPanel = new LoginPanel(this);
        serverPanel = new ServerPanel(this);
        numPlayersPanel = new NumPlayersPanel(this);
        boardPanel = new BoardPanel(this);


    }


    @Override
    public void run() {

        bigPanel = new JPanel();

        bigPanel.setLayout(new BorderLayout());

        bigPanel.add(serverPanel);
        jFrame.add(bigPanel);

        jFrame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale

        jFrame.setTitle("Master of Renaissance");
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        jFrame.setSize(width, height);

        ImageIcon image = null; //create an ImageIcon
        try {
            image = new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/calamaio.png")).readAllBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        jFrame.setIconImage(image != null ? image.getImage() : null); //change icon of the this

    }

    // TODO: 29/05/2021 si può anche porre i pannelli a setVisible = false e mettere quello che si vuol far vedere a true
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

    public JFrame getjFrame() {
        return jFrame;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }
}

