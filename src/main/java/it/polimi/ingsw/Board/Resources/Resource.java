package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.Exceptions.DepositHasReachedMaxLimit;

public class Resource {
    private ResourceType type;
    private ResourceActionStrategy strategy;

    /**
     * Class's Constructor made to define the resource type
     */
    public Resource(ResourceType type) {
        this.type = type;
    }




    /**
     * Set-method used to set the strategy of a resource when it's needed
     * @param strategy is the strategy to set
     */
    public void setStrategy(ResourceActionStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * get-method created to obtain the resource attributes
     */
    public ResourceType getType() {
        return type;
    }
    public ResourceActionStrategy getStrategy() { return strategy; }

    /**
     * method that links to the strategy method in order to modify the number of resources in a single deposit
     */
    public void useResource() {
        try {
            strategy.action();
        } catch (DepositHasAnotherResource | DepositHasReachedMaxLimit depositHasAnotherResource) {
            depositHasAnotherResource.printStackTrace();
        }
    }
}
