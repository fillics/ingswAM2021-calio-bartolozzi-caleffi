package it.polimi.ingsw.model.board.resources;

import it.polimi.ingsw.exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.exceptions.DepositHasReachedMaxLimit;

/**
 * Class Resource represents the resources used during the game
 */
public class Resource {
    private final ResourceType type;
    private ResourceActionStrategy strategy;

    /**
     * Constructor Resource creates a new Resource instance.
     * @param type (type ResourceType) - it indicates the type of the resource
     */
    public Resource(ResourceType type) {
        this.type = type;
    }

    /**
     * Method setStrategy sets the strategy of a resource when it's needed
     * @param strategy (type ResourceActionStrategy) - it is the strategy to set
     */
    public void setStrategy(ResourceActionStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Method getType returns the type of the resource
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * Method getStrategy returns the strategy of the resource
     */
    public ResourceActionStrategy getStrategy() {
        return strategy; }

    /**
     * Method useResource links to the strategy method in order to modify the number of resources in a single deposit
     * @throws DepositHasReachedMaxLimit if the deposit is full
     * @throws DepositHasAnotherResource if the deposit already contains another resources' type
     */
    public void useResource() throws DepositHasReachedMaxLimit, DepositHasAnotherResource {
        strategy.action();
    }
}
