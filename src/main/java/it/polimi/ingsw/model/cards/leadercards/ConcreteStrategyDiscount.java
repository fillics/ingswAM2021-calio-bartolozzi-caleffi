package it.polimi.ingsw.model.cards.leadercards;

import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.board.resources.ResourceType;

/**
 * This class represents the discount strategy for Leader Cards.
 */

public class ConcreteStrategyDiscount implements LeaderCardStrategy{

    private final ResourceType resourceType;
    private boolean active;

    /**
     * Constructor ConcreteStrategyDiscount creates a new ConcreteStrategyDiscount instance.
     * @param resourceType is the type of the strategy: COIN, STONE, SERVANT, SHIELD
     */
    public ConcreteStrategyDiscount(ResourceType resourceType) {
        this.resourceType = resourceType;
        active=false;
    }

    public ConcreteStrategyDiscount(){
        this.resourceType = null;
        active=false;
    }

    /**
     * Method ability() makes the attribute "active" true.
     */
    @Override
    public void ability() {
        if(!active)
            active = true;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString(){
        String escape = "???? -1";
        assert resourceType != null;
        if(resourceType.equals(ResourceType.COIN))
            escape= escape + Color.ANSI_YELLOW.escape() + Printable.SQUARE.print()+ Color.RESET;
        if(resourceType.equals(ResourceType.SERVANT))
            escape = escape + Color.ANSI_PURPLE.escape() + Printable.SQUARE.print()+ Color.RESET;
        if(resourceType.equals(ResourceType.SHIELD))
            escape = escape + Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourceType.equals(ResourceType.STONE))
            escape = escape + Color.ANSI_GREY.escape() + Printable.SQUARE.print() + Color.RESET;
        return escape;
    }
}
