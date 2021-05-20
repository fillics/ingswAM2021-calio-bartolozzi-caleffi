package it.polimi.ingsw.client;

/**
 * ViewInterface defines an interface for both CLI and GUI.
 */
public interface ViewInterface {
    ClientModelView getClientModelView();
    void printLeaderCards();
    void printDeposits();
    void printStrongbox();
    void printDevGrid();
    void printResourceBuffer();
    void printMarketTray();
    void printFaithTrack();
    void printDevSpaces();
    void printBaseProdPower();
}
