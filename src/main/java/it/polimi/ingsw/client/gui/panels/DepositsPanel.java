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

    private GUI gui;
    private Image background;
    private JButton deposit1Button;
    private JButton deposit2Button;
    private JButton deposit3Button;
    private JLabel resource1;
    private JLabel resource2;
    private JLabel resource3;
    private JLabel resource4;
    private JLabel resource5;
    private JLabel resource6;


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, 250, 300, null);
    }

    public DepositsPanel(GUI gui) {
        this.gui = gui;
        this.setPreferredSize(new Dimension(250, 300));
        InputStream is = getClass().getResourceAsStream("/images/board/deposits.jpg");
        try {
            background = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setPreferredSize(new Dimension(250, 300));

        JPanel depot1= new JPanel();
        depot1.setLayout(new BoxLayout(depot1, BoxLayout.X_AXIS));
        JPanel depot2 = new JPanel();
        depot2.setLayout(new BoxLayout(depot2, BoxLayout.X_AXIS));
        JPanel depot3 = new JPanel();
        depot3.setLayout(new BoxLayout(depot3, BoxLayout.X_AXIS));


        deposit1Button=new JButton();
        deposit2Button=new JButton();
        deposit3Button=new JButton();

        deposit1Button.addActionListener(this);
        deposit2Button.addActionListener(this);
        deposit3Button.addActionListener(this);

        deposit1Button.setBackground(new Color(151, 74, 74));
        deposit2Button.setBackground(new Color(151, 74, 74));
        deposit3Button.setBackground(new Color(151, 74, 74));

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getResourcetype() != null){
            try {
                resources.get(1).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(0).getResourcetype().path).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(2).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(1).getResourcetype().path).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                resources.get(1).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/empty.png").readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(2).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/empty.png").readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getResourcetype() != null){
            try {
                resources.get(3).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getResourcetype().path).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(4).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getResourcetype().path).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(5).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDeposits().get(2).getResourcetype().path).readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                resources.get(3).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/empty.png").readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(4).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/empty.png").readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
                resources.get(5).setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/empty.png").readAllBytes()).getImage().getScaledInstance(45,45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


     /*   try {
            resource2.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/shield.png").readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            resource3.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/shield.png").readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            resource4.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/servant.png").readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            resource5.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/coin.png").readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            resource6.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream("/images/punchboard/stone.png").readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public static void main(String[] args) {

       // DepositsPanel depositsPanel = new DepositsPanel();
        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale
     //   frame.add(depositsPanel);

        frame.setTitle("Master of Renaissance");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.pack();

    }

}
