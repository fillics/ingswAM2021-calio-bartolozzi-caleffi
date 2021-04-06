package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * This class represents the white marble strategy for Leader Cards.
 */

public class ConcreteStrategyMarble extends LeaderCardStrategy{
    private ResourceType resourceType;

    /**
     * Constructor ConcreteStrategyMarble creates a new ConcreteStrategyMarble instance.
     */
    public ConcreteStrategyMarble(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public boolean ability(int numofMarbles) {
        int i;
        for(i=0;i<numofMarbles;i++){
            Resource r= new Resource(resourceType);
            Player.getResourceBuffer().add(r);
        }
        System.out.println("I'm a white marble leader card");
        return true;
    }
}
