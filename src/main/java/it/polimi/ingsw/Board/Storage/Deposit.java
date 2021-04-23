package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.Exceptions.EmptyDeposit;

/**
 * Class Deposit represents the single deposit present in the Board
 */
public class Deposit extends Warehouse {
   private ResourceType resourcetype;
   private int quantity;
   private int maxLimit;

    /**
     * Constructor Deposit creates a new Deposit instance
     * @param maxLimit (type Int) - it indicates the max resources' quantity that can be stored in the deposit
     */
    public Deposit(int maxLimit) {
        quantity = 0;
        this.maxLimit = maxLimit;
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
     * Method setResourcetype modifies the resource type that's contained in a single deposit
     * @param resourcetype (type ResourceType) - it is the new type of resource in the deposit
     */
    public void setResourcetype(ResourceType resourcetype) {
        this.resourcetype = resourcetype;
    }

    /**
     * Method increaseNumberOfResources increases the number of resources. Exceptions still missing!
     * @return the new amount of resources
     */
    // TODO: 23/04/2021 modificare il pezzo di Exceptions still missing
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
        if(quantity == 0){
            throw new EmptyDeposit();
        }
        else {
            quantity -= 1;
            if(quantity == 0){
                resourcetype = null;
            }
            return new Resource(resourcetype);
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
     *
     * @param resourceType
     * @throws DepositDoesntHaveThisResource
     * @throws EmptyDeposit
     */
    // TODO: 23/04/2021 javadoc
    @Override
    public void remove(ResourceType resourceType) throws DepositDoesntHaveThisResource, EmptyDeposit {
        if(resourcetype != resourceType){
            throw new DepositDoesntHaveThisResource();
        }
        else if(quantity == 0){
            throw new EmptyDeposit();
        }
        else {
            quantity -= 1;
        }

    }

}
