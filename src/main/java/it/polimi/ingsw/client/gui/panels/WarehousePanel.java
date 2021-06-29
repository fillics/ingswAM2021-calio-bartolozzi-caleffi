package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that creates the panel that contains the warehouse
 */
public class WarehousePanel extends JPanel {
    private final DepositsPanel depositsPanel;
    private final StrongboxPanel strongboxPanel;
    private ClientModelView clientModelView;

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     */
    public WarehousePanel(GUI gui) {
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        depositsPanel = new DepositsPanel(gui);
        strongboxPanel = new StrongboxPanel(gui, depositsPanel);

        this.add(depositsPanel);
        this.add(strongboxPanel);

    }

    /**
     * Class' constructor used in the composition of the other player board panel
     * @param clientModelView is the client model view in which are contained the information
     */
    public WarehousePanel(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        depositsPanel = new DepositsPanel(clientModelView);
        strongboxPanel = new StrongboxPanel(clientModelView, depositsPanel);

        this.add(depositsPanel);
        this.add(strongboxPanel);

    }

    /**
     * Class' getter
     * @return the deposit panel
     */
    public DepositsPanel getDepositsPanel() {
        return depositsPanel;
    }

    /**
     * Class' getter
     * @return the strongbox panel
     */
    public StrongboxPanel getStrongboxPanel() {
        return strongboxPanel;
    }



}
