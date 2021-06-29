package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.gui.panels.DepositsPanel;
import it.polimi.ingsw.model.board.resources.ResourceType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class StrongBoxForBuyDevCardPanel extends JPanel implements ActionListener {

    private WarehouseForBuyDevCardPanel warehousePanel;
    private Image background;
    private DepositsPanel depositsPanel;

    private JButton coinButton, stoneButton, servantButton, shieldButton;
    private JPanel coinPanel, stonePanel, servantPanel, shieldPanel;

    private ArrayList<JButton> resourcesButtons;
    private int howManyCoins, howManyStones, howManyServants, howManyShields;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 250, 180, null);
    }

    public StrongBoxForBuyDevCardPanel(WarehouseForBuyDevCardPanel warehousePanel) {
        this.setPreferredSize(new Dimension(250, 180));
        this.warehousePanel = warehousePanel;
        InputStream is = getClass().getResourceAsStream("/images/board/strongbox.jpg");
        try {
            assert is != null;
            background = ImageIO.read(is);
        } catch (IOException ignored) {
        }

        resourcesButtons = new ArrayList<>();
        coinPanel = new JPanel();
        coinPanel.setLayout(new BoxLayout(coinPanel, BoxLayout.X_AXIS));
        coinPanel.setOpaque(false);

        stonePanel = new JPanel();
        stonePanel.setLayout(new BoxLayout(stonePanel, BoxLayout.X_AXIS));
        stonePanel.setOpaque(false);

        servantPanel = new JPanel();
        servantPanel.setLayout(new BoxLayout(servantPanel, BoxLayout.X_AXIS));
        servantPanel.setOpaque(false);

        shieldPanel = new JPanel();
        shieldPanel.setLayout(new BoxLayout(shieldPanel, BoxLayout.X_AXIS));
        shieldPanel.setOpaque(false);


        JLabel coin = new JLabel();
        JLabel stone = new JLabel();
        JLabel servant = new JLabel();
        JLabel shield = new JLabel();

        coinButton = new JButton();
        shieldButton = new JButton();
        servantButton = new JButton();
        stoneButton = new JButton();
        resourcesButtons.add(coinButton);
        resourcesButtons.add(shieldButton);
        resourcesButtons.add(servantButton);
        resourcesButtons.add(stoneButton);

        for(JButton btn: resourcesButtons){
            btn.addActionListener(this);
            btn.setBackground(new Color(0,0,0,0));
            btn.setOpaque(false);
            btn.setBorderPainted(false);
        }

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(250, 180));
        JPanel panel2 = new JPanel();
        //panel2.setPreferredSize(new Dimension(250, 80));
        JPanel panel3 = new JPanel();
        //panel3.setPreferredSize(new Dimension(250, 80));

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        try {
            coinButton.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/coin.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            stoneButton.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/stone.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            servantButton.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/servant.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            shieldButton.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/shield.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException ignored) {}

        howManyCoins = warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.COIN);
        coin.setText(String.valueOf(howManyCoins));
        coin.setForeground(Color.WHITE);
        coin.setFont(new Font(coin.getFont().getName(), Font.PLAIN, 20));

        howManyStones = warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.STONE);
        stone.setText(String.valueOf(howManyStones));
        stone.setForeground(Color.WHITE);
        stone.setFont(new Font(stone.getFont().getName(), Font.PLAIN, 20));

        howManyServants = warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SERVANT);
        servant.setText(String.valueOf(howManyServants));
        servant.setForeground(Color.WHITE);
        servant.setFont(new Font(servant.getFont().getName(), Font.PLAIN, 20));

        howManyShields = warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SHIELD);
        shield.setText(String.valueOf(howManyShields));
        shield.setForeground(Color.WHITE);
        shield.setFont(new Font(shield.getFont().getName(), Font.PLAIN, 20));

        coinPanel.add(coinButton);
        coinPanel.add(coin);

        shieldPanel.add(shieldButton);
        shieldPanel.add(shield);

        stonePanel.add(stoneButton);
        stonePanel.add(stone);

        servantPanel.add(servantButton);
        servantPanel.add(servant);

        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        panel2.add(coinPanel);
        panel2.add(stonePanel);
        panel3.add(servantPanel);
        panel3.add(shieldPanel);


        panel1.add(panel2);
        panel1.add(panel3);


        this.add(panel1);

    }

    public void setButton(ResourceType resourceType){
        warehousePanel.getChosenResources().add(resourceType);
        warehousePanel.getChosenWarehouses().add(warehousePanel.getGui().getClient().getClientModelView().getLiteBoard().getDeposits().size() + 1);
        warehousePanel.getWarehouse().addResource(resourceType);
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == coinButton && howManyCoins!=0){
            setButton(ResourceType.COIN);
        }
        if(e.getSource() == servantButton && howManyServants!=0){
            setButton(ResourceType.SERVANT);
        }
        if(e.getSource() == shieldButton && howManyShields!=0){
            setButton(ResourceType.SHIELD);

        }
        if(e.getSource() == stoneButton && howManyStones!=0){
            setButton(ResourceType.STONE);

        }
    }
}



