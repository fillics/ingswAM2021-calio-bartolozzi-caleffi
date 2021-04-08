package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * Represents a red marble that can be taken from the market tray
 */

public class RedMarble extends Marble{
    private Player player;

    public RedMarble(Player player) {
        this.player = player;
    }
    /**
     * Override method transform is used to transform a red marble into a faith marker and to fill resourceBuffer of Player with it
     */
    @Override
    public boolean transform(){
        Resource faithmarker= new Resource(ResourceType.FAITHMARKER);
        player.getResourceBuffer().add(faithmarker);
        System.out.println("I've been transformed into Faith Marker");
        return true;
    }
}
