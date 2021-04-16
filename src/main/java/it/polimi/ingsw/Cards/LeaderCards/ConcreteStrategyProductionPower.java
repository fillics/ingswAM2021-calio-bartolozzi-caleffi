package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;
import java.util.HashMap;

/**
 * This class represents the production power strategy for Leader Cards.
 */

public class ConcreteStrategyProductionPower implements LeaderCardStrategy{
    private HashMap<ResourceType,Integer> resourceNeeded;
    private HashMap<ResourceType,Integer> resourcesObtained;
    private ProductionPower extraProductionPower;
    private Board board;
    private boolean active;
    private final ResourceType resourceType;

    /**
     * Constructor ConcreteStrategyProductionPower creates a new ConcreteStrategyProductionPower instance.
     */
    public ConcreteStrategyProductionPower(HashMap<ResourceType, Integer> resourceNeeded, Board board, ResourceType resourceType) {
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
            System.out.println("Ability extra production power activated!");
        }
        else{
            System.out.println("Ability extra production power was already activated.");
        }
    }

}
