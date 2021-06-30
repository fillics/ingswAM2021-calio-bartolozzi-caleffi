package it.polimi.ingsw.client.gui.panels.pregamepanels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Class that creates the panel used for the choice of the number of players
 */
public class NumPlayersPanel extends JPanel implements ActionListener {

    private Image background;
    private final GridBagConstraints c;

    private final GUI gui;
    private JPanel biggestPanel, numbersPanel;
    private JButton btn1, btn2, btn3, btn4;
    private int number_of_players;

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
    public NumPlayersPanel(GUI gui) {
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

        createBiggestPanel();

        c.gridx=0;
        c.gridy=1;
        this.add(biggestPanel, c);
    }

    /**
     * Method that creates the biggest panel to contain all the other panels
     */
    public void createBiggestPanel(){
        biggestPanel = new JPanel();
        biggestPanel.setLayout(new GridBagLayout());
        //c.insets = new Insets(50,0,0,0);
        JLabel numPlayers = new JLabel(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS.getMessage());
        numPlayers.setFont(new Font("Times New Roman", numPlayers.getFont().getStyle(), 20));
        numPlayers.setBackground(new Color(233, 226, 193));
        numPlayers.setOpaque(true);
        numPlayers.setHorizontalAlignment(JLabel.CENTER);
        c.gridx=0;
        c.gridy=0;
        c.ipadx=50;
        c.ipady=20;
        this.add(numPlayers, c);

        createNumbersGrid();
        c.gridx=0;
        c.gridy=1;
        biggestPanel.add(numbersPanel, c);
        biggestPanel.setBackground(new Color(233, 226, 193));
        biggestPanel.setBorder(gui.getBorders().get(0));

    }

    /**
     * Method that creates the grid of the number of players to choose
     */
    public void createNumbersGrid(){

        numbersPanel = new JPanel();
        numbersPanel.setLayout(new GridBagLayout());
        c.insets = new Insets(10,10,10,10);
        btn1 = new JButton("1");
        btn1.setFont(new Font("Times New Roman", btn1.getFont().getStyle(), 15));
        btn1.setPreferredSize(new Dimension(50,50));
        changeBackground(btn1);
        btn2 = new JButton("2");
        btn2.setFont(new Font("Times New Roman", btn2.getFont().getStyle(), 15));
        btn2.setPreferredSize(new Dimension(50,50));
        changeBackground(btn2);
        btn3 = new JButton("3");
        btn3.setPreferredSize(new Dimension(50,50));
        btn3.setFont(new Font("Times New Roman", btn3.getFont().getStyle(), 15));
        changeBackground(btn3);
        btn4 = new JButton("4");
        btn4.setFont(new Font("Times New Roman", btn4.getFont().getStyle(), 15));
        btn4.setPreferredSize(new Dimension(50,50));
        changeBackground(btn4);

        c.gridx=0;
        c.gridy=0;
        numbersPanel.add(btn1, c);
        c.gridx=0;
        c.gridy=1;
        numbersPanel.add(btn3, c);
        c.gridx=1;
        c.gridy=0;
        numbersPanel.add(btn2, c);
        c.gridx=1;
        c.gridy=1;
        numbersPanel.add(btn4, c);
        numbersPanel.setBackground(new Color(0, 0, 0,0));
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
    }

    /**
     *  Method that colors the background of the button when the user hovers over it.
     * @param button is the button chosen
     */
    public void changeBackground(JButton button){
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 180, 76));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
            }
        });
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) number_of_players = 1;
        if (e.getSource() == btn2) number_of_players = 2;
        if (e.getSource() == btn3) number_of_players = 3;
        if (e.getSource() == btn4) number_of_players = 4;
        setDisabled();
        loading();
        gui.getClient().sendPacketToServer(new PacketNumPlayers(number_of_players));
    }

    /**
     * Method that disables the buttons of the panel
     */
    public void setDisabled(){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
    }

    /**
     * Method that sets invisible the buttons of the panel
     */
    public void setInvisible(){
        btn1.setVisible(false);
        btn2.setVisible(false);
        btn3.setVisible(false);
        btn4.setVisible(false);
    }

    /**
     * Method used to created the loading gif after the choice of the number of players
     */
    public void loading(){
        JPanel loadingPanel = new JPanel();

        Icon imgIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/gif/ajax-loader.gif")));
        JLabel label = new JLabel(imgIcon);
        loadingPanel.setLayout(new GridBagLayout());

        c.gridx=0;
        c.gridy=0;
        loadingPanel.add(label, c);

        loadingPanel.setBackground(new Color(233, 226, 193));

        setInvisible();
        numbersPanel.add(loadingPanel);

    }
}