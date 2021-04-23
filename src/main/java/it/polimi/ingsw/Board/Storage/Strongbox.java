package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Exceptions.EmptyDeposit;

import java.util.HashMap;

/**
 * Class Strongbox represents the strongbox of the Board
 */
public class Strongbox extends Warehouse{
    private HashMap<ResourceType, Integer> strongbox;

    
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
     * 
     * @param resourceType
     * @throws EmptyDeposit
     */
    // TODO: 23/04/2021 javadoc 
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
