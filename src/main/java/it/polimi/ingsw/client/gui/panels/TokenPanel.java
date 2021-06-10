package it.polimi.ingsw.client.gui.panels;

import it.polimi.ingsw.client.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TokenPanel extends JPanel {
    private GUI gui;
    private JLabel label;
    private String path;

    public TokenPanel(GUI gui) {
        this.gui = gui;
        if(gui.getClient().getClientModelView().getSoloActionToken()!=null){
            path = gui.getClient().getClientModelView().getSoloActionToken().getPath();
        }
        else{
            path= "/images/punchboard/retroCerchi.png";
        }
        label= new JLabel();
        this.setPreferredSize(new Dimension(159, 114));

        try {
            label.setIcon(new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResourceAsStream(path)).readAllBytes()).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException ignored){}

        this.add(label);
        this.setOpaque(false);
    }
}
