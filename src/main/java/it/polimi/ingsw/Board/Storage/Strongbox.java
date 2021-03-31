package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class Strongbox extends Warehouse{
    private HashMap<ResourceType, Integer> strongbox;

    /**
     * Class's constructor used to create the map where all the strongobx resources will be inserted
     */
    public Strongbox() {
        strongbox = new HashMap<>();
    }

    /**
     * Override methods created to return the number of resources for each resource
     * @return the number of resources for each resource
     */
    @Override
    int getTotalCoins() {
        return strongbox.get(ResourceType.COIN);
    }

    @Override
    int getTotalShields() {
        return strongbox.get(ResourceType.SHIELD);
    }

    @Override
    int getTotalServants() {
        return strongbox.get(ResourceType.SERVANT);
    }

    @Override
    int getTotalStones() {
        return strongbox.get(ResourceType.STONE);
    }
}
