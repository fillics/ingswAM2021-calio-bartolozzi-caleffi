package it.polimi.ingsw.client.gui;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.panels.*;
import it.polimi.ingsw.client.gui.panels.pregamepanels.LoginPanel;
import it.polimi.ingsw.client.gui.panels.pregamepanels.NumPlayersPanel;
import it.polimi.ingsw.client.gui.panels.pregamepanels.ServerPanel;
import it.polimi.ingsw.controller.Packet;
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

    private JPanel topPanel;
    private JPanel mainPanel;
    private JPanel messagesFromServerPanel;
    private JPanel curMessagePanel;
    private Client client;
    private ClientModelView clientModelView;
    private final Dimension dimension;
    private final int width;
    private final int height;
    private final JFrame jFrame;
    private Border blackline, raisedetched, loweredetched, raisedbevel, loweredbevel;
    private final ArrayList<Border> borders;
    private final Color greenColor;
    private final Color giallinoBackgroundColor;
    private final Color purpleColor;
    private final Color lightblueColor;
    private final Color yellowColor;
    private boolean defaultConnection=false;

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

        greenColor = new Color(54, 178, 76);
        giallinoBackgroundColor = new Color(233, 226, 193);
        purpleColor = new Color(139,117,180);
        lightblueColor = new Color(104,205,236);
        yellowColor = new Color(228,191,40);

    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDefaultConnection() {
       defaultConnection = true;
    }

    @Override
    public void run() {

        JPanel bigPanel = new JPanel();

        mainPanel = new JPanel();
        topPanel = new JPanel();

        createMessageFromServer("Welcome!");

        bigPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));

        mainPanel.add(new ServerPanel(this, defaultConnection));
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


    public void sendPacketToServer(Packet packet){
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = null;
        try {
            jsonResult = mapper.writeValueAsString(packet);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        client.getSocketClientConnection().sendToServer(jsonResult);
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

    public Color getGreenColor() {
        return greenColor;
    }

    public Color getGiallinoBackgroundColor() {
        return giallinoBackgroundColor;
    }

    public Color getPurpleColor() {
        return purpleColor;
    }

    public Color getLightblueColor() {
        return lightblueColor;
    }

    public Color getYellowColor() {
        return yellowColor;
    }

    public Color getRandomColor(){
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(yellowColor);
        colors.add(lightblueColor);
        colors.add(greenColor);
        colors.add(purpleColor);

        int index = (int) (Math.random() * colors.size());
        return colors.get(index);
    }

    public ArrayList<Border> getBorders() {
        return borders;
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }
}