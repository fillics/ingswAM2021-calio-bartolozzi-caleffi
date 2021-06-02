package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WarehousePanel extends JPanel implements ActionListener {
    private GUI gui;
    private DepositsPanel depositsPanel;
    private StrongboxPanel strongboxPanel;


    public WarehousePanel(GUI gui) {
        this.gui = gui;
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        depositsPanel = new DepositsPanel(gui);
        strongboxPanel = new StrongboxPanel(gui);
        this.add(depositsPanel);
        this.add(strongboxPanel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

        //WarehousePanel depositsPanel = new WarehousePanel();
        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale

        frame.setTitle("Master of Renaissance");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setSize(dimension.width, dimension.height);
     //
        //   frame.add(depositsPanel);
        frame.pack();



    }
}
