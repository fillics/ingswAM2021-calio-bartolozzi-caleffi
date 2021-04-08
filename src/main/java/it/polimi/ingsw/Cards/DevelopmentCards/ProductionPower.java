package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import java.util.HashMap;

/**
 * This class represents the Production Power of a Development Card and of the Board.
 */
public class ProductionPower {
    private final HashMap<ResourceType,Integer> resourcesNeeded;
    private static HashMap<ResourceType,Integer> resourcesObtained;

    /**
     * Constructor ProductionPower creates a new ProductionPower instance.
     */
    public ProductionPower(HashMap<ResourceType, Integer> resourcesNeeded, HashMap<ResourceType, Integer> resourceObtained) {
        this.resourcesNeeded = resourcesNeeded;
        ProductionPower.resourcesObtained = resourceObtained;
    }

    /**
     * Method getResourcesNeeded returns the resources needed to activate the production power.
     */
    public HashMap<ResourceType, Integer> getResourcesNeeded() {
        return resourcesNeeded;
    }

    /**
     * Method getResourceObtained returns the resources obtained from the activation of the production power.
     */
    public HashMap<ResourceType, Integer> getResourcesObtained() {
        return resourcesObtained;
    }
}
