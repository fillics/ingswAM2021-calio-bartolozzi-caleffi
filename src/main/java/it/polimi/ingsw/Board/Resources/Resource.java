package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Board.Storage.Deposit;

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
     * get-method created to obtain the resource type
     */
    public ResourceType getType() {
        return type;
    }

    /**
     * method that links to the strategy method in order to modify the number of resources in a single deposit
     */
    public boolean useResource(ResourceActionStrategy strategy) {
        strategy.action();
        return true;
    }
}
