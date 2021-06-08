package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.buydevcard.WarehouseForBuyDevCardPanel;
import it.polimi.ingsw.model.cards.leadercards.LeaderCardType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class LeaderCardPanel extends JPanel implements ActionListener {
    private TakeResourceFromMarketPanel takeResourceFromMarketPanel;
    private GUI gui;
    private Image background;
    private int id;
    private int position;
    private JButton button = new JButton();
    private JButton depositButton = new JButton();
    private JButton depostForBuyDevCardAndProdPower = new JButton();
    private DepositsPanel depositsPanel;
    private WarehouseForBuyDevCardPanel warehouseForBuyDevCardPanel;
    private JPanel leadercard;
    private JPanel standardPanel;



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0,159, 240, null);
    }

    public LeaderCardPanel(GUI gui, int id, TakeResourceFromMarketPanel takeResourceFromMarketPanel){
        this.takeResourceFromMarketPanel= takeResourceFromMarketPanel;
        this.setPreferredSize(new Dimension(159, 240));
        this.id = id;
        this.gui = gui;
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(id == gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId()){
                position = i;
            }
        }
        InputStream is = getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getPath());
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        leadercard = new JPanel();
        leadercard.setPreferredSize(new Dimension(159, 240));
        leadercard.setLayout(new BoxLayout(leadercard, BoxLayout.Y_AXIS));

        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.WHITE_MARBLE)){
            JPanel specialPower = new JPanel();
            specialPower.setPreferredSize(new Dimension(159, 70));
            button.addActionListener(takeResourceFromMarketPanel);
            button.setPreferredSize(new Dimension(159, 70));
            try {
                button.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(159, 70, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            specialPower.add(button);
            specialPower.setOpaque(false);

            leadercard.add(Box.createRigidArea(new Dimension(159, 170)));
            leadercard.add(specialPower);
        }

        this.add(leadercard);
        leadercard.setOpaque(false);
        this.setOpaque(false);
    }

    public void defaultSetup(){
        this.setPreferredSize(new Dimension(159, 240));
        for(int i = 0 ; i < gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().size(); i++){
            if(id == gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(i).getId()){
                position = i;
            }
        }
        InputStream is = getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getPath());
        try {
            background = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        leadercard.setPreferredSize(new Dimension(159, 240));
        leadercard.setLayout(new BoxLayout(leadercard, BoxLayout.Y_AXIS));



        JLabel isactive = new JLabel();
        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getStrategy().isActive()){
            try {
                isactive.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/active.png")).readAllBytes()).getImage().getScaledInstance(30,30, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                isactive.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/notactive.png")).readAllBytes()).getImage().getScaledInstance(30,30, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        standardPanel.setLayout(new BoxLayout(standardPanel, BoxLayout.X_AXIS));
        standardPanel.setPreferredSize(new Dimension(159, 40));
        standardPanel.add(Box.createRigidArea(new Dimension(129, 40)));
        standardPanel.add(isactive);
        standardPanel.setOpaque(false);
        leadercard.add(standardPanel);
        leadercard.setOpaque(false);

    }

    public void setMarbleAndProdPower(){
        JPanel specialPower = new JPanel();
        specialPower.setPreferredSize(new Dimension(159, 70));
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(159, 70));
        try {
            button.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(159, 70, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        specialPower.add(button);
        specialPower.setOpaque(false);

        leadercard.add(Box.createRigidArea(new Dimension(159, 125)));
        leadercard.add(specialPower);
    }

    public void setExtraDeposit (JButton depositButton){
        depositButton.setBackground(new Color(151, 74, 74));
        depositButton.addActionListener(this);

        JPanel depositspace = new JPanel();
        depositspace.setLayout(new BoxLayout(depositspace, BoxLayout.X_AXIS));
        depositspace.setPreferredSize(new Dimension(159, 35));
        JLabel resource1 = new JLabel();
        JLabel resource2 = new JLabel();
        ArrayList<JLabel> resources = new ArrayList<>();
        resources.add(resource1);
        resources.add(resource2);

        for(int i = 0; i < 2; i++){
            if(i < gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getDepositPosition()).getQuantity()){
                try {
                    resources.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getDepositPosition()).getResourcetype().path)).readAllBytes()).getImage().getScaledInstance(35,35, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    resources.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/empty.png")).readAllBytes()).getImage().getScaledInstance(35, 35, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        depositspace.add(resources.get(0));
        depositspace.add(Box.createRigidArea(new Dimension(20, 35)));
        depositspace.add(resources.get(1));
        depositspace.setOpaque(false);

        leadercard.add(Box.createRigidArea(new Dimension(159, 125)));
        leadercard.add(depositButton);
        leadercard.add(Box.createRigidArea(new Dimension(159, 10)));
        leadercard.add(depositspace);
    }
    public LeaderCardPanel(GUI gui, int id, int width, int height, DepositsPanel depositsPanel) {
        this.depositsPanel = depositsPanel;
        this.id = id;
        this.gui = gui;

        leadercard = new JPanel();
        standardPanel = new JPanel();

        defaultSetup();

        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.WHITE_MARBLE) ||
        gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.PRODUCTION_POWER)){
            setMarbleAndProdPower();
        }

        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.EXTRA_DEPOSIT)
        && gui.getClient().getClientModelView().getLiteBoard().getDeposits().size() > 3) {
            setExtraDeposit(depositButton);
        }

        this.add(leadercard);
        this.setOpaque(false);
    }

    public LeaderCardPanel(GUI gui, int id, int width, int height, WarehouseForBuyDevCardPanel warehouseForBuyDevCardPanel) {
        this.warehouseForBuyDevCardPanel = warehouseForBuyDevCardPanel;
        this.id = id;

        leadercard = new JPanel();
        standardPanel = new JPanel();

        defaultSetup();

        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.WHITE_MARBLE) ||
                gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.PRODUCTION_POWER)){
            setMarbleAndProdPower();
        }

        if(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getType().equals(LeaderCardType.EXTRA_DEPOSIT)
                && gui.getClient().getClientModelView().getLiteBoard().getDeposits().size() > 3) {
            setExtraDeposit(depostForBuyDevCardAndProdPower);
        }

        this.add(leadercard);
        this.setOpaque(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == depositButton){
            depositsPanel.getIdDepot().add(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getDepositPosition() + 1);
        }
        if(e.getSource() == depostForBuyDevCardAndProdPower){
            warehouseForBuyDevCardPanel.getChosenWarehouses().add(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getDepositPosition() + 1);
            warehouseForBuyDevCardPanel.getChosenResources().add(gui.getClient().getClientModelView().getMyPlayer().getLeaderCards().get(position).getResourceType());
        }
    }

    public JButton getButton() {
        return button;
    }

    public int getId() {
        return id;
    }

    public JButton getDepositButton() {
        return depositButton;
    }

    public JButton getDepostForBuyDevCardAndProdPower() {
        return depostForBuyDevCardAndProdPower;
    }


}
