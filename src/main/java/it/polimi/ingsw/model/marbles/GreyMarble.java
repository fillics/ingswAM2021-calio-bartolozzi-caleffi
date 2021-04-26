package it.polimi.ingsw.model.marbles;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.Player;

/**
 * Represents a grey marble that can be taken from the market tray
 */

public class GreyMarble extends Marble{

    /**
     * Override method transform is used to transform a grey marble into a stone and to fill resourceBuffer of Player with it
     */
    @Override
    public void transform(Player player){
        Resource stone= new Resource(ResourceType.STONE);
        player.getResourceBuffer().add(stone);
    }
}
