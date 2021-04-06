package it.polimi.ingsw.Marbles;

/**
 * Represents a white marble that can be taken from the market tray
 */

public class WhiteMarble extends Marble{
    /**
     * Override method transform is used to do nothing when the marble is white,
     * unless a marble ability of leader cards is activated.
     */
    @Override
    public boolean transform(){
        System.out.println("I'm nothing");
        return true;
    }
}
