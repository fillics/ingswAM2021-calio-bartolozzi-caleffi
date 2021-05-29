package it.polimi.ingsw.client;

import java.io.IOException;

/**
 * ViewInterface defines an interface for both CLI and GUI.
 */
public interface ViewInterface {
    ClientModelView getClientModelView();
    void printLeaderCards();
    void printActivatedLeaderCards();
    void printDeposits();
    void printStrongbox();
    void printDevGrid() throws IOException;
    void printResourceBuffer();
    void printMarketTray();
    void printFaithTrack();
    void printDevSpaces();
    void printBaseProdPower();
    void printResourcesLegend();
}
