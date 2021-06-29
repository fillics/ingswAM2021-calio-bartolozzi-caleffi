package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.WarehouseAndDevSpacesPanel;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class that creates the panel that contains the warehouse for the buy development card operation
 */
public class WarehouseForBuyDevCardPanel extends JPanel{
    private final ArrayList<ResourceType> chosenResources;
    private final ArrayList<Integer> chosenWarehouses;
    private final WarehouseAndDevSpacesPanel warehouse;

    /**
     * Class' constructor
     * @param warehouse is the warehouse and development space panel
     */
    public WarehouseForBuyDevCardPanel(WarehouseAndDevSpacesPanel warehouse) {
        this.warehouse = warehouse;
        chosenResources = new ArrayList<>();
        chosenWarehouses = new ArrayList<>();
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        DepositsForBuyDevCardPanel depositsPanel = new DepositsForBuyDevCardPanel(this);
        StrongBoxForBuyDevCardPanel strongboxPanel = new StrongBoxForBuyDevCardPanel(this);
        this.add(depositsPanel);
        this.add(strongboxPanel);

        this.setOpaque(false);
        this.setBackground(new Color(0,0,0,0));

    }

    /**
     * Class' getter
     * @return the gui of the warehouse panel
     */
    public GUI getGui() {
        if (warehouse.getBuyDevCardPanel()!= null) return warehouse.getBuyDevCardPanel().getGui();
        if (warehouse.getUseProductionPowerPanel()!= null) return warehouse.getUseProductionPowerPanel().getGui();
        else return null;
    }

    /**
     * Class' getter
     * @return the warehouse and development spaces panel
     */
    public WarehouseAndDevSpacesPanel getWarehouse() {
        return warehouse;
    }

    /**
     * Class' getter
     * @return the chosen resources
     */
    public ArrayList<ResourceType> getChosenResources() {
        return chosenResources;
    }

    /**
     * Class' getter
     * @return the chosen warehouses
     */
    public ArrayList<Integer> getChosenWarehouses() { return chosenWarehouses; }


}
