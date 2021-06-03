package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.controller.client_packets.PacketChooseInitialResources;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AdditionalResourcePanel extends JPanel implements ActionListener {

    private GUI gui;
    private Image background;
    private ArrayList<Integer> deposits;
    private ArrayList<ResourceType> resources;
    private JPanel resourcesPanel, buttonsPanel;
    private DepositsPanel depositsPanel;
    private final JButton coin = new JButton();
    private final JButton stone = new JButton();
    private final JButton servant = new JButton();
    private final JButton shield = new JButton();
    private JButton deposit1Button, deposit2Button, deposit3Button;
    private JButton confirmButton;
    private GridBagConstraints c;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, gui.getWidth(), gui.getHeight()-50, null);
    }

    public AdditionalResourcePanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/background/game.png");
        try {
            background = ImageIO.read(is);
        } catch (IOException ignored) {}
        if(gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==1 ||
                gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==2)
            gui.createMessageFromServer(ConnectionMessages.CHOOSE_ONE_RESOURCE_GUI.getMessage());

        else gui.createMessageFromServer(ConnectionMessages.CHOOSE_TWO_RESOURCES_GUI.getMessage());


        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        deposits = new ArrayList<>();
        resources = new ArrayList<>();
        createResourcesPanel();
        c.gridx=0;
        c.gridy=0;
        this.add(resourcesPanel, c);


        createDepositsPanel();
        c.gridx=0;
        c.gridy=1;
        this.add(depositsPanel, c);


        createButton();
        c.gridx=0;
        c.gridy=2;
        this.add(buttonsPanel, c);



    }


    public void createResourcesPanel(){
        resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new GridBagLayout());
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
        resourcesPanel.setBackground(new Color(0, 0, 0, 0));
        resourcesPanel.setOpaque(true);

    }

    public void createDepositsPanel(){
        depositsPanel = new DepositsPanel(gui);

        depositsPanel.setVisible(false);
    }

    public void createButton(){
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());

        confirmButton = new JButton("CONFIRM");
        c.gridx=0;
        c.gridy=0;
        c.ipadx=50;
        c.ipady=25;
        buttonsPanel.add(confirmButton, c);
        confirmButton.addActionListener(this);
        buttonsPanel.setBackground(new Color(0,0,0,0));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == coin) {
            if(gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==1 ||
                    gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==2){
                setDisabled();
            }
            if(resources.size()<3) {
                resources.add(ResourceType.COIN);

            }

            depositsPanel.setVisible(true);
        }
        if (e.getSource() == stone) {
            if(gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==1 ||
                    gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==2){
                setDisabled();
            }
            if(resources.size()<3) {
                resources.add(ResourceType.STONE);

            }
            depositsPanel.setVisible(true);

        }
        if (e.getSource() == servant) {
            if(gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==1 ||
                    gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==2){
                setDisabled();
            }
            if(resources.size()<3) {
                resources.add(ResourceType.SERVANT);

            }
            depositsPanel.setVisible(true);

        }
        if (e.getSource() == shield) {

            if(gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==1 ||
                    gui.getClient().getClientModelView().getMyPlayer().getPosInGame()==2){
                setDisabled();
            }

            if(resources.size()<3) {
                resources.add(ResourceType.SHIELD);

            }
            depositsPanel.setVisible(true);

        }


        if(e.getSource() == confirmButton){

            deposits = depositsPanel.getIdDepot();

            if(deposits.size() == resources.size()){
                gui.sendPacketToServer(new PacketChooseInitialResources(deposits, resources));
            }
            else{
                gui.createMessageFromServer("Arrays of different dimension. Please choose again the resources and the deposits");
                resources.clear();
                deposits.clear();
                setEnabled();
                depositsPanel.setEnabled();

            }
        }

    }



    public void setDisabled(){
        coin.setEnabled(false);
        stone.setEnabled(false);
        shield.setEnabled(false);
        servant.setEnabled(false);
    }

    public void setEnabled(){
        coin.setEnabled(true);
        stone.setEnabled(true);
        shield.setEnabled(true);
        servant.setEnabled(true);
    }

}