package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.Card;

/**
 * This class represents the Leader Card.
 */
public class LeaderCard extends Card {
    private LeaderCardType type;
    private LeaderCardStrategy strategy;
    private ResourceType resourceType;
    private Requirement requirements;
    private int victorypoint;
    private String useAbilityChoice;

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

    public String getUseAbilityChoice() {
        return useAbilityChoice;
    }

    public void setUseAbilityChoice(String useAbilityChoice) {
        this.useAbilityChoice = useAbilityChoice;
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
