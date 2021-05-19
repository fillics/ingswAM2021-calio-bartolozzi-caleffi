package it.polimi.ingsw.model.board.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.exceptions.EmptyDeposit;

import java.util.HashMap;

/**
 * Class Strongbox represents the strongbox of the Board
 */
public class Strongbox extends Warehouse{
    private final HashMap<ResourceType, Integer> strongbox;

    @JsonCreator
    public Strongbox(@JsonProperty("strongbox") HashMap<ResourceType, Integer> strongbox) {
        this.strongbox = strongbox;
    }

    /**
     * Constructor Strongbox creates a new Strongbox instance, 
     * used to create the map where all the strongobox resources will be inserted
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
     * Override method getTotalCoins returns the quantity of coins in the deposit
     */
    @Override
    public int getTotalCoins() {
        return strongbox.get(ResourceType.COIN);
    }

    /**
     * Override method getTotalShields returns the quantity of shields in the deposit
     */
    @Override
    public int getTotalShields() {
        return strongbox.get(ResourceType.SHIELD);
    }

    /**
     * Override method getTotalServants returns the quantity of servants in the deposit
     */
    @Override
    public int getTotalServants() {
        return strongbox.get(ResourceType.SERVANT);
    }

    /**
     * Override method getTotalStones returns the quantity of stones in the deposit
     */
    @Override
    public int getTotalStones() {
        return strongbox.get(ResourceType.STONE);
    }

    /**
     * Override method that removes one resource from the strongbox.
     * @param resourceType is the type of resource to remove from the strongbox.
     * @throws EmptyDeposit exception thrown when the deposit is empty and there's nothing to remove.
     */
    @Override
    public void remove(ResourceType resourceType) throws EmptyDeposit {
        if(strongbox.get(resourceType) == 0){
            throw new EmptyDeposit();
        }
        else {
            strongbox.replace(resourceType, strongbox.get(resourceType)-1);
        }
    }
}
