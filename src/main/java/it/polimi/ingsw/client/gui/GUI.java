package it.polimi.ingsw.client.gui;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.gui.panels.*;
import it.polimi.ingsw.controller.client_packets.cheatpackets.CheatClientPacketHandler;
import it.polimi.ingsw.controller.client_packets.ClientPacketHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GUI implements Runnable {

    private JPanel bigPanel, topPanel, mainPanel, messagesFromServerPanel;
    private JPanel loginPanel, serverPanel, numPlayersPanel, boardPanel, buyDevCardPanel, devGridPanel, marketPanel,
            removeLeaderCardPanel, additionalResourcePanel;
    private Client client;
    private ShowDevGridPanel showGridPanel;
    private static ArrayList<JPanel> panels;
    private Dimension dimension;
    private final int width;
    private final int height;
    private JFrame jFrame;
    private Border blackline, raisedetched, loweredetched, raisedbevel, loweredbevel;
    private ArrayList<Border> borders;

    public GUI(Client client) {
        jFrame = new JFrame();
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        width = dimension.width;
        height = dimension.height-50;
        this.client = client;

        blackline = BorderFactory.createLineBorder(Color.black);
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel  = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        loweredbevel = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        borders = new ArrayList<>();
        borders.add(blackline);
        borders.add(raisedbevel);
        borders.add(raisedetched);
        borders.add(loweredbevel);
        borders.add(loweredetched);

        loginPanel = new LoginPanel(this);
        serverPanel = new ServerPanel(this);
        numPlayersPanel = new NumPlayersPanel(this);



    }

    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public void run() {

        bigPanel = new JPanel();
        mainPanel = new JPanel();
        topPanel = new JPanel();
        createMessageFromServer("Welcome!");

        bigPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));

        mainPanel.add(serverPanel);
        mainPanel.setPreferredSize(new Dimension(width, height-50));
        topPanel.add(messagesFromServerPanel);
        topPanel.setPreferredSize(new Dimension(width, 50));


        bigPanel.add(mainPanel);

        bigPanel.add(topPanel, BorderLayout.NORTH);
        bigPanel.setPreferredSize(new Dimension(width, height));


        jFrame.add(bigPanel);
        jFrame.setTitle("Master of Renaissance");
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        jFrame.pack();

        ImageIcon image = null; //create an ImageIcon
        try {
            image = new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/calamaio.png")).readAllBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        jFrame.setIconImage(image != null ? image.getImage() : null); //change icon of the this

    }

    public void switchPanels(JPanel panel){
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.repaint();
        mainPanel.revalidate();

    }

    public void createMessageFromServer(String message){
        topPanel.removeAll();

        messagesFromServerPanel = new MessagesPanel(this, message);
        messagesFromServerPanel.setLayout(new BoxLayout(messagesFromServerPanel,  BoxLayout.PAGE_AXIS));
        messagesFromServerPanel.setBorder(blackline);

        topPanel.add(messagesFromServerPanel);
        topPanel.repaint();
        topPanel.revalidate();
    }

    /**
     * per mandare pacchetto normale
     * @param packet
     */
    public void sendPacketToServer(ClientPacketHandler packet){
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(packet);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        System.out.println(jsonResult);
        client.getSocketClientConnection().sendToServer(jsonResult);
    }

    /**
     * per mandare pacchetto dei cheat
     */
    public void sendPacketToServer(CheatClientPacketHandler packet){
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(packet);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        client.getSocketClientConnection().sendToServer(jsonResult);
    }





    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public JPanel getServerPanel() {
        return serverPanel;
    }

    public JPanel getNumPlayersPanel() {
        return numPlayersPanel;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JPanel getBuyDevCardPanel() {
        return buyDevCardPanel;
    }

    public JPanel getDevGridPanel() {
        return devGridPanel;
    }

    public JPanel getMarketPanel() {
        return marketPanel;
    }

    public JPanel getRemoveLeaderCardPanel() {
        return removeLeaderCardPanel;
    }

    public JPanel getAdditionalResourcePanel() {
        return additionalResourcePanel;
    }

    public ShowDevGridPanel getShowGridPanel() {
        return showGridPanel;
    }

    public Client getClient() {
        return client;
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

    public ArrayList<Border> getBorders() {
        return borders;
    }

}