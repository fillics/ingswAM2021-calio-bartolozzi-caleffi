package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class FaithTrackPanel extends JPanel{
    private Image imagereal;
    private GUI gui;

    public FaithTrackPanel(GUI gui){
        this.gui=gui;
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
        setFirstPanel(first);

        second.setLayout(new BoxLayout(second,BoxLayout.Y_AXIS));
        second.setPreferredSize(new Dimension(97,200));
        setSecondPanel(second);

        third.setLayout(new BoxLayout(third,BoxLayout.Y_AXIS));
        third.setPreferredSize(new Dimension(146,200));
        setThirdPanel(third);

        fourth.setLayout(new BoxLayout(fourth,BoxLayout.Y_AXIS));
        fourth.setPreferredSize(new Dimension(97,200));
        setFourthPanel(fourth);

        fifth.setLayout(new BoxLayout(fifth,BoxLayout.Y_AXIS));
        fifth.setPreferredSize(new Dimension(194,200));
        setFifthPanel(fifth);

        sixth.setLayout(new BoxLayout(sixth,BoxLayout.Y_AXIS));
        sixth.setPreferredSize(new Dimension(97,200));
        setSixthPanel(sixth);

        seventh.setLayout(new BoxLayout(seventh,BoxLayout.Y_AXIS));
        seventh.setPreferredSize(new Dimension(113,200));
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imagereal, 0 ,0, 970,200,null);
    }

    public void setFirstPanel(JPanel first){
        int numOfPlayers = gui.getClient().getClientModelView().getNumOfPlayers();
        int faithMarker = gui.getClient().getClientModelView().getLiteBoard().getFaithMarker();
        int blackCross = gui.getClient().getClientModelView().getLiteBoard().getBlackCross();
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
            }
            if(blackCross==5){
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
            }
            if(blackCross==5){
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
        }
        else if(blackCross==5){
            panel1.add(Box.createRigidArea(new Dimension(48,48)));
            panel1.add(label11);
            panel1.add(Box.createRigidArea(new Dimension(4,48)));
        } else
            panel1.add(Box.createRigidArea(new Dimension(97,48)));

        panel2.add(Box.createRigidArea(new Dimension(128,48)));
        if(gui.getClient().getClientModelView().getLiteBoard().getFaithMarker()==3){
            try {
                if(gui.getClient().getClientModelView().getLiteBoard().getBlackCross()==3){
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
        else if(gui.getClient().getClientModelView().getLiteBoard().getFaithMarker()!=3 && gui.getClient().getClientModelView().getLiteBoard().getBlackCross()==3) {
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
            if(blackCross==0 && numOfPlayers==1){
                panel3.add(label5);
                panel3.add(Box.createRigidArea(new Dimension(149,48)));
            }
            else if(blackCross==1){
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            }
            else if(blackCross==2){
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
            if(blackCross==0 && numOfPlayers==1){
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            }
            else if(blackCross==1){
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label5);
                panel3.add(Box.createRigidArea(new Dimension(101,48)));
            }
            else if(blackCross==2){
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
            if(blackCross==0 && numOfPlayers==1){
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(56,48)));
            }
            else if(blackCross==1){
                panel3.add(Box.createRigidArea(new Dimension(48,48)));
                panel3.add(label4);
                panel3.add(Box.createRigidArea(new Dimension(3,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            }
            else if(blackCross==2){
                panel3.add(Box.createRigidArea(new Dimension(96,48)));
                panel3.add(label5);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            }
            else{
                panel3.add(Box.createRigidArea(new Dimension(96,48)));
                panel3.add(label3);
                panel3.add(Box.createRigidArea(new Dimension(53,48)));
            }
        } else if(blackCross == 0 && numOfPlayers == 1){
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

    public void setSecondPanel(JPanel second){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel1.setPreferredSize(new Dimension(97,48));
        panel2.setPreferredSize(new Dimension(97,119));

        /*try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel1.add(Box.createRigidArea(new Dimension(49,48)));
        panel1.add(label1);
        panel1.add(Box.createRigidArea(new Dimension(3,48)));
        */
        panel1.add(Box.createRigidArea(new Dimension(97,48)));

        panel2.add(Box.createRigidArea(new Dimension(97,15)));
        try {
            label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoGiallo.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
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

    public void setThirdPanel(JPanel third){
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

        panel1.setPreferredSize(new Dimension(146,48));
        panel2.setPreferredSize(new Dimension(146,48));
        panel3.setPreferredSize(new Dimension(146,48));

        panel1.add(Box.createRigidArea(new Dimension(146,48)));
        panel2.add(Box.createRigidArea(new Dimension(146,48)));
        panel3.add(Box.createRigidArea(new Dimension(146,48)));
        /*try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel1.add(label1);
        panel1.add(Box.createRigidArea(new Dimension(101,48)));


        panel2.add(Box.createRigidArea(new Dimension(49,48)));
        try {
            label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel2.add(label2);
        panel2.add(Box.createRigidArea(new Dimension(49,48)));


        panel3.add(Box.createRigidArea(new Dimension(94,48)));
        try {
            label3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel3.add(label3);
        panel3.add(Box.createRigidArea(new Dimension(4,48)));*/

        third.add(Box.createRigidArea(new Dimension(146,33)));
        third.add(panel1);
        third.add(panel2);
        third.add(panel3);
        third.add(Box.createRigidArea(new Dimension(146,23)));
    }

    public void setFourthPanel(JPanel fourth){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

        panel1.setPreferredSize(new Dimension(97,119));
        panel2.setPreferredSize(new Dimension(97,48));

        try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoArancione.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        label1.setAlignmentX(0.55f);
        panel1.add(Box.createRigidArea(new Dimension(97,30)));
        panel1.add(label1);
        panel1.add(Box.createRigidArea(new Dimension(97,21)));
        /*
        try {
            label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel2.add(Box.createRigidArea(new Dimension(46,48)));
        panel2.add(label2);
        panel2.add(Box.createRigidArea(new Dimension(6,48)));*/
        panel2.add(Box.createRigidArea(new Dimension(97,48)));

        fourth.add(Box.createRigidArea(new Dimension(97,16)));
        fourth.add(panel1);
        fourth.add(panel2);
        fourth.add(Box.createRigidArea(new Dimension(97,25)));
    }

    public void setFifthPanel(JPanel fifth){
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

        panel1.setPreferredSize(new Dimension(194,48));
        panel2.setPreferredSize(new Dimension(194,48));
        panel3.setPreferredSize(new Dimension(194,48));

        panel1.add(Box.createRigidArea(new Dimension(194,48)));
        panel2.add(Box.createRigidArea(new Dimension(194,48)));
        panel3.add(Box.createRigidArea(new Dimension(194,48)));
        /*panel1.add(Box.createRigidArea(new Dimension(92,48)));
        try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel1.add(label1);
        panel1.add(Box.createRigidArea(new Dimension(57,48)));


        panel2.add(Box.createRigidArea(new Dimension(44,48)));
        try {
            label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel2.add(label2);
        panel2.add(Box.createRigidArea(new Dimension(105,48)));


        try {
            label3.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel3.add(label3);
        panel3.add(Box.createRigidArea(new Dimension(149,48)));*/

        fifth.add(Box.createRigidArea(new Dimension(194,33)));
        fifth.add(panel1);
        fifth.add(panel2);
        fifth.add(panel3);
        fifth.add(Box.createRigidArea(new Dimension(194,23)));
    }

    public void setSixthPanel(JPanel sixth){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        panel1.setOpaque(false);
        panel2.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel1.setPreferredSize(new Dimension(97,48));
        panel2.setPreferredSize(new Dimension(97,119));

        /*try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel1.add(Box.createRigidArea(new Dimension(42,48)));
        panel1.add(label1);
        panel1.add(Box.createRigidArea(new Dimension(10,48)));
        */
        panel1.add(Box.createRigidArea(new Dimension(97,48)));

        panel2.add(Box.createRigidArea(new Dimension(97,15)));
        try {
            label2.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/quadratoRosso.png")).readAllBytes()).getImage().getScaledInstance(60, 60, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        label2.setAlignmentX(0.7f);
        panel2.add(label2);
        panel2.add(Box.createRigidArea(new Dimension(97,44)));

        sixth.add(Box.createRigidArea(new Dimension(97,33)));
        sixth.add(panel1);
        sixth.add(panel2);
    }

    public void setSeventhPanel(JPanel seventh){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JLabel label1 = new JLabel();

        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        panel1.setPreferredSize(new Dimension(113,48));
        panel2.setPreferredSize(new Dimension(113,48));
        panel3.setPreferredSize(new Dimension(113,48));

        panel1.add(Box.createRigidArea(new Dimension(113,48)));
        panel2.add(Box.createRigidArea(new Dimension(113,48)));
        panel3.add(Box.createRigidArea(new Dimension(113,48)));
        /*try {
            label1.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream("/images/punchboard/croce.png")).readAllBytes()).getImage().getScaledInstance(45, 45, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel1.add(label1);
        panel1.add(Box.createRigidArea(new Dimension(68,48)));


        panel2.add(Box.createRigidArea(new Dimension(113,48)));

        panel3.add(Box.createRigidArea(new Dimension(113,48)));*/

        seventh.add(Box.createRigidArea(new Dimension(113,33)));
        seventh.add(panel1);
        seventh.add(panel2);
        seventh.add(panel3);
        seventh.add(Box.createRigidArea(new Dimension(113,23)));
    }

    /*public static void main(String[] args) {

        FaithTrackPanel faithTrackPanel = new FaithTrackPanel();
        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale
        frame.add(faithTrackPanel);

        frame.setTitle("Master of Renaissance");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        frame.setSize(970, 240);
    }*/
}
