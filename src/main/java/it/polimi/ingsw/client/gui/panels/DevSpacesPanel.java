package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.ClientModelView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class DevSpacesPanel extends JPanel implements ActionListener {
    private Image devSpaces;
    private JButton devSpace1;
    private JButton devspace2;
    private JButton devSpace3;
    private ClientModelView clientModelView;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(devSpaces, 0,0, 720, 480, null);
    }

    public DevSpacesPanel(ClientModelView clientModelView) {
        InputStream is = getClass().getResourceAsStream("/images/board/devSpaces.jpg");
        try {
            devSpaces = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBounds(0,0,1000,1000);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0,0,1000,1000);
        devSpace1 = new JButton("1");
        devspace2 = new JButton("2");
        devSpace3 = new JButton("3");
        setAllBounds();
        setButtons(buttonPanel);
        this.clientModelView = clientModelView;
        this.add(buttonPanel);
        this.setVisible(true);
        buttonPanel.setVisible(true);
        buttonPanel.setLayout(null);
        this.setLayout(null);
        buttonPanel.setOpaque(false);
        repaint();

    }

    public void setAllBounds(){
        devSpace1.setBounds(180, 430, 100, 30);
        devspace2.setBounds(360, 430, 100, 30);
        devSpace3.setBounds(548,430, 100, 30);
    }

    public void setButtons(JPanel buttonPanel){
        devSpace1.addActionListener(this);
        buttonPanel.add(devSpace1);
        devSpace1.setBackground(Color.ORANGE);
        devspace2.addActionListener(this);
        buttonPanel.add(devspace2);
        devspace2.setBackground(Color.ORANGE);
        devSpace3.addActionListener(this);
        buttonPanel.add(devSpace3);
        devSpace3.setBackground(Color.ORANGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
