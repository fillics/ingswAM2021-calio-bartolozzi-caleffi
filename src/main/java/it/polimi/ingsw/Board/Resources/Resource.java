package it.polimi.ingsw.Board.Resources;

public class Resource {
    private ResourceType type;
    private ResourceActionStrategy strategy;

    public Resource(ResourceType type, ResourceActionStrategy strategy) {
        this.type = type;
        this.strategy = strategy;
    }

    public boolean useResource() {
        strategy.action();
        return true;
    }
}
