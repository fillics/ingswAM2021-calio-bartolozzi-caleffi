package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WarehousePanel extends JPanel {
    private final DepositsPanel depositsPanel;
    private final StrongboxPanel strongboxPanel;
    private ClientModelView clientModelView;


    public WarehousePanel(GUI gui) {
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        depositsPanel = new DepositsPanel(gui);
        strongboxPanel = new StrongboxPanel(gui, depositsPanel);

        this.add(depositsPanel);
        this.add(strongboxPanel);

    }

    public WarehousePanel(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        depositsPanel = new DepositsPanel(clientModelView);
        strongboxPanel = new StrongboxPanel(clientModelView, depositsPanel);

        this.add(depositsPanel);
        this.add(strongboxPanel);

    }

    public DepositsPanel getDepositsPanel() {
        return depositsPanel;
    }

    public StrongboxPanel getStrongboxPanel() {
        return strongboxPanel;
    }



}
