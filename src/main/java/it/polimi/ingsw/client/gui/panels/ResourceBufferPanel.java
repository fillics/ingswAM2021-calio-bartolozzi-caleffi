package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that creates the panel used to show the resource buffer of a player
 */
public class ResourceBufferPanel extends JPanel implements ActionListener {
    private final GUI gui;
    private final ArrayList<JButton> resources;
    private final ArrayList<Integer> positions;

    /**
     * Class' constructor
     * @param gui is the GUI object linked to this panel
     */
    public ResourceBufferPanel(GUI gui) {
        resources = new ArrayList<>();
        positions = new ArrayList<>();
        this.gui = gui;
        this.setPreferredSize(new Dimension(970, 75));
        JPanel panel = new JPanel();
        panel.setBackground(new Color(233, 226, 193));
        panel.setPreferredSize(new Dimension(970, 75));

        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().size(); i++){
            JButton button = new JButton();
                try {
                    button.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().get(i).getType().path)).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    button.setDisabledIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().get(i).getType().path)).readAllBytes()).getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
                    button.addActionListener(this);
                    button.setBackground(Color.ORANGE);
                    button.setOpaque(false);
                    button.setBorderPainted(false);
                    resources.add(button);
                    panel.add(button);
                } catch (IOException ignored) {}
        }
        this.add(panel);
        this.setOpaque(false);
    }

    /**
     * Class' getter
     * @return the position of the resource chosen
     */
    public ArrayList<Integer> getPositions() {
        return positions;
    }

    /**
     * Class' getter
     * @return the type of the resource chosen
     */
    public ArrayList<JButton> getResources() {
        return resources;
    }

    /**
     * Method that, based on the button clicked, perform a determined action.
     * @param e is a ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < gui.getClient().getClientModelView().getMyPlayer().getResourceBuffer().size(); i ++){
            if(e.getSource() == resources.get(i)){
                positions.add(i);
            }
        }
    }
}
