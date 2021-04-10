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
     * get-method
     * @return tha hashmap that contains all the information about the number and type of resources inside the strongbox
     */
    public HashMap<ResourceType, Integer> getStrongbox() {
        return strongbox;
    }

    /**
     * Override methods created to return the number of resources for each resource
     * @return the number of resources for each resource
     */


    @Override
    public int getTotalCoins() {
        return strongbox.get(ResourceType.COIN);
    }

    @Override
    public int getTotalShields() {
        return strongbox.get(ResourceType.SHIELD);
    }

    @Override
    public int getTotalServants() {
        return strongbox.get(ResourceType.SERVANT);
    }

    @Override
    public int getTotalStones() {
        return strongbox.get(ResourceType.STONE);
    }

    @Override
    public void remove() {

    }
}
