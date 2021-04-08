package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * Represents a grey marble that can be taken from the market tray
 */

public class GreyMarble extends Marble{
    private Player player;

    public GreyMarble(Player player) {
        this.player = player;
    }

    /**
     * Override method transform is used to transform a grey marble into a stone and to fill resourceBuffer of Player with it
     */
    @Override
    public boolean transform(){
        Resource stone= new Resource(ResourceType.STONE);
        player.getResourceBuffer().add(stone);
        System.out.println("I've been transformed into Stone");
        return true;
    }
}
