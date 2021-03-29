package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Board.Storage.Deposit;

public class Resource {
    private ResourceType type;
    private ResourceActionStrategy strategy;

    /**
     * Class's constructor to create all the resources needed
     *
     */
    public Resource(ResourceType type, ResourceActionStrategy strategy) {
        this.type = type;
        this.strategy = strategy;
    }

    /**
     * get-method created to obtain the resource type
     *
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * method that links to the strategy method in order to modify the number of resources in a single deposit
     *
     */
    public boolean useResource() {
        strategy.action();
        return true;
    }
}
