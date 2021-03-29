package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;

public class Deposit extends Warehouse {
   private Resource resourcetype;
   private int quantity;
   private int maxLimit;

    /**
     * Class's Constructor made to define the deposit
     */
    public Deposit(Resource resourcetype, int quantity, int maxLimit) {
        this.resourcetype = resourcetype;
        this.quantity = quantity;
        this.maxLimit = maxLimit;
    }

    /**
     * Override methods created to return the number of resources for each resource for every singular deposit
     *
     */
    @Override
    int getTotalCoins() {
        if (this.resourcetype.getType().equals(ResourceType.COIN)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    @Override
    int getTotalShields() {
        if (this.resourcetype.getType().equals(ResourceType.SHIELD)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    @Override
    int getTotalServants() {
        if (this.resourcetype.getType().equals(ResourceType.SERVANT)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }

    @Override
    int getTotalStones() {
        if (this.resourcetype.getType().equals(ResourceType.STONE)){
            return this.quantity;
        }
        else {
            return 0;
        }
    }


}
