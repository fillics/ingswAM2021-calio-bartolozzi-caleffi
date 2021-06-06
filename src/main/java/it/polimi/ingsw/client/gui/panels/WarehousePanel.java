package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WarehousePanel extends JPanel implements ActionListener {
    private final DepositsPanel depositsPanel;
    private final StrongboxPanel strongboxPanel;


    public WarehousePanel(GUI gui) {
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        depositsPanel = new DepositsPanel(gui);
        strongboxPanel = new StrongboxPanel(gui);
        this.add(depositsPanel);
        this.add(strongboxPanel);

    }

    public DepositsPanel getDepositsPanel() {
        return depositsPanel;
    }

    public StrongboxPanel getStrongboxPanel() {
        return strongboxPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
