package it.polimi.ingsw.client.gui;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.gui.panels.*;
import it.polimi.ingsw.client.gui.panels.pregamepanels.LoginPanel;
import it.polimi.ingsw.client.gui.panels.pregamepanels.NumPlayersPanel;
import it.polimi.ingsw.client.gui.panels.pregamepanels.ServerPanel;
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

    private JPanel bigPanel, topPanel, mainPanel, messagesFromServerPanel, positionPanel, curMessagePanel, curPositionPanel;
    private JPanel loginPanel, serverPanel, numPlayersPanel;
    private final Client client;
    private final Dimension dimension;
    private final int width;
    private final int height;
    private final JFrame jFrame;
    private Border blackline, raisedetched, loweredetched, raisedbevel, loweredbevel;
    private final ArrayList<Border> borders;

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

        ImageIcon image = null;
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
        messagesFromServerPanel = new MessagesPanel(this, message);
        messagesFromServerPanel.setLayout(new BoxLayout(messagesFromServerPanel,  BoxLayout.PAGE_AXIS));
        messagesFromServerPanel.setBorder(blackline);
        if(curMessagePanel!=null) removeMessagePanel();
        else{
            topPanel.add(messagesFromServerPanel);

        }
        curMessagePanel = messagesFromServerPanel;
    }

    public void removeMessagePanel(){
        GridBagConstraints c = new GridBagConstraints();
        topPanel.remove(curMessagePanel);
        c.gridx=0;
        c.gridy=0;
        topPanel.add(messagesFromServerPanel, c);
        topPanel.repaint();
        topPanel.revalidate();
        topPanel.setOpaque(false);
    }

    // TODO: 08/06/2021 metodo di fil, devo finirlo
    public void setPositionPanel(String position){
        positionPanel = new PositionInGamePanel(this, position);
        positionPanel.setBorder(blackline);
        if(curPositionPanel!=null) removePositionPanel();
        else{
            GridBagConstraints c = new GridBagConstraints();
            c.gridx=1;
            c.gridy=0;
            topPanel.add(positionPanel, c);
            topPanel.setOpaque(false);

        }
        curPositionPanel = positionPanel;

    }

    public void removePositionPanel(){
        GridBagConstraints c = new GridBagConstraints();
        topPanel.remove(curPositionPanel);
        c.gridx=1;
        c.gridy=0;
        topPanel.add(positionPanel, c);
        topPanel.repaint();
        topPanel.revalidate();
        topPanel.setOpaque(false);
    }


    /**
     * per mandare pacchetto normale
     */
    public void sendPacketToServer(ClientPacketHandler packet){
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(packet);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
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


    public JPanel getNumPlayersPanel() {
        return numPlayersPanel;
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


    public ArrayList<Border> getBorders() {
        return borders;
    }

}