package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * Represents a yellow marble that can be taken from the market tray
 */

public class YellowMarble extends Marble{
    private Player player;

    public YellowMarble(Player player) {
        this.player = player;
    }
    /**
     * Override method transform is used to transform a yellow marble into a coin and to fill resourceBuffer of Player with it
     */
    @Override
    public boolean transform(){
        Resource coin= new Resource(ResourceType.COIN);
        player.getResourceBuffer().add(coin);
        System.out.println("I've been transformed into Coin");
        return true;
    }
}
