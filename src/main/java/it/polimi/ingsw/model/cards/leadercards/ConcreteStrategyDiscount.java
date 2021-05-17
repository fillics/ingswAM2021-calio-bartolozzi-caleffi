package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.polimi.ingsw.client.Color;
import it.polimi.ingsw.client.Printable;
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
