package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ResourceBufferPanel extends JPanel implements ActionListener {
    private GUI gui;
    private ArrayList<JButton> resources;
    private ArrayList<Integer> positions;

    public ResourceBufferPanel(GUI gui) {
        resources = new ArrayList<>();
        positions = new ArrayList<>();
        this.gui = gui;
        this.setPreferredSize(new Dimension(970, 75));
        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        panel.setPreferredSize(new Dimension(970, 75));

        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().size(); i++){
            JButton button = new JButton();
                try {
                    button.setIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().get(i).getType().path).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    button.setDisabledIcon(new ImageIcon(new ImageIcon(getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().get(i).getType().path).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    button.addActionListener(this);
                    button.setBackground(Color.ORANGE);
                    button.setOpaque(false);
                    button.setBorderPainted(false);
                    resources.add(button);
                    panel.add(button);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        this.add(panel);

    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public ArrayList<JButton> getResources() {
        return resources;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().size(); i ++){
            if(e.getSource() == resources.get(i)){
                positions.add(i);
            }
        }
    }
}
