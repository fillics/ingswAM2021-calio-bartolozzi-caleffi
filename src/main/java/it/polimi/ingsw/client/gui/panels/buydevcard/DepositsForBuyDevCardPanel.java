package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class that creates the panel that contains all the deposits for the buy development card operation
 */
public class DepositsForBuyDevCardPanel  extends JPanel implements ActionListener {

    private Image background;
    private final WarehouseForBuyDevCardPanel warehousePanel;
    private final GridBagConstraints c;
    private JButton resource1, resource2, resource3, resource4, resource5, resource6;
    private ArrayList<JButton> resources;
    private JPanel depot1, depot2, depot3;
    private final boolean[] emptyResources = new boolean[6];

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, 250, 300, null);
    }

    /**
     * Class' constructor
     * @param warehousePanel is the warehouse panel
     */
    public DepositsForBuyDevCardPanel (WarehouseForBuyDevCardPanel warehousePanel) {
        this.warehousePanel = warehousePanel;
        this.setPreferredSize(new Dimension(250, 300));
        InputStream is = getClass().getResourceAsStream("/images/board/deposits.jpg");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Arrays.fill(emptyResources, true);

        JPanel panel = new JPanel();

        c = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());

        createDeposits();
        c.gridx=0;
        c.gridy=0;
        panel.add(depot1, c);
        c.gridx=0;
        c.gridy=1;
        panel.add(depot2, c);
        c.gridx=0;
        c.gridy=2;
        panel.add(depot3, c);
        panel.setOpaque(false);

        this.add(panel);

    }

    /**
     * Method that creates all the deposit panels
     */
    public void createDeposits(){
        depot1 = new JPanel();
        depot1.setLayout(new GridBagLayout());
        depot2 = new JPanel();
        depot2.setLayout(new GridBagLayout());
        depot3 = new JPanel();
        depot3.setLayout(new GridBagLayout());


        createResources();

        c.insets = new Insets(55, 30, 0, 0);
        c.gridx=0;
        c.gridy=0;
        depot1.add(resource1, c);

        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(10, 50, 0, 0);
        depot2.add(resource2, c);
        c.gridx=1;
        c.gridy=0;
        c.insets = new Insets(10, 0, 0, 0);
        depot2.add(resource3, c);

        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(10, 50, 0, 0);
        depot3.add(resource4, c);
        c.gridx=1;
        c.gridy=0;
        c.insets = new Insets(10, 0, 0, 0);
        depot3.add(resource5, c);
        c.gridx=2;
        c.gridy=0;
        c.insets = new Insets(10, 0, 0, 0);
        depot3.add(resource6, c);


        depot1.setOpaque(false);
        depot2.setOpaque(false);
        depot3.setOpaque(false);
    }


    /**
     * Method that creates the resources to put in the deposits
     */
    public void createResources(){
        resources = new ArrayList<>();

        createButtons();

        createImages();

        for(JButton btn: resources){
            btn.addActionListener(this);
            btn.setBackground(new Color(0,0,0,0));
            btn.setOpaque(false);
            btn.setBorderPainted(false);
        }
    }

    /**
     * Method that creates the buttons for the panel
     */
    public void createButtons(){
        resource1 = new JButton();
        resource2 = new JButton();
        resource3 = new JButton();
        resource4 = new JButton();
        resource5 = new JButton();
        resource6 = new JButton();
        resources.add(resource1);
        resources.add(resource2);
        resources.add(resource3);
        resources.add(resource4);
        resources.add(resource5);
        resources.add(resource6);
    }

    /**
     * Method that creates the images of the resources in the deposits
     */
    public void createImages(){
        //FIRST DEPOSIT
        if(warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(0).getResourcetype() != null){
            try {
                resources.get(0).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(0).getResourcetype().path)).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                emptyResources[0]=false;
                resources.get(0).setEnabled(true);
            } catch (IOException ignored) {}

        }
        else {
            try {
                resources.get(0).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(0).setEnabled(false);
            } catch (IOException ignored) {}
        }

        //SECOND DEPOSIT
        if(warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getResourcetype() != null){
            for (int i = 1; i< warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getQuantity()+1; i++) {
                try {
                    resources.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getResourcetype().path)).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
                    emptyResources[i]=false;
                    resources.get(i).setEnabled(true);
                } catch (IOException ignored) {}
            }

        }
        for (int i = 2; i > warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getQuantity(); i--) {
            try {
                resources.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(i).setEnabled(false);
            } catch (IOException ignored) {}
        }


        //THIRD DEPOSIT
        if(warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getResourcetype() != null){
            for (int i = 3; i< warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getQuantity()+3; i++) {
                try {
                    resources.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getResourcetype().path)).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
                    emptyResources[i]=false;
                    resources.get(i).setEnabled(true);
                } catch (IOException ignored) {}
            }
        }

        for (int i = 5; i > 2+ warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getQuantity(); i--) {
            try {
                resources.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(i).setEnabled(false);
            } catch (IOException ignored) {}
        }
    }

    /**
     * Method that add information to the warehouse panel
     * @param i is the position of the deposit
     * @param warehouse is the number of the warehouse
     */
    public void addInformation(int i, int warehouse){
        warehousePanel.getChosenWarehouses().add(warehouse);
        ResourceType resourceType = warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().get(i).getResourcetype();
        warehousePanel.getChosenResources().add(resourceType);
        warehousePanel.getWarehouse().addResource(resourceType);

    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //entro nell'if solo se il bottone contiene l'immagine della risorsa
        if(e.getSource() == resource1 && !emptyResources[0]){
            addInformation(0, 1);
            resource1.setEnabled(false);
        }
        if(e.getSource() == resource2 && !emptyResources[1]){
            addInformation(1,2);
            resource2.setEnabled(false);

        }
        if(e.getSource() == resource3 && !emptyResources[2]){
            addInformation(1,2);
            resource3.setEnabled(false);

        }
        if(e.getSource() == resource4 && !emptyResources[3]){
            addInformation(2,3);
            resource4.setEnabled(false);

        }
        if(e.getSource() == resource5 && !emptyResources[4]){
            addInformation(2,3);
            resource5.setEnabled(false);

        }
        if(e.getSource() == resource6 && !emptyResources[5]){
            addInformation(2,3);
            resource6.setEnabled(false);

        }


    }

}
