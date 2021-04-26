package it.polimi.ingsw.model.cards.leadercards;

import it.polimi.ingsw.model.board.BoardInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.HashMap;

/**
 * This class represents the production power strategy for Leader Cards.
 */

public class ConcreteStrategyProductionPower implements LeaderCardStrategy{
    private HashMap<ResourceType,Integer> resourceNeeded;
    private HashMap<ResourceType,Integer> resourcesObtained;
    private ProductionPower extraProductionPower;
    private BoardInterface board;
    private boolean active;
    private final ResourceType resourceType;

    /**
     * Constructor ConcreteStrategyProductionPower creates a new ConcreteStrategyProductionPower instance.
     */
    public ConcreteStrategyProductionPower(HashMap<ResourceType, Integer> resourceNeeded, BoardInterface board, ResourceType resourceType) {
        this.resourceNeeded = resourceNeeded;
        this.board= board;
        resourcesObtained= new HashMap<>();
        resourcesObtained.put(ResourceType.FAITHMARKER,1);
        active=false;
        this.resourceType = resourceType;
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
            extraProductionPower= new ProductionPower(resourceNeeded,resourcesObtained);
            board.getSpecialProductionPowers().add(extraProductionPower);
            active = true;
        }
    }

}
