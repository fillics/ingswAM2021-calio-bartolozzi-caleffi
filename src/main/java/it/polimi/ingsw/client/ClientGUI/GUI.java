package it.polimi.ingsw.client.ClientGUI;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ViewInterface;

import javax.swing.*;
import java.awt.*;

public class GUI implements ViewInterface {
    @Override
    public ClientModelView getClientModelView() {
        return null;
    }

    @Override
    public void printLeaderCards() {

    }

    @Override
    public void printDeposits() {

    }

    @Override
    public void printStrongbox() {

    }

    @Override
    public void printDevGrid() {

    }

    @Override
    public void printResourceBuffer() {

    }

    @Override
    public void printMarketTray() {

    }

    @Override
    public void printFaithTrack() {

    }

    @Override
    public void printDevSpaces() {

    }

    @Override
    public void printBaseProdPower() {

    }

    @Override
    public void printResourcesLegend() {

    }

    public static void main(String[] args) {
        JFrame f = new CustomFrame();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                    SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(250,250);
        f.setVisible(true);
    }
}

