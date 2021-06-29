package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.DevSpacesPanel;
import it.polimi.ingsw.client.gui.panels.UseProductionPowerPanel;
import it.polimi.ingsw.client.gui.panels.buydevcard.BuyDevCardPanel;
import it.polimi.ingsw.client.gui.panels.buydevcard.WarehouseForBuyDevCardPanel;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class WarehouseAndDevSpacesPanel extends JPanel {
    private WarehouseForBuyDevCardPanel warehousePanel;
    private DevSpacesPanel devSpacesPanel;
    private BuyDevCardPanel buyDevCardPanel;
    private UseProductionPowerPanel useProductionPowerPanel;

    private JPanel mainPanel;
    private GridBagConstraints c;
    private int widthRes, heightRes;
    private ArrayList<JLabel> resources;


    // TODO: 29/06/2021 da fare javadoc 
    public WarehouseAndDevSpacesPanel(BuyDevCardPanel buyDevCardPanel) {
        this.buyDevCardPanel = buyDevCardPanel;
        constructor();

        devSpacesPanel.setProductionPowerInvisible();
        c.gridx=0;
        c.gridy=0;
        this.add(mainPanel, c);
        this.setOpaque(false);

    }
    public WarehouseAndDevSpacesPanel(UseProductionPowerPanel useProductionPowerPanel) {
        this.useProductionPowerPanel = useProductionPowerPanel;
        constructor();
        c.gridx=0;
        c.gridy=0;
        this.add(mainPanel, c);
        this.setOpaque(false);

    }

    public void constructor(){
        this.setLayout(new GridBagLayout());
        resources = new ArrayList<>();
        widthRes=0;
        heightRes=50;
        c = new GridBagConstraints();

        createMainPanel();
    }

    public void createMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        warehousePanel = new WarehouseForBuyDevCardPanel(this);
        GUI gui = null;
        if(buyDevCardPanel!=null) gui = buyDevCardPanel.getGui();
        if(useProductionPowerPanel!=null) gui = useProductionPowerPanel.getGui();

        devSpacesPanel = new DevSpacesPanel(gui);

        mainPanel.add(warehousePanel);
        mainPanel.add(devSpacesPanel);
    }

    public void createResources(){
        JPanel resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new BoxLayout(resourcesPanel, BoxLayout.X_AXIS));
        resourcesPanel.setBackground(new Color(233, 226, 193));
        resourcesPanel.setPreferredSize(new Dimension(widthRes, heightRes));

        for (JLabel resource: resources){
            resourcesPanel.add(resource);
        }
        c.gridx=0;
        c.gridy=1;
        resourcesPanel.revalidate();

        this.add(resourcesPanel, c);
        this.revalidate();
    }


    public void addResource(ResourceType resourceType){
        JLabel resource = new JLabel();
        String path;
        switch (resourceType){
            case COIN -> path = "/images/punchboard/coin.png";
            case STONE -> path = "/images/punchboard/stone.png";
            case SHIELD -> path = "/images/punchboard/shield.png";
            case SERVANT -> path = "/images/punchboard/servant.png";
            default -> throw new IllegalStateException("Unexpected value: " + resourceType);
        }
        try{
            resource.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(path)).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        }catch (IOException ignored) {}
        widthRes+=50;
        if(resources.size()<16) resources.add(resource);
        createResources();
    }


    public BuyDevCardPanel getBuyDevCardPanel() {
        return buyDevCardPanel;
    }

    public WarehouseForBuyDevCardPanel getWarehousePanel() {
        return warehousePanel;
    }

    public UseProductionPowerPanel getUseProductionPowerPanel() {
        return useProductionPowerPanel;
    }

    public DevSpacesPanel getDevSpacesPanel() {
        return devSpacesPanel;
    }
}