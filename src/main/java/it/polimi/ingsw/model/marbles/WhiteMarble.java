package it.polimi.ingsw.model.marbles;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.Player;

/**
 * Represents a white marble that can be taken from the market tray
 */

public class WhiteMarble extends Marble{

    /**
     * Override method transform is used to do nothing when the marble is white,
     * unless a marble ability of leader cards is activated.
     */
    @Override
    public void transform(Player player) {
        if (!player.getWhiteMarbleCardChoice().isEmpty()) {
            Resource newResource = new Resource(player.getWhiteMarbleCardChoice().get(0).getStrategy().getResourceType());
            player.getResourceBuffer().add(newResource);
            player.getWhiteMarbleCardChoice().remove(0);
        }
    }
}

