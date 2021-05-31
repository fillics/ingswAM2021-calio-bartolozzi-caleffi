package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DepositsPanel extends JPanel implements ActionListener {

    private JButton deposit1Button;
    private JButton deposit2Button;
    private JButton deposit3Button;

    public DepositsPanel() {

        this.setLayout(new GridLayout(1,1));

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
        this.add(picLabel);

        deposit1Button.addActionListener(this);
        deposit2Button.addActionListener(this);
        deposit3Button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public static void main(String[] args) {

        DepositsPanel depositsPanel = new DepositsPanel();
        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale
        frame.add(depositsPanel);

        frame.setTitle("Master of Renaissance");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(1920, 1080);


    }

}
