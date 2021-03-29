package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import java.util.HashMap;

public class ProductionPower {
    private HashMap<ResourceType,Integer> resourcesNeeded;
    private HashMap<ResourceType,Integer> resourceObtained;

    public HashMap<ResourceType, Integer> getResourcesNeeded() {
        return resourcesNeeded;
    }

    public HashMap<ResourceType, Integer> getResourceObtained() {
        return resourceObtained;
    }
}
