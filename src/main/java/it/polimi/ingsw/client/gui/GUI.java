package it.polimi.ingsw.client.gui;
import it.polimi.ingsw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GUI implements Runnable  {

    private static JPanel bigPanel;
    private LoginPanel loginPanel;
    private ServerPanel serverPanel;
    private static ArrayList<JPanel> panels;

    public GUI() {
        panels = new ArrayList<>();
        loginPanel = new LoginPanel();
        serverPanel = new ServerPanel();

        panels.add(loginPanel);
        panels.add(serverPanel);

    }


    public static void main(String[] args) {

        GUI gui = new GUI();
        gui.run();
    }

    public static ArrayList<JPanel> getPanels() {
        return panels;
    }

    public static void switchPanels(JPanel panel){
        bigPanel.removeAll();
        bigPanel.add(panel);
        bigPanel.repaint();
        bigPanel.revalidate();
    }


    @Override
    public void run() {
        JFrame frame = new JFrame();

        bigPanel = new JPanel();

        bigPanel.setVisible(true);
        bigPanel.setLayout(null);

        bigPanel.setBounds(0,0,1000,1000);

        bigPanel.add(serverPanel);
        frame.add(bigPanel);


        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale

        frame.setTitle("Master of Renaissance!");
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(800, 800);

        ImageIcon image = null; //create an ImageIcon
        try {
            image = new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/punchboard/calamaio.png")).readAllBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        frame.setIconImage(image != null ? image.getImage() : null); //change icon of the this
    }
}

