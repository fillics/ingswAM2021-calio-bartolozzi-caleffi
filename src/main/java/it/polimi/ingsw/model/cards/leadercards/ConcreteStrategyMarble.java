package it.polimi.ingsw.model.cards.leadercards;

import it.polimi.ingsw.model.board.resources.ResourceType;

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
        if(!active)
            active = true;
    }
}