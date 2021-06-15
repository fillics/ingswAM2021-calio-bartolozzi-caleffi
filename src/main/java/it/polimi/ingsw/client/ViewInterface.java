package it.polimi.ingsw.client;

import java.io.IOException;

/**
 * ViewInterface defines an interface for CLI
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
