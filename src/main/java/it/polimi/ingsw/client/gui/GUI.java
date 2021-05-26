package it.polimi.ingsw.client.gui;
import it.polimi.ingsw.client.Client;

import javax.swing.*;

public class GUI extends Client {


    public static void main(String[] args) {
        SetupFrame frame = new SetupFrame();

        frame.setTitle("Master of Renaissance");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(800, 800);
        frame.setVisible(true);
        //javax.swing.SwingUtilities.invokeLater(GUI::createAndShowGUI);
    }


}

