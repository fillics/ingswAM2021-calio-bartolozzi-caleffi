package it.polimi.ingsw.client.gui.panels;


import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DepositsPanel extends JPanel implements ActionListener {

    private Image background;
    private JButton deposit1Button, deposit2Button, deposit3Button;
    private JLabel resource1, resource2, resource3, resource4, resource5, resource6;
    private final ArrayList<Integer> idDepot;


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, 250, 300, null);
    }

    public DepositsPanel(GUI gui) {
        this.setPreferredSize(new Dimension(250, 300));
        InputStream is = getClass().getResourceAsStream("/images/board/deposits.jpg");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        idDepot = new ArrayList<>();
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setPreferredSize(new Dimension(250, 300));

        JPanel depot1= new JPanel();
        depot1.setLayout(new BoxLayout(depot1, BoxLayout.X_AXIS));
        JPanel depot2 = new JPanel();
        depot2.setLayout(new BoxLayout(depot2, BoxLayout.X_AXIS));
        JPanel depot3 = new JPanel();
        depot3.setLayout(new BoxLayout(depot3, BoxLayout.X_AXIS));


        createButtons();

        resource1 = new JLabel();
        resource2 = new JLabel();
        resource3 = new JLabel();
        resource4 = new JLabel();
        resource5 = new JLabel();
        resource6 = new JLabel();

        ArrayList<JLabel> resources = new ArrayList<>();
        resources.add(resource1);
        resources.add(resource2);
        resources.add(resource3);
        resources.add(resource4);
        resources.add(resource5);
        resources.add(resource6);

        //FIRST DEPOSIT
        if(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(0).getResourcetype() != null){
            try {
                resources.get(0).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(0).getResourcetype().path).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            try {
                resources.get(0).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/empty.png").readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException ignored) {}
        }

        //SECOND DEPOSIT
        if(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getResourcetype() != null){
            for (int i=1; i<gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getQuantity()+1; i++) {
                try {
                    resources.get(i).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getResourcetype().path).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
                } catch (IOException ignored) {
                }
            }

        }
        for (int i = 2; i > gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getQuantity(); i--) {
            try {
                resources.get(i).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/empty.png").readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException ignored) {}
        }


        //THIRD DEPOSIT
        if(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getResourcetype() != null){
            for (int i=3; i<gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getQuantity()+3; i++) {
                try {
                    resources.get(i).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getResourcetype().path).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
                    } catch (IOException ignored) {}
            }
        }

        for (int i=5; i > 2+gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getQuantity(); i--) {
            try {
                resources.get(i).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/empty.png").readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException ignored) {}
        }

        depot1.add(deposit1Button);
        depot1.add(Box.createRigidArea(new Dimension(35,0)));
        depot1.add(resource1);
        depot1.add(Box.createRigidArea(new Dimension(20,0)));
        depot2.add(deposit2Button);
        depot2.add(Box.createRigidArea(new Dimension(40, 0)));
        depot2.add(resource2);
        depot2.add(resource3);
        depot2.add(Box.createRigidArea(new Dimension(25, 0)));
        depot3.add(deposit3Button);
        depot3.add(Box.createRigidArea(new Dimension(35, 0)));
        depot3.add(resource4);
        depot3.add(resource5);
        depot3.add(resource6);
        depot3.add(Box.createRigidArea(new Dimension(25, 0)));


        depot1.setOpaque(false);
        depot2.setOpaque(false);
        depot3.setOpaque(false);


        panel1.add(Box.createRigidArea(new Dimension(0, 80)));
        panel1.add(depot1);
        panel1.add(Box.createRigidArea(new Dimension(0, 20)));
        panel1.add(depot2);
        panel1.add(Box.createRigidArea(new Dimension(0, 30)));
        panel1.add(depot3);

        panel1.setOpaque(false);
        this.add(panel1);
    }

    public void createButtons(){
        deposit1Button=new JButton();
        deposit2Button=new JButton();
        deposit3Button=new JButton();

        deposit1Button.addActionListener(this);
        deposit2Button.addActionListener(this);
        deposit3Button.addActionListener(this);

        changeBackgroundColor(new Color(151, 74, 74));

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==deposit1Button){
            idDepot.add(1);
            deposit1Button.setBackground(Color.GREEN);
            deposit1Button.setEnabled(false);

        }
        if(e.getSource()==deposit2Button){
            idDepot.add(2);
            deposit2Button.setBackground(Color.GREEN);
            deposit2Button.setEnabled(false);
        }
        if(e.getSource()==deposit3Button){
            idDepot.add(3);
            deposit3Button.setBackground(Color.GREEN);
            deposit3Button.setEnabled(false);
        }
    }



    public void changeBackgroundColor(Color color){
        deposit1Button.setBackground(color);
        deposit2Button.setBackground(color);
        deposit3Button.setBackground(color);
    }

    public ArrayList<Integer> getIdDepot() {
        return idDepot;
    }

    public void setDisabled(){
        deposit1Button.setEnabled(false);
        deposit2Button.setEnabled(false);
        deposit3Button.setEnabled(false);
    }

    public void setEnabled(){
        deposit1Button.setEnabled(true);
        deposit2Button.setEnabled(true);
        deposit3Button.setEnabled(true);
    }


    public JButton getDeposit1Button() {
        return deposit1Button;
    }

    public JButton getDeposit2Button() {
        return deposit2Button;
    }

    public JButton getDeposit3Button() {
        return deposit3Button;
    }
}
