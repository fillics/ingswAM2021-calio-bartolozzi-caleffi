package it.polimi.ingsw.Cards.LeaderCards;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;

/**
 * This class represents the discount strategy for Leader Cards.
 */

public class ConcreteStrategyDiscount extends  LeaderCardStrategy{
    private final ResourceType resourceType;

    /**
     * Constructor ConcreteStrategyDiscount creates a new ConcreteStrategyDiscount instance.
     */
    public ConcreteStrategyDiscount(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public boolean ability(DevelopmentCard developmentCard) {
        int oldvalue;
        if(developmentCard.getResourcePrice().containsKey(resourceType)){
            oldvalue= developmentCard.getResourcePrice().get(resourceType);
            developmentCard.getResourcePrice().replace(resourceType, oldvalue,oldvalue-1);
        }
        System.out.println("I'm a discount leader card");
        return true;
    }
}
