package it.polimi.ingsw.Cards.LeaderCards;
import it.polimi.ingsw.Board.Resources.ResourceType;

/**
 * This class represents the discount strategy for Leader Cards.
 */

public class ConcreteStrategyDiscount implements LeaderCardStrategy{
    private final ResourceType resourceType;
    private boolean active;

    /**
     * Constructor ConcreteStrategyDiscount creates a new ConcreteStrategyDiscount instance.
     */
    public ConcreteStrategyDiscount(ResourceType resourceType) {
        this.resourceType = resourceType;
        active=false;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public boolean isActive() {
        return active;
    }


    @Override
    public void ability() {
        if(!active){
            active = true;
            System.out.println("Ability discount activated!");
        }
        else{
            System.out.println("Ability discount was already activated.");
        }
    }
}
