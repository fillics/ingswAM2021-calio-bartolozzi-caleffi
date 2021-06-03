package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ResourceBufferPanel extends JPanel {
    private GUI gui;
    private ArrayList<JButton> resources;

    public ResourceBufferPanel(GUI gui) {
        resources = new ArrayList<>();
        this.gui = gui;
        this.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(970, 75));
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(970, 75));

        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().size(); i++){
            JButton button = new JButton();
                try {
                    button.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().get(i).getType().path).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    button.setBackground(Color.ORANGE);
                    button.setOpaque(false);
                    button.setBorderPainted(false);
                    resources.add(button);
                    panel.add(button);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        panel.setOpaque(false);
        this.add(panel);

    }

    public static void main(String[] args) throws IOException {
      //  ResourceBufferPanel depositsPanel = new ResourceBufferPanel();
        JFrame frame = new JFrame();

        frame.getContentPane().setBackground(new Color(233, 226, 193)); //change color of background - si può anche mettere il colore in esadecimale

        frame.setTitle("Master of Renaissance");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setSize(dimension.width, dimension.height);

     //   frame.add(depositsPanel);
        frame.pack();
    }

    public ArrayList<JButton> getResources() {
        return resources;
    }
}
