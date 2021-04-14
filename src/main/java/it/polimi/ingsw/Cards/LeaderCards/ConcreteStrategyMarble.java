package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * This class represents the white marble strategy for Leader Cards.
 */

public class ConcreteStrategyMarble implements LeaderCardStrategy{
    private ResourceType resourceType;
    private boolean active;


    /**
     * Constructor ConcreteStrategyMarble creates a new ConcreteStrategyMarble instance.
     */
    public ConcreteStrategyMarble(ResourceType resourceType) {
        this.resourceType = resourceType;
        active = false;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void ability() {
        if(!active){
            active = true;
            System.out.println("Ability white marble activated!");
        }
        else{
            System.out.println("Ability white marble was already activated.");
        }
    }
}
