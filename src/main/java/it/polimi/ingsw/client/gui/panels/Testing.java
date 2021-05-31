package it.polimi.ingsw.client.gui.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

class Testing
{
    public void buildGUI()
    {
        BackgroundPanel bp = new BackgroundPanel();
        bp.setLayout(new GridBagLayout());
        bp.add(new JButton("Can You See Me?"),new GridBagConstraints());
        JFrame f = new JFrame("JFrame");
        f.add(bp);
        f.setSize(400,300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Testing().buildGUI();
            }
        });
    }
}
class BackgroundPanel extends JPanel
{
    Image image;
    public BackgroundPanel()
    {
        try
        {
            image = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("/images/board/deposits.jpg"), "deposits.jpg"));
        }
        catch (Exception e) { /*handled in paintComponent()*/ }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null)
            g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
    }
}