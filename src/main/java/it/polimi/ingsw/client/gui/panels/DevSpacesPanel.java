package it.polimi.ingsw.client.gui.panels;


import it.polimi.ingsw.client.ClientModelView;
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

/**
 * Class that creates the panel that contains all the development spaces
 */
public class DevSpacesPanel extends JPanel implements ActionListener {
    private Image devSpaces;
    private JButton devSpace1, devSpace2, devSpace3;
    private JButton baseProdPower;
    private final ArrayList<Integer> productionPowers = new ArrayList<>();
    private final ArrayList<Integer> newProductionPowers = new ArrayList<>();
    private int idDevSpace;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(devSpaces, 0,0, 720, 480, null);
    }

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     */
    public DevSpacesPanel(GUI gui) {
        InputStream is = getClass().getResourceAsStream("/images/board/devSpaces.jpg");
        try {
            devSpaces = ImageIO.read(Objects.requireNonNull(is));
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
        setCards(cardsPanel, gui.getClient().getClientModelView());

        buttonPanel.setVisible(true);
        buttonPanel.setOpaque(false);
        cardsPanel.setOpaque(false);


        this.add(cardsPanel);
        this.add(buttonPanel);
        this.setVisible(true);

    }

    /**
     * Class' constructor used in the composition of the other player board panel
     * @param clientModelView is the client model view in which are contained the information
     */
    public DevSpacesPanel(ClientModelView clientModelView) {
        InputStream is = getClass().getResourceAsStream("/images/board/devSpaces.jpg");
        try {
            devSpaces = ImageIO.read(Objects.requireNonNull(is));
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
        setCards(cardsPanel, clientModelView);

        buttonPanel.setVisible(true);
        buttonPanel.setOpaque(false);
        cardsPanel.setOpaque(false);


        this.add(cardsPanel);
        this.add(buttonPanel);
        this.setVisible(true);

    }

    /**
     * Method that sets the buttons of the panel
     * @param buttonPanel is the panel that contains the buttons
     */
    public void setButtons(JPanel buttonPanel){
        buttonPanel.setPreferredSize(new Dimension( 720,100));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        baseProdPower = new JButton("BASE PROD POWER");
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

        baseProdPower.addActionListener(this);
        baseProdPower.setBackground(new Color(233, 226, 193));
        baseProdPower.setFont(new Font("Times New Roman", baseProdPower.getFont().getStyle(), 10));
        panel1.add(baseProdPower);
        devSpace1.addActionListener(this);
        devSpace1.setBackground(new Color(233, 226, 193));
        panel2.add(devSpace1);
        devSpace2.addActionListener(this);
        devSpace2.setBackground(new Color(233, 226, 193));
        panel3.add(devSpace2);
        devSpace3.addActionListener(this);
        devSpace3.setBackground(new Color(233, 226, 193));
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

    /**
     * Method that sets the cards on the development spaces
     * @param cardsPanel is the panel that contains the cards of every development space
     * @param clientModelView is the client model view in which are contained the information
     */
    public void setCards(JPanel cardsPanel, ClientModelView clientModelView){
        JPanel prodPower = new JPanel();
        JPanel devspace1 = new JPanel();
        JPanel devspace2 = new JPanel();
        JPanel devspace3 = new JPanel();

        devspace1.setLayout(new OverlayLayout(devspace1));
        devspace2.setLayout(new OverlayLayout(devspace2));
        devspace3.setLayout(new OverlayLayout(devspace3));

        prodPower.setPreferredSize(new Dimension(150, 380));
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
        for(int i = clientModelView.getLiteBoard().getDevelopmentSpaces().get(0).getDevelopmentCardsOfDevSpace().size()-1; i >= 0 ; i--){
            try {
                jLabels1.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getLiteBoard().getDevelopmentSpaces().get(0).getDevelopmentCardsOfDevSpace().get(i).getPath())).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
                jLabels1.get(i).setAlignmentY(shift);
                devspace1.add(jLabels1.get(i));
                shift = shift - 0.2f;
            } catch (IOException ignored) {}
        }

        shift = 0.5f;
        for(int i = clientModelView.getLiteBoard().getDevelopmentSpaces().get(1).getDevelopmentCardsOfDevSpace().size()-1; i >= 0 ; i--){
            try {
                jLabels2.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getLiteBoard().getDevelopmentSpaces().get(1).getDevelopmentCardsOfDevSpace().get(i).getPath())).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
                jLabels2.get(i).setAlignmentY(shift);
                devspace2.add(jLabels2.get(i));
                shift = shift - 0.2f;
            } catch (IOException ignored) {}
        }

        shift = 0.5f;
        for(int i = clientModelView.getLiteBoard().getDevelopmentSpaces().get(2).getDevelopmentCardsOfDevSpace().size()-1; i >= 0 ; i--){
            try {
                jLabels3.get(i).setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(GUI.class.getResourceAsStream(clientModelView.getLiteBoard().getDevelopmentSpaces().get(2).getDevelopmentCardsOfDevSpace().get(i).getPath())).readAllBytes()).getImage().getScaledInstance(150, 200, Image.SCALE_AREA_AVERAGING)));
                jLabels3.get(i).setAlignmentY(shift);
                devspace3.add(jLabels3.get(i));
                shift = shift - 0.2f;
            } catch (IOException ignored) {}
        }


        cardsPanel.add(prodPower);
        cardsPanel.add(devspace1);
        cardsPanel.add(devspace2);
        cardsPanel.add(devspace3);
        cardsPanel.add(Box.createRigidArea(new Dimension(30, 480)));

        prodPower.setOpaque(false);
        devspace1.setOpaque(false);
        devspace2.setOpaque(false);
        devspace3.setOpaque(false);
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == devSpace1){
            idDevSpace=1;
            if(!productionPowers.contains(1)) productionPowers.add(idDevSpace);
        }
        if(e.getSource() == devSpace2){
            idDevSpace=2;
            if(!productionPowers.contains(2)) productionPowers.add(idDevSpace);
        }
        if(e.getSource() == devSpace3){
            idDevSpace=3;
            if(!productionPowers.contains(3)) productionPowers.add(idDevSpace);
        }
        if(e.getSource() == baseProdPower){
            if(!newProductionPowers.contains(1)) newProductionPowers.add(1);
        }

    }

    /**
     * Class' getter
     * @return the id of the development space
     */
    public int getIdDevSpace() {
        return idDevSpace;
    }

    /**
     * Class' getter
     * @return the button of the first development space
     */
    public JButton getDevSpace1() {
        return devSpace1;
    }

    /**
     * Class' getter
     * @return the button of the second development space
     */
    public JButton getDevSpace2() {
        return devSpace2;
    }

    /**
     * Class' getter
     * @return the button of the third development space
     */
    public JButton getDevSpace3() {
        return devSpace3;
    }

    /**
     * Class' getter
     * @return the button of the base production power
     */
    public JButton getBaseProdPower() {
        return baseProdPower;
    }

    /**
     * Class' getter
     * @return the standard production powers selected in the development spaces
     */
    public ArrayList<Integer> getProductionPowers() {
        return productionPowers;
    }

    /**
     * Class' getter
     * @return the special production powers selected
     */
    public ArrayList<Integer> getNewProductionPowers() {
        return newProductionPowers;
    }

    /**
     * Class' setter
     */
    public void setProductionPowerInvisible(){
        baseProdPower.setVisible(false);
    }
}
