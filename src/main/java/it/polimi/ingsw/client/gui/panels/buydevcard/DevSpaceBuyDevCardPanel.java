package it.polimi.ingsw.client.gui.panels.buydevcard;

import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class DevSpaceBuyDevCardPanel extends JPanel implements ActionListener {

    private GUI gui;
    private Image devSpaces;
    private JButton devSpace1, devSpace2, devSpace3;
    private int idDevSpace;
    private JPanel mainPanel;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(devSpaces, 0,0, 720, 480, null);
    }

    public DevSpaceBuyDevCardPanel(GUI gui) {
        this.gui = gui;
        InputStream is = getClass().getResourceAsStream("/images/board/devSpaces.jpg");
        try {
            assert is != null;
            devSpaces = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(720, 480));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.X_AXIS));
        cardsPanel.setPreferredSize(new Dimension(720, 380));
        setButtons(buttonPanel);
        setCards(cardsPanel, gui);

        buttonPanel.setVisible(true);
        buttonPanel.setOpaque(false);
        cardsPanel.setOpaque(false);


        this.add(cardsPanel);
        this.add(buttonPanel);
        this.setBackground(new Color(0,0,0,0));


    }

    public void setButtons(JPanel buttonPanel){
        buttonPanel.setPreferredSize(new Dimension( 720,100));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        devSpace1 = new JButton("1");
        devSpace2 = new JButton("2");
        devSpace3 = new JButton("3");
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        panel1.setPreferredSize(new Dimension(120, 380));
        panel2.setPreferredSize(new Dimension(160, 380));
        panel3.setPreferredSize(new Dimension(160, 380));
        panel4.setPreferredSize(new Dimension(160, 380));



        devSpace1.addActionListener(this);
        devSpace1.setBackground(Color.ORANGE);
        panel2.add(devSpace1);
        devSpace2.addActionListener(this);
        devSpace2.setBackground(Color.ORANGE);
        panel3.add(devSpace2);
        devSpace3.addActionListener(this);
        devSpace3.setBackground(Color.ORANGE);
        panel4.add(devSpace3);

        buttonPanel.add(panel1);
        buttonPanel.add(panel2);
        buttonPanel.add(panel3);
        buttonPanel.add(panel4);
        buttonPanel.add(Box.createRigidArea(new Dimension(40, 480)));

        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);


    }

    public void setCards(JPanel cardsPanel, GUI gui){
        JPanel devspace1 = new JPanel();
        JPanel devspace2 = new JPanel();
        JPanel devspace3 = new JPanel();

        devspace1.setLayout(new OverlayLayout(devspace1));
        devspace2.setLayout(new OverlayLayout(devspace2));
        devspace3.setLayout(new OverlayLayout(devspace3));

        devspace1.setPreferredSize(new Dimension(180, 380));
        devspace2.setPreferredSize(new Dimension(180, 380));
        devspace3.setPreferredSize(new Dimension(180, 380));

        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();

        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();

        JLabel label7 = new JLabel();
        JLabel label8 = new JLabel();
        JLabel label9 = new JLabel();

        ArrayList<JLabel> jLabels1 = new ArrayList<>();
        jLabels1.add(label1);
        jLabels1.add(label2);
        jLabels1.add(label3);

        ArrayList<JLabel> jLabels2 = new ArrayList<>();
        jLabels2.add(label4);
        jLabels2.add(label5);
        jLabels2.add(label6);

        ArrayList<JLabel> jLabels3 = new ArrayList<>();
        jLabels3.add(label7);
        jLabels3.add(label8);
        jLabels3.add(label9);

        float shift = 0.5f;
        for(int i = gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(0).getDevelopmentCardsOfDevSpace().size()-1; i >= 0 ; i--){
            try {
                jLabels1.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(0).getDevelopmentCardsOfDevSpace().get(i).getPath())).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
                jLabels1.get(i).setAlignmentY(shift);
                shift = shift - 0.2f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        shift = 0.5f;
        for(int i = gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(1).getDevelopmentCardsOfDevSpace().size()-1; i >= 0 ; i--){
            try {
                jLabels2.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(1).getDevelopmentCardsOfDevSpace().get(i).getPath())).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
                jLabels2.get(i).setAlignmentY(shift);
                shift = shift - 0.2f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        shift = 0.5f;
        for(int i = gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(2).getDevelopmentCardsOfDevSpace().size()-1; i >= 0 ; i--){
            try {
                jLabels3.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(gui.getClient().getClientModelView().getLiteBoard().getDevelopmentSpaces().get(2).getDevelopmentCardsOfDevSpace().get(i).getPath())).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
                jLabels3.get(i).setAlignmentY(shift);
                shift = shift - 0.2f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cardsPanel.add(devspace1);
        cardsPanel.add(devspace2);
        cardsPanel.add(devspace3);
        cardsPanel.add(Box.createRigidArea(new Dimension(30, 480)));

        devspace1.setOpaque(false);
        devspace2.setOpaque(false);
        devspace3.setOpaque(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == devSpace1){
            idDevSpace=1;
            devSpace1.setEnabled(false);
        }
        if (e.getSource() == devSpace2){
            idDevSpace=2;
            devSpace2.setEnabled(false);
        }
        if (e.getSource() == devSpace3){
            idDevSpace=3;
            devSpace3.setEnabled(false);
        }

    }

    public int getIdDevSpace() {
        return idDevSpace;
    }


}