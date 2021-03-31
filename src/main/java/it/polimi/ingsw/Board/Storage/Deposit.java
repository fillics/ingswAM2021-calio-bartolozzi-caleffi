package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;

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
    public boolean setResourcetype(ResourceType resourcetype) {
        this.resourcetype = resourcetype;
        return true;
    }

    /**
     * Method created in order to increase the number of resources. Exceptions still missing!
     * @param amount is the number of resources to add in the deposit
     * @return the new amount of resources
     */
    public int increaseNumberOfResources(int amount){
        quantity +=  amount;
        return quantity;
    }

    /**
     * Override methods created to return the number of resources for each resource for every singular deposit
     */
    @Override
    int getTotalCoins() {
        if (this.resourcetype.equals(ResourceType.COIN)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    @Override
    int getTotalShields() {
        if (this.resourcetype.equals(ResourceType.SHIELD)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    @Override
    int getTotalServants() {
        if (this.resourcetype.equals(ResourceType.SERVANT)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    @Override
    int getTotalStones() {
        if (this.resourcetype.equals(ResourceType.STONE)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

}
