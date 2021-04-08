package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * This class represents the white marble strategy for Leader Cards.
 */

public class ConcreteStrategyMarble extends LeaderCardStrategy{
    private ResourceType resourceType;
    private Player player;


    /**
     * Constructor ConcreteStrategyMarble creates a new ConcreteStrategyMarble instance.
     */
    public ConcreteStrategyMarble(ResourceType resourceType, Player player) {
        this.player = player;
        this.resourceType = resourceType;
    }

    @Override
    public boolean ability(int numofMarbles) {
        int i;
        for(i=0;i<numofMarbles;i++){
            Resource r= new Resource(resourceType);
            player.getResourceBuffer().add(r);
        }
        System.out.println("I'm a white marble leader card");
        return true;
    }
}
