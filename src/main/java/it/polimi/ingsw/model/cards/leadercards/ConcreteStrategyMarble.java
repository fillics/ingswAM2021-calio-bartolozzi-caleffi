package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Color;
import it.polimi.ingsw.client.Printable;
import it.polimi.ingsw.model.board.resources.ResourceType;

/**
 * This class represents the white marble strategy for Leader Cards.
 */

public class ConcreteStrategyMarble implements LeaderCardStrategy{
    private final ResourceType resourceType;
    private boolean active;


    public ConcreteStrategyMarble() {
        this.resourceType = null;
    }

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

    @Override
    public String toString(){
        String escape= "";
        assert resourceType != null;
        if(resourceType.equals(ResourceType.COIN))
            escape= escape + Printable.WHITE_MARBLE.print()+ " = " +  Color.ANSI_YELLOW.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourceType.equals(ResourceType.SERVANT))
            escape = escape + Printable.WHITE_MARBLE.print()+ " = " + Color.ANSI_PURPLE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourceType.equals(ResourceType.SHIELD))
            escape = escape + Printable.WHITE_MARBLE.print()+ " = " + Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourceType.equals(ResourceType.STONE))
            escape = escape + Printable.WHITE_MARBLE.print()+ " = " + Color.ANSI_GREY.escape() + Printable.SQUARE.print() + Color.RESET;
        escape = escape + "   ";
        return escape;
    }
}
