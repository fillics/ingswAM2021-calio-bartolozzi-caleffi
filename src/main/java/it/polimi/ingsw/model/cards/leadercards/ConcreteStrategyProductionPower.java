package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.JsonCreator;
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

}
