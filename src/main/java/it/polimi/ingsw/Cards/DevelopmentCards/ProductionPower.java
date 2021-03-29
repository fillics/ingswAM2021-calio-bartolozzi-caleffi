package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import java.util.HashMap;

public class ProductionPower {
    private final HashMap<ResourceType,Integer> resourcesNeeded;
    private final HashMap<ResourceType,Integer> resourceObtained;

    public HashMap<ResourceType, Integer> getResourcesNeeded() {
        return resourcesNeeded;
    }

    public HashMap<ResourceType, Integer> getResourceObtained() {
        return resourceObtained;
    }

    public ProductionPower(HashMap<ResourceType, Integer> resourcesNeeded, HashMap<ResourceType, Integer> resourceObtained) {
        this.resourcesNeeded = resourcesNeeded;
        this.resourceObtained = resourceObtained;
    }
}
