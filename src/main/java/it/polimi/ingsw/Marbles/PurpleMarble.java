package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * Represents a purple marble that can be taken from the market tray
 */

public class PurpleMarble extends Marble{
    /**
     * Override method transform is used to transform a purple marble into a servant and to fill resourceBuffer of Player with it
     */
    @Override
    public boolean transform(){
        Resource servant= new Resource(ResourceType.SERVANT);
        Player.getResourceBuffer().add(servant);
        System.out.println("I've been transformed into Servant");
        return true;
    }
}