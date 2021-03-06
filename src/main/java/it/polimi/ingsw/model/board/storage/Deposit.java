package it.polimi.ingsw.model.board.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.exceptions.EmptyDeposit;

/**
 * Class Deposit represents the single deposit present in the Board
 */
public class Deposit extends Warehouse {
   private ResourceType resourcetype;
   private int quantity;
   private final int maxLimit;
   private final boolean special;

    /**
     * Constructor Deposit creates a new Deposit instance
     */
    @JsonCreator
    public Deposit(@JsonProperty("resourcetype") ResourceType resourcetype, @JsonProperty("quantity") int quantity, @JsonProperty("maxLimit") int maxLimit,
                   @JsonProperty("special") boolean special){
        this.resourcetype = resourcetype;
        this.quantity = quantity;
        this.maxLimit = maxLimit;
        this.special = special;
    }



    /**
     * Method getResourcetype returns the resource's type of the deposit
     */
    public ResourceType getResourcetype() { return resourcetype; }

    /**
     * Method getQuantity returns the number of resources present in the deposit
     */
    public int getQuantity() { return quantity; }

    /**
     * Method getMaxLimit returns the max limit of resources of the deposit
     */
    public int getMaxLimit() { return maxLimit; }

    /**
     * Method isSpecial returns true if the deposit is special, false if not
     * @return special
     */
    public boolean isSpecial() {
        return special;
    }

    /**
     * Method setResourcetype modifies the resource type that's contained in a single deposit
     * @param resourcetype (type ResourceType) - it is the new type of resource in the deposit
     */
    public void setResourcetype(ResourceType resourcetype) {
        this.resourcetype = resourcetype;
    }

    /**
     * Method increaseNumberOfResources increases the number of resources.
     * @return the new amount of resources
     */
    public int increaseNumberOfResources() {
        quantity +=  1;
        return quantity;
    }

    /**
     * Method takeResource decreases the number of resources because taken by the player
     * @return the resource requested
     * @throws EmptyDeposit if the deposit is empty
     */
    public Resource takeResource() throws EmptyDeposit {
        Resource resource;
        if(quantity == 0){
            throw new EmptyDeposit();
        }
        else {
            resource= new Resource(resourcetype);
            quantity -= 1;
            if(quantity == 0){
                if(!isSpecial()) this.resourcetype = null;
            }
            return resource;
        }
    }

    /**
     * Override method getTotalCoins returns the quantity of coins in the deposit
     */
    @Override
    public int getTotalCoins() {
        if (this.resourcetype.equals(ResourceType.COIN)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    /**
     * Override method getTotalShields returns the quantity of shields in the deposit
     */
    @Override
    public int getTotalShields() {
        if (this.resourcetype.equals(ResourceType.SHIELD)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    /**
     * Override method getTotalServants returns the quantity of servants in the deposit
     */
    @Override
    public int getTotalServants() {
        if (this.resourcetype.equals(ResourceType.SERVANT)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    /**
     * Override method getTotalStones returns the quantity of stones in the deposit
     */
    @Override
    public int getTotalStones() {
        if (this.resourcetype.equals(ResourceType.STONE)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    /**
     * Constructor Deposit creates a new Deposit instance
     * @param maxLimit (type Int) - it indicates the max resources' quantity that can be stored in the deposit
     */
    public Deposit(int maxLimit, boolean special) {
        this.resourcetype= null;
        this.quantity = 0;
        this.maxLimit = maxLimit;
        this.special = special;
    }

    /**
     * Override method that removes one resource from the deposit
     * @param resourceType is the type of resource to remove from the deposit.
     * @throws DepositDoesntHaveThisResource exception thrown when the deposit contains an another type of resource instead of resourceType.
     * @throws EmptyDeposit exception thrown when the deposit is empty and there's nothing to remove
     */
    @Override
    public void remove(ResourceType resourceType) throws DepositDoesntHaveThisResource, EmptyDeposit {
        if(quantity == 0) throw new EmptyDeposit();
        else if(resourcetype != resourceType) throw new DepositDoesntHaveThisResource();

        else {
            quantity -= 1;
            if(quantity == 0){
                if(!isSpecial()) this.resourcetype = null;
            }
        }
    }
}
