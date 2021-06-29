package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Class that creates the panel that contains the faith track
 */
public class FaithTrackPanel extends JPanel{
    private Image imagereal;
    int faithMarker,blackCross;
    boolean isSingleGame;
    private GUI gui;
    private ClientModelView clientModelView;

    /**
     * Class' constructor used in the composition of the other player board panel
     * @param clientModelView is the client model view in which are contained the information
     */
    public FaithTrackPanel(ClientModelView clientModelView){
        this.clientModelView = clientModelView;
        isSingleGame = false;
        faithMarker = clientModelView.getLiteBoard().getFaithMarker();
        blackCross = clientModelView.getLiteBoard().getBlackCross();

        this.setPreferredSize(new Dimension(970,200));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        InputStream input= getClass().getResourceAsStream("/images/board/faithTrack.jpg");
        try {
            assert input != null;
            imagereal= ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel first= new JPanel();
        JPanel second= new JPanel();
        JPanel third= new JPanel();
        JPanel fourth= new JPanel();
        JPanel fifth= new JPanel();
        JPanel sixth= new JPanel();
        JPanel seventh= new JPanel();

        first.setLayout(new BoxLayout(first,BoxLayout.Y_AXIS));
        first.setPreferredSize(new Dimension(226,200));
        setFirstPanel(first, clientModelView);

        second.setLayout(new BoxLayout(second,BoxLayout.Y_AXIS));
        second.setPreferredSize(new Dimension(97,200));
        setSecondPanel(second, clientModelView);

        third.setLayout(new BoxLayout(third,BoxLayout.Y_AXIS));
        third.setPreferredSize(new Dimension(146,200));
        setThirdPanel(third);

        fourth.setLayout(new BoxLayout(fourth,BoxLayout.Y_AXIS));
        fourth.setPreferredSize(new Dimension(97,200));
        setFourthPanel(fourth, clientModelView);

        fifth.setLayout(new BoxLayout(fifth,BoxLayout.Y_AXIS));
        fifth.setPreferredSize(new Dimension(190,200));
        setFifthPanel(fifth);

        sixth.setLayout(new BoxLayout(sixth,BoxLayout.Y_AXIS));
        sixth.setPreferredSize(new Dimension(96,200));
        setSixthPanel(sixth, clientModelView);

        seventh.setLayout(new BoxLayout(seventh,BoxLayout.Y_AXIS));
        seventh.setPreferredSize(new Dimension(118,200));
        setSeventhPanel(seventh);

        first.setOpaque(false);
        second.setOpaque(false);
        third.setOpaque(false);
        fourth.setOpaque(false);
        fifth.setOpaque(false);
        sixth.setOpaque(false);
        seventh.setOpaque(false);

        this.add(first);
        this.add(second);
        this.add(third);
        this.add(fourth);
        this.add(fifth);
        this.add(sixth);
        this.add(seventh);
    }

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     */
    public FaithTrackPanel(GUI gui){
        this.gui=gui;
        isSingleGame = gui.getClient().getClientModelView().isSingleGame();
        faithMarker = gui.getClient().getClientModelView().getLiteBoard().getFaithMarker();
        blackCross = gui.getClient().getClientModelView().getLiteBoard().getBlackCross();
        this.setPreferredSize(new Dimension(970,200));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        InputStream input= getClass().getResourceAsStream("/images/board/faithTrack.jpg");
        try {
            assert input != null;
            imagereal= ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel first= new JPanel();
        JPanel second= new JPanel();
        JPanel third= new JPanel();
        JPanel fourth= new JPanel();
        JPanel fifth= new JPanel();
        JPanel sixth= new JPanel();
        JPanel seventh= new JPanel();

        first.setLayout(new BoxLayout(first,BoxLayout.Y_AXIS));
        first.setPreferredSize(new Dimension(226,200));
        setFirstPanel(first, gui.getClient().getClientModelView());

        second.setLayout(new BoxLayout(second,BoxLayout.Y_AXIS));
        second.setPreferredSize(new Dimension(97,200));
        setSecondPanel(second, gui.getClient().getClientModelView());

        third.setLayout(new BoxLayout(third,BoxLayout.Y_AXIS));
        third.setPreferredSize(new Dimension(146,200));
        setThirdPanel(third);

        fourth.setLayout(new BoxLayout(fourth,BoxLayout.Y_AXIS));
        fourth.setPreferredSize(new Dimension(97,200));
        setFourthPanel(fourth, gui.getClient().getClientModelView());

        fifth.setLayout(new BoxLayout(fifth,BoxLayout.Y_AXIS));
        fifth.setPreferredSize(new Dimension(190,200));
        setFifthPanel(fifth);

        sixth.setLayout(new BoxLayout(sixth,BoxLayout.Y_AXIS));
        sixth.setPreferredSize(new Dimension(96,200));
        setSixthPanel(sixth, gui.getClient().getClientModelView());

        seventh.setLayout(new BoxLayout(seventh,BoxLayout.Y_AXIS));
        seventh.setPreferredSize(new Dimension(118,200));
        setSeventhPanel(seventh);

        first.setOpaque(false);
        second.setOpaque(false);
        third.setOpaque(false);
        fourth.setOpaque(false);
        fifth.setOpaque(false);
        sixth.setOpaque(false);
        seventh.setOpaque(false);

        this.add(first);
        this.add(second);
        this.add(third);
        this.add(fourth);
        this.add(fifth);
        this.add(sixth);
        this.add(seventh);
    }

    /**
     * Method used to set the panel background.
     * @param g is a Graphics object
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imagereal, 0 ,0, 970,200,null);
    }

    /**
     * Method that sets the first panel of the faith track panel
     * @param first is the panel set
     * @param clientModelView is the client model view in which are contained the information
     */
    public void setFirstPanel(JPanel first, ClientModelView clientModelView){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label11 = new JLabel();
        JLabel label12 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        panel1.setPreferredSize(new Dimension(226,48));
        panel2.setPreferredSize(new Dimension(226,48));
        panel3.setPreferredSize(new Dimension(226,48));

        try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label11.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label12.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel1.add(Box.createRigidArea(new Dimension(129,48)));
        if(faithMarker==4){
            if(blackCross==4){
                panel1.add(label12);
                panel1.add(Box.createRigidArea(new Dimension(52,48)));
            } else if(blackCross==5){
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label11);
                panel1.add(Box.createRigidArea(new Dimension(4,48)));
            }
            else{
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(52,48)));
            }
        } else if(faithMarker==5){
            if(blackCross==4){
                panel1.add(label11);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(4,48)));
            } else if(blackCross==5){
                panel1.add(Box.createRigidArea(new Dimension(48,48)));
                panel1.add(label12);
                panel1.add(Box.createRigidArea(new Dimension(4,48)));
            }
            else{
                panel1.add(Box.createRigidArea(new Dimension(48,48)));
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(4,48)));
            }
        } else if(blackCross==4){
            panel1.add(label11);
            panel1.add(Box.createRigidArea(new Dimension(52,48)));
        } else if(blackCross==5){
            panel1.add(Box.createRigidArea(new Dimension(48,48)));
            panel1.add(label11);
            panel1.add(Box.createRigidArea(new Dimension(4,48)));
        } else
            panel1.add(Box.createRigidArea(new Dimension(97,48)));

        panel2.add(Box.createRigidArea(new Dimension(128,48)));
        if(clientModelView.getLiteBoard().getFaithMarker()==3){
            try {
                if(clientModelView.getLiteBoard().getBlackCross()==3){
                    label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
                }
                else
                    label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            panel2.add(label2);
            panel2.add(Box.createRigidArea(new Dimension(53,48)));
        }
        else if(clientModelView.getLiteBoard().getFaithMarker()!=3 && clientModelView.getLiteBoard().getBlackCross()==3) {
            try {
                label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            panel2.add(label2);
            panel2.add(Box.createRigidArea(new Dimension(51,48)));
        }
        else
            panel2.add(Box.createRigidArea(new Dimension(98,48)));

        try {
            label3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label4.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label5.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel3.add(Box.createRigidArea(new Dimension(32,48)));
        if(faithMarker == 0){
            if(blackCross==0 && isSingleGame){
                panel3.add(label5);
                panel3.add(Box.createRigidArea(new Dimension(149,48)));
            } else if(blackCross==1){
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            } else if(blackCross==2){
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(56,48)));
            }
            else {
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(149,48)));
            }
        }
        else if(faithMarker ==1){
            if(blackCross==0 && isSingleGame){
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            } else if(blackCross==1){
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label5);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            } else if(blackCross==2){
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            }
            else{
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            }
        }
        else if(faithMarker == 2){
            if(blackCross==0 && isSingleGame){
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(56,48)));
            } else if(blackCross==1){
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            } else if(blackCross==2){
                panel3.add(Box.createRigidArea(new Dimension(96,48)));
                panel3.add(label5);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            } else{
                panel3.add(Box.createRigidArea(new Dimension(96,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            }
        } else if(blackCross == 0 && isSingleGame){
            panel3.add(label4);
            panel3.add(Box.createRigidArea(new Dimension(149,48)));
        } else if( blackCross ==1){
            panel3.add(Box.createRigidArea(new Dimension(48,48)));
            panel3.add(label4);
            panel3.add(Box.createRigidArea(new Dimension(101,48)));
        } else if( blackCross ==2 ){
            panel3.add(Box.createRigidArea(new Dimension(96,48)));
            panel3.add(label4);
            panel3.add(Box.createRigidArea(new Dimension(53,48)));
        } else
            panel3.add(Box.createRigidArea(new Dimension(194,48)));


        first.add(Box.createRigidArea(new Dimension(226,33)));
        first.add(panel1);
        first.add(panel2);
        first.add(panel3);
        first.add(Box.createRigidArea(new Dimension(226,23)));
    }

    //TODO: sistemare problema tile girato
    /**
     * Method that sets the second panel of the faith track panel
     * @param second is the panel set
     * @param clientModelView is the client model view in which are contained the information
     */
    public void setSecondPanel(JPanel second, ClientModelView clientModelView){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label11 = new JLabel();
        JLabel label12 = new JLabel();
        JLabel label2 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel1.setPreferredSize(new Dimension(97,48));
        panel2.setPreferredSize(new Dimension(97,119));

        try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label11.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label12.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(faithMarker==6){
            if(blackCross==6){
                panel1.add(label12);
                panel1.add(Box.createRigidArea(new Dimension(52,48)));
            } else if(blackCross==7){
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label11);
                panel1.add(Box.createRigidArea(new Dimension(4,48)));
            } else{
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(52,48)));
            }
        } else if(faithMarker==7){
            if(blackCross==6){
                panel1.add(label11);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(4,48)));
            } else if(blackCross==7){
                panel1.add(Box.createRigidArea(new Dimension(52,48)));
                panel1.add(label12);
            } else{
                panel1.add(Box.createRigidArea(new Dimension(52,48)));
                panel1.add(label1);
            }
        } else if(blackCross ==6 ){
            panel1.add(label11);
            panel1.add(Box.createRigidArea(new Dimension(52,48)));
        } else if(blackCross==7){
            panel1.add(Box.createRigidArea(new Dimension(52,48)));
            panel1.add(label11);
        } else
            panel1.add(Box.createRigidArea(new Dimension(97,48)));


        panel2.add(Box.createRigidArea(new Dimension(97,15)));
        try {
            for(int i=4; i<8;i++){ 
                if(clientModelView.getLiteBoard().getVaticanReportSections().get((clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection())-1).getPopefavortile().isVisible())
                    label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoGiallofront.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
                else
                    label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoGiallo.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        label2.setAlignmentX(0.5f);
        panel2.add(label2);
        panel2.add(Box.createRigidArea(new Dimension(97,44)));

        second.add(Box.createRigidArea(new Dimension(97,33)));
        second.add(panel1);
        second.add(panel2);
    }

    /**
     * Method that sets the third panel of the faith track panel
     * @param third is the panel set
     */
    public void setThirdPanel(JPanel third){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label11 = new JLabel();
        JLabel label12 = new JLabel();
        JLabel label2= new JLabel();
        JLabel label3 = new JLabel();
        JLabel label31 = new JLabel();
        JLabel label32 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        panel1.setPreferredSize(new Dimension(146,48));
        panel2.setPreferredSize(new Dimension(146,48));
        panel3.setPreferredSize(new Dimension(146,48));

        try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label11.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label12.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(faithMarker==8){
            if(blackCross==8){
                panel1.add(label12);
                panel1.add(Box.createRigidArea(new Dimension(101,48)));
            }else if(blackCross==9){
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label11);
                panel1.add(Box.createRigidArea(new Dimension(53,48)));
            } else{
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(101,48)));
            }
        } else if(faithMarker==9){
            if(blackCross==8){
                panel1.add(label11);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(53,48)));
            } else if(blackCross==9){
                panel1.add(Box.createRigidArea(new Dimension(48,48)));
                panel1.add(label12);
                panel1.add(Box.createRigidArea(new Dimension(53,48)));
            } else{
                panel1.add(Box.createRigidArea(new Dimension(48,48)));
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(53,48)));
            }
        } else if(blackCross==8){
            panel1.add(label11);
            panel1.add(Box.createRigidArea(new Dimension(101,48)));
        } else if(blackCross==9){
            panel1.add(Box.createRigidArea(new Dimension(48,48)));
            panel1.add(label11);
            panel1.add(Box.createRigidArea(new Dimension(53,48)));
        } else
            panel1.add(Box.createRigidArea(new Dimension(146,48)));

        panel2.add(Box.createRigidArea(new Dimension(49,48)));
        if(faithMarker==10){
            try {
                if(blackCross==10)
                    label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
                else
                    label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            panel2.add(label2);
            panel2.add(Box.createRigidArea(new Dimension(49,48)));
        } else if(blackCross==10){
            try {
                label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            panel2.add(label2);
            panel2.add(Box.createRigidArea(new Dimension(49,48)));
        } else
            panel2.add(Box.createRigidArea(new Dimension(146,48)));

        try {
            label3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label31.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label32.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel3.add(Box.createRigidArea(new Dimension(48,48)));
        if(faithMarker==11){
            if(blackCross==11){
                panel3.add(label32);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            } else if(blackCross==12){
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label31);
                panel3.add(Box.createRigidArea(new Dimension(5,48)));
            } else{
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            }
        } else if(faithMarker==12){
            if(blackCross==11){
                panel3.add(label31);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(5,48)));
            } else if(blackCross==12){
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label32);
                panel3.add(Box.createRigidArea(new Dimension(5,48)));
            } else{
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(5,48)));
            }
        } else if(blackCross==11){
            panel3.add(label31);
            panel3.add(Box.createRigidArea(new Dimension(53,48)));
        } else if(blackCross==12){
            panel3.add(Box.createRigidArea(new Dimension(48,48)));
            panel3.add(label31);
            panel3.add(Box.createRigidArea(new Dimension(5,48)));
        } else
            panel3.add(Box.createRigidArea(new Dimension(98,48)));

        third.add(Box.createRigidArea(new Dimension(146,33)));
        third.add(panel1);
        third.add(panel2);
        third.add(panel3);
        third.add(Box.createRigidArea(new Dimension(146,23)));
    }

    /**
     * Method that sets the fourth panel of the faith track panel
     * @param fourth is the panel set
     * @param clientModelView is the client model view in which are contained the information
     */
    public void setFourthPanel(JPanel fourth, ClientModelView clientModelView){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label21 = new JLabel();
        JLabel label22 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

        panel1.setPreferredSize(new Dimension(97,119));
        panel2.setPreferredSize(new Dimension(97,48));

        try {
            for(int i=11; i<16; i++) {
                if (clientModelView.getLiteBoard().getVaticanReportSections().get((clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()) - 1).getPopefavortile().isVisible())
                    label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoArancionefront.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
                else
                    label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoArancione.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        label1.setAlignmentX(0.55f);
        panel1.add(Box.createRigidArea(new Dimension(97,30)));
        panel1.add(label1);
        panel1.add(Box.createRigidArea(new Dimension(97,21)));

        try {
            label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label21.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label22.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(faithMarker==13){
            if(blackCross==13){
                panel2.add(label22);
                panel2.add(Box.createRigidArea(new Dimension(52,48)));
            } else if(blackCross==14){
                panel2.add(label2);
                panel2.add(Box.createRigidArea(new Dimension(3,48)));
                panel2.add(label21);
                panel2.add(Box.createRigidArea(new Dimension(4,48)));
            } else{
                panel2.add(label2);
                panel2.add(Box.createRigidArea(new Dimension(52,48)));
            }
        } else if(faithMarker==14){
            if(blackCross==14){
                panel2.add(Box.createRigidArea(new Dimension(46,48)));
                panel2.add(label22);
                panel2.add(Box.createRigidArea(new Dimension(6,48)));
            } else if(blackCross==13){
                panel2.add(label21);
                panel2.add(Box.createRigidArea(new Dimension(3,48)));
                panel2.add(label2);
                panel2.add(Box.createRigidArea(new Dimension(4,48)));
            } else {
                panel2.add(Box.createRigidArea(new Dimension(46,48)));
                panel2.add(label2);
                panel2.add(Box.createRigidArea(new Dimension(6,48)));
            }
        } else if(blackCross==13){
            panel2.add(label21);
            panel2.add(Box.createRigidArea(new Dimension(52,48)));
        } else if(blackCross==14){
            panel2.add(Box.createRigidArea(new Dimension(46,48)));
            panel2.add(label21);
            panel2.add(Box.createRigidArea(new Dimension(6,48)));
        } else
            panel2.add(Box.createRigidArea(new Dimension(97,48)));

        fourth.add(Box.createRigidArea(new Dimension(97,16)));
        fourth.add(panel1);
        fourth.add(panel2);
        fourth.add(Box.createRigidArea(new Dimension(97,25)));
    }

    /**
     * Method that sets the fifth panel of the faith track panel
     * @param fifth is the panel set
     */
    public void setFifthPanel(JPanel fifth){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label11 = new JLabel();
        JLabel label12 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        panel1.setPreferredSize(new Dimension(190,48));
        panel2.setPreferredSize(new Dimension(190,48));
        panel3.setPreferredSize(new Dimension(190,48));

        try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label11.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label12.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(faithMarker==15){
            if(blackCross==15){
                panel3.add(label12);
                panel3.add(Box.createRigidArea(new Dimension(145,48)));
            } else if(blackCross==16){
                panel3.add(label1);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label11);
                panel3.add(Box.createRigidArea(new Dimension(97,48)));
            } else{
                panel3.add(label1);
                panel3.add(Box.createRigidArea(new Dimension(145,48)));
            }
        } else if(faithMarker==16){
            if(blackCross==15){
                panel3.add(label11);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label1);
                panel3.add(Box.createRigidArea(new Dimension(97,48)));
            } else if(blackCross==16){
                panel3.add(Box.createRigidArea(new Dimension(44,48)));
                panel3.add(label12);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            } else{
                panel3.add(Box.createRigidArea(new Dimension(44,48)));
                panel3.add(label1);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            }
        } else if(blackCross==15){
            panel3.add(label11);
            panel3.add(Box.createRigidArea(new Dimension(145,48)));
        } else if(blackCross==16){
            panel3.add(Box.createRigidArea(new Dimension(44,48)));
            panel3.add(label11);
            panel3.add(Box.createRigidArea(new Dimension(101,48)));
        } else
            panel3.add(Box.createRigidArea(new Dimension(190,48)));

        if(faithMarker==17){
            panel2.add(Box.createRigidArea(new Dimension(44,48)));
            try {
                if(blackCross==17)
                    label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
                else
                    label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            panel2.add(label2);
            panel2.add(Box.createRigidArea(new Dimension(101,48)));
        } else if(blackCross==17){
            panel2.add(Box.createRigidArea(new Dimension(44,48)));
            try {
                label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            panel2.add(label2);
            panel2.add(Box.createRigidArea(new Dimension(101,48)));
        } else
            panel2.add(Box.createRigidArea(new Dimension(190,48)));

        try {
            label3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label4.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label5.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(faithMarker==18){
            if(blackCross==18){
                panel1.add(Box.createRigidArea(new Dimension(44,48)));
                panel1.add(label5);
                panel1.add(Box.createRigidArea(new Dimension(101,48)));
            }else if(blackCross==19){
                panel1.add(Box.createRigidArea(new Dimension(44,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label4);
                panel1.add(Box.createRigidArea(new Dimension(53,48)));
            }else if (blackCross==20){
                panel1.add(Box.createRigidArea(new Dimension(44,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(48,48)));
                panel1.add(label4);
                panel1.add(Box.createRigidArea(new Dimension(8,48)));
            }else{
                panel1.add(Box.createRigidArea(new Dimension(44,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(101,48)));
            }
        } else if(faithMarker==19){
            if(blackCross==18){
                panel1.add(Box.createRigidArea(new Dimension(44,48)));
                panel1.add(label4);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(53,48)));
            }else if(blackCross==19){
                panel1.add(Box.createRigidArea(new Dimension(89,48)));
                panel1.add(label5);
                panel1.add(Box.createRigidArea(new Dimension(56,48)));
            }else if (blackCross==20){
                panel1.add(Box.createRigidArea(new Dimension(89,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label4);
                panel1.add(Box.createRigidArea(new Dimension(8,48)));
            }else{
                panel1.add(Box.createRigidArea(new Dimension(89,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(56,48)));
            }
        } else if(faithMarker==20){
            if(blackCross==18){
                panel1.add(Box.createRigidArea(new Dimension(44,48)));
                panel1.add(label4);
                panel1.add(Box.createRigidArea(new Dimension(48,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(8,48)));
            }else if(blackCross==19){
                panel1.add(Box.createRigidArea(new Dimension(89,48)));
                panel1.add(label4);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(8,48)));
            }else if (blackCross==20){
                panel1.add(Box.createRigidArea(new Dimension(137,48)));
                panel1.add(label5);
                panel1.add(Box.createRigidArea(new Dimension(8,48)));
            }else{
                panel1.add(Box.createRigidArea(new Dimension(137,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(8,48)));
            }
        } else if(blackCross==18){
            panel1.add(Box.createRigidArea(new Dimension(44,48)));
            panel1.add(label4);
            panel1.add(Box.createRigidArea(new Dimension(101,48)));
        }else if(blackCross==19){
            panel1.add(Box.createRigidArea(new Dimension(89,48)));
            panel1.add(label4);
            panel1.add(Box.createRigidArea(new Dimension(56,48)));
        }else if(blackCross==20){
            panel1.add(Box.createRigidArea(new Dimension(137,48)));
            panel1.add(label4);
            panel1.add(Box.createRigidArea(new Dimension(8,48)));
        }else
            panel1.add(Box.createRigidArea(new Dimension(190,48)));

        fifth.add(Box.createRigidArea(new Dimension(190,33)));
        fifth.add(panel1);
        fifth.add(panel2);
        fifth.add(panel3);
        fifth.add(Box.createRigidArea(new Dimension(190,23)));
    }

    /**
     * Method that sets the sixth panel of the faith track panel
     * @param sixth is the panel set
     * @param clientModelView is the client model view in which are contained the information
     */
    public void setSixthPanel(JPanel sixth, ClientModelView clientModelView){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel1.setPreferredSize(new Dimension(96,48));
        panel2.setPreferredSize(new Dimension(96,119));

        try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(faithMarker==21){
            if(blackCross==21){
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(51,48)));
            }else if(blackCross==22){
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label2);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
            }else{
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(51,48)));
            }
        } else if(faithMarker==22){
            if(blackCross==21){
                panel1.add(label2);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(3,48)));
            }else if(blackCross==22){
                panel1.add(Box.createRigidArea(new Dimension(42,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(9,48)));
            }else {
                panel1.add(Box.createRigidArea(new Dimension(42,48)));
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(9,48)));
            }
        } else if(blackCross==21){
            panel1.add(label2);
            panel1.add(Box.createRigidArea(new Dimension(51,48)));
        }else if(blackCross==22){
            panel1.add(Box.createRigidArea(new Dimension(42,48)));
            panel1.add(label2);
            panel1.add(Box.createRigidArea(new Dimension(9,48)));
        }else
            panel1.add(Box.createRigidArea(new Dimension(96,48)));


        panel2.add(Box.createRigidArea(new Dimension(96,15)));
        try {
            for(int i=18; i<24;i++) {
                if (clientModelView.getLiteBoard().getVaticanReportSections().get((clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()) - 1).getPopefavortile().isVisible())
                    label4.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoRossofront.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
                else
                    label4.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoRosso.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        label4.setAlignmentX(0.6f);
        panel2.add(label4);
        panel2.add(Box.createRigidArea(new Dimension(96,44)));

        sixth.add(Box.createRigidArea(new Dimension(96,33)));
        sixth.add(panel1);
        sixth.add(panel2);
    }

    /**
     * Method that sets the seventh panel of the faith track panel
     * @param seventh is the panel set
     */
    public void setSeventhPanel(JPanel seventh){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();

        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        panel1.setPreferredSize(new Dimension(118,48));
        panel2.setPreferredSize(new Dimension(118,48));
        panel3.setPreferredSize(new Dimension(118,48));

        panel2.add(Box.createRigidArea(new Dimension(118,48)));
        panel3.add(Box.createRigidArea(new Dimension(118,48)));

        try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithMarker.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
            label3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/faithAndBlackCross.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(faithMarker==23){
            if(blackCross==23){
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(73,48)));
            }else if(blackCross==24){
                panel1.add(label1);
                panel1.add(label2);
                panel1.add(Box.createRigidArea(new Dimension(28,48)));
            }else{
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(73,48)));
            }
        } else if(faithMarker==24){
            if(blackCross==23){
                panel1.add(label2);
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(28,48)));
            }else if(blackCross==24){
                panel1.add(Box.createRigidArea(new Dimension(45,48)));
                panel1.add(label3);
                panel1.add(Box.createRigidArea(new Dimension(28,48)));
            }else {
                panel1.add(Box.createRigidArea(new Dimension(45,48)));
                panel1.add(label1);
                panel1.add(Box.createRigidArea(new Dimension(28,48)));
            }
        } else if(blackCross==23){
            panel1.add(label2);
            panel1.add(Box.createRigidArea(new Dimension(73,48)));
        }else if(blackCross==24){
            panel1.add(Box.createRigidArea(new Dimension(45,48)));
            panel1.add(label2);
            panel1.add(Box.createRigidArea(new Dimension(28,48)));
        }else
            panel1.add(Box.createRigidArea(new Dimension(118,48)));

        seventh.add(Box.createRigidArea(new Dimension(118,33)));
        seventh.add(panel1);
        seventh.add(panel2);
        seventh.add(panel3);
        seventh.add(Box.createRigidArea(new Dimension(118,23)));
    }
}
