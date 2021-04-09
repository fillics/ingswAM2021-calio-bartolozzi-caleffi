package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Player;

/**
 * Represents a white marble that can be taken from the market tray
 */

public class WhiteMarble extends Marble{
    /**
     * Override method transform is used to do nothing when the marble is white,
     * unless a marble ability of leader cards is activated.
     */
    @Override
    public void transform(Player player){
        System.out.println("I'm nothing");
    }
}
