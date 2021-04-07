package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.Exceptions.EmptyDeposit;
import it.polimi.ingsw.Player;

public class Deposit extends Warehouse {
   private ResourceType resourcetype;
   private int quantity;
   private int maxLimit;

    /**
     * Class's Constructor made to define the deposit
     */
    public Deposit(int maxLimit) {
        quantity = 0;
        this.maxLimit = maxLimit;
    }

    /**
     * get-method created to obtain the deposit's characteristics
     * @return the deposit's characteristics
     */
    public ResourceType getResourcetype() { return resourcetype; }

    public int getQuantity() { return quantity; }

    public int getMaxLimit() { return maxLimit; }

    /**
     * Method created in order to modify the resource type that's contained in a single deposit
     * @param resourcetype is the new type of resource in the deposit
     */
    public void setResourcetype(ResourceType resourcetype) {
        this.resourcetype = resourcetype;
    }

    /**
     * Method created in order to increase the number of resources. Exceptions still missing!
     * @return the new amount of resources
     */
    public int increaseNumberOfResources() {
        quantity +=  1;
        return quantity;
    }

    /**
     * Method made to decrease the number of resources because taken by the player
     * @return the resource requested
     */
    public Resource takeResource() throws EmptyDeposit {
        if(quantity == 0){
            throw new EmptyDeposit();
        }
        else {
            quantity -= 1;
            return new Resource(resourcetype);
        }
    }
    /**
     * Override methods created to return the number of resources for each resource for every singular deposit
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

    @Override
    public int getTotalShields() {
        if (this.resourcetype.equals(ResourceType.SHIELD)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    @Override
    public int getTotalServants() {
        if (this.resourcetype.equals(ResourceType.SERVANT)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    @Override
    public int getTotalStones() {
        if (this.resourcetype.equals(ResourceType.STONE)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

}
