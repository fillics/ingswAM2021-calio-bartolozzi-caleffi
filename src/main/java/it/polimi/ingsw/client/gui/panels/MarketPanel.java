package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.model.marbles.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Class that creates the panel that contains the market tray
 */
public class MarketPanel extends JPanel implements ActionListener {
    private Image imagereal;
    private JButton row1, row2, row3, column1, column2, column3, column4;

    private String line="";
    private int numline=0;
    private final Marble[][] table;
    private final Marble marble;

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imagereal, 0,0, 550, 700, null);
    }

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     * @param isShow is a boolean variable that, if true the market tray buttons are enabled, if false the market tray 
     * buttons are disabled
     */
    public MarketPanel(GUI gui, boolean isShow) {
        table= gui.getClient().getClientModelView().getMarketTray().getTable();
        marble = gui.getClient().getClientModelView().getMarketTray().getRemainingMarble();
        InputStream input= getClass().getResourceAsStream("/images/board/marketTray.png");
        try {
            assert input != null;
            imagereal= ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(550,700));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel topNullPanel = new JPanel();
        JPanel remainingMPanel = new JPanel();
        JPanel marketTrayPanel = new JPanel();
        JPanel columnChoicePanel = new JPanel();
        JPanel downNullPanel= new JPanel();

        topNullPanel.setPreferredSize(new Dimension(550,85));

        remainingMPanel.setLayout(new BoxLayout(remainingMPanel, BoxLayout.X_AXIS));
        remainingMPanel.setPreferredSize(new Dimension(550,65));
        setRemainingMarble(remainingMPanel);

        marketTrayPanel.setLayout(new BoxLayout(marketTrayPanel, BoxLayout.Y_AXIS));
        marketTrayPanel.setPreferredSize(new Dimension(550,204));

        columnChoicePanel.setLayout(new BoxLayout(columnChoicePanel, BoxLayout.X_AXIS));
        columnChoicePanel.setPreferredSize(new Dimension(550,71));

        downNullPanel.setPreferredSize(new Dimension(550, 280));

        if(isShow){
            setMarketTray(marketTrayPanel,true);
            columnChoicePanel.add(Box.createRigidArea(new Dimension(550,71)));
        } else{
            setMarketTray(marketTrayPanel, false);
            setcolumnChoicePanel(columnChoicePanel);
        }

        topNullPanel.setOpaque(false);
        remainingMPanel.setOpaque(false);
        marketTrayPanel.setOpaque(false);
        columnChoicePanel.setOpaque(false);
        downNullPanel.setOpaque(false);

        this.add(topNullPanel);
        this.add(remainingMPanel);
        this.add(marketTrayPanel);
        this.add(columnChoicePanel);
        this.add(downNullPanel);
    }

    /**
     * Method that sets the remaining marble of the market tray
     * @param remainingMPanel is the panel that contains the remaining marble
     */
    public void setRemainingMarble(JPanel remainingMPanel) {
        JPanel remainingMarble = new JPanel();

        remainingMarble.setLayout(new OverlayLayout(remainingMarble));
        remainingMarble.setPreferredSize(new Dimension(200,75));
        remainingMarble.setOpaque(false);
        JLabel label = new JLabel();

        try {
            if(marble instanceof BlueMarble)
                label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/blueMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            if(marble instanceof YellowMarble)
                label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/yellowMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            if(marble instanceof PurpleMarble)
                label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/purpleMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            if(marble instanceof GreyMarble)
                label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/greyMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            if(marble instanceof RedMarble)
                label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/redMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
            if(marble instanceof WhiteMarble)
                label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/whiteMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        remainingMarble.add(label);
        remainingMPanel.add(Box.createRigidArea(new Dimension(350,75)));
        remainingMPanel.add(remainingMarble);

    }

    /**
     * Method that sets the 12 marbles of the market tray
     * @param marketTrayPanel is the market tray panel
     * @param isShow is a boolean variable that, if true the market tray buttons are enabled, if false the market tray 
     * buttons are disabled
     */
    public void setMarketTray(JPanel marketTrayPanel, boolean isShow){
        JPanel row1Panel = new JPanel();
        JPanel row2Panel = new JPanel();
        JPanel row3Panel = new JPanel();
        row1Panel.setOpaque(false);
        row2Panel.setOpaque(false);
        row3Panel.setOpaque(false);

        if(!isShow){
        row1 = new JButton("R1");
        row1.addActionListener(this);
        row1.setBackground(Color.LIGHT_GRAY);
        row2 = new JButton("R2");
        row2.addActionListener(this);
        row2.setBackground(Color.LIGHT_GRAY);
        row3 = new JButton("R3");
        row3.addActionListener(this);
        row3.setBackground(Color.LIGHT_GRAY);
        }

        row1Panel.setLayout(new BoxLayout(row1Panel, BoxLayout.X_AXIS));
        row2Panel.setLayout(new BoxLayout(row2Panel, BoxLayout.X_AXIS));
        row3Panel.setLayout(new BoxLayout(row3Panel, BoxLayout.X_AXIS));

        row1Panel.setPreferredSize(new Dimension(550,68));
        row2Panel.setPreferredSize(new Dimension(550,68));
        row3Panel.setPreferredSize(new Dimension(550,68));

        JLabel [][] labels= new JLabel[3][4];
        JLabel label1_1 = new JLabel();
        JLabel label1_2 = new JLabel();
        JLabel label1_3 = new JLabel();
        JLabel label1_4 = new JLabel();
        JLabel label2_1 = new JLabel();
        JLabel label2_2 = new JLabel();
        JLabel label2_3 = new JLabel();
        JLabel label2_4 = new JLabel();
        JLabel label3_1 = new JLabel();
        JLabel label3_2 = new JLabel();
        JLabel label3_3 = new JLabel();
        JLabel label3_4 = new JLabel();
        labels[0][0]= label1_1;
        labels[0][1]= label1_2;
        labels[0][2]= label1_3;
        labels[0][3]= label1_4;
        labels[1][0]= label2_1;
        labels[1][1]= label2_2;
        labels[1][2]= label2_3;
        labels[1][3]= label2_4;
        labels[2][0]= label3_1;
        labels[2][1]= label3_2;
        labels[2][2]= label3_3;
        labels[2][3]= label3_4;

        try {
            for(int i=0; i<3;i++){
                for(int j=0; j<4;j++){
                    if(table[i][j] instanceof BlueMarble)
                        labels[i][j].setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/blueMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    if(table[i][j] instanceof YellowMarble)
                        labels[i][j].setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/yellowMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    if(table[i][j] instanceof PurpleMarble)
                        labels[i][j].setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/purpleMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    if(table[i][j] instanceof GreyMarble)
                        labels[i][j].setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/greyMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    if(table[i][j] instanceof RedMarble)
                        labels[i][j].setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/redMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    if(table[i][j] instanceof WhiteMarble)
                        labels[i][j].setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/marbles/whiteMarble.png")).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        row1Panel.add(Box.createRigidArea(new Dimension(98,68)));
        row1Panel.add(labels[0][0]);
        row1Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row1Panel.add(labels[0][1]);
        row1Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row1Panel.add(labels[0][2]);
        row1Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row1Panel.add(labels[0][3]);
        row1Panel.add(Box.createRigidArea(new Dimension(15,68)));
        if(!isShow)
            row1Panel.add(row1);
        else
            row1Panel.add(Box.createRigidArea(new Dimension(50,68)));

        row2Panel.add(Box.createRigidArea(new Dimension(98,68)));
        row2Panel.add(labels[1][0]);
        row2Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row2Panel.add(labels[1][1]);
        row2Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row2Panel.add(labels[1][2]);
        row2Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row2Panel.add(labels[1][3]);
        row2Panel.add(Box.createRigidArea(new Dimension(15,68)));
        if(!isShow)
            row2Panel.add(row2);
        else
            row2Panel.add(Box.createRigidArea(new Dimension(50,68)));

        row3Panel.add(Box.createRigidArea(new Dimension(98,68)));
        row3Panel.add(labels[2][0]);
        row3Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row3Panel.add(labels[2][1]);
        row3Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row3Panel.add(labels[2][2]);
        row3Panel.add(Box.createRigidArea(new Dimension(18,68)));
        row3Panel.add(labels[2][3]);
        row3Panel.add(Box.createRigidArea(new Dimension(15,68)));
        if(!isShow)
            row3Panel.add(row3);
        else
            row3Panel.add(Box.createRigidArea(new Dimension(50,68)));

        marketTrayPanel.add(row1Panel);
        marketTrayPanel.add(row2Panel);
        marketTrayPanel.add(row3Panel);
    }

    /**
     * Method that sets the choice of the column of the market tray
     * @param columnChoicePanel is the column choice panel
     */
    public void setcolumnChoicePanel(JPanel columnChoicePanel){
        column1 = new JButton("C1");
        column2 = new JButton("C2");
        column3 = new JButton("C3");
        column4 = new JButton("C4");

        column1.addActionListener(this);
        column1.setBackground(Color.LIGHT_GRAY);
        column2.addActionListener(this);
        column2.setBackground(Color.LIGHT_GRAY);
        column3.addActionListener(this);
        column3.setBackground(Color.LIGHT_GRAY);
        column4.addActionListener(this);
        column4.setBackground(Color.LIGHT_GRAY);

        columnChoicePanel.add(Box.createRigidArea(new Dimension(25,71)));
        columnChoicePanel.add(column1);
        columnChoicePanel.add(Box.createRigidArea(new Dimension(25,71)));
        columnChoicePanel.add(column2);
        columnChoicePanel.add(Box.createRigidArea(new Dimension(20,71)));
        columnChoicePanel.add(column3);
        columnChoicePanel.add(Box.createRigidArea(new Dimension(20,71)));
        columnChoicePanel.add(column4);
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == row1){
            line="row";
            numline=1;
            disableAllButtons();
        }
        if(e.getSource() == row2){
            line="row";
            numline=2;
            disableAllButtons();
        }
        if(e.getSource() == row3){
            line = "row";
            numline = 3;
            disableAllButtons();
        }
        if(e.getSource() == column1){
            line="column";
            numline=1;
            disableAllButtons();
        }
        if(e.getSource() == column2){
            line= "column";
            numline= 2;
            disableAllButtons();
        }
        if(e.getSource() == column3){
            line="column";
            numline=3;
            disableAllButtons();
        }
        if(e.getSource() == column4){
            line="column";
            numline=4;
            disableAllButtons();
        }
    }


    /**
     * Method that disables all the buttons of the panel
     */
    public void disableAllButtons(){
        row1.setEnabled(false);
        row2.setEnabled(false);
        row3.setEnabled(false);
        column1.setEnabled(false);
        column2.setEnabled(false);
        column3.setEnabled(false);
        column4.setEnabled(false);
    }

    /**
     * Method that enables all the buttons
     */
    public void activateAllButtons(){
        row1.setEnabled(true);
        row2.setEnabled(true);
        row3.setEnabled(true);
        column1.setEnabled(true);
        column2.setEnabled(true);
        column3.setEnabled(true);
        column4.setEnabled(true);
    }

    /**
     * Class' getter
     * @return the line chosen
     */
    public String getLine() {
        return line;
    }

    /**
     * Class' getter
     * @return the number of the line chosen
     */
    public int getNumline() {
        return numline;
    }
}

