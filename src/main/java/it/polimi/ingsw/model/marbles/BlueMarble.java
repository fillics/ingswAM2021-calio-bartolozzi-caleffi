package it.polimi.ingsw.model.marbles;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.Player;

/**
 * Represents a blue marble that can be taken from the market tray
 */

public class BlueMarble extends Marble{
    /**
     * Override method transform is used to transform a blue marble into a shield and to fill resourceBuffer of Player with it
     */
    @Override
    public void transform(Player player){
        Resource shield= new Resource(ResourceType.SHIELD);
        player.getResourceBuffer().add(shield);
    }
}
