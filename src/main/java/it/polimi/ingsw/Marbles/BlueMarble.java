package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

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
        System.out.println("I've been transformed into Shield");
    }
}
