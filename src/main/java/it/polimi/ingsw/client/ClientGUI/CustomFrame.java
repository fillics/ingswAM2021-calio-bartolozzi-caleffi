package it.polimi.ingsw.client.ClientGUI;

import javax.swing.*;
import java.awt.*;


public class CustomFrame extends JFrame {

    public CustomFrame(){
        this.setTitle("Master of Renaissance");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        this.setVisible(true);

        ImageIcon image = new ImageIcon("src/main/resources/images/punchboard/calamaio.png"); //create an ImageIcon

        this.setIconImage(image.getImage()); //change icon of the this

        this.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale

    }
}