package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.polimi.ingsw.model.board.resources.ResourceType;

/**
 * This class represents the discount strategy for Leader Cards.
 */

public class ConcreteStrategyDiscount implements LeaderCardStrategy{
    private final ResourceType resourceType;
    private boolean active;


    /**
     * Constructor ConcreteStrategyDiscount creates a new ConcreteStrategyDiscount instance.
     */
    public ConcreteStrategyDiscount(ResourceType resourceType) {
        this.resourceType = resourceType;
        active=false;
    }

    public ConcreteStrategyDiscount(){
        this.resourceType = null;
        active=false;
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
