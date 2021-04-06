package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;
import java.util.HashMap;

/**
 * This class represents the production power strategy for Leader Cards.
 */

public class ConcreteStrategyProductionPower extends LeaderCardStrategy{
    private HashMap<ResourceType,Integer> resourceNeeded;
    private HashMap<ResourceType,Integer> resourcesObtained;
    private ProductionPower extraProductionPower;

    /**
     * Constructor ConcreteStrategyProductionPower creates a new ConcreteStrategyProductionPower instance.
     */
    public ConcreteStrategyProductionPower(HashMap<ResourceType, Integer> resourceNeeded) {
        this.resourceNeeded = resourceNeeded;
        resourcesObtained= new HashMap<>();
        resourcesObtained.put(ResourceType.FAITHMARKER,1);
    }

    @Override
    public boolean activate() {
        extraProductionPower= new ProductionPower(resourceNeeded,resourcesObtained);
        System.out.println("I'm a production power leader card");
        return true;
    }

    @Override
    public boolean ability(ResourceType chosenResourceObtained){
        extraProductionPower.getResourceObtained().put(chosenResourceObtained,1);
        return true;
    }

    public ProductionPower getExtraProductionPower() {
        return extraProductionPower;
    }
}
