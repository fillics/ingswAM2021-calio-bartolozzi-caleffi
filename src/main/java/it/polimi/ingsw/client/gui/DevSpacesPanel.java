package it.polimi.ingsw.client.gui;

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
    GridBagConstraints c;
    JButton devSpace1;
    JButton devspace2;
    JButton devSpace3;
    ClientModelView clientModelView;

    public void paint(Graphics g){
        myDrawImage("images/board/devSpaces.jpg", g);
    }
    public void myDrawImage(String image, Graphics g){
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream url = cl.getResourceAsStream(image);
        BufferedImage img;
        try{
            assert url != null;
            img = ImageIO.read(url);
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        int width = 720;
        int height = 480;
        g.drawImage(img, 0, -1,width,height, null);
    }

    public DevSpacesPanel(ClientModelView clientModelView) {
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        devSpace1 = new JButton("1");
        devspace2 = new JButton("2");
        devSpace3 = new JButton("3");
        setButtons();
        this.clientModelView = clientModelView;
    }

    public void setButtons(){
        devSpace1.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        this.add(devSpace1,c);
        devspace2.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        this.add(devspace2,c);
        devSpace3.addActionListener(this);
        c.gridx = 2;
        c.gridy = 0;
        this.add(devSpace3,c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
