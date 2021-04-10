package it.polimi.ingsw.Cards.LeaderCards;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Player;

/**
 * This class represents the discount strategy for Leader Cards.
 */

public class ConcreteStrategyDiscount implements LeaderCardStrategy{
    private final ResourceType resourceType;
    private boolean active = false;
    /**
     * Constructor ConcreteStrategyDiscount creates a new ConcreteStrategyDiscount instance.
     */
    public ConcreteStrategyDiscount(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public void ability() {
        if(active = false){
            active = true;
        }
        else{
            System.out.println("ciaoneeeee");
        }
    }
}
