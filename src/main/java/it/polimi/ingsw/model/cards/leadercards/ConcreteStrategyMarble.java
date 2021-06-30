package it.polimi.ingsw.model.cards.leadercards;

import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
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
     * @param resourceType is the type of the strategy: COIN, STONE, SERVANT, SHIELD
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

    /**
     * Method ability() makes the attribute "active" true.
     */
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
