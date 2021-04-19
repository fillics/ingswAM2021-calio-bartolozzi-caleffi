package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.Card;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;

import java.util.HashMap;

/**
 * This class represents the Leader Card.
 */
public class LeaderCard extends Card {
    private LeaderCardType type;
    private LeaderCardStrategy strategy;
    private ResourceType resourceType;
    private Requirement requirements;
    private int victorypoint;
    private boolean useDiscountChoice = false;

    //TODO: java doc
    public LeaderCard(LeaderCardType type, Requirement requirements, ResourceType resourceType, int victorypoint) {
        this.type = type;
        this.requirements = requirements;
        this.resourceType = resourceType;
        this.victorypoint= victorypoint;
    }

    public void setStrategy(LeaderCardStrategy strategy) {
        this.strategy = strategy;
    }

    public LeaderCardStrategy getStrategy() {
        return strategy;
    }

    public void useDiscount(DevelopmentCard developmentCard, HashMap<ResourceType,Integer> resourcePriceBuffer){
        int oldvalue;
        ResourceType temp;
        if(useDiscountChoice){
                temp = (strategy.getResourceType());
                if(developmentCard.getResourcePrice().containsKey(temp)){
                    oldvalue = developmentCard.getResourcePrice().get(temp);
                    resourcePriceBuffer.replace(temp,oldvalue,oldvalue-1);
                }
        }
    }

    public boolean getUseDiscountChoice() {
        return useDiscountChoice;
    }

    public void setUseDiscountChoice(boolean useDiscountChoice) {
        this.useDiscountChoice = useDiscountChoice;
    }

    public void useAbility() {
        strategy.ability();
    }

    @Override
    public int getVictoryPoint() {
        return victorypoint;
    }

    @Override
    public String toString() {
        return "LeaderCard{" +
                "type=" + type +
                ", victorypoint=" + victorypoint +
                '}';
    }
}
