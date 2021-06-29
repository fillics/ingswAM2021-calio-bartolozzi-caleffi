package it.polimi.ingsw.client.gui;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.gui.panels.*;
import it.polimi.ingsw.client.gui.panels.pregamepanels.ServerPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * GUI class represents the thread run when the player chooses to play with GUI.
 */
public class GUI implements Runnable {
    private JPanel topPanel;
    private JPanel mainPanel;
    private JPanel messagesFromServerPanel;
    private JPanel curMessagePanel;
    private final Client client;
    private final Dimension dimension;
    private final int width;
    private final int height;
    private final JFrame jFrame;
    private final Border blackline;
    private final Border raisedetched;
    private final Border loweredetched;
    private final Border raisedbevel;
    private final Border loweredbevel;
    private final ArrayList<Border> borders;
    private final Color greenColor;
    private final Color giallinoBackgroundColor;
    private final Color purpleColor;
    private final Color lightblueColor;
    private final Color yellowColor;
    private boolean defaultConnection=false;

    /**
     * Class' constructor, it creates the frame in which will be inserted all the panels created to play the game.
     * @param client is the client linked to this GUI.
     */
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

    /**
     * Override method that starts the thread. It creates the first panel seen by the player.
     */
    @Override
    public void run() {

        JPanel bigPanel = new JPanel();

        mainPanel = new JPanel();
        topPanel = new JPanel();

        createMessageFromServer("Welcome!");

        bigPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));

        mainPanel.add(new ServerPanel(this));
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

    /**
     * Method used to switch from one panel to another one, it removes the previous panel and add the new one.
     * @param panel is the new panel added to the frame.
     */
    public void switchPanels(JPanel panel){
        mainPanel.removeAll();
        mainPanel.add(panel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * Method that shows the message received from server.
     * @param message is the message received from server.
     */
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

    /**
     * Method that removes the last message received from the topPanel.
     */
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

    /**
     * Class' getter
     * @return the client linked to this GUI
     */
    public Client getClient() {
        return client;
    }

    /**
     * Class' getter
     * @return the GUI's frame
     */
    public JFrame getjFrame() {
        return jFrame;
    }

    /**
     * Class' getter
     * @return the width of the frame
     */
    public int getWidth() {
        return width;
    }

    /**
     * Class' getter
     * @return the height of the frame
     */
    public int getHeight() {
        return height;
    }

    /**
     * Class' getter
     * @return the green color
     */
    public Color getGreenColor() {
        return greenColor;
    }

    /**
     * Class' getter
     * @return the GiallinoBackgroundColor
     */
    public Color getGiallinoBackgroundColor() {
        return giallinoBackgroundColor;
    }

    /**
     * Class' getter
     * @return the purple color
     */
    public Color getPurpleColor() {
        return purpleColor;
    }

    /**
     * Class' getter
     * @return the light blue color
     */
    public Color getLightblueColor() {
        return lightblueColor;
    }

    /**
     * Class' getter
     * @return the yellow color
     */
    public Color getYellowColor() {
        return yellowColor;
    }

    /**
     * Class' getter
     * @return a random color
     */
    public Color getRandomColor(){
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(yellowColor);
        colors.add(lightblueColor);
        colors.add(greenColor);
        colors.add(purpleColor);

        int index = (int) (Math.random() * colors.size());
        return colors.get(index);
    }

    /**
     * Class' getter
     * @return the borders
     */
    public ArrayList<Border> getBorders() {
        return borders;
    }

    /**
     * Class' getter
     * @return the dimension of the frame
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Class' setter
     */
    public void setDefaultConnection() {
        defaultConnection = true;
    }
}