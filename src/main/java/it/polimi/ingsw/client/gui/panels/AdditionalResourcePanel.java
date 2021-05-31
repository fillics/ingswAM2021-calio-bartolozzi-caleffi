package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketChooseInitialResources;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AdditionalResourcePanel extends JPanel implements ActionListener {

    private ArrayList<Integer> deposits;
    private ArrayList<ResourceType> resources;
    private JPanel resourcesPanel;
    private JPanel depositsPanel;

    private final JButton coin = new JButton();
    private final JButton stone = new JButton();
    private final JButton servant = new JButton();
    private final JButton shield = new JButton();
    private JButton deposit1Button;
    private JButton deposit2Button;
    private JButton deposit3Button;
    private GridBagConstraints c;

    private GridLayout gridLayout;

    public AdditionalResourcePanel() {
        gridLayout = new GridLayout(2,1);
        this.setLayout(gridLayout);
        createResourcesPanel();
        createDepositsPanel();

        this.add(resourcesPanel);
        this.add(depositsPanel);

        this.setBounds(0,0,1920,200);
        this.setVisible(true);

        deposits = new ArrayList<>();
        resources = new ArrayList<>();

        depositsPanel.setBounds(0, 200, 1920, 200);

    }


    public void createResourcesPanel(){
        resourcesPanel = new JPanel();
        c = new GridBagConstraints();
        c.insets = new Insets(0, 2, 0, 2);

        try {
            coin.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/coin.png").readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=0;
            c.gridy=0;
            resourcesPanel.add(coin, c);

            stone.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/stone.png").readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=1;
            c.gridy=0;
            resourcesPanel.add(stone, c);

            servant.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/servant.png").readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=2;
            c.gridy=0;
            c.insets = new Insets(50,50,50,50);
            resourcesPanel.add(servant, c);

            shield.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResourceAsStream("/images/punchboard/shield.png").readAllBytes()).getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING)));
            c.gridx=3;
            c.gridy=0;
            resourcesPanel.add(shield, c);

        } catch (IOException e) {
            e.printStackTrace();
        }

        coin.addActionListener(this);
        shield.addActionListener(this);
        servant.addActionListener(this);
        stone.addActionListener(this);
        resourcesPanel.setLayout(new GridBagLayout());

        resourcesPanel.setVisible(true);
        resourcesPanel.setSize(new Dimension(500,500));
    }

    public void createDepositsPanel(){
        depositsPanel = new JPanel();
        depositsPanel.setLayout(new GridLayout(1,1));

        deposit1Button=new JButton();
        deposit2Button=new JButton();
        deposit3Button=new JButton();

        BufferedImage myPicture = null;
        try {
            // TODO: 31/05/2021 cambiare con resource as stream
            //myPicture = ImageIO.read(new File(getClass().getResourceAsStream("/images/board/deposits.jpg").readAllBytes()));
            myPicture = ImageIO.read(new File("src/main/resources/images/board/deposits.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        depositsPanel.add(picLabel);

        deposit1Button.addActionListener(this);
        deposit2Button.addActionListener(this);
        deposit3Button.addActionListener(this);

        depositsPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ResourceType resourceType;

        if (e.getSource() == coin) {
            resourceType = ResourceType.COIN;
            setDisabled();
            depositsPanel.setVisible(true);
        }
        if (e.getSource() == stone) {
            resourceType = ResourceType.STONE;
            setDisabled();
            depositsPanel.setVisible(true);

        }
        if (e.getSource() == servant) {
            resourceType = ResourceType.SERVANT;
            setDisabled();
            depositsPanel.setVisible(true);

        }
        if (e.getSource() == shield) {
            resourceType = ResourceType.SHIELD;
            setDisabled();
            depositsPanel.setVisible(true);

        }


        PacketChooseInitialResources packet = new PacketChooseInitialResources(deposits, resources);


    }


    public static void main(String[] args) {

        AdditionalResourcePanel additionalResourcePanel = new AdditionalResourcePanel();
        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale
        frame.add(additionalResourcePanel);

        frame.setTitle("Master of Renaissance");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(1920, 1080);

    }

    public void setDisabled(){
        coin.setEnabled(false);
        stone.setEnabled(false);
        shield.setEnabled(false);
        servant.setEnabled(false);
    }

}
