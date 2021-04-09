package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * Represents a red marble that can be taken from the market tray
 */

public class RedMarble extends Marble{
    /**
     * Override method transform is used to transform a red marble into a faith marker and to fill resourceBuffer of Player with it
     */
    @Override
    public void transform(Player player){
        player.getBoard().increaseFaithMarker();
        System.out.println("I've been transformed into Faith Marker");
    }
}
