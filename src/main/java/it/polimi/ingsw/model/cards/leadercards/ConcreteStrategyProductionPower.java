package it.polimi.ingsw.model.cards.leadercards;

import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.board.BoardInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.HashMap;

/**
 * This class represents the production power strategy for Leader Cards.
 */

public class ConcreteStrategyProductionPower implements LeaderCardStrategy{
    private final HashMap<ResourceType,Integer> resourcesObtained;
    private ProductionPower extraProductionPower;
    private final BoardInterface board;
    private boolean active;
    private final ResourceType resourceType;

    /**
     * Constructor ConcreteStrategyProductionPower creates a new ConcreteStrategyProductionPower instance.
     */
    public ConcreteStrategyProductionPower(BoardInterface board, ResourceType resourceType) {
        this.board= board;
        resourcesObtained = new HashMap<>();
        resourcesObtained.put(ResourceType.FAITHMARKER,1);
        resourcesObtained.put(ResourceType.JOLLY,1);
        active=false;
        this.resourceType = resourceType;
    }

    public ConcreteStrategyProductionPower() {
        this.resourcesObtained = null;
        this.extraProductionPower = null;
        this.board = null;
        this.active = false;
        this.resourceType = null;
    }

    public boolean isActive() {
        return active;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public void ability(){
        if(!active){
            HashMap<ResourceType, Integer> resourceNeeded = new HashMap<>();
            resourceNeeded.put(resourceType, 1);
            extraProductionPower= new ProductionPower(resourceNeeded,resourcesObtained);
            board.getSpecialProductionPowers().add(extraProductionPower);
            active = true;
        }
    }

    @Override
    public String toString(){
        String escape= "";
        if(resourceType.equals(ResourceType.COIN))
            escape= escape + "1"+ Color.ANSI_YELLOW.escape() + Printable.SQUARE.print() + Color.RESET + "}1? 1" + Color.ANSI_RED.escape() + Printable.CROSS.print() + Color.RESET;
        if(resourceType.equals(ResourceType.SERVANT))
            escape = escape + "1" + Color.ANSI_PURPLE.escape() + Printable.SQUARE.print() + Color.RESET + "}1? 1" + Color.ANSI_RED.escape() + Printable.CROSS.print() + Color.RESET;
        if(resourceType.equals(ResourceType.SHIELD))
            escape = escape + "1" + Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + Color.RESET + "}1? 1" + Color.ANSI_RED.escape() + Printable.CROSS.print() + Color.RESET;
        if(resourceType.equals(ResourceType.STONE))
            escape = escape + "1" + Color.ANSI_GREY.escape() + Printable.SQUARE.print() + Color.RESET + "}1? 1" + Color.ANSI_RED.escape() + Printable.CROSS.print() + Color.RESET;
        return escape;
    }

}
