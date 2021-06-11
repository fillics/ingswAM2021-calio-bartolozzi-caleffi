package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.WarehouseAndDevSpacesPanel;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WarehouseForBuyDevCardPanel extends JPanel{
    private final DepositsForBuyDevCardPanel depositsPanel;
    private final StrongBoxForBuyDevCardPanel strongboxPanel;
    private GUI gui;
    private ArrayList<ResourceType> chosenResources;
    private ArrayList<Integer> chosenWarehouses;

    private WarehouseAndDevSpacesPanel warehouse;



    public WarehouseForBuyDevCardPanel(WarehouseAndDevSpacesPanel warehouse) {
        this.warehouse = warehouse;
        chosenResources = new ArrayList<>();
        chosenWarehouses = new ArrayList<>();
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        depositsPanel = new DepositsForBuyDevCardPanel(this);
        strongboxPanel = new StrongBoxForBuyDevCardPanel(this);
        this.add(depositsPanel);
        this.add(strongboxPanel);

        this.setOpaque(false);
        this.setBackground(new Color(0,0,0,0));

    }
    public WarehouseForBuyDevCardPanel(GUI gui) {
        this.gui = gui;
        chosenResources = new ArrayList<>();
        chosenWarehouses = new ArrayList<>();
        this.setPreferredSize(new Dimension(250, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        depositsPanel = new DepositsForBuyDevCardPanel(this);
        strongboxPanel = new StrongBoxForBuyDevCardPanel(this);
        this.add(depositsPanel);
        this.add(strongboxPanel);

        this.setOpaque(false);
        this.setBackground(new Color(0,0,0,0));

    }

    public WarehouseAndDevSpacesPanel getWarehouse() {
        return warehouse;
    }

    public DepositsForBuyDevCardPanel getDepositsPanel() {
        return depositsPanel;
    }

    public StrongBoxForBuyDevCardPanel getStrongboxPanel() {
        return strongboxPanel;
    }


    public GUI getGui() {
        if (warehouse.getBuyDevCardPanel()!= null) return warehouse.getBuyDevCardPanel().getGui();
        if (warehouse.getUseProductionPowerPanel()!= null) return warehouse.getUseProductionPowerPanel().getGui();
        else return null;
    }


    public ArrayList<ResourceType> getChosenResources() {
        return chosenResources;
    }

    public ArrayList<Integer> getChosenWarehouses() {
        return chosenWarehouses;
    }


}
