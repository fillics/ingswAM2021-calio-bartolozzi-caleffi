package it.polimi.ingsw.Marbles;

/**
 * Represents a generic marble that can be taken from the market tray
 */

public abstract class Marble {

    /**
     * Method transform is used to transform every single marble in its corresponding resource
     * AND to fill the resourceBuffer of Player
     */
    public abstract boolean transform();
}
